package ao.co.intellectus.servico.cafold;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.exceptions.UnirestException;

import ao.co.intellectus.DTO.proxpay.Unidade;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.GuiaPagamentoHistoricoAudit;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.GuiaPagamentoHistoricoAuditRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class GuiaService {
	
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private GuiaPagamentoHistoricoAuditRepository guiaPagamentoHistoricoAuditRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaRepository;
	@Autowired
	private ConexaoService conexaoService; 
	@Autowired
	private CompensassaoService compensssaoService;
	@Autowired
	private UnidadeService unidadeService;
	@Autowired 
	private ReferenciaService referenciaService;
	@Autowired
	private AlunoService alunoService;
	
	public Guia guia(Integer numero) {
		Guia guia = this.guiaRepository.findById(numero);
		if(guia != null)
			return guia;
		throw new RegistoNaoEncontradoException("Registo de guia não encontrado.");
	}
	
	public Date dataVecimento(Guia guia) {
		return guia.getDataVencimento();
	}
	
	public void gerarHistoricoAudit(GuiaPagamentoHistorico guiaPagamentoHistorico) {
		GuiaPagamentoHistoricoAudit guiaPagamentoHistoricoAudit = new GuiaPagamentoHistoricoAudit();
		BeanUtils.copyProperties(guiaPagamentoHistorico, guiaPagamentoHistoricoAudit);
		this.guiaPagamentoHistoricoAuditRepository.save(guiaPagamentoHistoricoAudit);
	}
	
	public ResponseEntity<ResponseCliente> gerarReferencia(Guia guia,String telefone) throws UnirestException {
		if(this.estaVencida(guia)) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,"Mensalidade vencida deve ser tratada nas finanças.");
		}else {
			if(guia.isLiquidada()) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Está guia já foi liquidada.");
			}else {
				boolean compensasoesApagadas = this.compensssaoService.apagarCompessassoes(guia);
				if(compensasoesApagadas) {
					List<GuiaPagamentoHistorico> guiaHistorico = this.historicoGuiaRepository.findByGuia(guia);
					Unidade unidade = this.unidadeService.filds(guiaHistorico, guia, telefone);
					this.referenciaService.enviarReferencia(unidade);
					this.compensssaoService.salvar(guiaHistorico, guia, telefone);
					this.alunoService.actualizarNumero(guia, telefone);
					return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Refêrencia enviada com sucesso.");	
				}else {
					return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,"Mensalidade vencida deve ser tratada nas finanças.");	
				}
			}
		}			
	}
	
	private boolean estaVencida(Guia guia) {
		boolean vencida = false;
		Date dataVencimento = this.dataVecimento(guia), hojeAtual = new Date();
		List<GuiaPagamentoHistorico> historico = this.historicoGuiaRepository.findByGuia(guia);
		for (GuiaPagamentoHistorico o : historico) {
			vencida = (o.getEmolumento().getId() >= 52 && o.getEmolumento().getId() <= 67) && hojeAtual.getTime() >= dataVencimento.getTime();
			if (vencida) {
				return vencida;
			}
		}
		return vencida;	
	}
	
	public byte[] guiaDePagamento(String codigoGuia, String userName) throws JRException, ClassNotFoundException, SQLException {
		Guia guia = this.guiaRepository.findByNumeroGuia(codigoGuia);
		List<Matricula> inscricoes = this.matriculaRepository.findByAluno(guia.getAluno());
		int ano=0;
		String turma="";
		int maximaMatricula=0;
		for (Matricula m : inscricoes) {
			if(maximaMatricula<m.getId()) {
				maximaMatricula=m.getId();
				turma=m.getTurmaBase().getTurma();
				ano=m.getAnoCurricular();
			}
		}
		return ficheiro(codigoGuia, userName, "2", turma, ano);
	}
	

	private byte[] ficheiro(String codigoGuia, String userName, String condicao,String turma,int ano) throws JRException, ClassNotFoundException, SQLException {
		Map<String, Object> paramets = new HashMap<>();
		
		paramets.put("numero_guia", codigoGuia);
		paramets.put("condicao", condicao);
		paramets.put("turma", turma);
		paramets.put("ano", ano);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guia_PagamentoAPP.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conexaoService.getConexaoAzure());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	

}
