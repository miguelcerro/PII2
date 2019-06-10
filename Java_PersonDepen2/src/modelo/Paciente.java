/*
 * @autor Miguel
 * 
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import Database.HandlerBBDD;





public class Paciente {
	
	User u = null;
	
	//metodo que realiza la lectura del csv
	
	public User recoveryUserData (String username) {
		
		return HandlerBBDD.getBBDD().recuperar(username);
		/*
		String archCSV = "datos\\usuarios.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(archCSV));) {
			String linea;
			do {
				linea = br.readLine();
				String[] fila = linea.split(",");
				if (fila[15].equals(username) ) {
					u = new User(fila[0],fila[1], fila[2], fila[3], fila[4], fila[5], fila[6],
							parseDate(fila[7]), fila[8], fila[9], fila[10], fila[11], fila[12],
							fila[13], fila[14],fila[15],fila[16], linea);	
					
					return u;
				}

			} while (linea != null);
			br.close();
		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, "¡Error al cargar el fichero");
			e.printStackTrace();
		} finally {
			return u;
		} */
		
	}
	
	//metodo que actualiza los datos en el csv
	
	public void saveUser (User user, String username) throws IOException {
		HandlerBBDD.getBBDD().actualiza(user);
		/*
		String archCSV = "datos\\usuarios.csv";
		
		CSVReader csvReader = new CSVReader(new FileReader(archCSV));
		List<String[]> csvBody =csvReader.readAll();
        // get CSV row column and replace with by using row and column
        for(int i=0; i<csvBody.size(); i++){
            String[] strArray = csvBody.get(i);
            if (strArray[15].equals(username)) {
            	
            	csvBody.get(i)[1]=user.getNombre();
            	csvBody.get(i)[2]=user.getApellido1();
            	csvBody.get(i)[3]=user.getApellido2();
            	csvBody.get(i)[4]=user.getDni();
            	csvBody.get(i)[5]=user.getTelefonoMovil();
            	csvBody.get(i)[6]=user.getTelefonoCasa();
            	csvBody.get(i)[7]=user.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));  /*formateamos para ser valida la fecha actualizada
            	csvBody.get(i)[8]=user.getCorreo();
            	csvBody.get(i)[9]=user.getFotoUsuario();
            	csvBody.get(i)[10]=user.getCalle();
            	csvBody.get(i)[11]=user.getNumeroCalle();
            	csvBody.get(i)[12]=user.getCiudad();
            	csvBody.get(i)[13]=user.getProvincia();
            	csvBody.get(i)[14]=user.getCP();      			
          	
            }
            
        }
        csvReader.close();

        // Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(archCSV), ',',CSVWriter.NO_ESCAPE_CHARACTER);
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();*/
    }
		

	// formatea fecha
	
	private LocalDate parseDate(String date) {
		// TODO Auto-generated method stub
		return  LocalDate.parse(date,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	
	
}
