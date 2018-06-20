package DomainModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Postulacion
{
	@Id
	@GeneratedValue
	private Long id;
	
	private String generoMusical;
	private String instrumento;
	
	//id de musico?
	
	//Relacion bidireccional
	@OneToMany(mappedBy = "postulacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Notificacion> Notificaciones = new ArrayList<Notificacion>();
	
    public Long getId()
    {
		return id;
    }
	
	public String getGeneroMusical()
	{
		return generoMusical;
	}
	
	public String getInstrumento()
	{
		return instrumento;
	}
	
	public Postulacion()
	{
		
	}
	
	public Postulacion (String generoMusical, String instrumento)
	{
		this.generoMusical = generoMusical;
		this.instrumento = instrumento;
	}
	
    public void setNotificacion(Notificacion notificacion)
    {
    	Notificaciones.add(notificacion);
    }
}
