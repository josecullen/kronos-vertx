package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;


/**
 * The persistent class for the ACONTECIMIENTO database table.
 * 
 */
@Entity
@NamedQuery(name="Acontecimiento.findAll", query="SELECT a FROM Acontecimiento a")
public class Acontecimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACONTECIMIENTO_ID_GENERATOR", sequenceName="SEQ_ACONTECIMIENTO_ID", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACONTECIMIENTO_ID_GENERATOR")
	@Column(name="ID")
	private long id;	
	
	
	private int año;

	private String categoria;

	private String contenido;

	@Column(name="COORD_X")
	private double coordX;

	@Column(name="COORD_Y")
	private double coordY;

	private int dia;

	private int mes;

	private String titulo;

	private int zoom;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(mappedBy="acontecimiento") 
	private List<AcontecimientoIcono> acontecimientoIconos;
	
	public List<AcontecimientoIcono> getAcontecimientoIconos() {
		return acontecimientoIconos;
	}
		
	public void setAcontecimientoIconos(List<AcontecimientoIcono> acontecimientoIconos) {
		this.acontecimientoIconos = acontecimientoIconos;
	}
	
	
	@OneToMany(mappedBy="acontecimiento")
	private List<AcontecimientoImagen> acontecimientoImagenes;
	
	public List<AcontecimientoImagen> getAcontecimientoImagenes() {
		return acontecimientoImagenes;
	}
		
	public void setAcontecimientoImagenes(List<AcontecimientoImagen> acontecimientoImagenes) {
		this.acontecimientoImagenes = acontecimientoImagenes;
	}
	
	

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
	
	
	
	public List<LineaDeTiempo> getLineaDeTiempos() {
		return lineaDeTiempos;
	}

	public void setLineaDeTiempos(List<LineaDeTiempo> lineaDeTiempos) {
		this.lineaDeTiempos = lineaDeTiempos;
	}

	public Acontecimiento() {}

	public int getAño() {
		return this.año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public int getZoom() {
		return this.zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	

}