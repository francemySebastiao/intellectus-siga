package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_equivalencia_historico_aluno")
public class EquivalenciaHistoricoAluno {
	@Id
	@GeneratedValue
    private Integer id;
    //VEIRIFICA A SITUAÇÃO DA DISCIPLINA NO ALUNO
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    private boolean aprovado;
    //NOTA FINAL
    @Column(nullable=true)
    private Float notaFinal;    
    //TABELAS DE CONEXÃO.
    @ManyToOne 
    @JoinColumn(name="codigo_aluno")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name="codigo_ano_lectivo")
    private AnoLectivo anoLectivo;
    @ManyToOne
    @JoinColumn(name="codigo_disciplina")
    private Disciplina disciplina;
    @ManyToOne
    @JoinColumn(name="codigo_curso")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name="codigo_instituicao")
    private Instituicao instituicao;
    //DADOS CONSOLIDADOS
    private Integer totalDisciplinas;
    private String numeroDeAluno;
    //CONTROLE SOBRE VALIDAÇÕES
    @Column(nullable=true)
    private boolean fechada;
    @Column(nullable=true)
    private boolean validada;
    private String descricao;
    @Column(nullable=true)
    private boolean equivalencia;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public boolean isAprovado() {
		return aprovado;
	}
	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}
	public Float getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(Float notaFinal) {
		this.notaFinal = notaFinal;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Integer getTotalDisciplinas() {
		return totalDisciplinas;
	}
	public void setTotalDisciplinas(Integer totalDisciplinas) {
		this.totalDisciplinas = totalDisciplinas;
	}
	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public boolean isFechada() {
		return fechada;
	}
	public void setFechada(boolean fechada) {
		this.fechada = fechada;
	}
	public boolean isValidada() {
		return validada;
	}
	public void setValidada(boolean validada) {
		this.validada = validada;
	}
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isEquivalencia() {
		return equivalencia;
	}
	public void setEquivalencia(boolean equivalencia) {
		this.equivalencia = equivalencia;
	}
}
