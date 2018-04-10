import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JdoMain {
	
	public static final int MAX_POINTS = 10;
	static Query query = null;

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("$objectdb/point1.odb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		for(int i = 0;i < MAX_POINTS;i++)
			entityManager.persist(new Point(new Random().nextInt(100), new Random().nextInt(100)));
		entityManager.getTransaction().commit();
		
		System.out.println("SELECT p FROM Point p");
		Query query = entityManager.createQuery("SELECT p FROM Point p");
		System.out.println(query.getResultList());

		System.out.println("SELECT p.x FROM Point p where p.y > 100");
		query = entityManager.createQuery("SELECT p.x FROM Point p where p.y > 10");
		System.out.println(query.getResultList());
	
		System.out.println("SELECT p FROM Point p where p.y > 100 && p.y < 100");
		query = entityManager.createQuery("SELECT p FROM Point p where p.y > 10 && p.y < 100");
		System.out.println(query.getResultList());
		
		System.out.println("SELECT max(p.x) FROM Point p");
		query = entityManager.createQuery("SELECT max(p.x) FROM Point p");
		System.out.println(query.getResultList());
		
		System.out.println("SELECT avg(p.x) FROM Point p");
		query = entityManager.createQuery("SELECT avg(p.x) FROM Point p");
		System.out.println(query.getResultList());
		
		System.out.println("SELECT count(p.x) FROM Point p");
		query = entityManager.createQuery("SELECT count(p.x) FROM Point p");
		System.out.println(query.getResultList());
		
		System.out.println("SELECT p FROM Point p order by p.y");
		query = entityManager.createQuery("SELECT p FROM Point p order by p.y");
		System.out.println(query.getResultList());
		
		System.out.println("SELECT p FROM Point p where p.y between 10 and 43");
		query = entityManager.createQuery("SELECT p FROM Point p where p.y between 10 and 43");
		System.out.println(query.getResultList());
		
		System.out.println("SELECT p FROM Point p order by p.y in (21,43,65)");
		query = entityManager.createQuery("SELECT p FROM Point p order by p.y  in (21,43,65)");
		System.out.println(query.getResultList());
		
		System.out.println("ETC ETC ETC ..............");
		
	}

}
