package ao.co.intellectus.DTO;

public class ResgistroDocumentoCliente {
	private Integer anoDeclaracao;
	private Integer aluno;
	private Integer declaracaoDestino;
	private Integer tipoDeclaracao; 
	private boolean retirado;
	private boolean guiaPaga;
	//private Usuario usuarioEmitiu;
	private boolean defesaTcc;
	private boolean projectoFinal;
	private boolean cerimonia;
	//private boolean requerimento;
	//private String entidadePesquisa;
	private Integer usuarioSolicitou;
	private Integer usuarioEmitiu;
	private String userName;
	private Integer userCode;
	
	/*
	public boolean isRequerimento() {
		return requerimento;
	}
	public void setRequerimento(boolean requerimento) {
		this.requerimento = requerimento;
	}
	*/
	public Integer getAnoDeclaracao() {
		return anoDeclaracao;
	}
	public void setAnoDeclaracao(Integer anoDeclaracao) {
		this.anoDeclaracao = anoDeclaracao;
	}
	public Integer getAluno() {
		return aluno;
	}
	public void setAluno(Integer aluno) {
		this.aluno = aluno;
	}
	public Integer getDeclaracaoDestino() {
		return declaracaoDestino;
	}
	public void setDeclaracaoDestino(Integer declaracaoDestino) {
		this.declaracaoDestino = declaracaoDestino;
	}
	public boolean isRetirado() {
		return retirado;
	}
	public void setRetirado(boolean retirado) {
		this.retirado = retirado;
	}
	public boolean isGuiaPaga() {
		return guiaPaga;
	}
	public void setGuiaPaga(boolean guiaPaga) {
		this.guiaPaga = guiaPaga;
	}
	public Integer getTipoDeclaracao() {
		return tipoDeclaracao;
	}
	public void setTipoDeclaracao(Integer tipoDeclaracao) {
		this.tipoDeclaracao = tipoDeclaracao;
	}
	/*public Usuario getUsuarioEmitiu() {
		return usuarioEmitiu;
	}
	public void setUsuarioEmitiu(Usuario usuarioEmitiu) {
		this.usuarioEmitiu = usuarioEmitiu;
	}*/
	public boolean isDefesaTcc() {
		return defesaTcc;
	}
	public void setDefesaTcc(boolean defesaTcc) {
		this.defesaTcc = defesaTcc;
	}
	public boolean isProjectoFinal() {
		return projectoFinal;
	}
	public void setProjectoFinal(boolean projectoFinal) {
		this.projectoFinal = projectoFinal;
	}
	public boolean isCerimonia() {
		return cerimonia;
	}
	public void setCerimonia(boolean cerimonia) {
		this.cerimonia = cerimonia;
	}
	public Integer getUsuarioSolicitou() {
		return usuarioSolicitou;
	}
	public void setUsuarioSolicitou(Integer usuarioSolicitou) {
		this.usuarioSolicitou = usuarioSolicitou;
	}
	public Integer getUsuarioEmitiu() {
		return usuarioEmitiu;
	}
	public void setUsuarioEmitiu(Integer usuarioEmitiu) {
		this.usuarioEmitiu = usuarioEmitiu;
	}
	
	/*
	public String getEntidadePesquisa() {
		return entidadePesquisa;
	}
	public void setEntidadePesquisa(String entidadePesquisa) {
		this.entidadePesquisa = entidadePesquisa;
	}
	*/
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

}
