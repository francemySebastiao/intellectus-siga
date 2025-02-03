package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_inscricao_extraordinaria")
public class InscricaoExtraordinaria {
	 @Id
	 @GeneratedValue
     private Integer id;
	 @Temporal(TemporalType.DATE)
	 private Date dataRegistro;
     private String nomeroDeAluno;
	 @ManyToOne
	 @JoinColumn(name="codigo_instituicao")
     private Instituicao instituicao;
	 @ManyToOne
	 @JoinColumn(name="codigo_aluno")
	 private Aluno aluno;
	 
	 @ManyToOne
	 @JoinColumn(name="codigo_disciplina")
	 private Disciplina disciplina;
	 
	 @ManyToOne
	 @JoinColumn(name="codigo_Ano_lectivo")
	 private AnoLectivo anoLectivo;

	 @ManyToOne
	 @JoinColumn(name="codigo_guia_pagamento")
	 private Guia guiaPagamento;
	 
	 @Enumerated(EnumType.ORDINAL)
	 private TipoProvaExtraOrdinaria tipoProvaExtraOrdinaria;
	 
	 @Enumerated(EnumType.STRING)
	 private TipoProvaExtraOrdinaria prova; 
	 
	 @ManyToOne
	 @JoinColumn(name="codigo_turma")
	 private Turma turma;

	 @ManyToOne
	 @JoinColumn(name="codigo_usuario_inscrveu")
	 private Usuario usuarioInscreveu;
	 
	 private Float nota;
	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getNomeroDeAluno() {
		return nomeroDeAluno;
	}

	public void setNomeroDeAluno(String nomeroDeAluno) {
		this.nomeroDeAluno = nomeroDeAluno;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public TipoProvaExtraOrdinaria getTipoProvaExtraOrdinaria() {
		return tipoProvaExtraOrdinaria;
	}

	public void setTipoProvaExtraOrdinaria(TipoProvaExtraOrdinaria tipoProvaExtraOrdinaria) {
		this.tipoProvaExtraOrdinaria = tipoProvaExtraOrdinaria;
	}

	public Guia getGuiaPagamento() {
		return guiaPagamento;
	}

	public void setGuiaPagamento(Guia guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Usuario getUsuarioInscreveu() {
		return usuarioInscreveu;
	}

	public void setUsuarioInscreveu(Usuario usuarioInscreveu) {
		this.usuarioInscreveu = usuarioInscreveu;
	}

	public TipoProvaExtraOrdinaria getProva() {
		return prova;
	}

	public void setProva(TipoProvaExtraOrdinaria prova) {
		this.prova = prova;
	}
}
