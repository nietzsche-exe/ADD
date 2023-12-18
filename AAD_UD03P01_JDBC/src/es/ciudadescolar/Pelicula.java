package es.ciudadescolar;

import java.util.HashMap;

public class Pelicula 
{
	private String titulo;
	private Integer anio;
	private HashMap<String,Boolean> actores;

	public Pelicula()
	{
		actores = new HashMap<String,Boolean>();
	}
	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void addActorProta(String actor, Boolean prota)
	{
		actores.put(actor, prota);
	}

	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.titulo);
		sb.append("|");
		sb.append(this.anio.toString());
		sb.append("|");
		for (String key:actores.keySet())
		{
			sb.append(key);
			sb.append("(");
			sb.append(actores.get(key));
			sb.append(") ");
		}
		
		return "Pelicula ["+ sb.toString() + "]";
	}
	public HashMap<String, Boolean> getActores() 
	{
		return actores;
	}
	
	
}
