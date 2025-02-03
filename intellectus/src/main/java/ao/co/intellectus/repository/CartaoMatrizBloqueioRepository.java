package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.CartaoMatrizBloqueio;
import ao.co.intellectus.model.Docente;

public interface CartaoMatrizBloqueioRepository extends JpaRepository<CartaoMatrizBloqueio,Integer>{
	public CartaoMatrizBloqueio findByDocente(Docente docente);
}
