package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {

	@Query(value="SELECT * FROM V_Payment WHERE DATA_EMISSAO BETWEEN :data1 AND :data2",nativeQuery=true)
	public List<Payment> buscarPayment(@Param("data1") String data1, @Param("data2") String data2);
}
