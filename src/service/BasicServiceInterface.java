package service;

import java.util.List;

import javax.persistence.EntityManager;

public interface BasicServiceInterface<AnEntity> {
	List<AnEntity> findAll();
	AnEntity create(String name);
	boolean remove(AnEntity entity);
	AnEntity find(String name);
	EntityManager getEM();
}
