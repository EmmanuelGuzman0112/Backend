package DomainModel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PostulacionDAO {

	private static EntityManagerFactory entityManagerFactory;
	
	public PostulacionDAO()
	{
		entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
	}

	public void savePostulacion(Postulacion p)
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(p);
        entityManager.getTransaction().commit();
        entityManager.close();
	}

	public List<Postulacion> getPostulaciones()
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Postulacion> postulaciones = entityManager.createQuery( "from Postulacion", Postulacion.class ).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        
        return postulaciones;
	}
}