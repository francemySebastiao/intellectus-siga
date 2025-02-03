package ao.co.intellectus.DTO;

public class Verificar {
	private boolean sala;
	private boolean docente;
	private boolean turma;
	
	public boolean isSala() {
		return sala;
	}
	public void setSala(boolean sala) {
		this.sala = sala;
	}
	public boolean isDocente() {
		return docente;
	}
	public void setDocente(boolean docente) {
		this.docente = docente;
	}
	public boolean isTurma() {
		return turma;
	}
	public void setTurma(boolean turma) {
		this.turma = turma;
	}
}
