package ao.co.intellectus.servico.guias;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.GuiaPagamentoCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;


@RestController
@RequestMapping("/guiaDefault")
public class CriarGuia {

	@Autowired
	private AlunoRepository aluno;
	@Autowired
	private EmolumentoHistoricoRepository historicoEmolumentoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivo;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private GuiaPagamentoRepository guiaPagamentoRepository;
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody GuiaPagamentoCliente guia) {	
		ResponseCliente c=new ResponseCliente();
		
		List<EmolumentoHistorico> hes=new ArrayList<EmolumentoHistorico>();
		
		Aluno aluno           = this.aluno.findByNumeroDeAluno(guia.getAluno().toString());
		AnoLectivo anoLectivo = this.anoLectivo.findOne(guia.getAnoLectivo());
	    Emolumento emolumento = this.emolumentoRepository.findByCodigo(guia.getEmolumento());
		
		EmolumentoHistorico hEmolumento = this.historicoEmolumentoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento, aluno.getCurso(), anoLectivo);
		
		hes.add(hEmolumento);
		
		CriarGuiaPadrao novaGuia=new CriarGuiaPadrao();
		
		Guia guiaCriada = novaGuia.guiaCriada(aluno, hes, anoLectivo);
		
		Guia guiaPagamento = this.guiaPagamentoRepository.save(guiaCriada);
		c.setResultado(guiaPagamento);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}	
}