package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaMultaNegociacao;
import ao.co.intellectus.model.GuiaMultaValor;

public interface GuiaMultaNegociacaoRepository extends CrudRepository<GuiaMultaNegociacao,Integer>{
	public List<GuiaMultaNegociacao> findByNumeroDeAlunoAndAnoLectivo(String numero,AnoLectivo anoLectivo);
	public List<GuiaMultaNegociacao> findByNumeroDeAluno(String numero);
	public List<GuiaMultaNegociacao> findByGuiaMultaValor(GuiaMultaValor guiaMultaValor);
	public List<GuiaMultaNegociacao> findByGuia(Guia guia);
}