package m3.uf4.pe1;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.commons.lang3.text.WordUtils;

public abstract class Pregunta implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final double PUNTUACIO_DEFECTE = 1.0;
	protected String text;
	protected double puntuacio;
	
	public Pregunta() {
		
	}

	public Pregunta(String text, double puntuacio) {
		this.text = text==null?"":text;
		this.puntuacio = puntuacio<0?PUNTUACIO_DEFECTE:puntuacio;
	}
	
	public Pregunta(String text) {
		this.text = text==null?"":text;
		this.puntuacio = PUNTUACIO_DEFECTE;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (text == null) return;
		this.text = text;
	}

	public double getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(double puntuacio) {
		if (puntuacio < 0) return;
		this.puntuacio = puntuacio;
	}

	public abstract String getEnunciatPregunta(int num);

	protected String crearEnunciatPregunta(int num, String resposta) {
		return WordUtils.wrap("Pregunta "+num+". ("+(new DecimalFormat("#0.0")).format(this.puntuacio)+" pts) "+this.text,
				Examen.AMPLE_ENUNCIAT, System.lineSeparator(), true)+
				System.lineSeparator()+resposta;
	}

}
