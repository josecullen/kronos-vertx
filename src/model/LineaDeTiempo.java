package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LINEA_DE_TIEMPO database table.
 * 
 */
@Entity
@Table(name="LINEA_DE_TIEMPO")
@NamedQuery(name="LineaDeTiempo.findAll", query="SELECT l FROM LineaDeTiempo l")
public class LineaDeTiempo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LINEA_ID_GENERATOR", sequenceName="SEQ_LINEA_DE_TIEMPO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LINEA_ID_GENERATOR")
	@Column(name="ID")
	private long id;	
	
	private String categoria;

	private String titulo;

	//bi-directional many-to-many association to Acontecimiento
	@ManyToMany(mappedBy="lineaDeTiempos")
	private List<Acontecimiento> acontecimientos;

	public LineaDeTiempo() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Acontecimiento> getAcontecimientos() {
		return this.acontecimientos;
	}

	public void setAcontecimientos(List<Acontecimiento> acontecimientos) {
		this.acontecimientos = acontecimientos;
	}

}