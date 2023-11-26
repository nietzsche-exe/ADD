package ADD_EX01E02;

public class Genero {

	private String nombre;
	private int total;
	private float mediaPuntuacion;
	
	public Genero() {
		
	}
	
	public Genero(String nombre, int total, float mediaPuntuacion) {
		super();
		this.nombre = nombre;
		this.total = total;
		this.mediaPuntuacion = mediaPuntuacion;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public float getMediaPuntuacion() {
		return mediaPuntuacion;
	}



	public void setMediaPuntuacion(float mediaPuntuacion) {
		this.mediaPuntuacion = mediaPuntuacion;
	}



	@Override
	public String toString() {
		return "Genero [nombre=" + nombre + ", total=" + total + ", mediaPuntuacion=" + mediaPuntuacion + "]";
	}
	
}


