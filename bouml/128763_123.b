class PreguntaOberta
!!!131323.java!!!	PreguntaOberta(in text : String, in puntuacio : double)
		super(text, puntuacio);
!!!131451.java!!!	PreguntaOberta(in text : String)
		super(text);
!!!131579.java!!!	getEnunciatPregunta(in num : int) : String
		return super.crearEnunciatPregunta(num, "  "+StringUtils.repeat("_",Examen.AMPLE_ENUNCIAT-2)+System.lineSeparator()+
												StringUtils.repeat(" |"+StringUtils.repeat(" ",Examen.AMPLE_ENUNCIAT-3)+" |"+System.lineSeparator(), 5)+
												" |"+StringUtils.repeat("_",Examen.AMPLE_ENUNCIAT-3)+"_|")+System.lineSeparator();
