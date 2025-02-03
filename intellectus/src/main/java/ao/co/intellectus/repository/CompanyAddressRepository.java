package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ao.co.intellectus.model.CompanyAddress;

public interface CompanyAddressRepository extends JpaRepository<CompanyAddress, String> {
	
	@Query(value="SELECT * FROM V_COMPANY_ADDRESS",nativeQuery=true)
	public CompanyAddress buscarCompanyAddress();

}
