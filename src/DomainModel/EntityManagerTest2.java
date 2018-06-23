package DomainModel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class EntityManagerTest2
{
	private static EntityManagerFactory entityManagerFactory;

	@BeforeClass
	public static void setUp() throws Exception
	{
			entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
	}

	@AfterClass
	public static void tearDown() throws Exception
	{
		entityManagerFactory.close();
	}

	@Test
	public void testHibernate()
	{		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		PostulacionDAO postulacionDAO = new PostulacionDAO();
		BusquedaDAO busquedaDAO = new BusquedaDAO();
		
		Busqueda b1 = new Busqueda("Guitarra","4 años","2019/04/01");
		Busqueda b2 = new Busqueda("Bateria","2 años","2019/06/01");
		busquedaDAO.saveBusqueda(b1);
		busquedaDAO.saveBusqueda(b2);
		//entityManager.persist(b1);
		//entityManager.persist(b2);
		
		Postulacion p1 = new Postulacion("Rock","Guitarra");
		Postulacion p2 = new Postulacion("Rock","Guitarra");
		postulacionDAO.savePostulacion(p1);
		postulacionDAO.savePostulacion(p2);
		//entityManager.persist(p1);
		//entityManager.persist(p2);
		
		//List<Postulacion> postulaciones = entityManager.createQuery( "from Postulacion", Postulacion.class ).getResultList();
		//List<Busqueda> busquedas = entityManager.createQuery( "from Busqueda", Busqueda.class ).getResultList();
		
		List<Postulacion> postulaciones = postulacionDAO.getPostulaciones();
		List<Busqueda> busquedas = busquedaDAO.getBusquedas();
		
		//RECORRO TODAS LAS POSTULACIONES
		for ( Postulacion postulacion : postulaciones )
		{
			System.out.println("Analizo postulacion: " + postulacion.getId() + " - " + postulacion.getGeneroMusical() + " - "+ postulacion.getInstrumento() );
			
			//POR CADA POSTULACION, RECORRO LAS BUSQUEDAS
			for ( Busqueda busqueda : busquedas )
			{
				System.out.println("Analizo busqueda: " + busqueda.getId() + " - " + busqueda.getExperiencia() + " - "+ busqueda.getFechaExp() + " - " + busqueda.getInstrumento() );
				if ( postulacion.getInstrumento().equals( busqueda.getInstrumento() ) )
				{
					Long idPostulacion = postulacion.getId();
					Long idBusqueda = busqueda.getId();
					
					//buscar en Notificacion un registro con ese par de ID
					//List<Notificacion> notificaciones = entityManager.createQuery( "from Notificacion", Notificacion.class ).getResultList();
					
					/*Notificacion notificacion1 = (Notificacion) entityManager.createQuery(
							"from Notificacion notificacion where notificacion.busqueda.id = :miparam1 and notificacion.postulacion.id = :miparam2")
							.setParameter("miparam1", idBusqueda).setParameter("miparam2", idPostulacion)
							.getSingleResult();*/ //RETOCAR
					
					List<Notificacion> notificacion = entityManager.createQuery(
							"from Notificacion notificacion where notificacion.busqueda.id = :miparam1 and notificacion.postulacion.id = :miparam2")
							.setParameter("miparam1", idBusqueda).setParameter("miparam2", idPostulacion)
							.getResultList();
					
					if ( notificacion.isEmpty() )
					{
						System.out.println( "NOTIFICACION NUEVA!" );
						Notificacion N1 = new Notificacion(postulacion, busqueda);
						//entityManager.persist(N1);
					}
					else
						System.out.println( "NOTIFICACION: " + notificacion.get(0).getPostulacion().getId() + " - " + notificacion.get(0).getBusqueda().getId() + " YA EXISTE!" );
				}
			}
		}
		
        entityManager.getTransaction().commit();
        entityManager.close();
	}
}

//Belongs to dueño de la relacion de que lado voy a actualizar la relacion



//COSAS A MEJORAR en BACKEND:

//PASAR LA CREACION DE POSTULACIONES Y BUSQUEDAS AFUERA DE LA CLASE MACHEO
//MEJORAR LO DE LOS DAOS, QUE UTILICEN UN SOLO DAT CON PARAMETRO T
//"INTENTAR" NO SE SI ES POSIBLE, QUE NUESTRA CLASE BUSQUEDA Y POSTULACIONES TENGAN LA MISMA ESTRUCTURA QUE EN LAS CLASES DE GRAILS,
//	PARA QUE DESDE LA APP CREAR POSTULACIONES Y BUSQUEDAS Y DESDE ACA SOLO HACER EL MACHEO

//COSAS A MEJORAR en APP PRICIPAL:

//UTILIZAR spring security PARA EL TEMA DEL USUARIO ADMINISTRADOR, VER SI ES NECESARIO CREAR UNA CLASE ADMINISTRADOR. MEJORAR EL TEMA DE PERMISOS
//CREAR LA CLASE POSTULACIONES DEL MUSICO, SIMILAR A COMO ESTA EN LA APP BACKEND!!
//APLICAR ALGUNA TECNOLOGIA DE LAS QUE VIMOS
//MEJORAR LA VISTA DE CADA PANTALLA