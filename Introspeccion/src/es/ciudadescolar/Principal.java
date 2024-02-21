package es.ciudadescolar;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Principal {

	public static void main(String[] args) {

		
		Class classAlumno = Alumno.class;
		
		mostrarConstructores (classAlumno);
		mostrarAtributos (classAlumno);
		mostrarMetodos(classAlumno);
		
		
		//Alumno al = new Alumno();
		
		Alumno al = (Alumno)instanciarObjeto(classAlumno);
//		al.setExpediente("12");
//		al.setNombre("Jon");
		invocarMetodoVoid(al, "setExpediente", new Class[] {String.class}, new Object[] {new String("12")});
		invocarMetodoVoid(al, "setNombre", new Class[] {String.class}, new Object[] {new String("Jon")});
		
		String toString = (String)invocarMetodo(al, "toString", new Class[] {}, new Object[] {});
		
		invocarMetodo(System.out, "println", new Class[] {String.class}, new Object[] {new String(toString)});
		
	}
	

	private static void invocarMetodoVoid(Object objeto, String nombreMetodo, Class[] parametrosFormales, Object[] valores) {
		// TODO Auto-generated method stub
		try {
		Method metodo = objeto.getClass().getDeclaredMethod(nombreMetodo, parametrosFormales);
		metodo.invoke(objeto, valores);
		}
		catch(NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch(SecurityException e) {
			e.printStackTrace();
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		catch(InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	private static Object invocarMetodo(Object objeto, String nombreMetodo, Class[] parametrosFormales, Object[] valores) {
		// TODO Auto-generated method stub
		
		Object returnedObject = null;
		try {
		Method metodo = objeto.getClass().getDeclaredMethod(nombreMetodo, parametrosFormales);
		returnedObject = metodo.invoke(objeto, valores);
		}
		catch(NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch(SecurityException e) {
			e.printStackTrace();
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		catch(InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return returnedObject;
	}


	private static Object instanciarObjeto(Class clase) {
		
		Class[] parametrosFormales = {};
		Object[] valoresParametros= {};
		
		Object objeto = null;
		
		// quiero invocar el constructor por defecto8 sin parametros)
		Constructor cons = null;
		try{ 
		cons = clase.getDeclaredConstructor(parametrosFormales);
		
		objeto = cons.newInstance(parametrosFormales);
		
		}catch(Exception e) {
			
		}
		return objeto;
		
	}


	private static void mostrarMetodos(Class clase) {
		
		Method[] metodos = clase.getDeclaredMethods();
		
		for (Method met:metodos) {
			Class[] parametrosFormales = met.getParameterTypes();
			System.out.println("-------------método---------------");
			if(parametrosFormales.length== 0) {
				System.out.println("No tiene parámetros");
			}
			System.out.println("Método: "+met.getName());
			
			for(Class param:parametrosFormales) {
				System.out.println("Parámetro: "+param.getName()+ " de tipo "+param.getTypeName());
			}
			System.out.println("Devuelve: "+ met.getReturnType().getName());
			
			int modificadroes = met.getModifiers();
			
			if(Modifier.isPrivate(modificadroes)) System.out.println("método privado");
			if(Modifier.isPublic(modificadroes)) System.out.println("método público");
			if(Modifier.isStatic(modificadroes)) System.out.println("método estático");
			
			Class[] excepcionesPropagadas = met.getExceptionTypes();
			for(Class excep :excepcionesPropagadas) {
				
				System.out.println("propaga excepción: "+excep.getName());
			}
		}
		
	}


	private static void mostrarAtributos(Class clase) {
		
		Field[] atributos = clase.getDeclaredFields();
		for(Field atrib:atributos) {
			
			System.out.println("-------atributo--------");
			System.out.println("Atributo:"+atrib.getName()+" de tipo "+atrib.getType());
		}
	}


	private static void mostrarConstructores(Class clase) {
		
		//mediante introspección vamos a recuperar los distintos constructores que tiene la clase
		Constructor[] constructores = clase.getDeclaredConstructors();
		
		for (Constructor cons:constructores) {
			
			System.out.println("---------constructor----------");
			Class[] parametrosFormales = cons.getParameterTypes();
			if(parametrosFormales.length == 0) {
				System.out.println("Cosntructor sin parámetros (por defecto)");
			}
			for(Class claseParametro:parametrosFormales) {
				
				System.out.println("Parametros: "+claseParametro.getName());
			}
		}
		
	}

}
