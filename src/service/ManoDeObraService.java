package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.ManoDeObra;


public class ManoDeObraService<AnEntity> implements BasicServiceInterface<AnEntity> {

	EntityManager em;
	
	public ManoDeObraService(EntityManager em){
		this.em = em;
	}
	
	@Override
	public List<AnEntity> findAll() {
		TypedQuery<ManoDeObra> query = em.createQuery("SELECT p FROM ManoDeObra p", ManoDeObra.class);
		return (List<AnEntity>) query.getResultList();	
	}

	@Override
	public AnEntity create(String name) {
		AnEntity manoDeObra = (AnEntity)new ManoDeObra();
		((ManoDeObra)manoDeObra).setNombre(name);
		em.persist(manoDeObra);
		return manoDeObra;
	}

	@Override
	public boolean remove(AnEntity entity) {
		ManoDeObra p = em.find(ManoDeObra.class, ((ManoDeObra)entity).getManoDeObraId());
		if(p != null) em.remove(p);
		return (p != null);
	}

	@Override
	public AnEntity find(String name) {
		TypedQuery<ManoDeObra> query = em.createQuery("SELECT p FROM ManoDeObra p WHERE p.nombre = :nombre", ManoDeObra.class);
		ManoDeObra mdo = null;
		
		try{
			mdo = query.setParameter("nombre", name).getSingleResult();
		}catch(NoResultException e){
			mdo = null;
		}
		return (AnEntity) mdo;
	}
	@Override
	public EntityManager getEM() {
		return em;
	}

}
