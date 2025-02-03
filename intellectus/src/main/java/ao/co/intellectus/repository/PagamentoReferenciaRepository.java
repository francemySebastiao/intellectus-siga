package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.PagamentoReferencia;

public interface PagamentoReferenciaRepository extends CrudRepository<PagamentoReferencia,Integer>{
    public PagamentoReferencia findByGuiaAndCompensado(Guia guia,boolean compensado);
}
