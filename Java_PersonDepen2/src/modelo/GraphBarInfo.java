/*
 * @Miguel
 * 
 * Clase de la grafica BAr
 * 
 * */

package modelo;

public class GraphBarInfo {
	
		private String x;

		private int y;
		
		
		//contructor de la grafica
		
		public GraphBarInfo(String x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
		//definicion de los metodos getter 
		public String getX() {
			return x;
		}


		public int getY() {
			return y;
		}

}
