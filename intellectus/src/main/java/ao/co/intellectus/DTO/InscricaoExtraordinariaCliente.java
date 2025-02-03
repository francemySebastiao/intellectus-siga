package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.TipoProvaExtraOrdinaria;

public class InscricaoExtraordinariaCliente {
	@Temporal(TemporalType.DATE)
	private Date dataRegistro;
	private Integer anoCorricular;
	private String turma;
    private String instituicao;
	private String nome;
	private Integer numeroDeAluno;
	private String descricao;
	private Integer anoLectivo;
	private String anoLectivoDescricao;
	private boolean guiaPagamento;
	@Enumerated(EnumType.ORDINAL)
	private TipoProvaExtraOrdinaria tipoProva;
	private Float nota;
	private boolean persistido;
	
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public Integer getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public boolean isGuiaPagamento() {
		return guiaPagamento;
	}
	public void setGuiaPagamento(boolean guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}
	public TipoProvaExtraOrdinaria getTipoProva() {
		return tipoProva;
	}
	public void setTipoProva(TipoProvaExtraOrdinaria tipoProva) {
		this.tipoProva = tipoProva;
	}
	public Float getNota() {
		return nota;
	}
	public void setNota(Float nota) {
		this.nota = nota;
	}
	public boolean isPersistido() {
		return persistido;
	}
	public void setPersistido(boolean persistido) {
		this.persistido = persistido;
	}
}
