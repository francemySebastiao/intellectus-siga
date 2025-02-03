package ao.co.intellectus.servico.Auditoria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.Auditoria.InscricaoExtraordinariaAuditoriaDto;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.InscricaoExtraordinaria;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.InscricaoExtraordinariaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@Service
public class InscricaoExtraordinariAuditService {
	
	@Autowired
	private InscricaoExtraordinariaRepository iRepository;
	@Autowired
	private AlunoRepository aRepository;
	@Autowired
	private HttpResponse httpResponse;

	public ResponseEntity<ResponseCliente> buscarInscricoesPorNumero(String numero){
		   ResponseCliente r = new ResponseCliente();
			
			Aluno aluno = aRepository.findByNumeroDeAluno(numero);

			if (aluno == null) {
				r.setMensagem("Nenhum Aluno encontrado.");
				r.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(r, HttpStatus.OK);
			}
		List<InscricaoExtraordinaria> inscricoes = this.iRepository.findByAluno(aluno);
		if(inscricoes.isEmpty()) {
			return this.httpResponse.MensagemDeRetorno(2, "Estudante não possui incrições extraordinárias ");
		}
		List<InscricaoExtraordinariaAuditoriaDto> lista= new ArrayList<InscricaoExtraordinariaAuditoriaDto>();
		
		for(InscricaoExtraordinaria c:inscricoes ) {
			InscricaoExtraordinariaAuditoriaDto inscricao= new InscricaoExtraordinariaAuditoriaDto();
			inscricao.setAnoLectivoDescricao(c.getAnoLectivo().getAnoLectivoDescricao());
			inscricao.setDataRegistro(c.getDataRegistro());
			inscricao.setTipoProva(c.getProva().getDescricao());
			inscricao.setTurma(c.getTurma().getTurma());
			inscricao.setUserEmitiu(c.getUsuarioInscreveu().getName());
			
			lista.add(inscricao);
		}
		
		return ObjectoDeRetornoParcial.MensagemDeRetorno(lista, 0, null);
	}

}
