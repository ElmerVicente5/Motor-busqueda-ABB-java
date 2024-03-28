package arbolBinarioBusqueda;


public class Nodo {
	String palabra; // guarda el nombre de la palabra
	String definicion;//guarda la definicion de la palabra
    Nodo izquierdo, derecho;// Punteros a los nodos hijos izquierdo y derecho
    int altura;
    
    public Nodo(String palabra, String definicion) {
        this.palabra=palabra;
        this.definicion=definicion;
        this.altura=1;
        izquierdo = derecho = null;
    }
    
    

}
