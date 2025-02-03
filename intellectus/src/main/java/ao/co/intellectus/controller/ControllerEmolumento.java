package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.EmolumentoLancamento;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.Turno;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/emolumento")
public class ControllerEmolumento {
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody EmolumentoLancamento e) {
		ResponseCliente c = new ResponseCliente();
		
		//BUSCA ANO LECTIVO ACTIVO
        List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
        
        //BUSCA A MOEDA EM ACÇÃO
        Moeda moeda = this.moedaRepository.findOne(3);
        
		
		//INSTANCIA UMA NOVA INSTÂNCIA DE EMOLUMENTO
		Emolumento emolumento =new Emolumento();
		
		emolumento.setCodigo(e.getCodigo());
		emolumento.setEmolumento(e.getEmolumento());
		emolumento.setStatus(true);
		emolumento.setParaContecioso(e.isParaContecioso());
		emolumento.setDesconta(e.isDesconta());
		emolumento.setPropina(e.isPropina());
		emolumento.setCodigoIva(e.getCodigoIva());
		emolumento.setPercentagemIva(e.getPercentagemIva());
		
		//SALVA EMOLUMENTO
		Emolumento emolumentoSalvo = this.emolumentoRepository.save(emolumento);
		

		//BUSCA OS CURSO PARA RELACIONAR COM O NOVO EMOLUMENTO
		List<Curso> cursos = this.cursoRepository.findByStatus(true);
		
		EmolumentoHistorico eH;
		for (Curso curso : cursos) {
			for (Turno turno : Turno.values()) {
		        eH = new EmolumentoHistorico();
		        
		        eH.setAnoLectivo(anoLectivo.get(0));
		        eH.setCurso(curso);
		        eH.setEmolumento(emolumentoSalvo);
		        eH.setMoeda(moeda);
		        eH.setValor(e.getValor());
		        eH.setTurno(turno.getDescricao().toUpperCase());
		        
		        this.emolumentoHistoricoRepository.save(eH);
		    }
		}
		
		c.setResultado(null);
		c.setCodigo(200); 
		c.setMensagem("Emolumento lançado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody EmolumentoLancamento e) {
		//BUSCA ANO LECTIVO ACTIVO
        List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
        //BUSCA A MOEDA EM ACÇÃO
        Moeda moeda = this.moedaRepository.findOne(3);
        
		//INSTANCIA UMA NOVA INSTÂNCIA DE EMOLUMENTO
		Emolumento emolumento =this.emolumentoRepository.findOne(e.getCodigo());
		emolumento.setCodigo(e.getCodigo());
		emolumento.setEmolumento(e.getEmolumento());
		emolumento.setStatus(true);
		emolumento.setParaContecioso(e.isParaContecioso());
		emolumento.setDesconta(e.isDesconta());
		emolumento.setPropina(e.isPropina());
		
		//SALVA EMOLUMENTO
		Emolumento emolumentoSalvo = this.emolumentoRepository.save(emolumento);
		//BUSCA OS CURSO PARA RELACIONAR COM O NOVO EMOLUMENTO
		List<Curso> cursos = this.cursoRepository.findByStatus(true);
		EmolumentoHistorico eH;
		for (Curso curso : cursos) {
			eH=new EmolumentoHistorico();
			eH.setAnoLectivo(anoLectivo.get(0));
			eH.setCurso(curso);
			eH.setEmolumento(emolumentoSalvo);
			eH.setMoeda(moeda);
			eH.setValor(e.getValor());
			this.emolumentoHistoricoRepository.save(eH);
		}
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Emolumento actualizado com sucesso.");
	}
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todos() {
		ResponseCliente c = new ResponseCliente();
		List<Emolumento> emolumentos =this.emolumentoRepository.findByStatusOrderByEmolumentoAsc(true);
		if(emolumentos.isEmpty()) {
			c.setMensagem("Nenhum emolumento encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(emolumentos);
		c.setCodigo(200); 
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/propinaNormal", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> propinaNormal() {
		ResponseCliente c = new ResponseCliente();
		List<Emolumento> emolumentos =this.emolumentoRepository.propinaNormal();
		if(emolumentos.isEmpty()) {
			c.setMensagem("Nenhum emolumento encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(emolumentos);
		c.setCodigo(200); 
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/propinaNormalTarde", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> propinaNormalTarde() {
		ResponseCliente c = new ResponseCliente();
		List<Emolumento> emolumentos =this.emolumentoRepository.propinaTarde();
		if(emolumentos.isEmpty()) {
			c.setMensagem("Nenhum emolumento encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(emolumentos);
		c.setCodigo(200); 
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/propinaDisciplina", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> propinaDisciplina() {
		ResponseCliente c = new ResponseCliente();
		List<Emolumento> emolumentos =this.emolumentoRepository.propinaDisciplina();
		if(emolumentos.isEmpty()) {
			c.setMensagem("Nenhum emolumento encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(emolumentos);
		c.setCodigo(200); 
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/buscarPorCodigo/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarEmolumento(@PathVariable Integer id) {
		ResponseCliente c = new ResponseCliente();
	    
		Emolumento emolumento = this.emolumentoRepository.findByCodigo(id);
		
		if(emolumento.equals(null)) {
			c.setMensagem("Não existe nenhum emolumento com esse código.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(emolumento);
		c.setCodigo(200); 
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscarPorDescricao/{emolumento}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarEmolumentos(@PathVariable String emolumento) {
		ResponseCliente c = new ResponseCliente();
	    
		List<Emolumento> emolumentos = this.emolumentoRepository.findByEmolumentoLike("%"+emolumento+"%");
		
		if(emolumento.isEmpty()) {
			c.setMensagem("Não existe nenhum emolumento com esse código.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(emolumentos);
		c.setCodigo(200); 
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}
	
	@GetMapping("/valores/emolumentos")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioValoresEmolumentos(@RequestParam Integer anoLectivo) throws Exception {
		
		byte[] relatrio = servicoValoresEmolumentos(anoLectivo);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoValoresEmolumentos(Integer anoLectivo) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano", anoLectivo);
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Emolumentos.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@GetMapping("/lista/emolumentos")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioListaEmolumentos() throws Exception {
		
		byte[] relatrio = servicoListaEmolumentos();
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoListaEmolumentos() throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Lista_Emolumentos.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}
