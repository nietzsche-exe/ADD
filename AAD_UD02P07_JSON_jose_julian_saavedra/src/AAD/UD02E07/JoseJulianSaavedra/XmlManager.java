package AAD.UD02E07.JoseJulianSaavedra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase XmlManager encargada de parsear el archivo xml con el fin de sacar el valor de las etiquetas correspondientes para su posterior uso en la Clase JsonManager
 * La Clase extiende de la clase DefaultHandler para implemnetar distintos metodos abstractos
 * Usamos los metodos startDocument, endDocument, startElement y error
 * @author Jose Julian Saavedra  
 * @version 1.0
 * @since curso 2023-2024
 */
public class XmlManager extends DefaultHandler {
	
		private static final Logger LOGGER = LoggerFactory.getLogger(XmlManager.class);
	
		private static XmlManager etiquetas = new XmlManager();
		
		private String division;
		private String fecha;
		private String goles_visitante;
		private String goles_local;
		private String equipo_local;
		private String equipo_visitante;
		private String tarjetas_rojas_visitante;
		private String tarjetas_rojas_local;
		private String tarjetas_amarillas_visitante;
		private String tarjetas_amarillas_local;
		
		public XmlManager() {
	        
	        division = "";
	        fecha = "";
	        goles_visitante = "";
	        goles_local = "";
	        equipo_local = "";
	        equipo_visitante = "";
	        tarjetas_rojas_visitante = "";
	        tarjetas_rojas_local = "";
	        tarjetas_amarillas_visitante = "";
	        tarjetas_amarillas_local = "";
	        
	    }
		/**
		 * Metodo que devuele la division de un equipo
		 * @return String division
		 */
		public String getDivision() {
			return division;
		}

		/**
		 * Metodo que asigna un valor a division
		 * @param String division
		 */
		public void setDivision(String division) {
			this.division = division;
		}

		/**
		 * Metodo que devuele la fecha de un partido
		 * @return String fecha
		 */
		public String getFecha() {
			return fecha;
		}

		/**
		 * Metodo que asigna un valor a la fecha de un partido
		 * @param String fecha
		 */
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		/**
		 * Metodo que devuele los goles como visitantes de un partido
		 * @return String goles_visitante
		 */
		public String getGoles_visitante() {
			return goles_visitante;
		}

		/**
		 * Metodo que asigna un valor a los goles como visitantes de un partido
		 * @param String goles_visitantes
		 */
		public void setGoles_visitante(String goles_visitante) {
			this.goles_visitante = goles_visitante;
		}

		/**
		 * Metodo que devuele los goles como local de un partido
		 * @return String goles_locales
		 */
		public String getGoles_local() {
			return goles_local;
		}

		/**
		 * Metodo que asigna un valor a los goles como local de un partido
		 * @param String goles_locales
		 */
		public void setGoles_local(String goles_local) {
			this.goles_local = goles_local;
		}

		/**
		 * Metodo que devuele el nombre del equipo local de un partido
		 * @return String equipo_local
		 */
		public String getEquipo_local() {
			return equipo_local;
		}

		/**
		 * Metodo que asigna un valor al equipo local de un partido
		 * @param String equipo_local
		 */
		public void setEquipo_local(String equipo_local) {
			this.equipo_local = equipo_local;
		}

		/**
		 * Metodo que devuele el nombre del equipo visitante de un partido
		 * @return String equipo_visitante
		 */
		public String getEquipo_visitante() {
			return equipo_visitante;
		}

		/**
		 * Metodo que asigna un valor al equipo visitante de un partido
		 * @param String equipo_visitante
		 */
		public void setEquipo_visitante(String equipo_visitante) {
			this.equipo_visitante = equipo_visitante;
		}

		/**
		 * Metodo que devuele las tarjetas rojas del equipo visitante de un partido
		 * @return String tarjetas_rojas_visitantes
		 */
		public String getTarjetas_rojas_visitante() {
			return tarjetas_rojas_visitante;
		}

		/**
		 * Metodo que asigna un valor a las tarjetas rojas del equipo visitante de un partido
		 * @param String tarjetas_rojas_visitantes
		 */
		public void setTarjetas_rojas_visitante(String tarjetas_rojas_visitante) {
			this.tarjetas_rojas_visitante = tarjetas_rojas_visitante;
		}

		/**
		 * Metodo que devuele las tarjetas rojas del equipo local de un partido
		 * @return String tarjetas_rojas_local
		 */
		public String getTarjetas_rojas_local() {
			return tarjetas_rojas_local;
		}

		/**
		 * Metodo que asigna un valor a las tarjetas rojas del equipo local de un partido
		 * @param String tarjetas_rojas_local
		 */
		public void setTarjetas_rojas_local(String tarjetas_rojas_local) {
			this.tarjetas_rojas_local = tarjetas_rojas_local;
		}

		/**
		 * Metodo que devuele las tarjetas amarillas del equipo visitante de un partido
		 * @return String tarjetas_amarillas_visitante
		 */
		public String getTarjetas_amarillas_visitante() {
			return tarjetas_amarillas_visitante;
		}

		/**
		 * Metodo que asigna un valor a las tarjetas amarillas del equipo visitante de un partido
		 * @param String tarjetas_amarillas_visitante
		 */
		public void setTarjetas_amarillas_visitante(String tarjetas_amarillas_visitante) {
			this.tarjetas_amarillas_visitante = tarjetas_amarillas_visitante;
		}

		/**
		 * Metodo que devuele las tarjetas amarillas del equipo local de un partido
		 * @return String tarjetas_amarillas_local
		 */
		public String getTarjetas_amarillas_local() {
			return tarjetas_amarillas_local;
		}

		/**
		 * Metodo que asigna un valor a las tarjetas amarillas del equipo local de un partido
		 * @param String tarjetas_amarillas_local
		 */
		public void setTarjetas_amarillas_local(String tarjetas_amarillas_local) {
			this.tarjetas_amarillas_local = tarjetas_amarillas_local;
		}

		/**
		 * Metodo que devuele el objeto etiquetas mediante el cual se hara referencia en la Clase JsonManager
		 * @return String etiquetas
		 */
		public static XmlManager getEtiquetas() {
			return etiquetas;
		}

	
		@Override
		public void startDocument() throws SAXException {
			LOGGER.debug("Inicio parseo XML");
		}

		@Override
		public void endDocument() throws SAXException {
			LOGGER.debug("Finalizacion parseo XML");
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) {
				
			 if (qName.equalsIgnoreCase("atributo")) {
		            String nombre = attributes.getValue("name");
		            String valor = attributes.getValue("value");
		            
		            switch (valor) {
	                case "division":
	                    etiquetas.setDivision(nombre);
	                    break;
	                case "fecha":
	                	etiquetas.setFecha(nombre);
	                    break;
	                case "goles visitante":
	                	etiquetas.setGoles_visitante(nombre);
	                    break;
	                case "goles local":
	                	etiquetas.setGoles_local(nombre);
	                    break;
	                case "equipo local":
	                	etiquetas.setEquipo_local(nombre);
	                    break;
	                case "equipo visitante":
	                	etiquetas.setEquipo_visitante(nombre);
	                    break;
	                case "tarjetas rojas visitante":
	                	etiquetas.setTarjetas_rojas_visitante(nombre);
	                    break;
	                case "tarjetas rojas local":
	                	etiquetas.setTarjetas_rojas_local(nombre);
	                    break;
	                case "tarjetas amarillas visitante":
	                	etiquetas.setTarjetas_amarillas_visitante(nombre);
	                    break;
	                case "tarjetas amarillas local":
	                	etiquetas.setTarjetas_amarillas_local(nombre);
	                    break;
		            }
			 }
				
		
		}

		@Override
		public void error(SAXParseException e) throws SAXException {
			LOGGER.debug("Error de parseo en el XmlManager");
		}
	
}
