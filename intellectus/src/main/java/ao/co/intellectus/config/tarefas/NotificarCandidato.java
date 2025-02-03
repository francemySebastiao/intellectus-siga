/*package ao.co.intellectus.config.tarefas;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ao.co.intellectus.DTO.proxpay.CustomFields;
import ao.co.intellectus.DTO.proxpay.ReferenciafForm;
import ao.co.intellectus.DTO.proxpay.ResumoReferenciaDTO;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.repository.CandidatoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.servico.cafold.CandidatoServie;

@Singleton
@Component
@EnableScheduling
public class NotificarCandidato {
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	
	@Autowired
	private CandidatoServie candidatoServie;
	@Autowired
	private CandidatoRepository candidatoRepository;
	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepository;
	
	
	@Scheduled(fixedDelay=MINUTO)
	public void validaPagamentoGuia()  {
		RestTemplate client=new RestTemplate();
		//01. NOTIFICAR COM DOCUMENTOS
		List<Candidato> candidato = this.candidatoRepository.buscarCandidatosNaoNotificadosComPagamento();
		for (Candidato o : candidato) {
			System.err.println("CANDIDATO NOTIFICADO COM SUCESSO!");
			//System.out.println("Será? " + o.getEmail());
			//System.out.println("Será? " + o.getNome());
			System.out.println("Será? " + o.getCurso());
			this.candidatoServie.enviarfichaDeInscricao(o);
			o.setEmailEnviado(true);
			this.candidatoRepository.save(o);
		}

		//02. GERAR REFERENCIAS
		List<GuiaCandidatura> guias = this.guiaCandidaturaRepository.buscarGuiasCandidaturaSemReferencia();
		for (GuiaCandidatura o : guias) {
			
			ReferenciafForm form=new ReferenciafForm();
			form.setAmount(new BigDecimal(o.getValor()));
			form.setExpiry_date(o.getDataVencimento().toString());
			
			Candidato c = o.getCandidato();
			Instituicao instituicao = c.getInstituicao();
			CustomFields cFields=new CustomFields();
			cFields.setDescription("Inscrição e frequência, por Módulo");
			cFields.setEmail(c.getEmail());
			cFields.setGuia(o.getNumeroGuia());
			cFields.setMobile(c.getTelefone());
			cFields.setName(c.getNome());
			cFields.setNumeroDeAluno(c.getNumeroCandidato() !=null ? c.getNumeroCandidato().toString(): null);
			
			cFields.setUnidade(instituicao!=null ? instituicao.getSigla():null);
			cFields.setUnidade("0010");
			cFields.setInst_description(instituicao!=null ? instituicao.getDescricao():null);

			form.setCustom_fields(cFields);
			
			ResponseEntity<ResumoReferenciaDTO> response = client.postForEntity("http://127.0.0.1:8830/api-proxypay/referencia/criar", form, ResumoReferenciaDTO.class);
			ResumoReferenciaDTO bodyResponse             = response.getBody();
			
			//APLICAR REFERENCIA NA LIQUIDAÇÃO
			o.setReferencia(bodyResponse.getReferencia());
			this.guiaCandidaturaRepository.save(o);
			
		}
	}
}
*/