package ao.co.intellectus.servico.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import ao.co.intellectus.DTO.AlunoInscricaoExtraordinaria;
import ao.co.intellectus.DTO.AnosCorricularesCliente;
import ao.co.intellectus.DTO.DisciplinaExtraordinariaCliente;
import ao.co.intellectus.DTO.InscricaoExtraordinariaCliente;
import ao.co.intellectus.DTO.InscricaoPorAno;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.ControloProvaExtraordianaria;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.EpocaEspecial;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.InscricaoExtraordinaria;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.Situacao;
import ao.co.intellectus.model.reponse.ObjectoDeRetorno;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.ControloProvaExtraordinariaRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EpocaEspecialRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.InscricaoExtraordinariaRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.servico.InscricaoExtraOrdinariaService;

@Component
public class InscricaoExtraOrdinariaServiceImpl implements InscricaoExtraOrdinariaService{
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private HistoricoAlunoRepository historicoAluno;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private InscricaoExtraordinariaRepository inscricaoExtraOrdinaria;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private EpocaEspecialRepository epocaEspecialRepository;
	@Autowired
	private ControloProvaExtraordinariaRepository controloProvaExtraordinariaRepository;	

	@Override
	public ResponseEntity<ResponseCliente> salvar(AlunoInscricaoExtraordinaria efetuarMatricula) {
		// TODO RESOLVER A QUESTÃO DA REFATORAÇÃO DO PROJECTO
	return null;
	}

	@Override
	public ResponseEntity<ResponseCliente> buscaAlunoDisciplina(String numero) {
		

		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
		if (aluno == null) {
			return ObjectoDeRetorno.MensagemDeRetorno(false, 0, ResponseCode.REGRA_NEGOCIO.getDescricao(), "Operação realizada com sucesso!");
		}
		
		EpocaEspecial epocaEspecial = this.epocaEspecialRepository.findByNumeroAluno(Integer.parseInt(numero));

		AlunoInscricaoExtraordinaria alunoDisciplina = new AlunoInscricaoExtraordinaria();
		alunoDisciplina.setNumero(Integer.parseInt(aluno.getNumeroDeAluno()));
		alunoDisciplina.setNome(aluno.getNome());
		alunoDisciplina.setCurso(aluno.getCurso().getPlano());
		alunoDisciplina.setFimCurso(aluno.isFimCurso());
		alunoDisciplina.setContencioso(aluno.isContencioso());
		alunoDisciplina.setGrau(aluno.getCurso().getGrau());
		alunoDisciplina.setCursoId(aluno.getCurso().getId());

		// AlunoInscricaoExtraordinaria
		// INSCRIÇÕES EXTRAORDINÁRIAS
		List<InscricaoExtraordinaria> inscricoes = this.inscricaoExtraOrdinaria.findByAluno(aluno);
		List<InscricaoExtraordinariaCliente> iedcS = new ArrayList<InscricaoExtraordinariaCliente>();
		InscricaoExtraordinariaCliente iedc;
		for (InscricaoExtraordinaria iExtra : inscricoes) {
			iedc = new InscricaoExtraordinariaCliente();
			iedc.setAnoLectivo(iExtra.getAnoLectivo().getAnoLectivo());
			iedc.setAnoLectivoDescricao(iExtra.getAnoLectivo().getAnoLectivoDescricao());
			iedc.setDataRegistro(iExtra.getDataRegistro());
			iedc.setDescricao(iExtra.getDisciplina().getDescricao());
			if (iExtra.getInstituicao() != null)
				iedc.setInstituicao(iExtra.getInstituicao().getDescricao());
			iedc.setNome(iExtra.getAluno().getNome());
			iedc.setNumeroDeAluno(Integer.parseInt(iExtra.getAluno().getNumeroDeAluno()));
			iedc.setTipoProva(iExtra.getTipoProvaExtraOrdinaria());
			iedc.setAnoCorricular(iExtra.getDisciplina().getAnoCorricular());
			if (iExtra.getTurma() != null)
				iedc.setTurma(iExtra.getTurma().getTurma());
			iedc.setNota(iExtra.getNota());
			iedc.setPersistido(true);
			
			iedcS.add(iedc);
		}
		alunoDisciplina.setInscricoes(iedcS);

		InscricaoPorAno inscricaoPorAno;
		List<InscricaoPorAno> inscricaosPorAno = new ArrayList<InscricaoPorAno>();
		// MATRICULAS FEITAS EM TEMPO DE FACULDADE.
		List<Matricula> matriculas = this.matriculaRepository.findByAlunoAndAnulado(aluno, false);
		for (Matricula matricula : matriculas) {
			inscricaoPorAno = new InscricaoPorAno();

			inscricaoPorAno.setId(matricula.getId());
			inscricaoPorAno.setAnoCurricular(matricula.getAnoCurricular());
			inscricaoPorAno.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
			inscricaoPorAno.setAnoLectivoDescricao(matricula.getAnoLectivo().getAnoLectivoDescricao());
			inscricaoPorAno.setAnoLectivoId(matricula.getAnoLectivo().getId());

			inscricaoPorAno.setAnulado(matricula.isAnulado());
			inscricaoPorAno.setCurso(matricula.getCurso().getDescricao());
			inscricaoPorAno.setSigla(matricula.getCurso().getSigla());
			inscricaoPorAno.setDataAnulamento(matricula.getDataAnulamento());
			inscricaoPorAno.setDataInscricao(matricula.getData());
			inscricaoPorAno.setTipoInscricao(matricula.getTipoInscricao().getDescricao());

			if (matricula.getTurmaBase() != null) {
				inscricaoPorAno.setTurma(matricula.getTurmaBase().getTurma());
				inscricaoPorAno.setTurmaId(matricula.getTurmaBase().getId());
			}

			inscricaosPorAno.add(inscricaoPorAno);
		}
		alunoDisciplina.setMatriculas(inscricaosPorAno);

		// BUSCAR TODAS AS DISCIPLINAS QUE ALUNO JÁ TEVE.
		List<HistoricoAluno> historico = this.historicoAluno.findByAlunoAndCursoOrderByAnoCorricular(aluno,
				aluno.getCurso());
		List<DisciplinaExtraordinariaCliente> disciplinas = new ArrayList<DisciplinaExtraordinariaCliente>();
		DisciplinaExtraordinariaCliente disciplina;
		AnosCorricularesCliente anosCorriculares = new AnosCorricularesCliente();

		for (HistoricoAluno historicoAluno : historico) {
			disciplina = new DisciplinaExtraordinariaCliente();

			disciplina.setAnoCorricular(historicoAluno.getDisciplina().getAnoCorricular());
			disciplina.setDescricao(historicoAluno.getDisciplina().getDescricao());
			disciplina.setId(historicoAluno.getDisciplina().getId());
			disciplina.setTipo(historicoAluno.getDisciplina().getTipo().getDescricao());
			disciplina.setData(new Date());
			// disciplina.setTipoProva(tipoProva);
			disciplina.setIdMatricula(historicoAluno.getMatricula().getId());

			disciplina.setSituacao(historicoAluno.getSituacao());
			disciplina.setAnoLectivo(historicoAluno.getAnoLectivo().getAnoLectivo());
			disciplina.setAnoLectivoDescricao(historicoAluno.getAnoLectivo().getAnoLectivoDescricao());

			disciplina.setNotaFinal(historicoAluno.getNotaFinal());

			// VERIFICA TODAS AS DISCIPLINAS QUE PASSOU...
			if (historicoAluno.getAnoCorricular() == 1 && historicoAluno.isAprovado())
				anosCorriculares.setPrimeiro(anosCorriculares.getPrimeiro() + 1);
			if (historicoAluno.getAnoCorricular() == 2 && historicoAluno.isAprovado())
				anosCorriculares.setSegundo(anosCorriculares.getSegundo() + 1);
			if (historicoAluno.getAnoCorricular() == 3 && historicoAluno.isAprovado())
				anosCorriculares.setTerceiro(anosCorriculares.getTerceiro() + 1);
			if (historicoAluno.getAnoCorricular() == 4 && historicoAluno.isAprovado())
				anosCorriculares.setQuarto(anosCorriculares.getQuarto() + 1);

			if (historicoAluno.getAnoLectivo().getAnoLectivo() == anoLectivo.get(0).getAnoLectivo())
				disciplina.setMatriculado(true);
			if (!aluno.isFimCurso())
				disciplinas.add(disciplina);
		}

		// BUSCA TODAS AS DISCIPLINAS DO CURSO
		List<Disciplina> dsCurso = this.disciplinaRepository.findByCursoAndStatusOrderByAnoCorricularAsc(aluno.getCurso(), true);

		int c1 = 0;
		int c2 = 0;
		int c3 = 0;
		int c4 = 0;
		for (Disciplina ds : dsCurso) {
			if (ds.getAnoCorricular() == 1)
				c1 += 1;
			if (ds.getAnoCorricular() == 2)
				c2 += 1;
			if (ds.getAnoCorricular() == 3)
				c3 += 1;
			if (ds.getAnoCorricular() == 4)
				c4 += 1;
		}

		// VERIFICA SE PODE PASSAR PARA O OUTRO ANO (PRECISAMOS DE UMA PARÁMETRO PARA
		// MAPEAR COM QUANTAS CADEIRA SE PASSA PARA O OUTRO ANO)
		boolean p1 = c1 - anosCorriculares.getPrimeiro() <= 3 ? true : false;
		boolean p2 = c2 - anosCorriculares.getSegundo() <= 3 ? true : false;
		boolean p3 = c3 - anosCorriculares.getTerceiro() <= 3 ? true : false;
		boolean p4 = c4 - anosCorriculares.getQuarto() <= 3 ? true : false;

		// VERIFICA O A QUANTIDADE TOTAL DE DISCIPLINAS EM ABERTO.
		int cc1 = c1 - anosCorriculares.getPrimeiro();
		int cc2 = c2 - anosCorriculares.getSegundo();
		int cc3 = c3 - anosCorriculares.getTerceiro();
		int cc4 = c4 - anosCorriculares.getQuarto();

		int ccTotal = cc1 + cc2 + cc3 + cc4;

		// APLICA DE ANTMÃO O PRIMEIRO ANO E REVALIDA OS ANOS APOSTERIOR CASO FOR O
		// CASO.
		alunoDisciplina.setAnoAtual(1);
		if (p1) {
			alunoDisciplina.setAnoAtual(2);
			if (p2) {
				alunoDisciplina.setAnoAtual(3);
				if (p3) {
					if (ccTotal <= 3)
						alunoDisciplina.setEpocaEspecial(true);
					alunoDisciplina.setAnoAtual(4);
					if (p4) {
						alunoDisciplina.setAnoAtual(4);
					}
				}
			}
		}

		// SE O ALUNO JÁ TEM DIM CURSO, RETIRO A QUALIDADE EPOCA ESPECIAL E SEPTO O ANO
		// CORRICULAR 4
		if (aluno.isFimCurso()) {
			alunoDisciplina.setAnoAtual(4);
			alunoDisciplina.setEpocaEspecial(false);
		}
		
		
		alunoDisciplina.setEpocaEspecial(epocaEspecial != null ? true : false);

		
		List<ControloProvaExtraordianaria> extraOrinario=null;
		if(epocaEspecial == null) {
			extraOrinario = this.controloProvaExtraordinariaRepository.findByNumeroDeAluno(Integer.parseInt(numero));	
		}
		if(extraOrinario!=null) {
			alunoDisciplina.setEpocaEspecial(extraOrinario != null ? true : false);
		}
		
		List<DisciplinaExtraordinariaCliente> dsOrinarias = new ArrayList<DisciplinaExtraordinariaCliente>();
		DisciplinaExtraordinariaCliente dOrinarias;
		
		extraOrinario = this.controloProvaExtraordinariaRepository.findByNumeroDeAluno(Integer.parseInt(numero));	
		for (ControloProvaExtraordianaria o : extraOrinario) {
			dOrinarias=new DisciplinaExtraordinariaCliente();
			dOrinarias.setAnoCorricular(o.getDisciplina().getAnoCorricular());
			dOrinarias.setDescricao(o.getDisciplina().getDescricao());
			dOrinarias.setId(o.getDisciplina().getId());
			dOrinarias.setTipo(o.getDisciplina().getTipo().getDescricao());
			dOrinarias.setData(new Date());
			dOrinarias.setSituacao(Situacao.APROVADO);
			dOrinarias.setAnoLectivo(o.getAnoLectivo().getAnoLectivo());
			dOrinarias.setAnoLectivoDescricao(o.getAnoLectivo().getAnoLectivoDescricao());
			dOrinarias.setNotaFinal(o.getNota());
			dsOrinarias.add(dOrinarias);
		}
		alunoDisciplina.setAnosCorriculares(anosCorriculares);
		alunoDisciplina.setInscricoes(iedcS);
		alunoDisciplina.setDisciplinas(disciplinas);
		alunoDisciplina.setDisciplinasExtraordinarias(dsOrinarias);
		return ObjectoDeRetorno.MensagemDeRetorno(alunoDisciplina, 0, ResponseCode.SUCESSO.getDescricao(), "Operação realizada com sucesso!");
	}
}
