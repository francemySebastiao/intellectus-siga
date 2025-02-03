package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Emolumento;

public interface EmolumentoRepository extends CrudRepository<Emolumento,Integer>{
	public Emolumento findByCodigo(Integer codigo);
	public Emolumento findByCodigoAndStatus(Integer codigo,boolean status);
	public Emolumento findById(Integer codigo);
	public List<Emolumento> findByEmolumentoLike(String emolumento);
	
	public List<Emolumento> findByStatus(boolean status);
	
	public List<Emolumento> findByStatusOrderByEmolumentoAsc(boolean status);
	
	public List<Emolumento> findByPodeMulta(boolean podeMulta);
	
	public List<Emolumento> findByIdBetween(int inicio, int fim);
	
	@Query(value="select * from T_EMOLUMENTO where EMOLUMENTO =:descricao", nativeQuery=true)
	public Emolumento buscarDescricao(@Param("descricao")String descricao);
	
	@Query(value = "SELECT * FROM T_EMOLUMENTO WHERE CODIGO BETWEEN '101' AND '112'", nativeQuery=true)
	public List<Emolumento> propinaNormal();
	
	@Query(value = "SELECT * FROM T_EMOLUMENTO WHERE CODIGO BETWEEN '70' AND '81'", nativeQuery=true)
	public List<Emolumento> propinaDisciplina();
	
	@Query(value = "SELECT * FROM T_EMOLUMENTO WHERE CODIGO BETWEEN '301' AND '312'", nativeQuery=true)
	public List<Emolumento> propinaTarde();
	
	@Query(value = "SELECT * FROM T_EMOLUMENTO WHERE CODIGO BETWEEN '101' AND '112'", nativeQuery=true)
	public List<Emolumento> propinaDisciplinaTarde();
}