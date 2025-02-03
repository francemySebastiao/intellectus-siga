package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.alerta.Sms;

public interface SmsRepository extends JpaRepository<Sms, Long>{
	public List<Sms> findByEnviado(boolean a);
}
