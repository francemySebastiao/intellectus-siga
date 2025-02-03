package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Provincia;

public interface ProvinciaRepository extends CrudRepository<Provincia,Integer>{
	public Provincia findByCodigoProvinciaAndProvincia(Integer codigoProvincia, String provincia);
}