package m3.uf4.pe1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class PreguntaOpcions extends Pregunta {

	private static final long serialVersionUID = 1L;
	private Set<String> opcions;

	public PreguntaOpcions() {
		
	}
	
	public PreguntaOpcions(String text, double puntuacio, String[] opcions) throws Excepcio {
		super(text, puntuacio);
		if (opcions != null && opcions.length > 2) {
			this.opcions = new HashSet<String>(Arrays.asList(opcions));
		} else {
			throw new Excepcio("PreguntaOpcions", "[PreguntaOpcions] El nombre de opcions no pot ser null ni inferior a 2");
		}
	}

	public PreguntaOpcions(String text, Set<String> opcions) {
		super(text);
		this.opcions = opcions;
	}

	public Set<String> getOpcions() {
		return opcions;
	}

	public void setOpcions(Set<String> opcions) {
		if (opcions == null) return;
		this.opcions = opcions;
	}

	@Override
	public String getEnunciatPregunta(int num) {
		String resposta = System.lineSeparator();
		Iterator<String> iteratorSet = this.opcions.iterator();
		while(iteratorSet.hasNext()){
			resposta += StringUtils.rightPad("  "+'\u20DE'+"  "+iteratorSet.next(), Examen.AMPLE_ENUNCIAT+2, ".")+System.lineSeparator();
		}

		return super.crearEnunciatPregunta(num, resposta);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((opcions == null) ? 0 : opcions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreguntaOpcions other = (PreguntaOpcions) obj;
		if (opcions == null) {
			if (other.opcions != null)
				return false;
		} else if (!opcions.equals(other.opcions))
			return false;
		return true;
	}

}
