package ao.co.intellectus.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Docente;
import ao.co.intellectus.model.Usuario;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {
	public List<Docente> findByNomeLike(String nome);
	
	public Docente findByUsuarioDocente(Usuario usuario);
	
	public Docente findByUsuarioDocenteUserCode(Integer userCode);
	
	public Docente findByEmailInstitucional(String email);
	
	public Optional<Docente> findByEmailInstitucionalAndDataDeNascimento(String email, Date data);
	
	public List<Docente> findByUsuarioDocenteOrderByNome(Usuario usuario);
	
	public List<Docente> findByEstadoOrderByNome(boolean estato);
}