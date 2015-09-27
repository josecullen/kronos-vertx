package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Icono;

public class IconoService implements BasicServiceInterface<Icono> {
	EntityManager em;
	
	public IconoService(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public List<Icono> findAll() {
		TypedQuery<Icono> query = em.createQuery("Icono.findAll", Icono.class);
		return (List<Icono>) query.getResultList();	
	}

	@Override
	public Icono create(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Icono entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Icono find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager getEM() {
		// TODO Auto-generated method stub
		return null;
	}

}
