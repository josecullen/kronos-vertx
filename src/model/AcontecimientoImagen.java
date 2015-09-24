package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ACONTECIMIENTO_IMAGEN database table.
 * 
 */
@Entity
@Table(name="ACONTECIMIENTO_IMAGEN")
@NamedQuery(name="AcontecimientoImagen.findAll", query="SELECT a FROM AcontecimientoImagen a")
public class AcontecimientoImagen implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="ACONTECIMIENTO_IMAGEN_ID_GENERATOR", sequenceName="SEQ_ACONTECIMIENTO_IMAGEN")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACONTECIMIENTO_IMAGEN_ID_GENERATOR")
	@Column(name="ID")
	private int id;


	@ManyToOne
	@PrimaryKeyJoinColumn(name="ACONTECIMIENTO_ID", referencedColumnName="ID")
	private Acontecimiento acontecimiento;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="IMAGEN_ID", referencedColumnName="ID")
	private Imagen imagen;
	
	

	private int orden;

	public AcontecimientoImagen() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrden() {
		return this.orden;
	}

	public Acontecimiento getAcontecimiento() {
		return acontecimiento;
	}

	public void setAcontecimiento(Acontecimiento acontecimiento) {
		this.acontecimiento = acontecimiento;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

}