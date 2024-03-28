package arbolBinarioBusqueda;

import ConexionBD.*;

public class arbolBinarioDeBusqueda  {
	Nodo raiz;
	
	public arbolBinarioDeBusqueda()
	{
		
		raiz=null;
	}
	
	
	public void insertar(String palabra,String definicion)
	{
		if(palabra=="\n" || definicion=="\n")
		{
			
			System.out.println("ingrese datos validos");
		}else
		{
			if(raiz==null)
			{
				System.out.println("se inserto la raiz: "+ palabra);
			}
				raiz=insertarNodo(raiz,palabra,definicion);
			
				DAO.insertarPalabra(palabra, definicion);
		}
		
		
	}
	public void llenarArboldesdeBD(String palabra,String definicion)
	{
		if(raiz==null)
		{
			System.out.println("se inserto la raiz: "+ palabra);
		}
		raiz=insertarNodo(raiz,palabra,definicion);
		
			
	}
	
	public Nodo insertarNodo(Nodo nodo,String palabra,String definicion)
	{
		//verifica si la raiz está vacia para insertar un nuevo nodo
		if(nodo==null)
		{
			
			nodo =new Nodo(palabra,definicion);
		}
		
		//verifica si la palabra ingresada tiene un valor menor que la palabra que
		//contenga la raiz, si se cumple lo inserta a la izquierda
		//de lo contrario insertara el nodo a la derecha
		//el orden irá segun su orden lexicografico
		if(palabra.compareTo(nodo.palabra)<0)
		{
			nodo.izquierdo=insertarNodo(nodo.izquierdo,palabra,definicion);
		}else if(palabra.compareTo(nodo.palabra)>0)
		{
			nodo.derecho=insertarNodo(nodo.derecho,palabra,definicion);
		}
		//acutalizar la altura del nodo
		/*
		nodo.altura=1+Math.max(getHeight(nodo.izquierdo),getHeight(nodo.derecho));
		
		int balance=getBalance(nodo);
		if(balance>1 && palabra.compareTo(nodo.izquierdo.palabra)<0)
		{
			//caso de rotacion derecha
			return rightRotate(nodo);
		}
		if(balance<-1 && palabra.compareTo(nodo.derecho.palabra)>0)
		{
			//caso de rotacion izquierda
			return leftRotate(nodo);
		}
		if(balance >1 && palabra.compareTo(nodo.derecho.palabra)>0)
		{
			//caso de rotacion izquierda-dercha
			nodo.izquierdo=leftRotate(nodo.izquierdo);
			return rightRotate(nodo);
		}
		if(balance <-1 && palabra.compareTo(nodo.derecho.palabra)<0)
		{
			//caso de rotacion derecha-izquierda
			nodo.derecho=rightRotate(nodo.derecho);
			return leftRotate(nodo);
		}
			*/
		
		return nodo;
	}
	// Realiza una rotación a la derecha del nodo dado
	private Nodo rightRotate(Nodo y) {
	    Nodo x = y.izquierdo;
	    Nodo T2 = x.derecho;

	    // Realiza la rotación
	    x.derecho= y;
	    y.izquierdo = T2;

	    // Actualiza las alturas
	    y.altura = Math.max(getHeight(y.izquierdo), getHeight(y.derecho)) + 1;
	    x.altura = Math.max(getHeight(x.izquierdo), getHeight(x.derecho)) + 1;

	    return x;
	}

	// Realiza una rotación a la izquierda del nodo dado
	private Nodo leftRotate(Nodo x) {
	    Nodo y = x.derecho;
	    Nodo T2 = y.izquierdo;

	    // Realiza la rotación
	    y.izquierdo= x;
	    x.derecho = T2;

	    // Actualiza las alturas
	    x.altura= Math.max(getHeight(x.izquierdo), getHeight(x.derecho)) + 1;
	    y.altura= Math.max(getHeight(y.izquierdo), getHeight(y.derecho)) + 1;

	    return y;
	}

	//funcion para obtener el valor del balance de un nodo
	private int getBalance(Nodo node) {
	    if (node == null) {
	        return 0;
	    }
	    return getHeight(node.izquierdo) - getHeight(node.derecho);
	}
	private int getHeight(Nodo node) {
	    if (node == null) {
	        return 0;
	    }
	    return node.altura;
	}
	//a traves de la funcion buscar palabra, busco el nodo que contiene la palabra
	public String buscar(String palabra)
	{
		Nodo resultado=buscarPalabra(raiz,palabra);
		if(resultado !=null)
		{
			return resultado.definicion;
		}else {
			
			return "palabra no encontrada";
		}
	}
	
	private Nodo buscarPalabra(Nodo raiz,String palabra)
	{
		//verifica si la ya se llegó al ultimo nodo, en caso contrario si ya se encontró la palabra
		if(raiz== null || raiz.palabra.equals(palabra)) {
			return raiz;
		}
		//compara si la palabra a buscar tiene un valor menor a la raiz para buscar a la izquierda
		if(palabra.compareTo(raiz.palabra)<0)
		{
			return buscarPalabra(raiz.izquierdo,palabra);
		}
		
		//en caso contrario que sea menor a la raiz, se busca a la derecha
		
		return buscarPalabra(raiz.derecho,palabra);
	}
	
	//mandamo los parametrros a eliminar, que empiece a buscar desde la raiz
	
	public void eliminar(String palabra)
	{
		Nodo resultado=eliminarPalabra(raiz,palabra);
		//raiz=eliminarPalabra(raiz,palabra);
		
		if(resultado!=null)
		{
			raiz=eliminarPalabra(raiz,palabra);
			System.out.println("Se elimino la palabra correctamente");
			DAO.borrarPalabraDB(palabra);	
		}else
		{
			System.out.println("no se pudo eliminar la palabra porque no existe en el arbol");
		}
		
	}
	
	private Nodo eliminarPalabra(Nodo raiz,String palabra)
	{
		// Caso base: Si el nodo es nulo, se ha llegado al final del árbol sin encontrar la palabra
		if(raiz==null ) 
			{
			return raiz;
			}
		
		
		if(palabra.compareTo(raiz.palabra)<0)
		{
			// La palabra es menor, buscar en el subárbol izquierdo
			raiz.izquierdo=eliminarPalabra(raiz.izquierdo,palabra);	
		}else if(palabra.compareTo(raiz.palabra)>0)
		{
			// La palabra es mayor, buscar en el subárbol derecho
			raiz.derecho=eliminarPalabra(raiz.derecho,palabra);
		}else {
			
			
			
			/* se verifica si el nodo a eliminar tiene un hijo izquierdo o derecho nulo.
			 *  Si el hijo izquierdo es nulo, se devuelve el hijo derecho para reemplazar el nodo actual.
			 *   Si el hijo derecho es nulo, se devuelve el hijo izquierdo para reemplazar el nodo actual.
			 * 
			 * */
			
			// Caso 1: Nodo sin hijos o con un solo hijo
			if(raiz.izquierdo==null)
			{
				
				return raiz.derecho;	
			}
			else if(raiz.derecho==null)
			{
				return raiz.izquierdo;
			}
			// Caso 2: Nodo con dos hijos
			Nodo successor=valorMinimo(raiz.derecho);
            // Encontrar el nodo mínimo en el subárbol derecho para reemplazar el nodo actual
			raiz.palabra=successor.palabra;
			raiz.definicion=successor.definicion;
			
			// Eliminar el nodo mínimo del subárbol derecho
			raiz.derecho=eliminarPalabra(raiz.derecho,raiz.palabra);
			
		}
		
			
		
		
		
		
		// Retornar el nodo actualizado
		return raiz;
	}
	//regla para eliminar un nodo que se encuentra a la derecha
	private Nodo valorMinimo(Nodo raiz)
	{
		
		while(raiz.izquierdo!=null)
		{
			raiz=raiz.izquierdo;
		}
		return raiz;
	}
	
	public void editarPalabra(String palabra, String nuevaDefinicion)
	{
		raiz=editarPalabraEnArbol(raiz,palabra,nuevaDefinicion);
		System.out.println("se edito la palabra correctamente");
				
	}
	private Nodo editarPalabraEnArbol(Nodo raiz,String palabra,String nuevaDefinicion)
	{
		if(raiz==null)
		{
			return null;// la palabra no se encontró en el arbol
		}
		if(palabra.compareTo(raiz.palabra)<0)
		{
			raiz.izquierdo=editarPalabraEnArbol(raiz.izquierdo,palabra,nuevaDefinicion);
		}else if(palabra.compareTo(raiz.palabra)>0)
		{
			raiz.derecho=editarPalabraEnArbol(raiz.derecho,palabra,nuevaDefinicion);
		}else
		{
			raiz.definicion=nuevaDefinicion;//actualiza la definicion de la palabra encontrada
		}
		
		return raiz;
	}
	// Realiza un recorrido en Post orden del árbol de forma recursiva (izquierda, derecho, raiz)
	 public void recorrido_post_orden(Nodo nodo)
	    {
	    	if(nodo!=null)
	    	{
	    		recorrido_post_orden(nodo.izquierdo);
	    		recorrido_post_orden(nodo.derecho);
	    		System.out.print(nodo.palabra+ " ");
	    	}
	    }
	// Realiza un recorrido en orden del árbol de forma recursiva (izquierda, raíz, derecha)
	    public void recorrido_in_orden(Nodo nodo) {
	        if (nodo != null) {
	            recorrido_in_orden(nodo.izquierdo);
	            System.out.print(nodo.palabra + " ");
	            recorrido_in_orden(nodo.derecho);
	        }
	    }
	    
	    // Realiza un recorrido en pre orden del árbol de forma recursiva (raíz, izquierdo, derecho)
	    public void recorrido_pre_orden(Nodo nodo)
	    {
	    	if(nodo!=null)
	    	{
	    		System.out.print(nodo.palabra+ " ");
	    		recorrido_pre_orden(nodo.izquierdo);
	    		recorrido_pre_orden(nodo.derecho);
	    		
	    	}	
	    }


		public Nodo getRaiz() {
			return raiz;
		}


		public void setRaiz(Nodo raiz) {
			this.raiz = raiz;
		}
	 
	

}
