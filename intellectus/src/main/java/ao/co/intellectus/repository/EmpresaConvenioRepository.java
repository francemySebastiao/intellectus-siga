package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.EmpresaConvenio;

public interface EmpresaConvenioRepository extends CrudRepository<EmpresaConvenio, Integer>{
    public EmpresaConvenio findByDesignacao(String designacao);
    public EmpresaConvenio findById(Integer id);
}
