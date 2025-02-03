package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Situacao;
import ao.co.intellectus.model.TipoDisciplina;
public class HistoricoAlunoCliente {
	private Integer codigoDisciplina;
    private int anoCorricular;
    private boolean extraordinaria;
    private int idUsuarioInscricao;
    private Date dataInscricao;
    private boolean aprovado;
    @Enumerated(EnumType.STRING)
    private Situacao situacao; 
    private boolean anulado;
    private Date dataAnulacao;
    private Integer anoLectivo;
    private String disciplina;
    //AVALIAÇÕES
    private Float avaliacao1;
    private Float avaliacao2;     
    private Float trabalho1;
    private Float trabalho2;
    private Float notaExame;
    private Float notaExameOral;
    private Float notaRecurso;
    private Float notaRecursoOral;
    private Float melhoriaNota;
    private Float notaFinal; 
    private Date dataNotaFinal;
    private Float notaEpocaEspecial;
    private Float notaEpocaEspecialOral;
    private String anoLectivoDescricao;
    //private String aluno;
    private String turma;
    @Enumerated(EnumType.STRING)
    private TipoDisciplina tipoDisciplina;
    
    
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public int getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(int anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
	public boolean isExtraordinaria() {
		return extraordinaria;
	}
	public void setExtraordinaria(boolean extraordinaria) {
		this.extraordinaria = extraordinaria;
	}
	public int getIdUsuarioInscricao() {
		return idUsuarioInscricao;
	}
	public void setIdUsuarioInscricao(int idUsuarioInscricao) {
		this.idUsuarioInscricao = idUsuarioInscricao;
	}
	public Date getDataInscricao() {
		return dataInscricao;
	}
	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	public boolean isAprovado() {
		return aprovado;
	}
	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	public Date getDataAnulacao() {
		return dataAnulacao;
	}
	public void setDataAnulacao(Date dataAnulacao) {
		this.dataAnulacao = dataAnulacao;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	
	public Float getAvaliacao1() {
		return avaliacao1;
	}
	public void setAvaliacao1(Float avaliacao1) {
		this.avaliacao1 = avaliacao1;
	}
	public Float getAvaliacao2() {
		return avaliacao2;
	}
	public void setAvaliacao2(Float avaliacao2) {
		this.avaliacao2 = avaliacao2;
	}
	public Float getTrabalho1() {
		return trabalho1;
	}
	public void setTrabalho1(Float trabalho1) {
		this.trabalho1 = trabalho1;
	}
	public Float getTrabalho2() {
		return trabalho2;
	}
	public void setTrabalho2(Float trabalho2) {
		this.trabalho2 = trabalho2;
	}
	public Float getNotaExame() {
		return notaExame;
	}
	public void setNotaExame(Float notaExame) {
		this.notaExame = notaExame;
	}
	public Float getNotaExameOral() {
		return notaExameOral;
	}
	public void setNotaExameOral(Float notaExameOral) {
		this.notaExameOral = notaExameOral;
	}
	public Float getNotaRecurso() {
		return notaRecurso;
	}
	public void setNotaRecurso(Float notaRecurso) {
		this.notaRecurso = notaRecurso;
	}
	public Float getNotaRecursoOral() {
		return notaRecursoOral;
	}
	public void setNotaRecursoOral(Float notaRecursoOral) {
		this.notaRecursoOral = notaRecursoOral;
	}
	public Float getMelhoriaNota() {
		return melhoriaNota;
	}
	public void setMelhoriaNota(Float melhoriaNota) {
		this.melhoriaNota = melhoriaNota;
	}
	public Float getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(Float notaFinal) {
		this.notaFinal = notaFinal;
	}
	public Date getDataNotaFinal() {
		return dataNotaFinal;
	}
	public void setDataNotaFinal(Date dataNotaFinal) {
		this.dataNotaFinal = dataNotaFinal;
	}
	public Float getNotaEpocaEspecial() {
		return notaEpocaEspecial;
	}
	public void setNotaEpocaEspecial(Float notaEpocaEspecial) {
		this.notaEpocaEspecial = notaEpocaEspecial;
	}
	public Float getNotaEpocaEspecialOral() {
		return notaEpocaEspecialOral;
	}
	public void setNotaEpocaEspecialOral(Float notaEpocaEspecialOral) {
		this.notaEpocaEspecialOral = notaEpocaEspecialOral;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public TipoDisciplina getTipoDisciplina() {
		return tipoDisciplina;
	}
	public void setTipoDisciplina(TipoDisciplina tipoDisciplina) {
		this.tipoDisciplina = tipoDisciplina;
	}
	public Integer getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(Integer codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	
}