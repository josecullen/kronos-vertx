package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Acontecimiento;
import model.LineaDeTiempo;

public class LineaDeTiempoService implements BasicServiceInterface<LineaDeTiempo> {

	EntityManager em;
	
	public LineaDeTiempoService(EntityManager em){
		this.em = em;
	}
	
	public List<LineaDeTiempo> findAll() {
		TypedQuery<LineaDeTiempo> query = em.createNamedQuery("LineaDeTiempo.findAll", LineaDeTiempo.class);
		return (List<LineaDeTiempo>) query.getResultList();	
	}

	public LineaDeTiempo create(String titulo) {
		LineaDeTiempo linea = new LineaDeTiempo();
		linea.setTitulo(titulo);
		em.persist(linea);
		return linea;
	}
	
	public LineaDeTiempo create(String titulo, List<Acontecimiento> aconts) {
		LineaDeTiempo linea = new LineaDeTiempo();
		System.out.println("1");
		linea.setTitulo(titulo);
		System.out.println("2");
		linea.setAcontecimientos(aconts);
		System.out.println("3");
		em.persist(linea);
		return linea;
	}

	public boolean remove(LineaDeTiempo entity) {
		
		return false;
	}

	public LineaDeTiempo find(String titulo) {
		TypedQuery<LineaDeTiempo> query = 
				em.createNamedQuery("LineaDeTiempo.findByTitulo", LineaDeTiempo.class).setParameter("titulo", titulo);
		List<LineaDeTiempo> lista = query.getResultList();
		return  lista != null ? lista.get(0) : null;
	}

	public EntityManager getEM() {
		return null;
	}


}
