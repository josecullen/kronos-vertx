package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.Acontecimiento;
import model.AcontecimientoImagen;
import model.Imagen;


public class AcontecimientoImagenService implements BasicServiceInterface<AcontecimientoImagen> {
	EntityManager em;

	public AcontecimientoImagenService(EntityManager em) {
		this.em = em;
	}
	@Override
	public List<AcontecimientoImagen> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AcontecimientoImagen create(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public AcontecimientoImagen create(Imagen imagen, Acontecimiento acontecimiento,  int orden){
		AcontecimientoImagen acontecimientoImagen = new AcontecimientoImagen();
		acontecimientoImagen.setAcontecimiento(acontecimiento);
		acontecimientoImagen.setImagen(imagen);
		acontecimientoImagen.setOrden(orden);
		em.persist(acontecimientoImagen);
		return acontecimientoImagen;
	}
	
	

	@Override
	public boolean remove(AcontecimientoImagen entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AcontecimientoImagen find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager getEM() {
		// TODO Auto-generated method stub
		return null;
	}

}
