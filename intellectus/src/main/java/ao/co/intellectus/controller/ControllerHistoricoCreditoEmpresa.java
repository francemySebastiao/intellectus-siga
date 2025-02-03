/*package ao.co.intellectus.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.HistoricoCreditoEmpresaDTO;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Banco;
import ao.co.intellectus.model.BorderoEmpresa;
import ao.co.intellectus.model.EmpresaConvenio;
import ao.co.intellectus.model.HistoricoCreditoEmpresa;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.BancoRepository;
import ao.co.intellectus.repository.BorderoEmpresaRepository;
import ao.co.intellectus.repository.EmpresaConvenioRepository;
import ao.co.intellectus.repository.UsuarioRepository;

@RestController
@RequestMapping("/historicoCredito")
public class ControllerHistoricoCreditoEmpresa {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BorderoEmpresaRepository borderoRepo;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private BancoRepository bancoRepository;
	@Autowired
	private EmpresaConvenioRepository empresaRepo;
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody HistoricoCreditoEmpresaDTO credito) {
		
		ResponseCliente c = new ResponseCliente();
		
		Usuario usuario = this.usuarioRepository.findByUserName(credito.getUserName() !=null ? credito.getUserName() : null);
		
		BorderoEmpresa borderoExterno = this.borderoRepo.buscarNumeroBanco(credito.getBorderoExterno(),credito.getBanco());
		
		if(borderoExterno!=null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Este número de operação já foi usado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		AnoLectivo anoLectivo  = this.anoLectivoRepository.findOne(credito.getAnoLectivo());
		Banco banco = this.bancoRepository.findOne(credito.getBanco());
		
		BorderoEmpresa bordero = new BorderoEmpresa();
		EmpresaConvenio empresa = this.empresaRepo.findById(credito.getEmpresa());
		
		bordero.setEmpresConvenio(empresa);
		bordero.setBanco(banco);
		bordero.setNumero(credito.getBorderoExterno());
		bordero.setValor(credito.getValorDeposito());
		bordero.setDataRegistro(new Date());
		
		this.borderoRepo.save(bordero);
		
		HistoricoCreditoEmpresa hCredito = new HistoricoCreditoEmpresa();
		
		hCredito.setEmpresa(empresa);
		
		
		return null;
	}

}
*/