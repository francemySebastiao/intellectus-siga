package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoPedido;
import ao.co.intellectus.model.TipoDeDeclaracao;

public interface EmolumentoPedidoRepository extends CrudRepository<EmolumentoPedido,Integer>{
	public EmolumentoPedido findByTipoDeDeclaracaoAndEmolumento(TipoDeDeclaracao tipoDeclaracao,Emolumento emolumento);
	public EmolumentoPedido findByTipoDeDeclaracao(TipoDeDeclaracao tipoDeclaracao);
	
	//public EmolumentoPedido findByEmolumento(Emolumento emolumento);
	public EmolumentoPedido findByEmolumentoCodigo(Integer integer);
	
}
