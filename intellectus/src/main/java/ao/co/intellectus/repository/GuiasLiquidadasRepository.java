package ao.co.intellectus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.GuiasLiquidadas;


public interface GuiasLiquidadasRepository extends CrudRepository<GuiasLiquidadas,String> {
	public List<GuiasLiquidadas> findByDataLiquidacaoBetween(Date de, Date para);
}
