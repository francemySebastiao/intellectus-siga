package ao.co.intellectus.controller;

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

import ao.co.intellectus.DTO.AlunoAndAnoLectivoCliente;
import ao.co.intellectus.DTO.ControlePagamentoCliente;
import ao.co.intellectus.model.ResumoPagamento;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.ResumoPagamentoRepository;

@RestController
@RequestMapping("/controllePagamentos")
public class ControllerPagamentos {
	@Autowired
	private ResumoPagamentoRepository resumoPagamentoRepository;
	@RequestMapping(value = "/gerais", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> processamentoMensal(@RequestBody AlunoAndAnoLectivoCliente aluno) {
		ResponseCliente c = new ResponseCliente();
		
		List<ResumoPagamento> pagamentos = this.resumoPagamentoRepository.findByNumeroDeAlunoAndCodigoAnoLectivo(aluno.getAluno(), aluno.getAnoLectivo());
		ControlePagamentoCliente cPagamento=new ControlePagamentoCliente();
		for (ResumoPagamento o : pagamentos) {
		    
			if(o.getDescricaoPagamento()=="JANEIRO")
			  {
				cPagamento.setJaneiro(o.getValor());	
			  }
				
			if(o.getDescricaoPagamento()=="FEVEREIRO")
			  {
				cPagamento.setFevereiro(o.getValor());	
			  }
				
				
			if(o.getDescricaoPagamento().equals("MARCO"))
			  {
				cPagamento.setMarco(o.getValor());	
			  }
				
			if(o.getDescricaoPagamento().equals("ABRIL"))
			  {
				cPagamento.setAbril(o.getValor());	
			  }	
				
				
			if(o.getDescricaoPagamento().equals("MAIO"))
			  {
				cPagamento.setMaio(o.getValor());	
			  }	
				
			if(o.getDescricaoPagamento().equals("JUNHO"))
			  {
				cPagamento.setJunho(o.getValor());	
			  }	
			if(o.getDescricaoPagamento().equals("JULHO"))
			  {
				cPagamento.setJulho(o.getValor());	
			  }	
			if(o.getDescricaoPagamento().equals("AGOSTO"))
			  {
				cPagamento.setAgosto(o.getValor());	
			  }	
			if(o.getDescricaoPagamento().equals("SETEMBRO"))
			  {
				cPagamento.setSetembro(o.getValor());	
			  }	
			if(o.getDescricaoPagamento().equals("OUTUBRO"))
			  {
				cPagamento.setOutubro(o.getValor());	
			  }	
			if(o.getDescricaoPagamento().equals("NOVEMBRO"))
			  {
				cPagamento.setNovembro(o.getValor());	
			  }	
			
			if(o.getDescricaoPagamento().equals("DEZEMBRO"))
			  {
				cPagamento.setDezembro(o.getValor());	
			  }	
			
			if(o.getDescricaoPagamento().equals("INSCRICAO"))
			  {
				cPagamento.setInscricao(o.getValor());	
			  }	
			if(o.getDescricaoPagamento().equals("OUTROS"))
			  {
				cPagamento.setOutrosPagamentos(o.getValor());	
			  }	
		}
		//cPagamento.setTotalPropinas(totalPropinas);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(cPagamento);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	//DEPRECIADO 
	/*
	 * DEPRECIADOR: ERNESTO TADEU T. SAMBONGO
	 * DATA: 24/08/2019
	 * 08:59
	 * 
	 */
	/*
	 @RequestMapping(value = "/gerais", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> processamentoMensal(@RequestBody AlunoAndAnoLectivoCliente aluno) {
		ResponseCliente c = new ResponseCliente();
		//ControlePagamentoCliente pagamentos=new ControlePagamentoCliente();
		
		//List<EmolumentoHistorico> listEmolumentos = this.emolumentoHistoricoRepository.ListEmolumentos();
		Emolumento em1       = this.emolumentoRepository.findOne(52);
		Emolumento em2       = this.emolumentoRepository.findOne(67);
		Emolumento eMatricula= this.emolumentoRepository.findOne(23);
		Aluno alunoBusca     = this.alunoRepository.findByNumeroDeAluno(aluno.getAluno().toString());
		AnoLectivo anoLectivo= this.anoLectivoRepository.findOne(aluno.getAnoLectivo());
		 
		List<GuiaPagamentoHistorico> eMatriculaAluno = this.historicoGuiaPagamentoRepository.findByEmolumentoAndAlunoAndAnoLectivo(eMatricula,alunoBusca,anoLectivo);
		List<GuiaPagamentoHistorico> hFinanceiro = this.historicoGuiaPagamentoRepository.findByAnoLectivoAndNumeroDeAlunoAndEmolumentoBetween(anoLectivo,aluno.getAluno().toString(),em1, em2);
		ControlePagamentoCliente cPagamento=new ControlePagamentoCliente();
		
		
		
		cPagamento.setInscricao(eMatriculaAluno.get(0).getValor()); 
		double totalPropinas=0;
		EmolumentoHistorico eml;
		for (GuiaPagamentoHistorico hf : hFinanceiro) {
			if(hf.getEmolumento().getCodigo()==101 && hf.getGuia().isLiquidada()) {
				
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
			    
				
				
				
				cPagamento.setJaneiro(eml.getValor());
				totalPropinas+=eml.getValor();
			}
			if(hf.getEmolumento().getCodigo()==102 && hf.getGuia().isLiquidada()) {
				
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				
				
				cPagamento.setFevereiro(eml.getValor());
				totalPropinas+=eml.getValor();	
			}
			if(hf.getEmolumento().getCodigo()==103 && hf.getGuia().isLiquidada()) {
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				float valor = eml.getValor();
				
				cPagamento.setMarco(valor);
				totalPropinas+=valor;
			}
			if(hf.getEmolumento().getCodigo()==104 && hf.getGuia().isLiquidada()) {
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				float valor = eml.getValor();
				
				cPagamento.setAbril(valor);
				totalPropinas+=valor;
			}
			if(hf.getEmolumento().getCodigo()==105 && hf.getGuia().isLiquidada()) {
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				float valor = eml.getValor();
				
				cPagamento.setMaio(valor);
				totalPropinas+=valor;
			}
			if(hf.getEmolumento().getCodigo()==106 && hf.getGuia().isLiquidada()) {
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				float valor = eml.getValor();
				
				cPagamento.setJunho(valor);
				totalPropinas+=valor;
			}
			if(hf.getEmolumento().getCodigo()==107 && hf.getGuia().isLiquidada()) {
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				float valor = eml.getValor();
				
				cPagamento.setJulho(valor);
				totalPropinas+=valor;	
			}
			if(hf.getEmolumento().getCodigo()==108 && hf.getGuia().isLiquidada()) {
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				float valor = eml.getValor();
				
				cPagamento.setAgosto(valor);
				totalPropinas+=valor;	
			}
			if(hf.getEmolumento().getCodigo()==109 && hf.getGuia().isLiquidada()) {
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				float valor = eml.getValor();
				
				cPagamento.setSetembro(valor);
				totalPropinas+=valor;	
			}
			if(hf.getEmolumento().getCodigo()==110 && hf.getGuia().isLiquidada()) {
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				float valor = eml.getValor();
				
				cPagamento.setOutubro(valor);
				totalPropinas+=valor;	
			}
			if(hf.getEmolumento().getCodigo()==111 && hf.getGuia().isLiquidada()) {
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				float valor = eml.getValor();
				
				cPagamento.setNovembro(valor);
				totalPropinas+=valor;	
			}
			if(hf.getEmolumento().getCodigo()==112 && hf.getGuia().isLiquidada()) {
				eml = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(hf.getEmolumento(), hf.getAluno().getCurso(), anoLectivo);
				float valor = eml.getValor();
				
				cPagamento.setDezembro(valor);
				totalPropinas+=valor;	
			}
		}
		cPagamento.setTotalPropinas(totalPropinas);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(cPagamento);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	 */
}
