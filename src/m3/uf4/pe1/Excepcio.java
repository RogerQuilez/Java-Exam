package m3.uf4.pe1;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Excepcio extends Exception {

	private static final long serialVersionUID = 1L;
	private static Map<Date, String> registre = new TreeMap<Date, String>();
	private String classe;
	
	public Excepcio(String classe, String message) {
		super(message);
		this.classe = classe;
		registre.put(new Date(), message);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		
		}
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	public static String registreErrors() {
		String registreErrors = "Registre d'Errors: " + System.lineSeparator();
		int i = 0;
		for(Map.Entry<Date,String> entry : registre.entrySet()) {
			  Date key = entry.getKey();
			  String value = entry.getValue();
			  registreErrors += i + " " + key + " => " + value +  System.lineSeparator();
			  i++;
			}
		return registreErrors;
	}
}
