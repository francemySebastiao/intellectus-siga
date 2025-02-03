package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.HistoricoCredito;

public interface HistoricoCreditoRepository extends CrudRepository<HistoricoCredito, Integer> {
	public HistoricoCredito findByBorderoInterno(String borderoInterno);

	public HistoricoCredito findByBorderoInternoAndAnulado(String borderoInterno, boolean anulado);

	public List<HistoricoCredito> findByAluno(Aluno aluno);

	public List<HistoricoCredito> findByAlunoAndAnulado(Aluno aluno, boolean anulado);

	/// ADICIONADO...
	public List<HistoricoCredito> findByAlunoAndAnuladoOrderByIdDesc(Aluno aluno, boolean anulado);

	public List<HistoricoCredito> findByCandidato(Candidato candidato);

	//@Query(value = "SELECT * from T_HISTORICO_CREDITO WHERE n_factura_recibo =:recibo", nativeQuery = true)
	//public HistoricoCredito findFacturaRecibo(@Param("recibo") String recibo);
}
