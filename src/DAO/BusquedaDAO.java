package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import DomainModel.Busqueda;

public class BusquedaDAO
{
	private static EntityManagerFactory entityManagerFactory;
	
	public BusquedaDAO()
	{
		entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
	}

	public void saveBusqueda(Busqueda b)
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(b);
        entityManager.getTransaction().commit();
        entityManager.close();
	}

	public List<Busqueda> getBusquedas()
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Busqueda> busquedas = entityManager.createQuery( "from Busqueda", Busqueda.class ).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        
        return busquedas;
	}
	
	/*public void close()
	{
		entityManager.close();
	}*/
}