package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Imagen;

public class ImagenService implements BasicServiceInterface<Imagen> {
	EntityManager em;
	public ImagenService(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Imagen> findAll() {
		return null;
	}

	@Override
	public Imagen create(String name) {
		Imagen imagen = new Imagen();
		imagen.setTitulo(name);
		em.persist(imagen);
		return imagen;
	}

	@Override
	public boolean remove(Imagen entity) {
		return false;
	}

	@Override
	public Imagen find(String name) {
		return null;
	}

	@Override
	public EntityManager getEM() {
		return null;
	}
	
	public Long getMax(){
		TypedQuery<Long> query = em.createQuery("select max(e.id) from Imagen e", Long.class);
		return query.getSingleResult();
	}
	
	public Imagen getLast(){
		TypedQuery<Imagen> query = em.createQuery("select e from Imagen e where max(e.id)", Imagen.class);
		return query.getSingleResult();

	}

}
