package m3.uf4.pe1;

import java.io.EOFException;
import java.io.IOException;

public class MainAvaluacio {
		public static final String DAM = "Desenvolupament d'Aplicacions Multiplataforma";
		public static final String DAW = "Desenvolupament d'Aplicacions Web";
		public static final String ASIX = "Administraci� de Sistemes Inform�tics en la Xarxa";

		public static void main(String[] args) throws Excepcio, IOException, EOFException{

			Estudiant joan = new Estudiant("Joan", "P�rez i Castells", 19);
			Estudiant maria = new Estudiant("Maria", "Gonz�lez i Fornells", 21);
			Estudiant john = new Estudiant("John", "Doe", 31);
			Estudiant marta = new Estudiant("Marta", "Mart�nez i Miracles", 24);
			Estudiant pere = new Estudiant("Pere", "G�lvez i Gaud�", 22);
			Estudiant anna = new Estudiant("Anna", "Ruiz i Roure", 22);
			Estudiant raul = new Estudiant("Raul", "Ruiz i Miralls", 26);
			Estudiant toni = new Estudiant("Toni", "T�llez i Saperas", 28);
			Estudiant gemma = new Estudiant("Gemma", "Su�rez i Rius", 19);
			Estudiant[] estudiants = { joan, maria, john, marta, pere, anna, raul, toni, gemma };
			Estudiant marc = new Estudiant("Marc", "G�mez i Crusellas", 23);

			UnitatFormativa m3damUf4 = new UnitatFormativa("DAM", "M03. Programaci�", 4, "Programaci� orientada a objectes (POO). Fonaments", 35);

			UnitatFormativa m7asixUf2 = new UnitatFormativa("ASIX", "M07. Planificaci� i Administraci� de Xarxes", 2, "Administraci� de dispositius de xarxa", 55);

			Examen pe1Uf1M7 = new Examen(m7asixUf2);

			if (!pe1Uf1M7.esAvaluable()) System.out.println("1. \"pe1Uf1M7\" no �s avaluable" + System.lineSeparator());

			Examen pe1Uf4M3 = new Examen(m3damUf4);

			/* Matr�cula alumnat */
			
			try {
				pe1Uf4M3.inscriureEstudiants(estudiants);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			
			try {
				pe1Uf4M3.inscriureEstudiant(joan); // repetit
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.inscriureEstudiant(marc);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.anularMatriculaEstudiant(john);	
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			System.out.println("2. Llistat assist�ncia 9 alumnes: Marta, Pere, Marc, Maria, Joan, Raul, Anna, Gemma, Toni" + System.lineSeparator());
			System.out.println(pe1Uf4M3.generarLlistatAssistencia()); // sense checks

			/* Redacci� de l'examen */
			try {
				pe1Uf4M3.esborrarPregunta(2);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			if (pe1Uf4M3.afegirPreguntaOberta("Explica qu� �s una classe abstracte, quines caracter�stiques t� i les difer�ncies respecte una classe normal.", 1.5));
			if (pe1Uf4M3.afegirPreguntaOberta("Lorem ipsum dolor sit amet, consectetuer adipiscing elit...", 2));
			try {
				if (pe1Uf4M3.afegirPreguntaOpcions("Marca la resposta correcte. Exemples de tipus de dades primitives poden ser:", 1.2,
						new String[] { "int, double, String.", "char, double, float.", "Char, Float, Boolean." }));
			} catch (IOException e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			try {
				if (pe1Uf4M3.afegirPreguntaOpcions("Marca la resposta correcte. La millor manera per comparar el text \"Hola\" amb la variable de tipus String hola �s:",
						0.8, new String[] { "\"Hola\" == hola", "hola.equals(\"Hola\")", "\"Hola\".equals(hola)", "Cap de les anteriors" }));
			} catch (IOException e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				if (pe1Uf4M3.afegirPreguntaOpcions("Marca la resposta correcte. L'ordre correcte dels elements dins una classe �s:", 1.5,
						new String[] { "atributs > constructor > getters/setters > altres m�todes.",
								"atributs > getters/setters > constructor > altres m�todes.",
								"atributs > altres m�todes > constructor > getters/setters.",
						"altres m�todes > getters/setters > constructor > atributs." }));
			} catch (IOException e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			if (pe1Uf4M3.afegirPreguntaOberta("Descriu l'esquema jer�rquic de classes que implementa el llenguatge Java i les seves caracter�stiques principals. Posa exemples concrets.", 2));
			if (pe1Uf4M3.afegirPreguntaOberta("Justifica si la seg�ent afirmaci� �s certa o falsa. Una classe abstracta A implementa la interf�cie I, per tant la classe A est� obligada a implementar els m�todes de I.", 0.8));
			if (pe1Uf4M3.afegirPreguntaOberta("Justifica si la seg�ent afirmaci� �s certa o falsa. Una classe A hereta d'una classe B, aleshores es pot afirmar que B hereta atributs i m�todes de A.", 1.2)) ;

			try {
				pe1Uf4M3.esborrarPregunta(-1);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.esborrarPregunta(2);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}

			if (pe1Uf4M3.afegirPreguntaOberta("Proposa el diagrama UML complet d'una classe que representi una persona", 2.5)) System.out.println(System.lineSeparator());

			if (pe1Uf4M3.esAvaluable()) {
				System.out.println("4. Enunciat \"pe1Uf4M3\"" + System.lineSeparator());
				System.out.println(pe1Uf4M3.getTitol());
				System.out.println(pe1Uf4M3.getEnunciat() + System.lineSeparator());
			}

			/* Lliuraments dels alumnes */
			
			System.out.println(pe1Uf4M3.mostrarCorreccions());// Pila buida
			
			try {
				pe1Uf4M3.apilarLliurament(marta);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.apilarLliurament(marc);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.apilarLliurament(gemma);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.apilarLliurament(toni);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.apilarLliurament(raul);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.apilarLliurament(anna);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			

			System.out.println("5. Llistat assist�ncia 6 lliuraments: Marc, Marta, Raul, Anna, Gemma, Toni" + System.lineSeparator());
			System.out.println(pe1Uf4M3.generarLlistatAssistencia()); // amb checks i sense
			
			try {
				pe1Uf4M3.apilarLliurament(joan);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.apilarLliurament(joan); // Existent //NO VALIDA
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.apilarLliurament(pere);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}

			System.out.println("6. Estudiant per corregir: Pere G�lvez i Gaud�" + System.lineSeparator());
			System.out.println(pe1Uf4M3.consultarEstudiantPerCorregir().getCognomsNom() + System.lineSeparator());

			System.out.println("7. Cap correcci� i 8 lliuraments" + System.lineSeparator());
			System.out.println(pe1Uf4M3.mostrarCorreccions());

			try {
				pe1Uf4M3.corregirLliurament(-4.5); // No corregeix
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.corregirLliurament(24.5); // No corregeix
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.corregirLliurament(9); // Pere
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.corregirLliurament(7.75); // Joan
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.corregirLliurament(8.1); // Anna
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.corregirLliurament(4.3); // Raul
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.apilarLliurament(maria);
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}

			System.out.println("8. Total correccions 4: Raul, Anna, Joan i Pere" + System.lineSeparator());
			
			System.out.println(pe1Uf4M3.mostrarCorreccions());
			
			try {
				pe1Uf4M3.corregirLliurament(7.05); // Maria
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.corregirLliurament(8.25); // Toni
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.corregirLliurament(9.65); // Gemma
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.corregirLliurament(5.54); // Marc
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.corregirLliurament(2); // Marta
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			try {
				pe1Uf4M3.corregirLliurament(0); // pila buida
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			

			System.out.println("9. Correccions finalitzades. Total 9: Marta, Marc, Gemma, Toni, Maria, Raul, Anna, Joan, Pere "	+ System.lineSeparator());
			System.out.println(pe1Uf4M3.mostrarCorreccions()); // ??

			System.out.println("10. Llistat assist�ncia correccions finalitzades. Tots marcats" + System.lineSeparator());
			System.out.println(pe1Uf4M3.generarLlistatAssistencia()); // amb checks
			
			System.out.println("11. Desar preguntes fitxer preguntes.xml" + System.lineSeparator());
			
			try {
				pe1Uf4M3.desarPreguntes("preguntes.xml");
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}

			System.out.println("12. Carregar preguntes fitxer preguntes.xml" + System.lineSeparator());
			
			try {
				pe1Uf4M3.carregarPreguntes("preguntes.xml");
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			System.out.println(pe1Uf4M3.getEnunciat() + System.lineSeparator());			
			
			System.out.println("13. Desar alumnes fitxer alumnes.bin" + System.lineSeparator());
			
			try {
				pe1Uf4M3.desarAlumnes("alumnes.bin");
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}

			System.out.println("14. Carregar alumnes fitxer alumnes.bin" + System.lineSeparator());
			
			try {
				pe1Uf4M3.carregarAlumnes("alumnes.bin");
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read();
			}
			
			System.out.println(pe1Uf4M3.generarLlistatAssistencia());

			System.out.println("15. Registre ERRORS (6 en total)" + System.lineSeparator());
			System.out.println(Excepcio.registreErrors());
		}
	}



