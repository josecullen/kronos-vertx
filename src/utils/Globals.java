package utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Globals {
	public static javax.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("VertxJPA");
	public static EntityManager em = emf.createEntityManager();

}
