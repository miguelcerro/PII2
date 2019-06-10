package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Mensaje {
	
	/* ATRIBUTOS */
	private String timeStamp;
	private String enviado;	//"supervisor" o "paciente"
	private String texto;
	
	
	/* CONSTRUCTORES */
	public Mensaje() {}
	public Mensaje(String timeStamp, String enviado, String texto) {
		this.timeStamp = timeStamp;	
		this.enviado = enviado;
		this.texto = texto;
	}

	/* SETTERS */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public void setEnviado(String enviado) {
		this.enviado = enviado;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/* GETTERS */
	public String getTimeStamp() {
		return timeStamp;
	}
	public String getEnviado() {
		return enviado;
	}
	public String getTexto() {
		return texto;
	}


	/* METODOS */
	public String printMensaje() {
		return printTimeStamp() +" / "+enviado+" / "+texto;
	}
	//Convierte fecha en string
	public String printTimeStamp() {
		return timeStamp;
	}
	// Devuelve la fecha en string
	public String printDate() {
		return timeStamp;
	}
	// Devuelve la hora en string
	public String printTime() {
		return timeStamp;
	}
	/*
	// Leer archivo mensajes
	public Mensaje[] leerMensajes(String file) {
		Mensaje[] lista = new Mensaje[20];
		int aux=0;
		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			String linea;
			do {
				linea = br.readLine();
				if (linea!=null) {
					String[] fila = linea.split(",");
					if (fila[0]!=null) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
						LocalDateTime dateTime = LocalDateTime.parse(fila[0], formatter);
						Mensaje m = new Mensaje(dateTime, fila[1], fila[2]);
						lista[aux]=m;
						aux++;
					}
				}
			} while (linea != null);
			br.close();
			return lista;
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "¡Error al cargar el fichero");
			e.printStackTrace();
		}
		return lista;
	}*/
	

	/* MAIN - Solo para pruebas */
	public static void main(String[] args) {
		User u1 = new User();
		u1.setNombre("Miguel Angel"); u1.setApellido1("del Cerro"); u1.setApellido2("Recuero");
		User u2 = new User();
		u2.setNombre("Pablo"); u2.setApellido1("Olivera"); u2.setApellido2("Maganto");
		
		// Crea un elemento de tiempo actual
		Calendar calendar = Calendar.getInstance ();
		java.util.Date now = calendar.getTime ();
		java.sql.Timestamp time = new java.sql.Timestamp (now.getTime ());
		
		/*Mensaje m = new Mensaje();
 		// Guarda la fecha/hora en el mensaje e imprime
 		m.setTimeStamp(time.toLocalDateTime());
 		System.out.println(m.printTime());
 		System.out.println(m.printDate());
 		System.out.println(m.printTimeStamp());
 		
 		// Guarda destino/remite/texto en mensaje e imprime
 		m.setDestinatario(u1);
 		m.setRemitente(u2);
 		m.setTexto("Hola Mundo!!");
 		System.out.print(m.printMensaje()); */
		
		
		// Creando lista de mensajes
	/*	Mensaje[] listaMensajes = new Mensaje[50];
		
		Mensaje m1 = new Mensaje();
		m1.setTimeStamp(time.toLocalDateTime());
		m1.setTexto("Hola, comemos?");
		Mensaje m2 = new Mensaje();
		m2.setTimeStamp(time.toLocalDateTime());
		m2.setTexto("Vale, cuando?");
		Mensaje m3 = new Mensaje();
		m3.setTimeStamp(time.toLocalDateTime());
		m3.setTexto("En 10 minutos");
		Mensaje m4 = new Mensaje();
		m4.setTimeStamp(time.toLocalDateTime());
		m4.setTexto("En el edificio A");
		Mensaje m5 = new Mensaje();
		m5.setTimeStamp(time.toLocalDateTime());
		m5.setTexto("Vale!!");
		listaMensajes[0]= m1; listaMensajes[1]= m2; listaMensajes[2]= m3; listaMensajes[3]= m4; listaMensajes[4]= m5;
		
		for (int i=0; i<5; i++) {
			System.out.println(listaMensajes[i].printMensaje());
		}	*/
		
		Mensaje[] lista = new Mensaje[20];
		String archCSV = "datos\\777777D.csv";
		//lista=leerMensajes(archCSV);
		
	/*	for (int i=0; i<3; i++) {
			System.out.println(lista[i].printMensaje());
		}*/
	} 
	
	public String toString() {
		return timeStamp+":"+enviado+"> "+texto;
	}
}