package ao.co.intellectus.DTO;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.TipoProvaExtraOrdinaria;

public class AlunoInscricaoExtraordinaria {
	private Integer numero;
	private String nome;
	private String curso;
	private boolean fimCurso;
	private boolean anulado;
	private boolean contencioso;
	private Integer anoLectivo;
	private Integer anoInscricao;
	private List<DisciplinaExtraordinariaCliente> disciplinas;
	private List<DisciplinaExtraordinariaCliente> disciplinasExtraordinarias;
	private List<InscricaoExtraordinariaCliente> inscricoes;
	private List<InscricaoPorAno> matriculas;
	private AnosCorricularesCliente anosCorriculares;
	private Integer anoAtual;
	private boolean epocaEspecial;
	private TipoProvaExtraOrdinaria tipoProvaExtraOrdinaria;
	private Integer usuarioInscreveu;
	@Enumerated(EnumType.STRING)
	private Grau grau;
	private Integer cursoId;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public boolean isFimCurso() {
		return fimCurso;
	}

	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public boolean isContencioso() {
		return contencioso;
	}

	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public List<DisciplinaExtraordinariaCliente> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaExtraordinariaCliente> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<InscricaoExtraordinariaCliente> getInscricoes() {
		return inscricoes;
	}

	public void setInscricoes(List<InscricaoExtraordinariaCliente> inscricoes) {
		this.inscricoes = inscricoes;
	}

	public List<InscricaoPorAno> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<InscricaoPorAno> matriculas) {
		this.matriculas = matriculas;
	}

	public AnosCorricularesCliente getAnosCorriculares() {
		return anosCorriculares;
	}

	public void setAnosCorriculares(AnosCorricularesCliente anosCorriculares) {
		this.anosCorriculares = anosCorriculares;
	}

	public boolean isEpocaEspecial() {
		return epocaEspecial;
	}

	public void setEpocaEspecial(boolean epocaEspecial) {
		this.epocaEspecial = epocaEspecial;
	}

	public Integer getAnoAtual() {
		return anoAtual;
	}

	public void setAnoAtual(Integer anoAtual) {
		this.anoAtual = anoAtual;
	}

	public Integer getUsuarioInscreveu() {
		return usuarioInscreveu;
	}

	public void setUsuarioInscreveu(Integer usuarioInscreveu) {
		this.usuarioInscreveu = usuarioInscreveu;
	}

	public Grau getGrau() {
		return grau;
	}

	public void setGrau(Grau grau) {
		this.grau = grau;
	}

	public Integer getCursoId() {
		return cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

	public List<DisciplinaExtraordinariaCliente> getDisciplinasExtraordinarias() {
		return disciplinasExtraordinarias;
	}

	public void setDisciplinasExtraordinarias(List<DisciplinaExtraordinariaCliente> disciplinasExtraordinarias) {
		this.disciplinasExtraordinarias = disciplinasExtraordinarias;
	}

	public TipoProvaExtraOrdinaria getTipoProvaExtraOrdinaria() {
		return tipoProvaExtraOrdinaria;
	}

	public void setTipoProvaExtraOrdinaria(TipoProvaExtraOrdinaria tipoProvaExtraOrdinaria) {
		this.tipoProvaExtraOrdinaria = tipoProvaExtraOrdinaria;
	}

	public Integer getAnoInscricao() {
		return anoInscricao;
	}

	public void setAnoInscricao(Integer anoInscricao) {
		this.anoInscricao = anoInscricao;
	}
	

}