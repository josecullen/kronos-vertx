package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Acontecimiento;
import model.AcontecimientoIcono;

public class AcontecimientoIconoService implements BasicServiceInterface<AcontecimientoIcono>{
	EntityManager em;
	
	public AcontecimientoIconoService(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public List<AcontecimientoIcono> findAll() {
		TypedQuery<AcontecimientoIcono> query = em.createQuery("AcontecimientoIcono.findAll", AcontecimientoIcono.class);
		return (List<AcontecimientoIcono>) query.getResultList();

	}

	@Override
	public AcontecimientoIcono create(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(AcontecimientoIcono entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AcontecimientoIcono find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager getEM() {
		// TODO Auto-generated method stub
		return null;
	}

}
