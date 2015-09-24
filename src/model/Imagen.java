package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the IMAGEN database table.
 * 
 */
@Entity
@NamedQuery(name="Imagen.findAll", query="SELECT i FROM Imagen i")
public class Imagen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="IMAGEN_ID_GENERATOR", sequenceName="SEQ_IMAGEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IMAGEN_ID_GENERATOR")
	@Column(name="ID")
	private long id;	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	private String copete;

	private String titulo;

	@OneToMany(mappedBy="imagen")
	private List<AcontecimientoImagen> acontecimientoImagenes;	
	
	public List<AcontecimientoImagen> getAcontecimientoImagenes() {
		return acontecimientoImagenes;
	}
	public void setAcontecimientoImagenes(List<AcontecimientoImagen> acontecimientoImagenes) {
		this.acontecimientoImagenes = acontecimientoImagenes;
	}

	public Imagen() {
	}

	public String getCopete() {
		return this.copete;
	}

	public void setCopete(String copete) {
		this.copete = copete;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



}