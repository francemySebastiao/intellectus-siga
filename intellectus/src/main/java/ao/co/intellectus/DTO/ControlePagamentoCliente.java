package ao.co.intellectus.DTO;

public class ControlePagamentoCliente {
	private Integer anoCorricular;
    private Integer numeroAluno;
    private Integer anoLectivo;
    private String nome;
    private String turmaBase;
    private boolean contencioso;
    private String planoPagamento;
    private boolean inscrito;
    private double anuidade;
    private double semestre1;
    private double semestre2;
    private double porDisciplina;
    private double janeiro;
    private double fevereiro;
    private double marco;
    private double abril;
    private double maio;
    private double junho;
    private double julho;
    private double agosto;
    private double setembro;
    private double outubro;
    private double novembro;
    private double dezembro;
    private double matricula;
    private double inscricao;
    private double outrosPagamentos;
    private double totalGeral;
    private double totalPropinas;
	public Integer getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTurmaBase() {
		return turmaBase;
	}
	public void setTurmaBase(String turmaBase) {
		this.turmaBase = turmaBase;
	}
	public boolean isContencioso() {
		return contencioso;
	}
	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}
	public String getPlanoPagamento() {
		return planoPagamento;
	}
	public void setPlanoPagamento(String planoPagamento) {
		this.planoPagamento = planoPagamento;
	}
	public boolean isInscrito() {
		return inscrito;
	}
	public void setInscrito(boolean inscrito) {
		this.inscrito = inscrito;
	}
	public double getAnuidade() {
		return anuidade;
	}
	public void setAnuidade(double anuidade) {
		this.anuidade = anuidade;
	}
	public double getSemestre1() {
		return semestre1;
	}
	public void setSemestre1(double semestre1) {
		this.semestre1 = semestre1;
	}
	public double getSemestre2() {
		return semestre2;
	}
	public void setSemestre2(double semestre2) {
		this.semestre2 = semestre2;
	}
	public double getPorDisciplina() {
		return porDisciplina;
	}
	public void setPorDisciplina(double porDisciplina) {
		this.porDisciplina = porDisciplina;
	}
	public double getJaneiro() {
		return janeiro;
	}
	public void setJaneiro(double janeiro) {
		this.janeiro = janeiro;
	}
	public double getFevereiro() {
		return fevereiro;
	}
	public void setFevereiro(double fevereiro) {
		this.fevereiro = fevereiro;
	}
	public double getMarco() {
		return marco;
	}
	public void setMarco(double marco) {
		this.marco = marco;
	}
	public double getAbril() {
		return abril;
	}
	public void setAbril(double abril) {
		this.abril = abril;
	}
	public double getMaio() {
		return maio;
	}
	public void setMaio(double maio) {
		this.maio = maio;
	}
	public double getJunho() {
		return junho;
	}
	public void setJunho(double junho) {
		this.junho = junho;
	}
	public double getJulho() {
		return julho;
	}
	public void setJulho(double julho) {
		this.julho = julho;
	}
	public double getAgosto() {
		return agosto;
	}
	public void setAgosto(double agosto) {
		this.agosto = agosto;
	}
	public double getSetembro() {
		return setembro;
	}
	public void setSetembro(double setembro) {
		this.setembro = setembro;
	}
	public double getOutubro() {
		return outubro;
	}
	public void setOutubro(double outubro) {
		this.outubro = outubro;
	}
	public double getNovembro() {
		return novembro;
	}
	public void setNovembro(double novembro) {
		this.novembro = novembro;
	}
	public double getDezembro() {
		return dezembro;
	}
	public void setDezembro(double dezembro) {
		this.dezembro = dezembro;
	}
	public double getMatricula() {
		return matricula;
	}
	public void setMatricula(double matricula) {
		this.matricula = matricula;
	}
	public double getInscricao() {
		return inscricao;
	}
	public void setInscricao(double inscricao) {
		this.inscricao = inscricao;
	}
	public double getOutrosPagamentos() {
		return outrosPagamentos;
	}
	public void setOutrosPagamentos(double outrosPagamentos) {
		this.outrosPagamentos = outrosPagamentos;
	}
	public double getTotalGeral() {
		this.totalGeral+=this.getTotalPropinas()+this.getInscricao()+this.getMatricula();
		return totalGeral;
	}
	public void setTotalGeral(double totalGeral) {
		this.totalGeral = totalGeral;
	}
	public double getTotalPropinas() {
		this.totalPropinas=this.janeiro+this.fevereiro+this.marco+this.abril+this.maio+this.junho+this.julho+this.agosto+this.setembro+this.outubro+this.novembro+this.dezembro;
		return totalPropinas;
	}
	public void setTotalPropinas(double totalPropinas) {
		this.totalPropinas = totalPropinas;
	}
}
