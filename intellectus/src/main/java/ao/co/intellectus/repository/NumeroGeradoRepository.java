package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.NumeroGerado;

public interface NumeroGeradoRepository extends JpaRepository<NumeroGerado,Integer>{

	public NumeroGerado findByDescricao(String descricao);
	
}
