package ao.co.intellectus.servico.cafold;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoAlunoAudit;
import ao.co.intellectus.repository.HistoricoAlunoAuditRepository;

@Service
public class HistoricoAlunoService {
	
	@Autowired
	private HistoricoAlunoAuditRepository historicoAlunoAuditRepository;
	
	public void gerarHistorico(HistoricoAluno historicoAluno) {
		HistoricoAlunoAudit historicoAlunoAudit = new HistoricoAlunoAudit();
		BeanUtils.copyProperties(historicoAluno,historicoAlunoAudit);
		this.historicoAlunoAuditRepository.save(historicoAlunoAudit);
	}
	
//	public void gerarHistorico(HistoricoAlunoCdd historicoAluno) {
//		HistoricoAlunoCcdAudit historicoAlunoAudit = new HistoricoAlunoCcdAudit();
//		BeanUtils.copyProperties(historicoAluno,historicoAlunoAudit);
//		this.historicoAlunoAuditRepository.save(historicoAlunoAudit);
//	}

}
