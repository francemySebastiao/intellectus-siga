package ao.co.intellectus.DTO;

import java.util.Date;

public class GuiaCandidaturaClinete {
	private Integer idGuia;
	private String numeroContribuinte;
	private String instituicao;
	private String grupoOwner;
	private String endereco;
	private String numeroGuia;
	private String numeroFacturaRecibo;
	private String nome;
	private Integer numeroAluno;
	private double montanteTotal;
	private Date dataEmissao;
	private Date dataVencimento;
	private double refUsd;
	private String emitidoPor;
	private DetalhePagamento detalhesPagamento;
	private Integer anoLectivo;
	private String anoLectivoDescricao;
	private boolean liquidar;
	private boolean liquidada;
	private String grau;
	

	public Integer getIdGuia() {
		return idGuia;
	}
	public void setIdGuia(Integer idGuia) {
		this.idGuia = idGuia;
	}
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public String getNumeroContribuinte() {
		return numeroContribuinte;
	}
	public void setNumeroContribuinte(String numeroContribuinte) {
		this.numeroContribuinte = numeroContribuinte;
	}
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public String getGrupoOwner() {
		return grupoOwner;
	}
	public void setGrupoOwner(String grupoOwner) {
		this.grupoOwner = grupoOwner;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	
	public String getNumeroFacturaRecibo() {
		return numeroFacturaRecibo;
	}
	public void setNumeroFacturaRecibo(String numeroFacturaRecibo) {
		this.numeroFacturaRecibo = numeroFacturaRecibo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public double getMontanteTotal() {
		return montanteTotal;
	}
	public void setMontanteTotal(double montanteTotal) {
		this.montanteTotal = montanteTotal;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public double getRefUsd() {
		return refUsd;
	}
	public void setRefUsd(double refUsd) {
		this.refUsd = refUsd;
	}
	public String getEmitidoPor() {
		return emitidoPor;
	}
	public void setEmitidoPor(String emitidoPor) {
		this.emitidoPor = emitidoPor;
	}
	public DetalhePagamento getDetalhesPagamento() {
		return detalhesPagamento;
	}
	public void setDetalhesPagamento(DetalhePagamento detalhesPagamento) {
		this.detalhesPagamento = detalhesPagamento;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public boolean isLiquidar() {
		return liquidar;
	}
	public void setLiquidar(boolean liquidar) {
		this.liquidar = liquidar;
	}
	public boolean isLiquidada() {
		return liquidada;
	}
	public void setLiquidada(boolean liquidada) {
		this.liquidada = liquidada;
	}
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
}