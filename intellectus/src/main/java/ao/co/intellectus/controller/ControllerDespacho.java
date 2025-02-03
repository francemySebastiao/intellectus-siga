package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.ControleExtraordinarioCliente;
import ao.co.intellectus.DTO.DespachoDisciplina;
import ao.co.intellectus.DTO.DisciplinaDespacho;
import ao.co.intellectus.DTO.DisciplinaMatricula;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.ControloProvaExtraordianaria;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.ControloProvaExtraordinariaRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.util.DisciplinasAprovadas;

@RestController
@RequestMapping("/despacho")
public class ControllerDespacho {

	@Autowired
	private HistoricoAlunoRepository repositoryHistoricoAluno;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private ControloProvaExtraordinariaRepository despachoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private UsuarioRepository usuariosRepository;
	@Autowired
	private EquivalenciaHistoricoRepository equivalenciaHistoricoRepository;

	/*
	 * DESENVOLVEDOR: FRANCISCO LOURENÇO
	 * DATA: 10-07-2019
	 * REALIZAR UM DESPACHO PARA INSCRIÇÃO EM ÉPOCA EXTRAORDINARIA.
	 */
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/buscarHistorico/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarAlunoAndHistorico(@PathVariable String numero) {

		
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);

		
		if (aluno == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(aluno, 2, "Nenhum registro encontrado.");
		} else {
			
			DespachoDisciplina despacho = new DespachoDisciplina();
			
			
			despacho.setContencioso(aluno.isContencioso());
			despacho.setCurso(aluno.getCurso().getPlano());
			despacho.setFimCurso(aluno.isFimCurso());
			despacho.setGrau(aluno.getCurso().getGrau().getDescricao());
			despacho.setInativo(aluno.isInactivo());
			despacho.setIdAluno(aluno.getId());
			despacho.setNome(aluno.getNome());
			despacho.setNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
			
		
			if (aluno.isFimCurso()) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(despacho, 2, "Finalizou o curso.");
			} else if (aluno.isInactivo()) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(despacho, 2, "Está inactivo.");
			} else {

				List<DisciplinaMatricula> disciplinas = new ArrayList<DisciplinaMatricula>();
				DisciplinaMatricula disciplina;
				List<HistoricoAluno> pesquisar = DisciplinasAprovadas.pesquisar(aluno,false,this.repositoryHistoricoAluno,equivalenciaHistoricoRepository);
				for (HistoricoAluno dadosDaDisciplina : pesquisar) {
					//System.err.println("CODIGO DO HISTORICO: "+dadosDaDisciplina.getId());
					if(dadosDaDisciplina.getDisciplina()!=null) {
						disciplina = new DisciplinaMatricula();
						disciplina.setId(dadosDaDisciplina.getDisciplina().getId());
						disciplina.setDescricao(dadosDaDisciplina.getDisciplina().getDescricao());
						disciplina.setTipoDisciplina(dadosDaDisciplina.getDisciplina().getTipo());
						disciplina.setAnoCorricular(dadosDaDisciplina.getAnoCorricular());
						disciplina.setAnoLectivo(dadosDaDisciplina.getAnoLectivo().getAnoLectivo());
						disciplina.setAnoLectivoDescricao(dadosDaDisciplina.getAnoLectivo().getAnoLectivoDescricao());
						disciplina.setConcluida(dadosDaDisciplina.isFechada());
						disciplina.setAprovado(dadosDaDisciplina.isAprovado());
						disciplina.setNota(String.valueOf(dadosDaDisciplina.getNotaFinal()));
						disciplinas.add(disciplina);						
					}
				}
				despacho.setDisciplinas(disciplinas);
				return ObjectoDeRetornoParcial.MensagemDeRetorno(despacho, 0, null);	
			}	
		}
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody ControleExtraordinarioCliente despacho) {
		

		Aluno aluno     = this.alunoRepository.findByNumeroDeAluno(String.valueOf(despacho.getNumeroDeAluno()));
		Usuario usuario = this.usuariosRepository.findByUserCode(despacho.getUserCode());
		Disciplina disciplina;
		AnoLectivo anoLectivo;
		List<DisciplinaDespacho> disciplinas = despacho.getDisciplinas();


		ControloProvaExtraordianaria novoDespacho;
		for (DisciplinaDespacho o : disciplinas) {
			
			
			
            novoDespacho = new ControloProvaExtraordianaria();
			anoLectivo = this.anoLectivoRepository.findByAnoLectivo(o.getAnoLectivo());
			
			disciplina = this.disciplinaRepository.findOne(o.getId());
			System.out.println("Disci " + disciplina);

			novoDespacho.setAluno(aluno);
			novoDespacho.setNumeroDeAluno(Integer.valueOf(aluno.getNumeroDeAluno()));
			novoDespacho.setUsuario(usuario);
			novoDespacho.setDisciplina(disciplina);
			novoDespacho.setAnoLectivo(anoLectivo);
			novoDespacho.setNota(o.getNota());
			System.out.println("Te peguei " + novoDespacho);
			this.despachoRepository.save(novoDespacho);
		}
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Despacho emitido com sucesso.");
	}
}
