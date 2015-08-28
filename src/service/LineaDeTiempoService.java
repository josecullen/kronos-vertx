package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

	public boolean remove(LineaDeTiempo entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public LineaDeTiempo find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public EntityManager getEM() {
		return null;
	}


}
