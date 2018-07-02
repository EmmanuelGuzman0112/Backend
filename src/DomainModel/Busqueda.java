package DomainModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Busqueda
{
	@Id
	@GeneratedValue
	private Long id;
	
	private String instrumento;
	private String experiencia;
	private String fechaExp;
	
	//Relacion bidireccional
	@OneToMany(mappedBy = "busqueda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Notificacion> Notificaciones = new ArrayList<Notificacion>();
	
    public Long getId()
    {
		return id;
    }
	
	public String getInstrumento()
	{
		return instrumento;
	}
	
	public String getExperiencia()
	{
		return experiencia;
	}
	
	public String getFechaExp()
	{
		return fechaExp;
	}
	
	public Busqueda()
	{
		
	}

	public Busqueda(String instrumento, String experiencia, String fechaExp)
	{
		this.instrumento = instrumento;
		this.experiencia = experiencia;
		this.fechaExp = fechaExp;
	}
	
    public void addNotificacion(Notificacion notificacion)
    {
    	Notificaciones.add(notificacion);
    	notificacion.setBusqueda(this);
    }
    
    public void setNotificacion(Notificacion notificacion)
    {
    	Notificaciones.add(notificacion);
    }
}
