package ao.co.intellectus.servico.cafold;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.AnexoCliente;
import ao.co.intellectus.DTO.EmailCliente;
import ao.co.intellectus.DTO.PublicoAlvo;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.ContaCorrenteCandidato;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.RelatorioFinanceiro;
import ao.co.intellectus.model.request.CandidatoRequest;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CandidatoRepository;
import ao.co.intellectus.repository.ContaCorrenteCandidatoRepository;
import ao.co.intellectus.servico.exception.DadosDuplicadoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class CandidatoServie {
	
	@Autowired
	private CandidatoRepository candidadoRepository;
	@Autowired
	private ConexaoService conexaoService; 
	@Autowired
	private InstituicaoService instituicaoService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private ContaCorrenteCandidatoRepository contaCandidatoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;

	private EmailCliente emailCliente;
	@Autowired
	private AnoLectivoService anoLectivoService;

	public Candidato candidato(String numeroDocumento) {
		List<AnoLectivo> anoLectivos =  this.anoLectivoRepository.findByStatus(true);
		List<Candidato> candidatos = this.candidadoRepository.findBynumeroDocumentoAndAnoLectivoId(numeroDocumento, anoLectivos.get(0).getId());
		if(candidatos.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de Candidato não encontrado.");
		Candidato candidato=null;
		for (Candidato c : candidatos) {
			candidato=c;
		}
		return candidato;
	}
	public Candidato candidato(Integer numero, AnoLectivo anoLectivo) {
		Candidato candidato = this.candidadoRepository.findByNumeroCandidatoAndAnoLectivo(numero,anoLectivo);
		if(candidato == null)
			throw new RegistoNaoEncontradoException("Nenhum registo de candidato encontrado.");
		return candidato;
	}
	
	public List<Candidato> candidato(String numeroDoDocumento, Integer anoLectivo) {
		List<Candidato> candidatos = this.candidadoRepository.findBynumeroDocumentoAndAnoLectivoId(numeroDoDocumento,anoLectivo);
		if(candidatos.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum registo de candidato encontrado.");
		return candidatos;
	}
	
	public List<Candidato> candidatos(PublicoAlvo publico){
		AnoLectivo anoLectivo = this.anoLectivoService.anoLectivo(publico.getAnoLectivo());
		List<Candidato> candidatos = this.candidadoRepository.findByAnoLectivoAndGrauAndTipoCandidatura(anoLectivo,publico.getGrauAcademico(),publico.getTipoCandidatura());
		if(candidatos.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum registo de candidato encontrado.");
		return candidatos;
	}
	
	public List<Candidato> candidatosPorData(PublicoAlvo publico){
		AnoLectivo anoLectivo = this.anoLectivoService.anoLectivo(publico.getAnoLectivo());
		List<Candidato> candidatos = this.candidadoRepository.
				findByAnoLectivoAndGrauAndTipoCandidaturaAndDataCandidaturaBetween(anoLectivo,publico.getGrauAcademico(),publico.getTipoCandidatura(), publico.getDataInicial(),publico.getDataFim());
		if(candidatos.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum registo de candidato encontrado.");
		return candidatos;
	}
	
	public Candidato salvar(CandidatoRequest candidatoRequest, Candidato candidato) {
		if(this.alunoService.validar(candidatoRequest)) 
			return this.candidadoRepository.save(candidato);
		throw new DadosDuplicadoException("O aluno já existente.");
	}
	
	public void salvarContaCorrenteCandidato(AnoLectivo anoLectivo,Instituicao instituicao, Candidato candidato) {
		ContaCorrenteCandidato cCandidato=new ContaCorrenteCandidato();
		//PREENCHER OS DADOS DA CONTA DE CANDIDATO.
		cCandidato.setAnoLectivo(anoLectivo);
		cCandidato.setCandidato(candidato);
		cCandidato.setDataMovimento(new Date());
		cCandidato.setInstituicao(instituicao);
		cCandidato.setNumeroDeCandidato(candidato.getNumeroCandidato().toString());
		cCandidato.setValor(0.0);
		cCandidato.setValorAnterior(0.0);
		//SALVAR A CONTA DO CANDIDATO.
		this.contaCandidatoRepository.save(cCandidato);
	}
	
	public CandidatoRequest validarGrau(Curso curso,Candidato candidato, CandidatoRequest candidatoRequest) {
		if(curso.getGrau()==Grau.CESE) {
			candidato.setNotaExame(20.0);
			candidatoRequest.setCursoAcesso(false);
			candidatoRequest.setTipoDeCandidatura(false);
		}else if(curso.getGrau()==Grau.POSGRADUACAO) {
			candidato.setNotaExame(20.0);
			candidatoRequest.setCursoAcesso(false);
			candidatoRequest.setTipoDeCandidatura(false);
		}else if(curso.getGrau()==Grau.MESTRADO) {
			candidatoRequest.setCursoAcesso(false);
			candidatoRequest.setTipoDeCandidatura(false);
		}
		return candidatoRequest;
	}
	
	public Candidato candidato(CandidatoRequest candidatoRequest,AnoLectivo anoLectivo) {
		return candidadoRepository.findByNumeroDocumentoAndNomeAndAnoLectivo(candidatoRequest.getNumeroDocumento(), candidatoRequest.getNome(), anoLectivo);
	}
	
	public List<Candidato> todosCandidatosPorMensagemEnviada(boolean mensagemenviada){
		return this.candidadoRepository.findByMensagemEnviada(mensagemenviada);	
	}
	
	public void enviarfichaDeInscricao(Candidato candidato) {
		try {
			Instituicao instituicao = this.instituicaoService.instituicao(2);
			this.emailCliente = new EmailCliente();
			
			String[] destinario = {candidato.getEmail()};
			
			String[] bCC = {"acesso2021@ugs.ed.ao","otilioasdrid@gmail.com","ernesto.sambongo@outlook.com","ernesto.sambongo@okayulatech.ao", "djanilson.lazaro@okayulatech.ao"};
			
			this.emailCliente.setAssunto("PRÉ-CANDIDATURA ONLINE - " + instituicao.getDescricao().toUpperCase());
			
			this.emailCliente.setMensagem("Prezado(a) "+ candidato.getNome() + ", a sua candidatura para o curso de "+candidato.getCurso().getDescricao()+" foi efectuada com sucesso."
					+ "\nQueira encontrar em anexo a sua ficha de candidatura, bem como os tópicos e as instruções para o exame. A ficha de candidatura deve ser impressa e apresentada no dia do exame de acesso sem rasura, nem corte."
					+"\nEncontre abaixo as suas credenciais para o exame de acesso electrónico:"
					+"\nUsuário: Número de telefone inserido no acto da candidatura"
					+"\nSenha: Será atribuída na hora do exame"
					+"\n\n"+instituicao.getDescricao()
					+"\nRigor, qualidade e modernidade"
					+"\n\nPara mais informações -  E-mail: acesso2021@ugs.ed.ao; Whatsapp: +244 938 083 939; Telefone: +244 939 581 574.");
			
			this.emailCliente.setDestinatario(destinario);
			this.emailCliente.setbCC(bCC);
			String tituloDoFicheiro = "FICHA DE PRE-CANDIDATURA";
			byte[] fichaDeInscricao = this.fichaDeInscricao(candidato.getId());
			String extensao = ".pdf";
			this.emailCliente.setAnexo(new AnexoCliente(tituloDoFicheiro, fichaDeInscricao,extensao));
			this.emailService.enviar(candidato,this.emailCliente);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	
	
	public byte[] enviartotalCandidatura(RelatorioFinanceiro pendente) {
		byte[] totalCandidatura = null;
		try {
			Instituicao instituicao = this.instituicaoService.instituicao(2);
			this.emailCliente = new EmailCliente();
			emailCliente.setAssunto(pendente.getTipoRelatorio().getDescricao());
			emailCliente.setMensagem("Saudações prezado(a), em anexo está o relatório de controlo de candidatura diária da(o) "+instituicao.getDescricao());
			String[] destinatario = {pendente.getPrimeiroResponsavel()};
			String[] cC = {pendente.getSegundoResponsavel()};
			String[] bCC = {"francisco.lourenco@intellectus.co.ao","ernesto.sambongo@intellectus.co.ao", "marcio.coelho@intellectus.co.ao"};
			this.emailCliente.setDestinatario(destinatario);
			this.emailCliente.setCc(cC);
			this.emailCliente.setbCC(bCC);
			totalCandidatura = this.totalCandidatura(pendente.getDataEnviar());
			this.emailCliente.setAnexo(new AnexoCliente("RELATORIO-CANDIDATURA", totalCandidatura, ".pdf"));
			if(totalCandidatura!=null)
				this.emailService.enviar(this.emailCliente);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCandidatura;
	}
	
	
	
	private byte[] fichaDeInscricao(Integer codigoCandidado) throws JRException, ClassNotFoundException, SQLException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("id", codigoCandidado);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_PreInscricao_Online.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, this.conexaoService.getConexaoLocal());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	private byte[] totalCandidatura(Date data) throws JRException, ClassNotFoundException, SQLException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data_candidatura", data);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Dashboard.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, this.conexaoService.getConexaoLocal());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
 
}
