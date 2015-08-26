package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Calendar;


/**
 * The persistent class for the ACONTECIMIENTO database table.
 * 
 */
@Entity
@NamedQuery(name="Acontecimiento.findAll", query="SELECT a FROM Acontecimiento a")
public class Acontecimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	private String contenido;

	@Column(name="COORD_X")
	private double coordX;

	@Column(name="COORD_Y")
	private double coordY;

	@Temporal(TemporalType.DATE)
	private Calendar fecha;
	
	@Id
	@SequenceGenerator(name="ACONTECIMIENTO_ID_GENERATOR", sequenceName="SEQ_ACONTECIMIENTO_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACONTECIMIENTO_ID_GENERATOR")
	@Column(name="ID")
	private long id;

	private String titulo;

	public Acontecimiento() {
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

	public Calendar getFecha() {
		return this.fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}