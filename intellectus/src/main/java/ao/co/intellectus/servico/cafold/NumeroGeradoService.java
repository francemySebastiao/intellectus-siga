package ao.co.intellectus.servico.cafold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class NumeroGeradoService {
	
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;

	public NumeroGerado numeroGerado(Integer id) {
		NumeroGerado numeroGerado = this.numeroGeradoRepository.getOne(id);
		if (numeroGerado != null)
			return numeroGerado;
		throw new RegistoNaoEncontradoException("Erro ao validar registos.");
	}
	
	public void salvar(NumeroGerado numeroGerado) {
		this.numeroGeradoRepository.save(numeroGerado);
	}
	
	public void salvar(NumeroGerado numeroGerado,Integer proximoNumeroInteiro) {
		String geradoString = proximoNumeroInteiro.toString();
		Long geradoLong = Long.parseLong(geradoString);
		numeroGerado.setUltimoNumero(geradoLong);
		numeroGerado.setProximoNumero(geradoLong + 1);
		this.numeroGeradoRepository.save(numeroGerado);
	}
	
	
	
}
