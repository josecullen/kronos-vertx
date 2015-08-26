package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the MANO_DE_OBRA database table.
 * 
 */
@Entity
@Table(name="MANO_DE_OBRA")
@NamedQuery(name="ManoDeObra.findAll", query="SELECT m FROM ManoDeObra m")
public class ManoDeObra implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="MANO_DE_OBRA_MANODEOBRAID_GENERATOR", sequenceName="SEQ_MANO_DE_OBRA_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MANO_DE_OBRA_MANODEOBRAID_GENERATOR")
	@Column(name="MANO_DE_OBRA_ID")
	private long manoDeObraId;
	
	@Column(name="COSTO_HORA")
	private double costoHora;

	private String nombre;

	public ManoDeObra() {
	}

	public double getCostoHora() {
		return this.costoHora;
	}

	public void setCostoHora(double costoHora) {
		this.costoHora = costoHora;
	}

	public Object getManoDeObraId() {
		return this.manoDeObraId;
	}

	public void setManoDeObraId(long manoDeObraId) {
		this.manoDeObraId = manoDeObraId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}