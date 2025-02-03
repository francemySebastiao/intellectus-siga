package ao.co.intellectus.repository.portal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.portal.RolePermition;

public interface RolePermitionRepository extends JpaRepository<RolePermition, Integer>{
    public List<RolePermition> findByRoleId(Integer codigo);
}
