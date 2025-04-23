package ao.co.intellectus.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AlunoResumoCliente;
import ao.co.intellectus.DTO.BolseirosListaCliente;
import ao.co.intellectus.DTO.ConvenioCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.ContaCorrenteAluno;
import ao.co.intellectus.model.ContaCorrenteEmpresa;
import ao.co.intellectus.model.CreaditoDeAluno;
import ao.co.intellectus.model.EmpresaConvenio;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.ContaCorrenteRepository;
import ao.co.intellectus.repository.ContaCorreteEmpresaRepository;
import ao.co.intellectus.repository.CreditoAlunoRepository;
import ao.co.intellectus.repository.EmpresaConvenioRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/empresaConvenio")
public class ControllerEmpresaConvenio {
	@Autowired
	private EmpresaConvenioRepository convenioRepository;
	@Autowired
	private CreditoAlunoRepository creditoAlunoRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivo;
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	private ContaCorreteEmpresaRepository contaCorrenteEmpresaRepo;
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces= "application/json")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody EmpresaConvenio convenio) {
	
		EmpresaConvenio empresaConvenio = this.convenioRepository.findByDesignacao(convenio.getDesignacao());
		if(empresaConvenio != null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Já existe este tipo de empresa.");
		empresaConvenio = new EmpresaConvenio();
		BeanUtils.copyProperties(convenio, empresaConvenio);
		empresaConvenio.setDataCadastro(new Date());
        EmpresaConvenio empresa = this.convenioRepository.save(empresaConvenio);
        
        ContaCorrenteEmpresa contaEmpresa = new ContaCorrenteEmpresa();
        
        AnoLectivo ano = anoLectivo.buscarPorEstado();
        
        contaEmpresa.setEmpresa(empresa);
        contaEmpresa.setAnoLectivo(ano);
        contaEmpresa.setValor(0.0);
        contaEmpresa.setValorAnterior(0.0);
        
        contaCorrenteEmpresaRepo.save(contaEmpresa);
        return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Empresa cadastrada com sucesso!");
	} 
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody EmpresaConvenio convenio) {
        EmpresaConvenio empresaConvenio = this.convenioRepository.findOne(convenio.getId());
        if(empresaConvenio == null)
        	return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,"Registo de empresa não encontrada.");
        else if(empresaConvenio.getId() != convenio.getId())
        	return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Já existe este tipo de empresa.");
       BeanUtils.copyProperties(convenio, empresaConvenio);
       empresaConvenio.setDataCadastro(new Date());
       this.convenioRepository.save(empresaConvenio);
       return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Empresa alterada com sucesso."); 
	}
	
	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> empresasConvenio() {
		Iterable<EmpresaConvenio> empresaConvenio = this.convenioRepository.findAll();
		if(empresaConvenio == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de empresa encontrado.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(empresaConvenio, 0,null);	
	}
	
	
	@RequestMapping(value = "/registrarCredito", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> efetuarConvenio(@RequestBody ConvenioCliente convenio) {
        ResponseCliente c=new ResponseCliente();
        
        EmpresaConvenio empresa = this.convenioRepository.findOne(convenio.getEmpresa());
        ContaCorrenteEmpresa contaEmpresa = contaCorrenteEmpresaRepo.buscarEmpresa(convenio.getEmpresa());
      
        AnoLectivo ano = this.anoLectivo.findOne(convenio.getAnoLectivo());
        
        CreaditoDeAluno credito;
        List<AlunoResumoCliente> bolceiros = convenio.getBolseiros();
        
        double somaValores = 0;
		for (AlunoResumoCliente o : bolceiros) {
			somaValores += o.getValorAdd();
		}
        
        if(bolceiros.isEmpty()) {
        	c.setCodigo(ResponseCode.values()[2].getDescricao()); 
    	    c.setMensagem("Nenhum bolseiro localizado!");
    		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
        }
        
        
       
        if(convenio.getValor() >= somaValores) {
        	
        	for (AlunoResumoCliente aluno : bolceiros) {
            	if(!aluno.isStatus()) {
            		
            		credito=new CreaditoDeAluno();
            		
                	Aluno alunoPego = this.alunoRepository.findByNumeroDeAluno(aluno.getId().toString());
                	
                	ContaCorrenteAluno contaAluno = this.contaCorrenteRepository.findByAluno(alunoPego);
                	
                	credito.setAluno(alunoPego);
                	credito.setNumeroDeAluno(Integer.parseInt(alunoPego.getNumeroDeAluno()));
                	credito.setEmpresa(empresa);
                	credito.setDataRegistro(new Date());
                	credito.setAnoLectivo(ano);
                	credito.setValor(aluno.getValorAdd());
                	this.creditoAlunoRepository.save(credito);
                	
                	
                	//ALTERAR OS DADOS DA CONTA CORRENTE
                	double  valor = contaAluno.getValor() + aluno.getValorAdd();
                	contaAluno.setValorAnterior(contaAluno.getValor());
                	contaAluno.setValor(valor);
                	contaAluno.setDataMovimento(new Date());
                	this.contaCorrenteRepository.save(contaAluno);
                	//SALVAR CONTA CORRENTE
                	this.contaCorrenteRepository.save(contaAluno);
                	
            	}
    		}
        	
        	double novoValor = 0.0;
			double valorAntigo = contaEmpresa.getValor();
			
			novoValor = contaEmpresa.getValor() - somaValores;
			contaEmpresa.setValorAnterior(valorAntigo);
			contaEmpresa.setValor(novoValor);
			
			contaCorrenteEmpresaRepo.save(contaEmpresa);
			
			c.setResultado(convenio);
    		c.setCodigo(ResponseCode.values()[0].getDescricao()); 
    	    c.setMensagem("Registro de crédito efetuado com sucesso");
    		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
    		
        }
        
        c.setCodigo(ResponseCode.values()[3].getDescricao()); 
	    c.setMensagem("O total distribuído deve ser igual ao valor depósito");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);	
	}
}
