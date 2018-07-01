package Controller;

import DAO.BusquedaDAO;
import DAO.PostulacionDAO;
import DomainModel.Busqueda;
import DomainModel.Postulacion;
import Service.MacheoService;

public class Navegacion
{
	public static void main(String[] args)
	{
		//***SIMULACION DE CARGA PREVIA
		/*PostulacionDAO postulacionDAO = new PostulacionDAO();
		BusquedaDAO busquedaDAO = new BusquedaDAO();
		
		Postulacion p1 = new Postulacion("Rock","Guitarra");
		Postulacion p2 = new Postulacion("Rock","Guitarra");
		postulacionDAO.savePostulacion(p1);
		postulacionDAO.savePostulacion(p2);
		
		Busqueda b1 = new Busqueda("Guitarra","4 años","2019/04/01");
		Busqueda b2 = new Busqueda("Bateria","2 años","2019/06/01");
		busquedaDAO.saveBusqueda(b1);
		busquedaDAO.saveBusqueda(b2);*/
		//***
		
		MacheoService ejecutar = new MacheoService();
		ejecutar.ejecutarMacheo();
	}
}
