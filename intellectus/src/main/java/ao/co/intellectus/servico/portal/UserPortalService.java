package ao.co.intellectus.servico.portal;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.portal.UserPortalView;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.repository.portal.UserRepository;
import ao.co.intellectus.servico.cafold.DataService;
import ao.co.intellectus.servico.cafold.InstituicaoService;
import ao.co.intellectus.servico.exception.DadoInvalidoException;
import ao.co.intellectus.servico.exception.DadosDuplicadoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class UserPortalService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private InstituicaoService instituicaoService;
	@Autowired
	private DataService dataService;

	public void salvar(User user) {
		if (this.dadosInvaldiados(user))
			throw new DadosDuplicadoException("Já existe um usuário com este username ou usercode.");
		user.setInstCode(this.codigoInstituicao());
		this.userRepository.save(user);
	}
	
	public void actualizar(User user) {
		if((user.getId() == null) || (this.user(user.getId()) == null)) {
			throw new RegistoNaoEncontradoException("Registo de usuário não encontrado.");
		}else {
			this.salvar(user);	
		}
	}
	
	public void refinirSenha(User user) {
		this.userRepository.save(user);
	}

	public List<User> pesquisar(UserPortalView user) {
		List<User> users = userRepository.findByNameOrUserCodeOrUserName(user.getName(), user.getUserCode(),user.getUserName());
		if (users.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de usuário não encontrado.");
		return users;
	}

	public User user(Integer id) {
		User user = this.userRepository.findOne(id);
		if (user == null)
			throw new RegistoNaoEncontradoException("Registo de usuário não encontrado.");
		return user;
	}

	public User mudarEstado(User user) {
		user.setActive(!user.isActive());
		return this.userRepository.save(user);
	}
	
	private boolean dadosInvaldiados(User u) {
		User user = this.userRepository.findByIdNotAndUserCodeOrUserName(u.getId(), u.getUserCode(), u.getUserName());
		if (user.getName().equalsIgnoreCase(u.getName()))
			throw new DadosDuplicadoException("O usuário introduzido já existe.");
		else if (user.getUserCode().equalsIgnoreCase(u.getUserCode()))
			throw new DadosDuplicadoException("Já existe um usuário com código.");
		return user.getName().equalsIgnoreCase(u.getName()) || user.getUserCode().equalsIgnoreCase(u.getUserCode());
	}

	private String codigoInstituicao() {
		Instituicao instituicao = this.instituicaoService.instituicao(2);
		return instituicao.getCode();
	}

	@SuppressWarnings("unused")
	private boolean validarDatas(Date dataInicial, Date dataFinal) {
		if(this.dataService.diferenciaDeDias(dataInicial) < 0)
			throw new DadoInvalidoException("A data inicial introduzida não é valida.");
		else if(this.dataService.validarDataFinal(dataInicial, dataInicial))
			throw new DadoInvalidoException("A data final não pode ser inferior a inicial.");
		return this.dataService.diferenciaDeDias(dataInicial) < 0 || this.dataService.validarDataFinal(dataInicial, dataInicial);	
	}

}
