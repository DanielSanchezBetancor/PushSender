package pushsender;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

public class FileAlterer {
	Properties props = new Properties();

	/*
	 * Comprueba la existencia del fichero de configuracion. Existe -> True ; No existe -> False
	 */
	public boolean checkFile() {
		try {
			FileInputStream is = new FileInputStream("config");
			is.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/* Se llama para guardar los ficheros iniciales */
	public void saveFiles(String opt) {
		try {
			savePropsFile(0, opt);
			FileOutputStream fos = new FileOutputStream("messages");
			fos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean saveData(String[] content) {
		String[][] preparedData = prepareData(getData(), content);
		try {
			FileOutputStream fos = new FileOutputStream("messages");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(preparedData);
			oos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/*Recoge los datos del fichero*/
	public String[][] getData() {
		String[][] data = new String[4][getCounter()];
		try {
			FileInputStream fis = new FileInputStream("messages");
			ObjectInputStream ois = new ObjectInputStream(fis);
			data = (String[][]) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("Error - FileAlterer - getData - EOFE");
			return new String[4][1];
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}

	// Puntero para saber la cantidad de datos en el fichero
	public int getCounter() {
		int aux = 0;
		try {
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream("config");
			props.load(fis);
			aux = Integer.parseInt(props.getProperty("counter"));
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aux;
	}
	/*Copia los datos del fichero en un array mas grande (+1)*/
	private String[][] prepareData(String[][] data, String[] content) {
		String[][] copy = new String[4][getCounter() + 1];
		for (int i = 0; i < getCounter(); i++) {
			copy[0][i] = data[0][i];
			copy[1][i] = data[1][i];
			copy[2][i] = data[2][i];
			copy[3][i] = data[3][i];
			System.out.println(copy[0][i] + " " + data[0][i]);
			System.out.println(copy[1][i] + " " + data[1][i]);
			System.out.println(copy[2][i] + " " + data[2][i]);
			System.out.println(copy[3][i] + " " + data[3][i]);
		}
		copy[0][getCounter()] = content[0];
		copy[1][getCounter()] = content[1];
		copy[2][getCounter()] = content[2];
		copy[3][getCounter()] = content[3];
		addCounter();
		return copy;
	}

	private void addCounter() {
		int newCounter = getCounter() + 1;
		savePropsFile(newCounter, getRegionFile());
		
	}
	/* Devuelve la region leida en el fichero */
	public String getRegionFile() {
		try {
			FileInputStream is = new FileInputStream("config");
			props.load(is);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty("region");
	}
	private void savePropsFile(int newCounter, String region) {
		try {
			FileOutputStream fos;
			fos = new FileOutputStream("config");
			props.setProperty("counter", Integer.toString(newCounter));
			props.setProperty("region", region);
			props.store(fos, "config file");
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
