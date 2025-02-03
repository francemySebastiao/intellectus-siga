package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
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

import ao.co.intellectus.DTO.DocenteCliente;
import ao.co.intellectus.DTO.DocenteNome;
import ao.co.intellectus.DTO.DocenteResumo;

//import com.nitgen.SDK.BSP.NBioBSPJNI;

import ao.co.intellectus.model.Docente;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.Municipio;
import ao.co.intellectus.model.Pais;
import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.Provincia;
import ao.co.intellectus.model.TipoContratoLaboral;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.DocenteRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.repository.MuniciopioRepository;
import ao.co.intellectus.repository.PaisRepository;
import ao.co.intellectus.repository.ProvinciaRepository;
import ao.co.intellectus.repository.TipoContratoLaboralRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.cafold.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/docente")
public class ControllerDocente {

	@Autowired
	private DocenteRepository docenteRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private PaisRepository paisRepository;
	@Autowired
	private ProvinciaRepository provinciaRepository;
    @Autowired
	private TipoContratoLaboralRepository tipoContratoRepository;
    @Autowired
	private MoedaRepository moedaRepository;
    @Autowired
    private MuniciopioRepository municipioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    //@Autowired
    //private DocenteService docenteService;
    //@Autowired
	//private BiometricoService biometricoService;
    
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody DocenteCliente cDocente) {
		
		ResponseCliente resultado=new ResponseCliente();
		 
		Docente docente =new Docente();
        BeanUtils.copyProperties(cDocente, docente, "moeda");
       
        Instituicao instituicao       = cDocente.getInstituicao()        !=null ? this.instituicaoRepository.findOne(cDocente.getInstituicao()):null;
        Moeda moedaRemuneracao        = cDocente.getMoedaRemuneracao()   !=null ? this.moedaRepository.findOne(cDocente.getMoedaRemuneracao()): null;
        Municipio municipio           = cDocente.getMunicipioResidencia()!=null ? this.municipioRepository.findOne(cDocente.getMunicipioResidencia()):null;
        Pais nacionalidade            = cDocente.getPaisNacionalidade()  !=null ? this.paisRepository.findOne(cDocente.getPaisNacionalidade()):null;
        Provincia provinciaResidencia = cDocente.getProvinciaResidencia()!=null ? this.provinciaRepository.findOne(cDocente.getProvinciaResidencia()):null;
        TipoContratoLaboral contrato  = cDocente.getTipoContratoLaboral()!=null ? this.tipoContratoRepository.findOne(cDocente.getTipoContratoLaboral()):null;
       

        docente.setInstituicao(instituicao);
        docente.setMoedaRemuneracao(moedaRemuneracao);
        docente.setMunicipioResidencia(municipio);
        docente.setPaisNacionalidade(nacionalidade);
        docente.setProvinciaResidencia(provinciaResidencia);
        docente.setTipoContratoLaboral(contrato);
        docente.setEstado(true);
        docente.setDataFimContrato(cDocente.getDataFimContrato());

        this.docenteRepository.save(docente);
		/*SETAR OS VALORES DE RETORNO.*/
		resultado.setCodigo(ResponseCode.values()[0].getDescricao());
		resultado.setMensagem("Docente Cadastrado com sucesso!");
		resultado.setResultado(docente);
		
		return new ResponseEntity<ResponseCliente>(resultado, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody DocenteCliente cDocente) {
		ResponseCliente resultado=new ResponseCliente();
		//Docente docente =new Docente();
		Docente docente = this.docenteRepository.findOne(cDocente.getId());
		
        BeanUtils.copyProperties(cDocente, docente, "moeda");
       
        Instituicao instituicao       = cDocente.getInstituicao()        !=null ? this.instituicaoRepository.findOne(cDocente.getInstituicao()):null;
        Moeda moedaRemuneracao        = cDocente.getMoedaRemuneracao()   !=null ? this.moedaRepository.findOne(cDocente.getMoedaRemuneracao()): null;
        Municipio municipio           = cDocente.getMunicipioResidencia()!=null ? this.municipioRepository.findOne(cDocente.getMunicipioResidencia()):null;
        Pais nacionalidade            = cDocente.getPaisNacionalidade()  !=null ? this.paisRepository.findOne(cDocente.getPaisNacionalidade()):null;
        Provincia provinciaResidencia = cDocente.getProvinciaResidencia()!=null ? this.provinciaRepository.findOne(cDocente.getProvinciaResidencia()):null;
        TipoContratoLaboral contrato  = cDocente.getTipoContratoLaboral()!=null ? this.tipoContratoRepository.findOne(cDocente.getTipoContratoLaboral()):null;

        docente.setInstituicao(instituicao);
        docente.setMoedaRemuneracao(moedaRemuneracao);
        docente.setMunicipioResidencia(municipio);
        docente.setPaisNacionalidade(nacionalidade);
        docente.setProvinciaResidencia(provinciaResidencia);
        docente.setTipoContratoLaboral(contrato);
        docente.setDataFimContrato(cDocente.getDataFimContrato());

        this.docenteRepository.save(docente);
		/*SETAR OS VALORES DE RETORNO.*/
		resultado.setCodigo(ResponseCode.values()[0].getDescricao());
		resultado.setMensagem("Docente atualizado com sucesso!");
		resultado.setResultado(docente);
		return new ResponseEntity<ResponseCliente>(resultado, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarTodos() {
		ResponseCliente c=new ResponseCliente();
		Iterable<Docente> docentes = this.docenteRepository.findByEstadoOrderByNome(true);		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(docentes);	
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/buscarPorId/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCodigo(@PathVariable Integer id) {
		ResponseCliente c=new ResponseCliente();
		Docente docente = this.docenteRepository.findOne(id);
		
		DocenteCliente dc = new DocenteCliente();
		
		BeanUtils.copyProperties(docente, dc, "moedaRemuneracao", "municipioResidencia", "paisNacionalidade");
		
		if(docente.getInstituicao()!=null)
		dc.setInstituicao(docente.getInstituicao().getId());
		
		if(docente.getMunicipioResidencia()!=null)
		dc.setMunicipioResidencia(docente.getMunicipioResidencia().getId());
		
		if(docente.getPaisNacionalidade()!=null)
		dc.setPaisNacionalidade(docente.getPaisNacionalidade().getId());
		
		
		dc.setSexo(docente.getSexo());
		
		c.setResultado(dc);
		
		
		if(c.getResultado()==null) {
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem("Nenhum Docente não encontrado");
		}else{
			c.setCodigo(ResponseCode.values()[0].getDescricao());
		}
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}


	@RequestMapping(value = "/buscarPorId1/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCodigoOriginal(@PathVariable Integer id) {
		ResponseCliente c=new ResponseCliente();
		Docente docente = this.docenteRepository.findOne(id);
		
		c.setResultado(docente);
		if(c.getResultado()==null) {
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem("Nenhum Docente não encontrado");
		}else{
			c.setCodigo(ResponseCode.values()[0].getDescricao());
		}
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	//docente/buscarPorNomeLike
	
	@RequestMapping(value = "/buscarPorNomeLike", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorNomeLike(@RequestBody DocenteNome dc) {
		List<Docente> docente = this.docenteRepository.findByNomeLike(dc.getNome());
		ResponseCliente c = new ResponseCliente();
		if (docente.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.NOT_FOUND);
		}
		
		c.setPermissao(Permissao.LEITURA);
		c.setResultado(docente);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	//DocenteResumo
	@RequestMapping(value = "/atualizarUsuario", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizarUsuario(@RequestBody DocenteResumo dRerumo) {
		ResponseCliente c = new ResponseCliente();
		
		Docente docente = this.docenteRepository.findOne(dRerumo.getDocenteId());
		
		Usuario usuario = this.usuarioRepository.findOne(dRerumo.getUsuarioId());
		
		
		
		//List<Docente> ativosAntes = this.docenteRepository.findByUsuarioDocenteOrderByName(usuario);
		List<Docente> ativosAntes = this.docenteRepository.findByUsuarioDocenteOrderByNome(usuario);
		
		Docente dcs;
		for (Docente o : ativosAntes) {
			dcs = this.docenteRepository.findOne(o.getId());
			dcs.setUsuarioDocente(null);
			
			this.docenteRepository.save(dcs);
		}
		
		
		docente.setUsuarioDocente(usuario);
		System.out.println("Testando --> " + usuario.getId());
		this.docenteRepository.save(docente);
		
		
		c.setPermissao(Permissao.LEITURA);
		c.setMensagem("Associação efetivada com sucesso!");
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	
	/*
	@RequestMapping(value = "/salvarBiometrico/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente>  salvarBiometrico(@PathVariable Integer id) {
		Docente docente = this.docenteService.docente(id);
		if(docente == null) {
			return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Docente não encontrado");
		}else {
			NBioBSPJNI bsp = new NBioBSPJNI();
			if (biometricoService.temErro(bsp)) {
				return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Erro ao carregar as Drivers !");
			} else {
				if(biometricoService.temDispositivo(bsp)) {
					bsp = biometricoService.capturarImpressao(bsp);
					if (biometricoService.temErro(bsp)) {
						return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Erro ao capturar impressão !");
					} else {
						return this.docenteService.salvarImpressao(bsp, biometricoService.getImpressaoCapturada(), docente);
					}
				}else {
					return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Dispositivo não encontrado !");
				}
			}
		}
	}
	
	@RequestMapping(value = "/pesquisarBiometrico", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pesquisarBiometrico() {
		NBioBSPJNI bsp = new NBioBSPJNI();
		if (biometricoService.temErro(bsp)) {
			return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Erro ao verificar disposivo !");
		} else {
			if(biometricoService.temDispositivo(bsp)) {
				return this.docenteService.pesquisaImpressao(bsp);
			}else {
				return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Dispositivo não encontrado !");
			}
		}
	}
	*/
	
	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}

	@GetMapping("/relatorio/assiduidade")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioAssiduidade(@RequestParam String data1, @RequestParam String data2, @RequestParam String tipo, @RequestParam(required = false) Integer filtro) throws Exception {
		System.out.println(data1);
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);
		
		
		
		byte[] relatrio = servicoRelatorioAssiduidadeTipo(dataF1, dataF2, tipo, filtro);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoRelatorioAssiduidadeTipo(Date data1, Date dat2, String tipo, Integer filtro) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data1", data1);
		paramets.put("data2", dat2);
		
		
		InputStream inputStream = null;
		if(tipo.equals("TODOS_CURSOS")) {
			System.out.println("Entrou aqui");
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Assiduidade_Docente.jasper");
		}else if(tipo.equals("SEPARA_POR_CURSO")) {
			System.out.println("Entramos na realidade dura");
			paramets.put("id_curso", filtro);
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Assiduidade_Docente_Por_Curso.jasper");
		}else if(tipo.equals("SOMENTE_DOCENTE")) {
			System.out.println("Entrei bagaçaaa");
			paramets.put("id_docente", filtro);
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Assiduidade_Docente_Individual.jasper");
			System.out.println("Entrei");
		}
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}