package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ACONTECIMIENTO_ICONO")
@NamedQuery(name="AcontecimientoIcono.findAll", query="SELECT a FROM AcontecimientoIcono a")
public class AcontecimientoIcono {
	@Id
	@SequenceGenerator(name="ACONTECIMIENTO_ICONO_ID_GENERATOR", sequenceName="SEQ_ACONTECIMIENTO_ICONO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACONTECIMIENTO_ICONO_ID_GENERATOR")
	@Column(name="ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ID_ACONTECIMIENTO")
	private Acontecimiento acontecimiento;
	
	@ManyToOne
	@JoinColumn(name="ID_ICONO")
	private Icono icono;

	public int getId() {
		return id;
	}

	public Acontecimiento getAcontecimiento() {
		return acontecimiento;
	}

	public void setAcontecimiento(Acontecimiento acontecimiento) {
		this.acontecimiento = acontecimiento;
	}

	public Icono getIcono() {
		return icono;
	}

	public void setIcono(Icono icono) {
		this.icono = icono;
	}
	
	
	
	
	
	
}
