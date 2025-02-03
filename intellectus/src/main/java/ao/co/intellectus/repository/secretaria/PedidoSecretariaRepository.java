package ao.co.intellectus.repository.secretaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.secretaria.PedidoSecretaria;

public interface PedidoSecretariaRepository extends JpaRepository<PedidoSecretaria, Integer> {



	List<PedidoSecretaria> findByGuiaGerada(boolean b);

}
