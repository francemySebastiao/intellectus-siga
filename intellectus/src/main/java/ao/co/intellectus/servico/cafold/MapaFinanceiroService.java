package ao.co.intellectus.servico.cafold;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.MapaFinanceiro;
import ao.co.intellectus.model.RegistroDocumentos;
import ao.co.intellectus.repository.MapaFinanceiroRepository;
import ao.co.intellectus.repository.RegistroDocumentoRepository;

@Service
public class MapaFinanceiroService {

	@Autowired
	private MapaFinanceiroRepository mapaFinanceiroRepository;
	
	@Autowired
	private RegistroDocumentoRepository registroDocumentoRepository;

	public List<MapaFinanceiro> listarPorAnoEGrau(String grau, Integer ano) {

		return this.mapaFinanceiroRepository.findByGrauAndLectivo(grau, ano);
	}
	
	/*public List<RegistroDocumentos> buscarListagem_1(Date data1, Date data2) {

		return this.registroDocumentoRepository.buscarTodos(data1, data2);
	}

	public List<RegistroDocumentos> buscarListagem_2(Date data1, Date data2) {

		return this.registroDocumentoRepository.Listagem_7(data1, data2);
	}
*/
}
