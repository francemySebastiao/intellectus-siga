package ao.co.intellectus.model;

public enum Prefixo {
	DR("Dr."),
	DRA("Dra."),
	ENG("Eng."),
	ENGA("Engª."),
	PROF("Prof."),
	PROFA("profª"),
	ARQ("Arq."),
	ARQA("Arqª."),
	SR("Sr."),
	SRA("Sra."),
	PROF_DR("Professor Doutor"),
	PROFA_DRA("Prossora Doutora"),
	MRC("Msc.");
	 
	private String descricao;
	
	private Prefixo(String descricao) {
	    this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
