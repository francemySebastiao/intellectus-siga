package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Bordero;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.Moeda;

public interface BorderoRepository extends CrudRepository<Bordero,Integer>{
	public Bordero findByNumeroAndBancoId(String numero,Integer banco);
	
	public Bordero findByNumero(String numero);
	
	public Bordero findByGuia(Guia guia); 
	
	public List<Bordero> findByGuiaAndAluno(Guia guia,Aluno aluno);
	
	public List<Bordero> findByGuiaAndMoeda(Guia guia,Moeda moeda);
	
	@Query(value="SELECT * FROM T_BORDERO WHERE CODIGO_FACTURA =:factura",nativeQuery=true)
	public Bordero buscarIdFactura(@Param("factura") Long factura);
}
