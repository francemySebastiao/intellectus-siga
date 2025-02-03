package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.CompensassaoProvisoria;

public interface CompensassaoProvisoriaRepository extends CrudRepository<CompensassaoProvisoria,Integer>{
    public CompensassaoProvisoria findByNomeAndTelefone(String nome,String telefone);
    public List<CompensassaoProvisoria> findByNumeroGuia(String numeroGuia);
    
    public CompensassaoProvisoria findByNumeroGuiaAndNome(String numeroGuia,String nome);
    
    
    public CompensassaoProvisoria findByNumeroGuiaAndNomeAndCompensado(String numeroGuia,String nome,boolean compensado);
    
    
    public CompensassaoProvisoria findByNumeroGuiaAndCompensado(String numeroGuia,boolean compesado);
    
}
