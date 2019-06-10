/*
 * @Miguel
 * 
 * Clase de la grafica lineal
 * 
 * */
package modelo;

public class GraphLineInfo {
	private int x;

	private int y;
	
	//contructor de la grafica
	
	public GraphLineInfo(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	

	//definicion de los metodos getter 
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

}
