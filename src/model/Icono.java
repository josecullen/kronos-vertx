package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;


@Entity
@NamedQuery(name="Icono.findAll", query="SELECT a FROM Icono a")
public class Icono {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ICONO_ID_GENERATOR", sequenceName="SEQ_ICONO_ID", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ICONO_ID_GENERATOR")
	@Column(name="ID")
	private long id;	
	
	private String nombre;

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
