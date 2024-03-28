package arbolBinarioBusqueda;

import java.util.Scanner;
import ConexionBD.Service;

public class AppDiccionario {
	public static void main(String[] args)
	{
	   
		
	int opcionSeleccionada=0;
	Scanner imput=new Scanner(System.in);
	Service.llenarArbolDesdeBaseDeDatos();
		do {
			
			
			
			System.out.println("\n \n");
			System.out.println("1.Buscar palabra en diccionario");
			System.out.println("2.Agregar nueva palabra al diccionario");
			System.out.println("3.listar palabras del diccionario");
			System.out.println("4.Editar palabra");
			System.out.println("5 Eliminar Palabra");
			System.out.println("6.Recorrer el arbol InOrden");
			System.out.println("7.Recorrer el arbol PreOrden");
			System.out.println("8.Recorrer el arbol PostOrden");
			opcionSeleccionada=imput.nextInt();
			
			switch(opcionSeleccionada)
			{
			case 1:
				
				Service.buscarPalabra();
				
				break;
			case 2:
				Service.ingresarNuevaPalabra();
				break;
			case 3:
				Service.listarPalabras();
				break;
			case 4:
				Service.editarPalabra();
				break;
			case 5:
				Service.eliminarPalabra();
				break;
			case 6:
				Service.recorrerArbolInOrden();
				break;
			case 7:
				Service.recorrerArbolPreOrden();
				break;
			case 8:
				Service.recorrerArbolPostOrden();
				break;
			default:
				break;
			}
			
		}while(opcionSeleccionada!=9);
		
		/*	
	arbolBinarioDeBusqueda diccionario= new arbolBinarioDeBusqueda();
	
	
	DAO.llenarArbol(1,diccionario);
	DAO.llenarArbol(2, diccionario);
	
	
	
	diccionario.insertar("manzana", "la manzana es una fruta comestible");
	
	diccionario.insertar("Banana", "la manzana es una fruta comestible");
	diccionario.insertar("Pera", "la pera es una fruta comestible");
	diccionario.insertar("piña", "la pina es una fruta comestible");
	diccionario.insertar("arandano", "el arandano es una fruta comestible");
	
	
	
	String definicion= diccionario.buscar("zarpar");
	System.out.println("definicion: "+definicion);
	diccionario.eliminar("benevolencia");
	//DAO.borrarPalabraDB("integro");
	*/
	}

}
