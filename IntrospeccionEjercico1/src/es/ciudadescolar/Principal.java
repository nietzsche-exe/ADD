package es.ciudadescolar;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Principal {

public static void main(String[] args) {

		
		Class classArrayList = ArrayList.class;
		Class classAlumno = Alumno.class;
		
		mostrarConstructores (classArrayList);
		mostrarAtributos (classArrayList);
		mostrarMetodos(classArrayList);
		
		
		
		Alumno al1 = (Alumno)instanciarObjeto(classAlumno);
		Alumno al2 = (Alumno)instanciarObjeto(classAlumno);
		Alumno al3 = (Alumno)instanciarObjeto(classAlumno);
		Alumno al4 = (Alumno)instanciarObjeto(classAlumno);
		Alumno al5 = (Alumno)instanciarObjeto(classAlumno);
		
		ArrayList arrayList = (ArrayList)instanciarObjeto(classArrayList);
		
		invocarMetodoVoid(al1, "setExpediente", new Class[] {String.class}, new Object[] {new String("2001")});
		invocarMetodoVoid(al1, "setNombre", new Class[] {String.class}, new Object[] {new String("Diego")});
		invocarMetodoVoid(al1, "setEdad", new Class[] {Integer.class}, new Object[] {new Integer(19)});
		
		invocarMetodoVoid(al2, "setExpediente", new Class[] {String.class}, new Object[] {new String("2002")});
		invocarMetodoVoid(al2, "setNombre", new Class[] {String.class}, new Object[] {new String("Manuel")});
		invocarMetodoVoid(al2, "setEdad", new Class[] {Integer.class}, new Object[] {new Integer(23)});
		
		invocarMetodoVoid(al3, "setExpediente", new Class[] {String.class}, new Object[] {new String("2003")});
		invocarMetodoVoid(al3, "setNombre", new Class[] {String.class}, new Object[] {new String("Javier")});
		invocarMetodoVoid(al3, "setEdad", new Class[] {Integer.class}, new Object[] {new Integer(20)});
		
		invocarMetodoVoid(al4, "setExpediente", new Class[] {String.class}, new Object[] {new String("2004")});
		invocarMetodoVoid(al4, "setNombre", new Class[] {String.class}, new Object[] {new String("Francisco")});
		invocarMetodoVoid(al4, "setEdad", new Class[] {Integer.class}, new Object[] {new Integer(25)});
		
		invocarMetodoVoid(al5, "setExpediente", new Class[] {String.class}, new Object[] {new String("2005")});
		invocarMetodoVoid(al5, "setNombre", new Class[] {String.class}, new Object[] {new String("Pedro")});
		invocarMetodoVoid(al5, "setEdad", new Class[] {Integer.class}, new Object[] {new Integer(18)});
		
		invocarMetodoVoid(arrayList, "add", new Class[] {Object.class}, new Object[] {al1});
		invocarMetodoVoid(arrayList, "add", new Class[] {Object.class}, new Object[] {al2});
		invocarMetodoVoid(arrayList, "add", new Class[] {Object.class}, new Object[] {al3});
		invocarMetodoVoid(arrayList, "add", new Class[] {Object.class}, new Object[] {al4});
		invocarMetodoVoid(arrayList, "add", new Class[] {Object.class}, new Object[] {al5});
		
		for (Object object : arrayList) {
			System.out.println(object);
		}
		
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
