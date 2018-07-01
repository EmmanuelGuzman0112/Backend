package Service;

import java.util.List;

import DAO.BusquedaDAO;
import DAO.NotificacionDAO;
import DAO.PostulacionDAO;
import DomainModel.Busqueda;
import DomainModel.Notificacion;
import DomainModel.Postulacion;

public class MacheoService
{
	public void ejecutarMacheo()
	{
		PostulacionDAO postulacionDAO = new PostulacionDAO();
		BusquedaDAO busquedaDAO = new BusquedaDAO();
		NotificacionDAO notificacionDAO = new NotificacionDAO();
		
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
