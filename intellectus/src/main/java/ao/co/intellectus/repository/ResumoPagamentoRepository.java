package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.ResumoPagamento;

public interface ResumoPagamentoRepository extends CrudRepository<ResumoPagamento,Integer>{
    public List<ResumoPagamento> findByNumeroDeAlunoAndCodigoAnoLectivo(Integer numeroDeAluno,Integer anoLectivo);
}
