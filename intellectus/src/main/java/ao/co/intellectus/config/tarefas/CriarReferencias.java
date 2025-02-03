/*package ao.co.intellectus.config.tarefas;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import ao.co.intellectus.DTO.proxpay.Filds;
import ao.co.intellectus.DTO.proxpay.Referencias;
import ao.co.intellectus.DTO.proxpay.Unidade;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.CompensassaoProvisoria;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.ReferenciaProcessamento;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.CandidatoRepository;
import ao.co.intellectus.repository.CompensassaoProvisoriaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.proxypay.ReferenciaProcessamentoRepository;
import ao.co.intellectus.servico.cafold.CandidatoServie;
import ao.co.intellectus.servico.cafold.VerifiqueInternet;


@RestController
@RequestMapping("/referencias")
public class CriarReferencias{
	@Autowired
	private ReferenciaProcessamentoRepository referenciaProcessamentoRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private CompensassaoProvisoriaRepository compensassaoRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private CandidatoServie candidatoServie;
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@RequestMapping(value = "/notificar-candidato", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> notificarCandidato()  {
		ResponseCliente c = new ResponseCliente();
		
		List<Candidato> candidato = this.candidatoRepository.buscarCandidatosNaoNotificadosComPagamento();
		for (Candidato o : candidato) {
			System.err.println("CANDIDATO NOTIFICADO COM SUCESSO!");
			this.candidatoServie.enviarfichaDeInscricao(o);
			o.setEmailEnviado(true);
			this.candidatoRepository.save(o);
		}
		
		c.setMensagem("CANDIDATOS NOTIFICADOS COM SUCESSO!");
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/processar", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> processarReferenciasCNT() throws UnirestException {
		ResponseCliente c = new ResponseCliente();

		boolean connect = VerifiqueInternet.connect("https://www.google.com/");
			List<ReferenciaProcessamento> referencia = this.referenciaProcessamentoRepository.findAll();

			int contador=0;
			if(connect) {
				Instituicao instituicao = this.instituicaoRepository.findOne(2);
				for (ReferenciaProcessamento o : referencia) {
					
					
					Unidade unidade = new Unidade();
					
					unidade.setExpiry_date(o.getDataVencimento());
					unidade.setAmount(o.getValor());
					
					
					
					// ------------------
					Filds custom_fields = new Filds();
					custom_fields.setDescription("Propina do mês de " + o.getMesProcessamento());
					custom_fields.setMobile(o.getTelefone());
					custom_fields.setName(o.getNome());
					custom_fields.setGuia(o.getNumeroGuia().toString());
					custom_fields.setNumeroDeAluno(o.getNumeroDeAluno().toString());
					custom_fields.setUnidade(instituicao.getCode());
					custom_fields.setInst_description("Intellectus - "+instituicao.getSigla());
					
					unidade.setCustom_fields(custom_fields);
					// INVONCANDO API PROXPAY PARA GERAR A REFERENCIA
					// ----------------------------------------------
					Gson gson = new Gson();
					String json = gson.toJson(unidade);
	       
					String payload = "{\"reference\":" + json + "}";
	
					HttpResponse<JsonNode> asJson = 
							Unirest
							.post("https://api.proxypay.co.ao/references")
							.basicAuth("api", "un5kodf4euv5s13b6i22qf0bkks1mosn")
							.header("accept", "application/vnd.proxypay.v1+json")
							.header("Content-Type", "application/json")
							.body(payload).asJson();
					
					String jsonDevolvido = asJson.getBody().getObject().toString();
					
					
					Referencias referencias = gson.fromJson(jsonDevolvido, Referencias.class);
					
					
					
					contador++;
					CompensassaoProvisoria compensassao = new CompensassaoProvisoria();
					Guia guia = this.guiaRepository.findByNumeroGuia(o.getNumeroGuia());
					compensassao.setNumeroGuia(guia.getNumeroGuia());
					compensassao.setDataCriacao(new Date());
					compensassao.setDataVencimento(guia.getDataVencimento());
					compensassao.setDescricao("Propina do mês de " + o.getMesProcessamento());
					compensassao.setNome(o.getNome());
					compensassao.setNumero(o.getNumeroDeAluno());
					compensassao.setTelefone(o.getTelefone());
					compensassao.setReferencia(referencias.getNumber());
					compensassao.setValor(o.getValor());
					// valorGuia
					this.compensassaoRepository.save(compensassao);
				}
			}
			
			c.setMensagem("Total de guais geradas: " +contador);
			c.setCodigo(200);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
}*/