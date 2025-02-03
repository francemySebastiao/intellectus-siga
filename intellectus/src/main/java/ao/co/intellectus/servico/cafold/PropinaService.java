package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;

@Service
public class PropinaService {
	
	@Autowired
	private EmolumentoRepository emolumentos;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicos;
	
	public int numeroDePorpinasEmAtraso(AnoLectivo anoLectivo,Aluno aluno) {
		int numeroDePorpinasEmAtraso = 10;
		int numeroDePropinasPagas = 0;
		for(Emolumento emolumento: emolumentos.findByIdBetween(55, 67)) {
			if(historicos.findByAnoLectivoAndAlunoAndEmolumento(anoLectivo, aluno, emolumento)!=null) {
				numeroDePorpinasEmAtraso+=1;
			}
		}
		return (numeroDePorpinasEmAtraso-numeroDePropinasPagas);
	}

}
