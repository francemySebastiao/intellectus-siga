package ao.co.intellectus.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AnoLectivoCliente;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.HorarioCalendario;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.Semestre;
import ao.co.intellectus.model.TipoDia;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.HorarioCalendarioRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.TipoDiaRepository;

@RestController
@RequestMapping("/anoLectivo")
public class ControllerAnoLectivo {
	
	
	//anoLectivo/gerarCalendario
	
	@Autowired
	private AnoLectivoRepository repositoryAnoLectivo;
	@Autowired
	private InstituicaoRepository repositoryInstituicao;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private TipoDiaRepository tipoDiaRepository;
	@Autowired
	private HorarioCalendarioRepository horarioCalendarioRepository;
	
	
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() { 	
		ResponseCliente c=new ResponseCliente();
		
        Iterable<AnoLectivo> todos = this.repositoryAnoLectivo.findAll();
		
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todos);
		
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscarPorCodigo/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCodigo(@PathVariable Integer numero) { 	
		ResponseCliente c=new ResponseCliente();
		
        AnoLectivo anoLectivo = this.repositoryAnoLectivo.findByAnoLectivo(numero);
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(anoLectivo);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/listaOrdenada", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listaOrdenadaAnoLectivo() { 
		ResponseCliente c=new ResponseCliente();
		
		List<AnoLectivo> anosOrd  = this.repositoryAnoLectivo.findByOrderByAnoLectivoDesc();
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(anosOrd);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	} 
	
	@RequestMapping(value = "/listaOrdenad", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listaOrdenad() { 
		ResponseCliente c=new ResponseCliente();
		
		List<AnoLectivo> anosOrd  = this.repositoryAnoLectivo.findByOrderByAnoLectivoDesc();
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(anosOrd);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	} 
	
	@RequestMapping(value = "/ativo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")  
	public List<AnoLectivo> buscarAnoActivo() {
		return repositoryAnoLectivo.findByStatus(true);
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@Valid @RequestBody AnoLectivoCliente anoLectivoCliente,HttpServletResponse response) {
        
		ResponseCliente c=new ResponseCliente();
        
        Iterable<AnoLectivo> anosCadastrados = this.repositoryAnoLectivo.findAll();
        
        List<AnoLectivo> anoPassado = this.repositoryAnoLectivo.findByStatus(true);
        
        
        AnoLectivo seAberto = this.repositoryAnoLectivo.findByAnoLectivo(anoLectivoCliente.getAnoLectivo());
        if(seAberto!=null) {
        	c.setCodigo(ResponseCode.values()[1].getDescricao()); 
	    	c.setMensagem("Este ano lectivo já se encontra aberto.");
        }
        
        
        Calendar atual=Calendar.getInstance();
        int anoCorrente = atual.get(Calendar.YEAR);
        
        if(anoLectivoCliente.getAnoLectivo()==anoCorrente)
        {
	        for (AnoLectivo lectivo : anosCadastrados) {
	        	if(anoLectivoCliente.getAnoLectivo()!=lectivo.getAnoLectivo()){
		        	lectivo.setStatus(false);
		        	repositoryAnoLectivo.save(lectivo);	        		
	        	}else{
	        		c.setCodigo(ResponseCode.values()[3].getDescricao());
	        		c.setMensagem(messageSource.getMessage("ano.lectivo.aberto",null,Locale.getDefault()));
	    			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	        	}
			}  

	        AnoLectivo anoLectivo = new AnoLectivo();
	        BeanUtils.copyProperties(anoLectivoCliente, anoLectivo, "instit");
	        Instituicao instituicao = this.repositoryInstituicao.findOne(anoLectivoCliente.getInstit());
	        
	        anoLectivo.setStatus(true);
	        anoLectivo.setInstit(instituicao);
	        AnoLectivo novoAno = repositoryAnoLectivo.save(anoLectivo);
	        System.out.println(anoLectivo);
	        //começo de cópia dos emolumentos
	        
	        //.....
	        List<EmolumentoHistorico> hEmolumentos = this.emolumentoHistoricoRepository.findByAnoLectivo(anoPassado.get(0));
	        
	        EmolumentoHistorico emolumento;
	        for (EmolumentoHistorico he : hEmolumentos) {
	        	emolumento=new EmolumentoHistorico();
	        	
	        	emolumento.setCurso(he.getCurso());
	        	emolumento.setEmolumento(he.getEmolumento());
	        	emolumento.setMoeda(he.getMoeda());
	        	emolumento.setValor(he.getValor());
	        	emolumento.setAnoLectivo(novoAno);
	        	
	        	this.emolumentoHistoricoRepository.save(emolumento);
			}
	        //fecho da cópia dos emolumentos
	        
	        //precisamos zerar o gerador de números.
	        List<NumeroGerado> gerados = this.numeroGeradoRepository.findAll();
	        for (NumeroGerado n : gerados) {
				NumeroGerado numero = this.numeroGeradoRepository.findOne(n.getId());
				numero.setUltimoNumero(0L);
				numero.setProximoNumero(1L);
				
				this.numeroGeradoRepository.save(numero);
			}
	        
	        //precisamos abrir o calendário academico
	        
	        
	        c.setCodigo(ResponseCode.values()[0].getDescricao()); 
	    	c.setResultado(anoLectivo);
	    	c.setMensagem("Bem vindo ao Ano Lectivo de: "+anoLectivo.getAnoLectivo());
	    	System.out.println(anoLectivo);
	    	return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
        }else{
        	c.setCodigo(ResponseCode.values()[0].getDescricao());
        	c.setMensagem("O referido ano não pode ser aberto, Verificar o certo!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
        }
	}

	
	
	//TODO - TRANSFORMAR EM UM CONSUMER PARA SER CHAMADO NO MOMENTO DE ABERTURA DE ANO LECTIVO
	@RequestMapping(value = "/gerarCalendario/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> gerarCalendario(@PathVariable Integer ano) { 
		ResponseCliente c=new ResponseCliente();
		AnoLectivo anoLectivo = this.repositoryAnoLectivo.findByAnoLectivo(ano);
		Instituicao instituicao = this.repositoryInstituicao.findOne(2);
		
		System.err.println("ANO LECTIVO ENVIADO: "+ano);
		int i=0;
		if(anoLectivo!=null) {
			HorarioCalendario horarioCalendario;
			
			Date inicioPrimeiroSemestre = anoLectivo.getInicioPrimeiroSemestre();
			Date fimPrimeiroSemestre    = anoLectivo.getFimPrimeiroSemestre();
			
			
			Date inicioSegundoSemestre = anoLectivo.getInicioSegundoSemestre();
			Date fimSegundoSemestre    = anoLectivo.getFimSegundoSemestre();
			
			
			
			TipoDia primeiro = this.tipoDiaRepository.findOne(1);

			Calendar startCal = Calendar.getInstance();
			startCal.setTime(inicioPrimeiroSemestre);

			Calendar endCal = Calendar.getInstance();
			endCal.setTime(fimPrimeiroSemestre);
		
           Map<String, String> mapaSemana=new HashMap<String,String>();
           mapaSemana.put("Sun", "DOMINGO");
           mapaSemana.put("Mon", "SEGUNDA");
           mapaSemana.put("Tue", "TERCA");
           mapaSemana.put("Wed", "QUARTA");
           mapaSemana.put("Thu", "QUINTA");
           mapaSemana.put("Fri", "SEXTA");
           mapaSemana.put("Sat", "SABADO");
          
           Map<String, Integer> indice=new HashMap<String,Integer>();
           indice.put("Sun", 1);
           indice.put("Mon", 2);
           indice.put("Tue", 3);
           indice.put("Wed", 4);
           indice.put("Thu", 5);
           indice.put("Fri", 6);
           indice.put("Sat", 7);
       
			do {
				Integer semana = indice.get(startCal.getTime().toString().substring(0,3));
				horarioCalendario=new HorarioCalendario();
				
				horarioCalendario.setAno(ano);
				horarioCalendario.setAnoLectivo(anoLectivo);
				horarioCalendario.setInstituicao(instituicao);
				horarioCalendario.setTipoDia(primeiro);
				horarioCalendario.setSemestre(Semestre.PRIMEIRO);
				horarioCalendario.setData(startCal.getTime());
				horarioCalendario.setDiaSemanaNome(mapaSemana.get(startCal.getTime().toString().substring(0,3)));
				horarioCalendario.setDiaSemana(indice.get(startCal.getTime().toString().substring(0,3)));
				
				//if(semana!=1 && semana!=7)
					
				horarioCalendario.setDiaUtil(semana!=1 && semana!=7 ? true:false);
				horarioCalendario.setFinalDeSemana(semana==1 || semana==7 ? true:false);
				
				horarioCalendarioRepository.save(horarioCalendario);
				i++;
				startCal.add(Calendar.DAY_OF_MONTH, 1);
				startCal.get(Calendar.DAY_OF_WEEK);
			} while (startCal.getTimeInMillis() < endCal.getTimeInMillis());
			
			
			
			startCal = Calendar.getInstance();
			startCal.setTime(inicioSegundoSemestre);

			endCal = Calendar.getInstance();
			endCal.setTime(fimSegundoSemestre);
			
			do {
				Integer semana = indice.get(startCal.getTime().toString().substring(0,3));
				horarioCalendario=new HorarioCalendario();
				
				horarioCalendario.setAno(ano);
				horarioCalendario.setAnoLectivo(anoLectivo);
				horarioCalendario.setInstituicao(instituicao);
				horarioCalendario.setTipoDia(primeiro);
				horarioCalendario.setSemestre(Semestre.SEGUNDO);
				horarioCalendario.setData(startCal.getTime());
				horarioCalendario.setDiaSemanaNome(mapaSemana.get(startCal.getTime().toString().substring(0,3)));
				horarioCalendario.setDiaSemana(indice.get(startCal.getTime().toString().substring(0,3)));
				
				//if(semana!=1 && semana!=7)
					
				horarioCalendario.setDiaUtil(semana!=1 && semana!=7 ? true:false);
				horarioCalendario.setFinalDeSemana(semana==1 || semana==7 ? true:false);
				
				horarioCalendarioRepository.save(horarioCalendario);
				i++;
				startCal.add(Calendar.DAY_OF_MONTH, 1);
				startCal.get(Calendar.DAY_OF_WEEK);
			} while (startCal.getTimeInMillis() < endCal.getTimeInMillis());
			
		}
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(i);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	} 
}