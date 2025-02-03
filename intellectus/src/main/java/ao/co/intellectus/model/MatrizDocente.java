package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_matriz_docente")
public class MatrizDocente {
	@Id
	@GeneratedValue
    private Integer id;
	private String linha;
    private Integer C1;
    private Integer C2;
    private Integer C3;
    private Integer C4;
    private Integer C5;
    private Integer C6;
    private Integer C7;
    @ManyToOne
    @JoinColumn(name="codigo_docente")
    private Docente docente;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLinha() {
		return linha;
	}
	public void setLinha(String linha) {
		this.linha = linha;
	}
	public Integer getC1() {
		return C1;
	}
	public void setC1(Integer c1) {
		C1 = c1;
	}
	public Integer getC2() {
		return C2;
	}
	public void setC2(Integer c2) {
		C2 = c2;
	}
	public Integer getC3() {
		return C3;
	}
	public void setC3(Integer c3) {
		C3 = c3;
	}
	public Integer getC4() {
		return C4;
	}
	public void setC4(Integer c4) {
		C4 = c4;
	}
	public Integer getC5() {
		return C5;
	}
	public void setC5(Integer c5) {
		C5 = c5;
	}
	public Integer getC6() {
		return C6;
	}
	public void setC6(Integer c6) {
		C6 = c6;
	}
	public Integer getC7() {
		return C7;
	}
	public void setC7(Integer c7) {
		C7 = c7;
	}
	
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	
}
