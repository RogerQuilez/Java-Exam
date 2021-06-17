class Examen
!!!128635.java!!!	Examen(inout unitat : UnitatFormativa)
		super();
		this.unitat = unitat;
		this.preguntes = new Pregunta[MAX_PREGUNTES];
!!!128763.java!!!	getUnitat() : UnitatFormativa
		return unitat;
!!!128891.java!!!	setUnitat(inout unitat : UnitatFormativa) : void
		this.unitat = unitat;
!!!129019.java!!!	afegirPreguntaOberta(in text : String, in puntuacio : double) : boolean
		if (!validarPregunta(text, puntuacio)) return false;

		int index = cercarIndexLliure();
		if (index < 0) return false;

		this.preguntes[index] = new PreguntaOberta(text, puntuacio);
		return true;
!!!129147.java!!!	afegirPreguntaOpcions(in text : String, in puntuacio : double, inout opcions : String [[]]) : boolean
		if (!validarPregunta(text, puntuacio) || opcions == null) return false;

		int index = cercarIndexLliure();
		if (index < 0) return false;

		this.preguntes[index] = new PreguntaOpcions(text, puntuacio, opcions);
		return true;
!!!129275.java!!!	esborrarPregunta(in num : int) : boolean
		if (num < 1 || num > MAX_PREGUNTES) return false;

		this.preguntes[num-1] = null;
		return true;
!!!129403.java!!!	validarPregunta(in text : String, in puntuacio : double) : boolean
		return (text != null && puntuacio > 0);
!!!129531.java!!!	cercarIndexLliure() : int
		int i = 0;
		while (i < this.preguntes.length && this.preguntes[i] != null) i++;

		if (i >= this.preguntes.length) return -1;
		return i;
!!!129659.java!!!	esAvaluable() : boolean
		return this.getPuntuacio() > 0;
!!!129787.java!!!	getTitol() : String
		return StringUtils.center(" Examen "+unitat.getModul()+" ",AMPLE_ENUNCIAT, "#")+System.lineSeparator()+System.lineSeparator()+
				StringUtils.rightPad(StringUtils.abbreviate("UF"+unitat.getNum()+": "+unitat.getTitol(), AMPLE_ENUNCIAT), AMPLE_ENUNCIAT)+
				System.lineSeparator();
!!!129915.java!!!	getPuntuacio() : double
		double punt = 0;
		for (Pregunta pregunta : preguntes) {
			if (pregunta != null) punt += pregunta.getPuntuacio();
		}
		return punt;
!!!130043.java!!!	getEnunciat() : String
		String enunciat = 	StringUtils.rightPad("ENUNCIAT", AMPLE_ENUNCIAT-AMPLE_PUNTUACIO)+
				StringUtils.leftPad((new DecimalFormat("##0.0")).format(this.getPuntuacio())+" PUNTS", AMPLE_PUNTUACIO);
		enunciat +=			System.lineSeparator() + StringUtils.repeat("-", AMPLE_ENUNCIAT)+System.lineSeparator()+System.lineSeparator();

		int num = 1;
		for (Pregunta pregunta : preguntes) {
			if (pregunta != null) {
				enunciat += pregunta.getEnunciatPregunta(num)+StringUtils.repeat(System.lineSeparator(),3);
				num++;
			}
		}
		return enunciat;
