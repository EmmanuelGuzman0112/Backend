package DomainModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notificacion
{
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Postulacion postulacion;
	
	@ManyToOne
	private Busqueda busqueda;
	
	public Notificacion()
	{
		
	}
	
	public Notificacion(Postulacion postulacion, Busqueda busqueda)
	{
		addPostulacion(postulacion);
		addBusqueda(busqueda);
	}
	
	public Postulacion getPostulacion()
	{
		return postulacion;
	}
	
	public Busqueda getBusqueda()
	{
		return busqueda;
	}
	
    public void addBusqueda(Busqueda busqueda)
    {
    	this.busqueda = busqueda;
    	busqueda.setNotificacion(this);
    }
    
    
    public void addPostulacion(Postulacion postulacion)
    {
    	this.postulacion = postulacion;
    	postulacion.setNotificacion(this);
    }
    
	public void setBusqueda(Busqueda busqueda)
	{
		this.busqueda = busqueda;
	}
}
