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
		//***SIMULACION DE CARGA PREVIA - BANCO DE PRUEBAS
		PostulacionDAO postulacionDAO = new PostulacionDAO();
		BusquedaDAO busquedaDAO = new BusquedaDAO();
		
		Postulacion p1 = new Postulacion("Musica clasica","Guitarra");
		postulacionDAO.savePostulacion(p1);
		Postulacion p2 = new Postulacion("Jazz","Trompeta");
		postulacionDAO.savePostulacion(p2);
		Postulacion p3 = new Postulacion("Rock and Roll","Bajo");
		postulacionDAO.savePostulacion(p3);
		Postulacion p4 = new Postulacion("Rock","Guitarra");
		postulacionDAO.savePostulacion(p4);
		Postulacion p5 = new Postulacion("Metal","Guitarra");
		postulacionDAO.savePostulacion(p5);
		
		Busqueda b1 = new Busqueda("Trompeta","4 años","2019/04/01");
		busquedaDAO.saveBusqueda(b1);
		Busqueda b2 = new Busqueda("Guitarra","2 años","2019/12/01");
		busquedaDAO.saveBusqueda(b2);
		Busqueda b3 = new Busqueda("Bajo","3 años","2019/10/01");
		busquedaDAO.saveBusqueda(b3);
		Busqueda b4 = new Busqueda("Guitarra","5 años","2019/02/01");
		busquedaDAO.saveBusqueda(b4);
		Busqueda b5 = new Busqueda("Trompeta","6 años","2019/01/01");
		busquedaDAO.saveBusqueda(b5);
		//***
		
		/*Busqueda b6 = new Busqueda("Guitarra","1 años","2019/01/01");
		busquedaDAO.saveBusqueda(b6);*/
		
		MacheoService.ejecutarMacheo();
	}
}
