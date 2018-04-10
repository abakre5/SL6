import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JdoMain {
	
	public static final int MAX_POINTS = 10;
	static Query query = null;

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("$objectdb/point.odb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
			entityManager.persist(new Point(new Random().nextInt(100), new Random().nextInt(100)));
		entityManager.getTransaction().commit();
		
		System.out.println("SELECT p FROM Point p");
		Query query = entityManager.createQuery("SELECT p FROM Point p");
		System.out.println(query.getResultList());

		System.out.println("SELECT p.x FROM Point p where p.y > 100");
		query = entityManager.createQuery("SELECT p.x FROM Point p where p.y > 10");
		System.out.println(query.getResultList());
	
		System.out.println("ETC ETC ETC ..............");
		
	}

}
