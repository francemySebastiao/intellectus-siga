package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.BillingAddress;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, String> {

	@Query(value="SELECT * FROM V_BILLING_ADDRESS WHERE NUMERO_DE_ALUNO =:numeroAluno",nativeQuery=true)
	public List<BillingAddress> buscarBillingAddress(@Param("numeroAluno") String numeroAluno);
}
