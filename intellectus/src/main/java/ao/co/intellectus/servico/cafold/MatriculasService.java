package ao.co.intellectus.servico.cafold;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.AnexoCliente;
import ao.co.intellectus.DTO.EmailCliente;
import ao.co.intellectus.DTO.PublicoAlvo;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Faculdade;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.MatriculaAudit;
import ao.co.intellectus.model.secretaria.RegressoEstatistica;
import ao.co.intellectus.repository.FaculdadeRepository;
import ao.co.intellectus.repository.MatriculaAuditRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.secretaria.RegressoEstatisticaRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class MatriculasService {

	@Autowired
	private MatriculaRepository matriculas;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private MatriculaAuditRepository matriculaAuditoria;
	@Autowired
	private AnoLectivoService anoLectivoService;
	@Autowired
	private CursoService cursoService;
	@Autowired
	private ConexaoService conexaoService;
	@Autowired
	private FaculdadeRepository faculdadeRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private RegressoEstatisticaRepository regressoEstatisticaRepository;
	private static List<Matricula> matriculasVerificadas = null;

	public List<Matricula> todasMatriculasDeUmAluno(String numeroDoAluno) {
		List<Matricula> matriculasDoAluno = new ArrayList<Matricula>();
		Aluno aluno = (numeroDoAluno == null) ? null : this.alunoService.aluno(numeroDoAluno);
		matriculasDoAluno = this.matriculas.findByAluno(aluno);
		if(matriculasDoAluno.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum registo de Matrícula encontrado.");
		return matriculasDoAluno;
	}

	public void gerarHistorico(Matricula matricula) {
		MatriculaAudit matriculaAudit = new MatriculaAudit();
		BeanUtils.copyProperties(matricula, matriculaAudit);
		this.matriculaAuditoria.save(matriculaAudit);
	}
	
	public List<Matricula> matriculas(AnoLectivo anoLectivo, Curso curso){
		List<Matricula> matriculas = this.matriculas.findByAnoLectivoAndCurso(anoLectivo,curso);
		if(matriculas.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de matrículas não encontradas.");
		return matriculas;
	}
	
	public List<Matricula> matriculas(AnoLectivo anoLectivo, Curso curso, Date dataInicial, Date dataFim){
		List<Matricula> matriculas = this.matriculas.findByAnoLectivoAndCurso(anoLectivo,curso);
		if(matriculas.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de matrículas não encontradas.");
		return matriculas;
	}
	
	public List<Aluno> alunos(PublicoAlvo publico){
		AnoLectivo anoLectivo = this.anoLectivoService.anoLectivo(publico.getAnoLectivo());
		Curso curso = this.cursoService.curso(publico.getCurso());
		List<Aluno> alunos = new ArrayList<Aluno>();
		for(Matricula matricula : this.matriculas(anoLectivo, curso)) {
			if(publico.getGrauAcademico() == matricula.getCurso().getGrau()) {
				alunos.add(matricula.getAluno());
			}
		}
		return alunos;
	}
	
	public List<Aluno> alunosPorData(PublicoAlvo publico){
		AnoLectivo anoLectivo = this.anoLectivoService.anoLectivo(publico.getAnoLectivo());
		Curso curso = this.cursoService.curso(publico.getCurso());
		List<Aluno> alunos = new ArrayList<Aluno>();
		for(Matricula matricula : this.matriculas(anoLectivo, curso)) {
			if(publico.getGrauAcademico() == matricula.getCurso().getGrau()) {
				alunos.add(matricula.getAluno());
			}
		}
		return alunos;
	}
	
	
	public void enviarRelatorioInscricaoOnline() throws ClassNotFoundException, JRException, SQLException {
		EmailCliente emailCliente = new EmailCliente();
		matriculasVerificadas = new ArrayList<Matricula>();
		emailCliente.setMensagem("Saudações prezado(a), em anexo está o relatório dos estudantes que fizeram matrícula online recentemente.");
		String[] bCC = {"francisco.lourenco@intellectus.co.ao","ernesto.sambongo@intellectus.co.ao", "marcio.coelho@intellectus.co.ao"};
	    for(Faculdade faculdade: this.faculdadeRepository.findAll()) {
	    	List<Curso> cursos = this.cursoService.cursos(faculdade);
	    	if((this.matriculas(cursos) > 0) && (faculdade.getFaculdade()!= null)) {
	    		emailCliente.setAssunto("Relatório de Matrícula Online - " + faculdade.getFaculdade());
	    		byte[] inscricaoOnline = this.inscricaoOnline(faculdade.getFaculdade());
	    		String[] destinatario = {faculdade.getEmail()};
	    		emailCliente.setDestinatario(destinatario);
	    		emailCliente.setbCC(bCC);
	    		emailCliente.setAnexo(new AnexoCliente("MATRICULA-ONLINE", inscricaoOnline, ".pdf"));
	    		this.emailService.enviar(emailCliente);	
	    	}
	    }
	    this.validarMatriculasVerificadas(matriculasVerificadas);
	}
	
	public void enviarRelatorioRegresso() {
		List<RegressoEstatistica>  todos = this.regressoEstatisticaRepository.findAll();
		for(RegressoEstatistica regressoEstatistica: todos) {
			if(regressoEstatistica.getEnviada() == false) {
				EmailCliente emailCliente = new EmailCliente();
				emailCliente.setMensagem("Saudações Prezado(a)\n" + 
										 "Até ao momento, "+regressoEstatistica.getTotal()+" estudantes já confirmaram o regresso as aulas.");
				emailCliente.setAssunto("Relatório de regresso as aulas");
				String[] destinatario = {"vladmir@ugs.ed.ao"};
				String[] bCC = {"francisco.lourenco@intellectus.co.ao","ernesto.sambongo@intellectus.co.ao", "marcio.coelho@intellectus.co.ao"};
				emailCliente.setDestinatario(destinatario);
				emailCliente.setbCC(bCC);
				regressoEstatistica.setEnviada(true);
				this.regressoEstatisticaRepository.save(regressoEstatistica);
				this.emailService.enviar(emailCliente);	
			}
		}
	}

	private Integer matriculas(List<Curso> cursos){
		Integer matriculasEncontrada = 0;
		for(Curso curso: cursos) {
			List<Matricula> matriculas = this.matriculas.findByCursoAndInscritoOnlineAndVerificado(curso,true,false);
			if(matriculas.size() > 0) {
				matriculasVerificadas.addAll(matriculas);
				matriculasEncontrada += matriculas.size();
			}
		}
		return matriculasEncontrada;		
	}
	
	private void validarMatriculasVerificadas(List<Matricula> matriculas) {
		for(Matricula matricula: matriculas) {
			matricula.setVerificado(true);
			this.matriculas.save(matricula);
		}
	}
	
	private byte[] inscricaoOnline(String faculdade) throws JRException, ClassNotFoundException, SQLException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("faculdade", faculdade);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/academico/R_Alunos_Inscritos_Online.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conexaoService.getConexaoActual());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
