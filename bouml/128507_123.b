class Pregunta
!!!130299.java!!!	Pregunta(in text : String, in puntuacio : double)
		this.text = text==null?"":text;
		this.puntuacio = puntuacio<0?PUNTUACIO_DEFECTE:puntuacio;
!!!130427.java!!!	Pregunta(in text : String)
		this.text = text==null?"":text;
		this.puntuacio = PUNTUACIO_DEFECTE;
!!!130555.java!!!	getText() : String
		return text;
!!!130683.java!!!	setText(in text : String) : void
		if (text == null) return;
		this.text = text;
!!!130811.java!!!	getPuntuacio() : double
		return puntuacio;
!!!130939.java!!!	setPuntuacio(in puntuacio : double) : void
		if (puntuacio < 0) return;
		this.puntuacio = puntuacio;
!!!131195.java!!!	crearEnunciatPregunta(in num : int, in resposta : String) : String
		return WordUtils.wrap("Pregunta "+num+". ("+(new DecimalFormat("#0.0")).format(this.puntuacio)+" pts) "+this.text,
				Examen.AMPLE_ENUNCIAT, System.lineSeparator(), true)+
				System.lineSeparator()+resposta;
