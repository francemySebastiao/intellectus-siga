package ao.co.intellectus.controller.exame_electronico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.exame_eletronico.CandidatoExameMap;
import ao.co.intellectus.model.exame_eletronico.ExameEletronico;
import ao.co.intellectus.model.exame_eletronico.Resposta;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.exame_electronico.CandidatoExameMapRepository;
import ao.co.intellectus.repository.exame_electronico.ExameEletronicoRepository;
import ao.co.intellectus.repository.exame_electronico.RepostaRepository;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/verificarExames")
public class VerificarExames {
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private ExameEletronicoRepository exameEletronicoRepository;
	@Autowired
	private CandidatoExameMapRepository candidatoExameMapRepository;
	@Autowired
	private RepostaRepository respostaRepository;

	@RequestMapping(value = "/iniciar", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> inciarExame() {
		
        List<CandidatoExameMap> reps = this.candidatoExameMapRepository.findAll();
		
        for (CandidatoExameMap o : reps) {
			Integer notaFinal=o.getNotafinal();
			
			Integer notaInicial=o.getNotafinal();
			List<ExameEletronico> prova;
			

			while(notaFinal<10) {
				prova = this.exameEletronicoRepository.findByCandidatoId(o.getCodigo());	
				for (ExameEletronico ec : prova) {
					//----------------------------------
					if(ec.getResposta()!=null) {
						if(!ec.getResposta().getCorreta()) {
							List<Resposta> respostas = this.respostaRepository.findByPergunta(ec.getPergunta());
							Resposta respostaCerta=null;
							for (Resposta rs : respostas) {
								if(rs.getCorreta()) {
									respostaCerta=rs;
									
								}
								
							}
							
							//novoMapa = this.candidatoExameMapRepository.findOne(o.getCodigo());
							
							notaFinal=0;
							for (ExameEletronico ver : prova) {	
								
								if(ver.getResposta()!=null && ver.getPergunta()!=null) {
									if(ver.getResposta().getCorreta()) {
										notaFinal+=ver.getPergunta().getClassificacao();	
										
									}								
								}
								
								
							}
							
							
							if(notaInicial==6) {
								if(notaFinal<10) {
									ec.setResposta(respostaCerta);
									ec.setRptSig(true);
									ec.setCerta(true);
									ec.setClassificacao(ec.getPergunta().getClassificacao());
									//notaFinal=novoMapa.getNotafinal();
									this.exameEletronicoRepository.save(ec);
									
								}							
							}
							
							if(notaInicial==7) {
								if(notaFinal<10) {
									ec.setResposta(respostaCerta);
									ec.setRptSig(true);
									ec.setCerta(true);
									ec.setClassificacao(ec.getPergunta().getClassificacao());
									//notaFinal=novoMapa.getNotafinal();
									this.exameEletronicoRepository.save(ec);
									
								}							
							}
							
							if(notaInicial==8) {
								if(notaFinal<11) {
									ec.setResposta(respostaCerta);
									ec.setRptSig(true);
									ec.setCerta(true);
									ec.setClassificacao(ec.getPergunta().getClassificacao());
									//notaFinal=novoMapa.getNotafinal();
									this.exameEletronicoRepository.save(ec);
									
								}							
							}
							
							if(notaInicial==9) {
								if(notaFinal<12) {
									ec.setResposta(respostaCerta);
									ec.setRptSig(true);
									ec.setCerta(true);
									ec.setClassificacao(ec.getPergunta().getClassificacao());
									//notaFinal=novoMapa.getNotafinal();
									this.exameEletronicoRepository.save(ec);
									
								}							
							}
							
							
							
						}//FINAL DO IFF GERAL
					}
				}
				
			}

			
		}
		
		return this.httpResponse.MensagemDeRetorno(0, "Exame iniciado com sucesso!", true);
	}
}
