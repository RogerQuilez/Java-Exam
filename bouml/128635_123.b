class MainPe1Examen
!!!130171.java!!!	main(inout args : String [[]]) : void
		UnitatFormativa m3damUf4 = new UnitatFormativa("DAM", "M03. Programació", 4, "Programació orientada a objectes (POO). Fonaments", 35);

		UnitatFormativa m7asixUf2 = new UnitatFormativa("ASIX", "M07. Planificació i Administració de Xarxes", 2, "Administració de dispositius de xarxa", 55);

		Examen pe1Uf1M7 = new Examen(m7asixUf2);

		if (!pe1Uf1M7.esAvaluable()) System.out.println("1. \"pe1Uf1M7\" no és avaluable");

		Examen pe1Uf4M3 = new Examen(m3damUf4);

		System.out.print("2. ");
		if (pe1Uf4M3.esborrarPregunta(2)) System.out.print("É");
		if (pe1Uf4M3.afegirPreguntaOberta("Explica què és una classe abstracte, quines característiques té i les diferències respecte una classe normal.", 1.5)) System.out.print("s");
		if (pe1Uf4M3.afegirPreguntaOberta("Lorem ipsum dolor sit amet, consectetuer adipiscing elit...", 2)) System.out.print(" ");
		if (pe1Uf4M3.afegirPreguntaOpcions(	"Marca la resposta correcte. Exemples de tipus de dades primitives poden ser:", 1.0,
										new String[] {"int, double, String.", "char, double, float.", "Char, Float, Boolean." })) System.out.print("c");
		if (pe1Uf4M3.afegirPreguntaOpcions(	"Marca la resposta correcte. La millor manera per comparar el text \"Hola\" amb la variable de tipus String hola és:", 1.0,
										new String[] {"\"Hola\" == hola", "hola.equals(\"Hola\")", "\"Hola\".equals(hola)", "Cap de les anteriors" })) System.out.print("o");
		if (pe1Uf4M3.afegirPreguntaOpcions(	"Marca la resposta correcte. L'ordre correcte dels elements dins una classe és:", 1.0,
										new String[] {	"atributs > constructor > getters/setters > altres mètodes.",
														"atributs > getters/setters > constructor > altres mètodes.",
														"atributs > altres mètodes > constructor > getters/setters.",
														"altres mètodes > getters/setters > constructor > atributs." })) System.out.print("r");
		if (pe1Uf4M3.afegirPreguntaOberta("Descriu l'esquema jeràrquic de classes que implementa el llenguatge Java i les seves característiques principals. Posa exemples concrets.", 2)) System.out.print("r");
		if (!pe1Uf4M3.esborrarPregunta(-1)) System.out.print("e");
		if (pe1Uf4M3.esborrarPregunta(2)) System.out.print("c");
		if (pe1Uf4M3.afegirPreguntaOberta("Proposa el diagrama UML complet d'una classe que representi una persona", 2.5)) System.out.print("t");
		if (pe1Uf4M3.afegirPreguntaOpcions(	"Marca la resposta correcte. Suposa que \"b=1==1;\", quin seria el resultat de:  \"String s = (b?\"S\":\"N\");\"  ", 1.0,
				new String[] {	"\"SN\"", "\"S\"", "\"N\"", "Cap de les anteriors" })) System.out.print("e");
		if (!pe1Uf4M3.afegirPreguntaOberta("Lorem ipsum dolor sit amet, consectetuer adipiscing elit...", 2)) System.out.println("!"); // No s'afegeix

		if (pe1Uf4M3.esAvaluable()) {
			System.out.println("3. Enunciat \"pe1Uf4M3\""+System.lineSeparator());
			System.out.println(pe1Uf4M3.getTitol());
			System.out.println(pe1Uf4M3.getEnunciat());
		}

