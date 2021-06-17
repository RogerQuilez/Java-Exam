package m3.uf4.pe1;

import org.apache.commons.lang3.StringUtils;

public class PreguntaOberta extends Pregunta {

	private static final long serialVersionUID = 1L;
	
	public PreguntaOberta() {
		
	}

	public PreguntaOberta(String text, double puntuacio) {
		super(text, puntuacio);
	}
	
	public PreguntaOberta(String text) {
		super(text);
	}

	@Override
	public String getEnunciatPregunta(int num) {
		return super.crearEnunciatPregunta(num, "  "+StringUtils.repeat("_",Examen.AMPLE_ENUNCIAT-2)+System.lineSeparator()+
												StringUtils.repeat(" |"+StringUtils.repeat(" ",Examen.AMPLE_ENUNCIAT-3)+" |"+System.lineSeparator(), 5)+
												" |"+StringUtils.repeat("_",Examen.AMPLE_ENUNCIAT-3)+"_|")+System.lineSeparator();
	}

}
