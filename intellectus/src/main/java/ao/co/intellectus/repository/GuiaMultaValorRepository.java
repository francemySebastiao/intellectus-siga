package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.GuiaMultaValor;

public interface GuiaMultaValorRepository extends CrudRepository<GuiaMultaValor,Integer>{
	public List<GuiaMultaValor> findByNumeroDeAluno(Integer numeroDeAluno);
}
