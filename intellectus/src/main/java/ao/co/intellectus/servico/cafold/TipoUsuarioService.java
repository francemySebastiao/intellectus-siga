package ao.co.intellectus.servico.cafold;

import org.springframework.stereotype.Service;

import ao.co.intellectus.servico.exception.TipoDeUsuarioNaoEncontradoException;

@Service
public class TipoUsuarioService {

	public String validarTipoUsuario(String tipoUsuario) {
		if (tipoUsuario.equalsIgnoreCase("D"))
			return tipoUsuario;
		else if (tipoUsuario.equalsIgnoreCase("A"))
			return tipoUsuario;
		else
			throw new TipoDeUsuarioNaoEncontradoException("Erro ao validar tipo de Usuário");
	}

}
