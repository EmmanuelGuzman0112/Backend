package Service;

import java.util.List;

import DomainModel.Busqueda;
import DomainModel.BusquedaDAO;
import DomainModel.Notificacion;
import DomainModel.NotificacionDAO;
import DomainModel.Postulacion;
import DomainModel.PostulacionDAO;

public class MacheoService
{
	public static void main(String[] args)
	{
		PostulacionDAO postulacionDAO = new PostulacionDAO();
		BusquedaDAO busquedaDAO = new BusquedaDAO();
		NotificacionDAO notificacionDAO = new NotificacionDAO();
		
		Postulacion p1 = new Postulacion("Rock","Guitarra");
		Postulacion p2 = new Postulacion("Rock","Guitarra");
		postulacionDAO.savePostulacion(p1);
		postulacionDAO.savePostulacion(p2);
		
		List<Postulacion> postulaciones = postulacionDAO.getPostulaciones();
		
		Busqueda b1 = new Busqueda("Guitarra","4 años","2019/04/01");
		Busqueda b2 = new Busqueda("Bateria","2 años","2019/06/01");
		busquedaDAO.saveBusqueda(b1);
		busquedaDAO.saveBusqueda(b2);
		
		List<Busqueda> busquedas = busquedaDAO.getBusquedas();

		//RECORRO TODAS LAS POSTULACIONES
		for ( Postulacion postulacion : postulaciones )
		{
			System.out.println("Analizo postulacion: " + postulacion.getId() + " - " + postulacion.getGeneroMusical() + " - "+ postulacion.getInstrumento() );
			
			//POR CADA POSTULACION, RECORRO LAS BUSQUEDAS
			for ( Busqueda busqueda : busquedas )
			{
				System.out.println("Analizo busqueda: " + busqueda.getId() + " - " + busqueda.getExperiencia() + " - "+ busqueda.getFechaExp() + " - " + busqueda.getInstrumento() );
				
				if ( postulacion.getInstrumento().equals(busqueda.getInstrumento()) )
				{					
					Long idPostulacion = postulacion.getId();
					Long idBusqueda = busqueda.getId();
										
					List<Notificacion> notificacion = notificacionDAO.getNotificacion(idPostulacion, idBusqueda);
					
					if ( notificacion.isEmpty() )
					{
						System.out.println( "NOTIFICACION NUEVA!" );
						Notificacion N1 = new Notificacion(postulacion, busqueda);
						notificacionDAO.saveNotificacion(N1);
					}
					else
						System.out.println( "NOTIFICACION: " + notificacion.get(0).getPostulacion().getId() + " - " + notificacion.get(0).getBusqueda().getId() + " YA EXISTE!" );
				}
			}
		}
	}
}
