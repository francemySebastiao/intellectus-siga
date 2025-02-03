package ao.co.intellectus.repository.exame_electronico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.exame_eletronico.MensagemCandidato;

public interface MensagemCandidatoRepository extends JpaRepository<MensagemCandidato, Integer>{
	
	public List<MensagemCandidato> findByEnviadoAndIdentificador(boolean enviado,String identificador);

}
