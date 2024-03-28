package ConexionBD;

import java.util.Scanner;

import arbolBinarioBusqueda.arbolBinarioDeBusqueda;

public class Service {
	static arbolBinarioDeBusqueda diccionario= new arbolBinarioDeBusqueda();
	
	
	public static void listarPalabras()
	{
		DAO.llenarArbol(2, diccionario);
	}
	public static void llenarArbolDesdeBaseDeDatos()
	{
		DAO.llenarArbol(1, diccionario);
	}
	
	public static void ingresarNuevaPalabra()
	{
		Scanner imput =new Scanner(System.in);
		String nombre,definicion;
		
		System.out.print("Ingrese nombre de palabra: ");
		nombre=imput.nextLine();
		System.out.print("Definicion: ");
		definicion=imput.nextLine();
		if(nombre.isEmpty() && definicion.isEmpty())
		{
			System.out.println("parametros no validos");
			//diccionario.insertar(nombre, definicion);	
		}else
		{

			diccionario.insertar(nombre, definicion);
			
		}
		
		
		
	}
	public static void editarPalabra()
	{
		Scanner imput =new Scanner(System.in);
		String palabra,definicion;
		System.out.print("Ingrese palabra a editar");
		palabra=imput.nextLine();
		System.out.print("Nueva Definicion: ");
		definicion=imput.nextLine();
		diccionario.editarPalabra(palabra, definicion);
		
		
	}
	
	public static void buscarPalabra()
	{
		Scanner imput =new Scanner(System.in);
		
		String palabra;
		System.out.print("Palabra a buscar: ");
		palabra=imput.nextLine();
		System.out.println("Definicion: "+diccionario.buscar(palabra));
		
		
	}
	
	public static void eliminarPalabra()
	{
		Scanner imput =new Scanner(System.in);
		String palabra;
		System.out.print("Palabra a eliminar: ");
		palabra=imput.nextLine();
		
		diccionario.eliminar(palabra);
		
		
	}
	public static void recorrerArbolInOrden()
	{
		diccionario.recorrido_in_orden(diccionario.getRaiz());
	}
	public static void recorrerArbolPostOrden()
	{
		diccionario.recorrido_post_orden(diccionario.getRaiz());
	}
	public static void recorrerArbolPreOrden()
	{
		diccionario.recorrido_pre_orden(diccionario.getRaiz());
	}
}
