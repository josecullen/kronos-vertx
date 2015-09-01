package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Acontecimiento;

public class AcontecimientoService implements BasicServiceInterface<Acontecimiento> {
	EntityManager em;
	
	public AcontecimientoService(EntityManager em){
		this.em = em;
	}
	
	@Override
	public List<Acontecimiento> findAll() {
		TypedQuery<Acontecimiento> query = em.createQuery("SELECT p FROM Acontecimiento p", Acontecimiento.class);
		return (List<Acontecimiento>) query.getResultList();	
	}

	@Override
	public Acontecimiento create(String titulo) {
		Acontecimiento acontecimiento = new Acontecimiento();
		acontecimiento.setTitulo(titulo);
		em.persist(acontecimiento);
		return acontecimiento;
	}

	@Override
	public boolean remove(Acontecimiento entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Acontecimiento find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager getEM() {
		return em;
	}
	
	public List<Acontecimiento> getAcontecimientoByYear(int year){
		TypedQuery<Acontecimiento> query = em.createNamedQuery("Acontecimiento.findByYear", Acontecimiento.class);
		return query.setParameter("año", year).getResultList();
	}
	
	public Acontecimiento getAcontecimientoById(int id){
		TypedQuery<Acontecimiento> query = em.createNamedQuery("Acontecimiento.findById", Acontecimiento.class);
		Acontecimiento acontecimiento = query.setParameter("id", id).getResultList().get(0);
		return acontecimiento;
	}

}
