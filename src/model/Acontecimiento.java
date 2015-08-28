package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ACONTECIMIENTO database table.
 * 
 */
@Entity
@NamedQuery(name="Acontecimiento.findAll", query="SELECT a FROM Acontecimiento a")
public class Acontecimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACONTECIMIENTO_ID_GENERATOR", sequenceName="SEQ_ACONTECIMIENTO_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACONTECIMIENTO_ID_GENERATOR")
	@Column(name="ID")
	private long id;
	
	private String contenido;

	@Column(name="COORD_X")
	private double coordX;

	@Column(name="COORD_Y")
	private double coordY;
	
	private int a�o;

	private int dia;

	private int mes;

	private String titulo;

	//bi-directional many-to-many association to LineaDeTiempo
	@ManyToMany
	@JoinTable(
		name="LINEA_ACONTECIMIENTO"
		, joinColumns={
			@JoinColumn(name="ACONTECIMIENTO_ID", referencedColumnName="ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="LINEA_ID", referencedColumnName="ID")
			}
		)
	private List<LineaDeTiempo> lineaDeTiempos;

	public Acontecimiento() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getA�o() {
		return this.a�o;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public double getCoordX() {
		return this.coordX;
	}

	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return this.coordY;
	}

	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

	public int getDia() {
		return this.dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return this.mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<LineaDeTiempo> getLineaDeTiempos() {
		return this.lineaDeTiempos;
	}

	public void setLineaDeTiempos(List<LineaDeTiempo> lineaDeTiempos) {
		this.lineaDeTiempos = lineaDeTiempos;
	}

}