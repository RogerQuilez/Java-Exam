class PreguntaOpcions
!!!131707.java!!!	PreguntaOpcions(in text : String, in puntuacio : double, inout opcions : String [[]])
		super(text, puntuacio);
		if (opcions == null) this.opcions = new String[]{};
		else this.opcions = opcions;
!!!131835.java!!!	PreguntaOpcions(in text : String, inout opcions : String [[]])
		super(text);
		if (opcions == null) this.opcions = new String[]{};
		else this.opcions = opcions;
!!!131963.java!!!	getOpcions() : String
		return opcions;
!!!132091.java!!!	setOpcions(inout opcions : String [[]]) : void
		if (opcions == null) return;
		this.opcions = opcions;
!!!132219.java!!!	getEnunciatPregunta(in num : int) : String
		String resposta = System.lineSeparator();
		for (String opcio : opcions) resposta += StringUtils.rightPad("  "+'\u20DE'+"  "+opcio, Examen.AMPLE_ENUNCIAT+2, ".")+System.lineSeparator();
		//resposta += System.lineSeparator();
		return super.crearEnunciatPregunta(num, resposta);
