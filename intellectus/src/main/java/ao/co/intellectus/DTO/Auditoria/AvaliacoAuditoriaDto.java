package ao.co.intellectus.DTO.Auditoria;

import java.util.Date;

import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoAlunoAudit;
import ao.co.intellectus.model.Usuario;
@SuppressWarnings("unused")
public class AvaliacoAuditoriaDto {
	
	private String anoLectivoDescricao;
	private Integer anoCurricular;
	private String disciplina;
	private boolean aprovado;
	private Float primeiraFrequencia;
	private Date dataPrimeiraFrequencia;
    private String usuarioPrimeiraFrequencia;
    private Float segundaFrequencia;
    private Date dataSegundaFrequencia;
    private String usuarioSegundaFrequencia;
    private Float terceiraFrequencia;
    private Date dataTerceiraFrequencia;
	private String usuarioTerceiraFrequencia;
	private Float quartaFrequencia;
	private Date dataQuartaFrequencia;
	private String usuarioQuartaFrequencia;
	private Date dataInscricao;
	private String usuarioInscreveu;
	private Date dataAlteracao;
	private String usuarioAlterou;
	private Date dataAnulacao;
	private Date dataExame;
	private Float notaExame;
	private String usuarioExame;
	private Date dataExameOral;
	private Float notaExameOral;
	private String usuarioExameOral; 
	private Date dataInscricaoEpocaEspecial;
	private Date dataNotaEpocaEspecial; 
	private Float notaEpocaEspecial; 
	private String usuarioEspecial;
	private Date dataNotaEpocaEspecialOral;
	private Float notaEpocaEspecialOral;
	private String usuarioEspecialOral;
	private Date dataNotaFinal;
	private Float notaFinal;
	private Date dataInscricaoMelhoria;
	private Date dataMelhoria;
	private Float melhoriaNota;
	private Float notaFinalMelhoria;
	private String usuarioMelhoria;
	private Date dataMelhoriaOral;
	private Float melhoriaNotaOral;
	private String usuarioMelhoriaOral;
	private Date dataNotaCursoVerao;
	private Float notaCursoDeVerao;
	private String usuarioCursoVerao;
	private boolean extraordinaria;
	private Date dataNotaExtraordinaria;
	private Float notaExtraodinaria;
	private String usuarioExtraordinaria;
	private Date dataNotaFinalContinua;
	private Float notaFinalContinua;
	private Date dataNotaPratica;
	private Float notaPratica;
	private String usuarioPratica;
	private Date dataNotaProjecto;
	private Float projectoNota;
	private String usuarioNotaProjecto;
	private Date dataNotaRecursoOral;
	private Float notaRecursoOral;
	private String usuarioRecursoOral;
	private Date dataInscricaoRecurso;
	private Date dataRecurso;
	private Float notaRecurso;
	private Float notaFinalRecurso;
	private String usuarioRecurso;
	private boolean fechada;
	private boolean validada;
	private Date dataValidacao;
	private String usuarioValidou;
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public boolean isAprovado() {
		return aprovado;
	}
	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}
	public Float getPrimeiraFrequencia() {
		return primeiraFrequencia;
	}
	public void setPrimeiraFrequencia(Float primeiraFrequencia) {
		this.primeiraFrequencia = primeiraFrequencia;
	}
	public Date getDataPrimeiraFrequencia() {
		return dataPrimeiraFrequencia;
	}
	public void setDataPrimeiraFrequencia(Date dataPrimeiraFrequencia) {
		this.dataPrimeiraFrequencia = dataPrimeiraFrequencia;
	}
	public String getUsuarioPrimeiraFrequencia() {
		return usuarioPrimeiraFrequencia;
	}
	public void setUsuarioPrimeiraFrequencia(String usuarioPrimeiraFrequencia) {
		this.usuarioPrimeiraFrequencia = usuarioPrimeiraFrequencia;
	}
	public Float getSegundaFrequencia() {
		return segundaFrequencia;
	}
	public void setSegundaFrequencia(Float segundaFrequencia) {
		this.segundaFrequencia = segundaFrequencia;
	}
	public Date getDataSegundaFrequencia() {
		return dataSegundaFrequencia;
	}
	public void setDataSegundaFrequencia(Date dataSegundaFrequencia) {
		this.dataSegundaFrequencia = dataSegundaFrequencia;
	}
	public String getUsuarioSegundaFrequencia() {
		return usuarioSegundaFrequencia;
	}
	public void setUsuarioSegundaFrequencia(String usuarioSegundaFrequencia) {
		this.usuarioSegundaFrequencia = usuarioSegundaFrequencia;
	}
	public Float getTerceiraFrequencia() {
		return terceiraFrequencia;
	}
	public void setTerceiraFrequencia(Float terceiraFrequencia) {
		this.terceiraFrequencia = terceiraFrequencia;
	}
	public Date getDataTerceiraFrequencia() {
		return dataTerceiraFrequencia;
	}
	public void setDataTerceiraFrequencia(Date dataTerceiraFrequencia) {
		this.dataTerceiraFrequencia = dataTerceiraFrequencia;
	}
	public String getUsuarioTerceiraFrequencia() {
		return usuarioTerceiraFrequencia;
	}
	public void setUsuarioTerceiraFrequencia(String usuarioTerceiraFrequencia) {
		this.usuarioTerceiraFrequencia = usuarioTerceiraFrequencia;
	}
	public Float getQuartaFrequencia() {
		return quartaFrequencia;
	}
	public void setQuartaFrequencia(Float quartaFrequencia) {
		this.quartaFrequencia = quartaFrequencia;
	}
	public Date getDataQuartaFrequencia() {
		return dataQuartaFrequencia;
	}
	public void setDataQuartaFrequencia(Date dataQuartaFrequencia) {
		this.dataQuartaFrequencia = dataQuartaFrequencia;
	}
	public String getUsuarioQuartaFrequencia() {
		return usuarioQuartaFrequencia;
	}
	public void setUsuarioQuartaFrequencia(String usuarioQuartaFrequencia) {
		this.usuarioQuartaFrequencia = usuarioQuartaFrequencia;
	}
	public Date getDataInscricao() {
		return dataInscricao;
	}
	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	public String getUsuarioInscreveu() {
		return usuarioInscreveu;
	}
	public void setUsuarioInscreveu(String usuarioInscreveu) {
		this.usuarioInscreveu = usuarioInscreveu;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public String getUsuarioAlterou() {
		return usuarioAlterou;
	}
	public void setUsuarioAlterou(String usuarioAlterou) {
		this.usuarioAlterou = usuarioAlterou;
	}
	public Date getDataAnulacao() {
		return dataAnulacao;
	}
	public void setDataAnulacao(Date dataAnulacao) {
		this.dataAnulacao = dataAnulacao;
	}
	public Date getDataExame() {
		return dataExame;
	}
	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}
	public Float getNotaExame() {
		return notaExame;
	}
	public void setNotaExame(Float notaExame) {
		this.notaExame = notaExame;
	}
	public String getUsuarioExame() {
		return usuarioExame;
	}
	public void setUsuarioExame(String usuarioExame) {
		this.usuarioExame = usuarioExame;
	}
	public Date getDataExameOral() {
		return dataExameOral;
	}
	public void setDataExameOral(Date dataExameOral) {
		this.dataExameOral = dataExameOral;
	}
	public Float getNotaExameOral() {
		return notaExameOral;
	}
	public void setNotaExameOral(Float notaExameOral) {
		this.notaExameOral = notaExameOral;
	}
	public String getUsuarioExameOral() {
		return usuarioExameOral;
	}
	public void setUsuarioExameOral(String usuarioExameOral) {
		this.usuarioExameOral = usuarioExameOral;
	}
	public Date getDataInscricaoEpocaEspecial() {
		return dataInscricaoEpocaEspecial;
	}
	public void setDataInscricaoEpocaEspecial(Date dataInscricaoEpocaEspecial) {
		this.dataInscricaoEpocaEspecial = dataInscricaoEpocaEspecial;
	}
	public Date getDataNotaEpocaEspecial() {
		return dataNotaEpocaEspecial;
	}
	public void setDataNotaEpocaEspecial(Date dataNotaEpocaEspecial) {
		this.dataNotaEpocaEspecial = dataNotaEpocaEspecial;
	}
	public Float getNotaEpocaEspecial() {
		return notaEpocaEspecial;
	}
	public void setNotaEpocaEspecial(Float notaEpocaEspecial) {
		this.notaEpocaEspecial = notaEpocaEspecial;
	}
	public String getUsuarioEspecial() {
		return usuarioEspecial;
	}
	public void setUsuarioEspecial(String usuarioEspecial) {
		this.usuarioEspecial = usuarioEspecial;
	}
	public Date getDataNotaEpocaEspecialOral() {
		return dataNotaEpocaEspecialOral;
	}
	public void setDataNotaEpocaEspecialOral(Date dataNotaEpocaEspecialOral) {
		this.dataNotaEpocaEspecialOral = dataNotaEpocaEspecialOral;
	}
	public Float getNotaEpocaEspecialOral() {
		return notaEpocaEspecialOral;
	}
	public void setNotaEpocaEspecialOral(Float notaEpocaEspecialOral) {
		this.notaEpocaEspecialOral = notaEpocaEspecialOral;
	}
	public String getUsuarioEspecialOral() {
		return usuarioEspecialOral;
	}
	public void setUsuarioEspecialOral(String usuarioEspecialOral) {
		this.usuarioEspecialOral = usuarioEspecialOral;
	}
	public Date getDataNotaFinal() {
		return dataNotaFinal;
	}
	public void setDataNotaFinal(Date dataNotaFinal) {
		this.dataNotaFinal = dataNotaFinal;
	}
	public Float getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(Float notaFinal) {
		this.notaFinal = notaFinal;
	}
	public Date getDataInscricaoMelhoria() {
		return dataInscricaoMelhoria;
	}
	public void setDataInscricaoMelhoria(Date dataInscricaoMelhoria) {
		this.dataInscricaoMelhoria = dataInscricaoMelhoria;
	}
	public Date getDataMelhoria() {
		return dataMelhoria;
	}
	public void setDataMelhoria(Date dataMelhoria) {
		this.dataMelhoria = dataMelhoria;
	}
	public Float getMelhoriaNota() {
		return melhoriaNota;
	}
	public void setMelhoriaNota(Float melhoriaNota) {
		this.melhoriaNota = melhoriaNota;
	}
	public Float getNotaFinalMelhoria() {
		return notaFinalMelhoria;
	}
	public void setNotaFinalMelhoria(Float notaFinalMelhoria) {
		this.notaFinalMelhoria = notaFinalMelhoria;
	}
	public String getUsuarioMelhoria() {
		return usuarioMelhoria;
	}
	public void setUsuarioMelhoria(String usuarioMelhoria) {
		this.usuarioMelhoria = usuarioMelhoria;
	}
	public Date getDataMelhoriaOral() {
		return dataMelhoriaOral;
	}
	public void setDataMelhoriaOral(Date dataMelhoriaOral) {
		this.dataMelhoriaOral = dataMelhoriaOral;
	}
	public Float getMelhoriaNotaOral() {
		return melhoriaNotaOral;
	}
	public void setMelhoriaNotaOral(Float melhoriaNotaOral) {
		this.melhoriaNotaOral = melhoriaNotaOral;
	}
	public String getUsuarioMelhoriaOral() {
		return usuarioMelhoriaOral;
	}
	public void setUsuarioMelhoriaOral(String usuarioMelhoriaOral) {
		this.usuarioMelhoriaOral = usuarioMelhoriaOral;
	}
	public Date getDataNotaCursoVerao() {
		return dataNotaCursoVerao;
	}
	public void setDataNotaCursoVerao(Date dataNotaCursoVerao) {
		this.dataNotaCursoVerao = dataNotaCursoVerao;
	}
	public Float getNotaCursoDeVerao() {
		return notaCursoDeVerao;
	}
	public void setNotaCursoDeVerao(Float notaCursoDeVerao) {
		this.notaCursoDeVerao = notaCursoDeVerao;
	}
	public String getUsuarioCursoVerao() {
		return usuarioCursoVerao;
	}
	public void setUsuarioCursoVerao(String usuarioCursoVerao) {
		this.usuarioCursoVerao = usuarioCursoVerao;
	}
	public boolean isExtraordinaria() {
		return extraordinaria;
	}
	public void setExtraordinaria(boolean extraordinaria) {
		this.extraordinaria = extraordinaria;
	}
	public Date getDataNotaExtraordinaria() {
		return dataNotaExtraordinaria;
	}
	public void setDataNotaExtraordinaria(Date dataNotaExtraordinaria) {
		this.dataNotaExtraordinaria = dataNotaExtraordinaria;
	}
	public Float getNotaExtraodinaria() {
		return notaExtraodinaria;
	}
	public void setNotaExtraodinaria(Float notaExtraodinaria) {
		this.notaExtraodinaria = notaExtraodinaria;
	}
	public String getUsuarioExtraordinaria() {
		return usuarioExtraordinaria;
	}
	public void setUsuarioExtraordinaria(String usuarioExtraordinaria) {
		this.usuarioExtraordinaria = usuarioExtraordinaria;
	}
	public Date getDataNotaFinalContinua() {
		return dataNotaFinalContinua;
	}
	public void setDataNotaFinalContinua(Date dataNotaFinalContinua) {
		this.dataNotaFinalContinua = dataNotaFinalContinua;
	}
	public Float getNotaFinalContinua() {
		return notaFinalContinua;
	}
	public void setNotaFinalContinua(Float notaFinalContinua) {
		this.notaFinalContinua = notaFinalContinua;
	}
	public Date getDataNotaPratica() {
		return dataNotaPratica;
	}
	public void setDataNotaPratica(Date dataNotaPratica) {
		this.dataNotaPratica = dataNotaPratica;
	}
	public Float getNotaPratica() {
		return notaPratica;
	}
	public void setNotaPratica(Float notaPratica) {
		this.notaPratica = notaPratica;
	}
	public String getUsuarioPratica() {
		return usuarioPratica;
	}
	public void setUsuarioPratica(String usuarioPratica) {
		this.usuarioPratica = usuarioPratica;
	}
	public Date getDataNotaProjecto() {
		return dataNotaProjecto;
	}
	public void setDataNotaProjecto(Date dataNotaProjecto) {
		this.dataNotaProjecto = dataNotaProjecto;
	}
	public Float getProjectoNota() {
		return projectoNota;
	}
	public void setProjectoNota(Float projectoNota) {
		this.projectoNota = projectoNota;
	}
	public String getUsuarioNotaProjecto() {
		return usuarioNotaProjecto;
	}
	public void setUsuarioNotaProjecto(String usuarioNotaProjecto) {
		this.usuarioNotaProjecto = usuarioNotaProjecto;
	}
	public Date getDataNotaRecursoOral() {
		return dataNotaRecursoOral;
	}
	public void setDataNotaRecursoOral(Date dataNotaRecursoOral) {
		this.dataNotaRecursoOral = dataNotaRecursoOral;
	}
	public Float getNotaRecursoOral() {
		return notaRecursoOral;
	}
	public void setNotaRecursoOral(Float notaRecursoOral) {
		this.notaRecursoOral = notaRecursoOral;
	}
	public String getUsuarioRecursoOral() {
		return usuarioRecursoOral;
	}
	public void setUsuarioRecursoOral(String usuarioRecursoOral) {
		this.usuarioRecursoOral = usuarioRecursoOral;
	}
	public Date getDataInscricaoRecurso() {
		return dataInscricaoRecurso;
	}
	public void setDataInscricaoRecurso(Date dataInscricaoRecurso) {
		this.dataInscricaoRecurso = dataInscricaoRecurso;
	}
	public Date getDataRecurso() {
		return dataRecurso;
	}
	public void setDataRecurso(Date dataRecurso) {
		this.dataRecurso = dataRecurso;
	}
	public Float getNotaRecurso() {
		return notaRecurso;
	}
	public void setNotaRecurso(Float notaRecurso) {
		this.notaRecurso = notaRecurso;
	}
	public Float getNotaFinalRecurso() {
		return notaFinalRecurso;
	}
	public void setNotaFinalRecurso(Float notaFinalRecurso) {
		this.notaFinalRecurso = notaFinalRecurso;
	}
	public String getUsuarioRecurso() {
		return usuarioRecurso;
	}
	public void setUsuarioRecurso(String usuarioRecurso) {
		this.usuarioRecurso = usuarioRecurso;
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
	public Date getDataValidacao() {
		return dataValidacao;
	}
	public void setDataValidacao(Date dataValidacao) {
		this.dataValidacao = dataValidacao;
	}
	public String getUsuarioValidou() {
		return usuarioValidou;
	}
	public void setUsuarioValidou(String usuarioValidou) {
		this.usuarioValidou = usuarioValidou;
	}
	
	
	
	}
