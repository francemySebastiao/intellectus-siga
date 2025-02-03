package ao.co.intellectus.servico.cafold;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Equivalencia;
import ao.co.intellectus.model.EquivalenciaAudit;
import ao.co.intellectus.model.EquivalenciaHistoricoAlunoAudit;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.EquivalenciaAuditRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoAuditRepository;

@Service
public class EquivalenciaServices {
	//@Autowired
	//private EquivalenciaRepository equivalenciaRepository;
	@Autowired
	private EquivalenciaAuditRepository equivalenciaAuditRepository;
	@Autowired
	private EquivalenciaHistoricoAuditRepository equivalenciaHistoricoAuditRepository;
	
	public ResponseCliente salvar(Equivalencia equivalencia){
		ResponseCliente c=new ResponseCliente();
		//this.equivalenciaRepository.findOne(arg0);
        //BeanUtils.copyProperties(lecionaCliente, leciona, "disciplina","curso","turma","docente","anoLectivo","Sala");   
		
		//Equivalencia EquivalenciaSalva = this.equivalenciaRepository.save(equivalencia);
		
		c.setCodigo(ResponseCode.values()[0].getDescricao()); 
    	c.setResultado(equivalencia);
    	c.setMensagem("Equivalencia efetuada com sucesso!");
		return c;
	}
	
	public void gerarHistorico(Equivalencia equivalencia) {
		EquivalenciaAudit equivalenciaAudit = new EquivalenciaAudit();
		BeanUtils.copyProperties(equivalencia, equivalenciaAudit);
		this.equivalenciaAuditRepository.save(equivalenciaAudit);
	}
	
	public void gerarHistorico(HistoricoEquivalencia equivalenciaHistoricoAluno) {
		EquivalenciaHistoricoAlunoAudit equivalenciaHistoricoAlunoAudit = new EquivalenciaHistoricoAlunoAudit();
		BeanUtils.copyProperties(equivalenciaHistoricoAluno, equivalenciaHistoricoAlunoAudit);
		this.equivalenciaHistoricoAuditRepository.save(equivalenciaHistoricoAlunoAudit);
	}
	
	
}
