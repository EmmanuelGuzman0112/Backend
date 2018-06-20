package DomainModel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class NotificacionDAO
{
	private static EntityManagerFactory entityManagerFactory;
	
	public NotificacionDAO()
	{
		entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
	}

	public void saveNotificacion(Notificacion n)
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(n);
        entityManager.getTransaction().commit();
        entityManager.close();
	}

	public List<Notificacion> getNotificaciones()
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Notificacion> notificaciones = entityManager.createQuery( "from Notificacion", Notificacion.class ).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        
        return notificaciones;
	}
	
	public List<Notificacion> getNotificacion(Long idPostulacion, Long idBusqueda)
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Notificacion> notificacion = entityManager.createQuery(
				"from Notificacion notificacion where notificacion.busqueda.id = :miparam1 and notificacion.postulacion.id = :miparam2")
				.setParameter("miparam1", idBusqueda).setParameter("miparam2", idPostulacion)
				.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        
        return notificacion;
	}
}