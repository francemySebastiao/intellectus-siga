/*package ao.co.intellectus.servico.cafold;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.RegistroDocumentos;
import ao.co.intellectus.repository.RegistroDocumentoRepository;

@Service
public class ListagemCertificadosService {

	@Autowired
	private RegistroDocumentoRepository registroDocumentoRepository;

	public List<RegistroDocumentos> listarData(Date data1, Date data2) {

		return this.registroDocumentoRepository.buscarTodos(data1, data2);
	}
}
*/
