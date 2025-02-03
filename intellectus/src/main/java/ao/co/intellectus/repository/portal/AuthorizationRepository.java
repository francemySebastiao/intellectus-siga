package ao.co.intellectus.repository.portal;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.portal.Authorization;

public interface AuthorizationRepository extends JpaRepository<Authorization, Integer> {

}
