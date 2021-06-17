package m3.uf4.pe1;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class Examen implements Avaluable {
	
	private static final int COL1 = 10;
	private static final int COL2 = 20;
	private static final int COL3 = 10;
	private static final int COL4 = 20;
	private static final int AMPLE_ASSISTENCIA = 30;
	
	private UnitatFormativa unitat;
	public static final int MAX_PREGUNTES = 7;
	public static final int AMPLE_ENUNCIAT = 70;
	public static final int AMPLE_PUNTUACIO = 15;
	private List<Pregunta> preguntes;
	private Set<Estudiant> alumnes;
	private Deque<Lliurament> lliuraments;
	private Deque<Lliurament> correccions;

	public Examen(UnitatFormativa unitat) {
		this.unitat = unitat;
		this.preguntes = new LinkedList<>();
		alumnes = new TreeSet<>();
		lliuraments = new LinkedList<>();
		correccions = new LinkedList<>();
	}

	public boolean afegirPreguntaOberta(String text, double puntuacio) {
		if (preguntes.size() <= MAX_PREGUNTES && text != null && puntuacio > 0) {
			preguntes.add(new PreguntaOberta(text, puntuacio));
			return true;
		}
		return false;
	}

	public boolean afegirPreguntaOpcions(String text, double puntuacio, String[] opcions) throws IOException {
		if (preguntes.size() <= MAX_PREGUNTES && text != null && puntuacio > 0) {
			try {
				preguntes.add(new PreguntaOpcions(text, puntuacio, opcions));
				return true;
			} catch (Excepcio e) {
				System.out.println("ERROR => " + e.getMessage());
				System.in.read(); 
			}
		}
		return false;
	}

	public void esborrarPregunta(int num) throws Excepcio {
		if (num >= 1 && num <= preguntes.size()) {
			preguntes.remove(num);
		} else {
			throw new Excepcio("Examen", "[Examen] La pregunta no es troba en la Llista");
		}
	}

	public void inscriureEstudiant(Estudiant estudiant) throws Excepcio {
		if (estudiant != null) {
			alumnes.add(estudiant);
		} else {
			throw new Excepcio("Examen", "[Examen] L'alumne no pot ser null");
		}
	}

	public void inscriureEstudiants(Estudiant[] estudiants) throws Excepcio {
		if (estudiants != null) {
			this.alumnes.addAll(Arrays.asList(estudiants));
		} else {
			throw new Excepcio("Examen", "[Examen] L'estudiant no pot ser null");
		}
	}

	public void anularMatriculaEstudiant(Estudiant estudiant) throws Excepcio {
		if (estudiant != null) {
			if (this.alumnes.contains(estudiant)) {
				this.alumnes.remove(estudiant);				
			} else {
				throw new Excepcio("Examen", "[Examen] L'estudiant no es troba a la Llista");
			}
		} else {
			throw new Excepcio("Examen", "[Examen] L'estudiant no pot ser null");
		}
	}

	public String generarLlistatAssistencia() {
		if (!alumnes.isEmpty()) {
			String llistatAssistencia = "";
			
			llistatAssistencia += StringUtils.center(" Llistat Assistència", AMPLE_ENUNCIAT, " ") + System.lineSeparator()
				+ System.lineSeparator();
			llistatAssistencia += StringUtils.rightPad(" ", COL2);
			llistatAssistencia += StringUtils.repeat(".", AMPLE_ASSISTENCIA) + System.lineSeparator() + System.lineSeparator();
			
			llistatAssistencia = getAssistenciaText(llistatAssistencia);
			
			llistatAssistencia += StringUtils.rightPad(" ", COL2);
			llistatAssistencia += StringUtils.repeat("_", AMPLE_ASSISTENCIA) + System.lineSeparator() + System.lineSeparator();
			return llistatAssistencia;
		} else {
			return StringUtils.center(" LLISTAT D'ASSISTÈNCIA BUIDA", AMPLE_ENUNCIAT, " ") + System.lineSeparator()
			+ System.lineSeparator();
		}
		
	}

	private String getAssistenciaText(String llistatAssistencia) {
		Iterator<Estudiant> iteratorSetEstudiant = this.alumnes.iterator();
		
		while (iteratorSetEstudiant.hasNext()) {
			
			Estudiant estudiant = (Estudiant) iteratorSetEstudiant.next();
			Lliurament newLliurament = new Lliurament(estudiant, this);
			
			if (lliuraments.contains(newLliurament) || correccions.contains(newLliurament)) {
				
				llistatAssistencia += StringUtils.rightPad(" ", COL2);
				llistatAssistencia += StringUtils.rightPad("  " + 'X' + "  " + StringUtils.abbreviate(estudiant.getCognomsNom(), AMPLE_ASSISTENCIA - 10),
						Examen.AMPLE_ENUNCIAT/2) + System.lineSeparator();
				
			} else {

				llistatAssistencia += StringUtils.rightPad(" ", COL2);
				llistatAssistencia += StringUtils.rightPad("  " + 'O' + "  " + StringUtils.abbreviate(estudiant.getCognomsNom(), AMPLE_ASSISTENCIA - 10),
						Examen.AMPLE_ENUNCIAT/2) + System.lineSeparator();
			}
			
		}
		return llistatAssistencia;
	}

	public void apilarLliurament(Estudiant estudiant) throws Excepcio {
		if (estudiant != null) {
			Lliurament newLliurament = new Lliurament(estudiant, this);
			if (!lliuraments.contains(newLliurament)) {
				
				lliuraments.push(newLliurament);
				
			} else {
				throw new Excepcio("Examen", "[Examen] L'estudiant ja té un lliurament");
			}
		} else {
			throw new Excepcio("Examen", "[Examen] L'estudiant no pot ser null");
		}
	}

	public void corregirLliurament(double nota) throws Excepcio {
		if (!lliuraments.isEmpty()) {
			validarNota(nota);
			lliuraments.getFirst().setNota(nota);
			correccions.push(lliuraments.pop());
		} else {
			throw new Excepcio("Examen", "[Examen] No hi han lliuraments");
		}
	}

	public Estudiant consultarEstudiantPerCorregir() throws Excepcio {
		if (!lliuraments.isEmpty()) {
			return lliuraments.element().getEstudiant();
		} else {
			throw new Excepcio("Examen", "[Examen] No hi ha estudiants per corretgir");
		}
	}

	public void validarNota(double nota) throws Excepcio {
		if (nota < 0 || nota > this.getPuntuacio()) {
			throw new Excepcio("Examen", "[Examen] La nota no es vàlida");
		}
	}

	public String mostrarCorreccions() {
		if (!lliuraments.isEmpty()|| !correccions.isEmpty()) {
			String textCorreccions = "";

			textCorreccions += StringUtils.center("LLIURAMENTS", COL1 + COL2 + COL3);
			
			textCorreccions += StringUtils.rightPad(" ", COL1);

			textCorreccions += StringUtils.center("CORRECCIONS", COL1 + COL2 + COL3) + System.lineSeparator();
			
			textCorreccions += System.lineSeparator() + System.lineSeparator();
			
			textCorreccions = iterateLliuramentText(textCorreccions);
			
			textCorreccions += System.lineSeparator();
			
			textCorreccions += StringUtils.leftPad(" ", COL1);
			
			textCorreccions += StringUtils.center(" TAULA PROFESOR ", AMPLE_ENUNCIAT+COL1, "_") + System.lineSeparator()
					+ System.lineSeparator();
			
			return textCorreccions;
		} else {
			return "";
		}
	}
	
	//Generem el text per Lliuraments
	private String iterateLliuramentText(String textCorreccions) {
		
		DecimalFormat df = new DecimalFormat("#.00");
		Object[] lliuramentsArray = lliuraments.toArray();
		Object[] correccionsArray = correccions.toArray();
		int i = 0;
		int maxSize;
		if (lliuramentsArray.length > correccionsArray.length) {
			maxSize = lliuramentsArray.length;
		} else {
			maxSize = correccionsArray.length;
		}
		while (i < maxSize) {
			if (i == 0) {
				if (i < lliuramentsArray.length) {
					textCorreccions += StringUtils.rightPad("  TOP => ", COL1);
					textCorreccions += "[ " + StringUtils.rightPad(StringUtils.abbreviate(((Lliurament) lliuramentsArray[i]).getEstudiant().getCognomsNom(), COL2+COL1-8), COL2+COL1-8) + " ]";
				}
				if (correccionsArray.length > 0 && lliuramentsArray.length > 0) {
					if (i < correccionsArray.length) {
						textCorreccions += StringUtils.leftPad(" ", COL1);
						textCorreccions += StringUtils.leftPad("TOP => ", COL3);
						textCorreccions += "[ " + StringUtils.rightPad(StringUtils.abbreviate(df.format(notaNormalitzada(((Lliurament) correccionsArray[i]).getNota())) + " - " + ((Lliurament) correccionsArray[i]).getEstudiant().getCognomsNom(), COL3+COL4-5), COL3+COL4-5) + " ]";
					} 
				} else {
					if (i < correccionsArray.length) {
						textCorreccions += StringUtils.leftPad(" ", COL1+COL2+COL3+5);
						textCorreccions += StringUtils.leftPad("TOP => ", COL3);
						textCorreccions += "[ " + StringUtils.rightPad(StringUtils.abbreviate(df.format(notaNormalitzada(((Lliurament) correccionsArray[i]).getNota())) + " - " + ((Lliurament) correccionsArray[i]).getEstudiant().getCognomsNom(), COL3+COL4-5), COL3+COL4-5) + " ]";
					} 
				}
				
			} else {
				if (i < lliuramentsArray.length) {
					textCorreccions += StringUtils.rightPad(" ", COL1);
					textCorreccions += "[ " + StringUtils.rightPad(StringUtils.abbreviate(((Lliurament) lliuramentsArray[i]).getEstudiant().getCognomsNom(), COL2+COL1-8), COL2+COL1-8) + " ]";
				}
				if (correccionsArray.length > 0 && lliuramentsArray.length > 0) {
					if (i < correccionsArray.length) {
						textCorreccions += StringUtils.leftPad(" ", COL1);
						textCorreccions += StringUtils.rightPad(" ", COL3);
						textCorreccions += "[ " + StringUtils.rightPad(StringUtils.abbreviate(df.format(notaNormalitzada(((Lliurament) correccionsArray[i]).getNota())) + " - " + ((Lliurament) correccionsArray[i]).getEstudiant().getCognomsNom(), COL3+COL4-5), COL3+COL4-5) + " ]";
					}
				} else {
					if (i < correccionsArray.length) {
						textCorreccions += StringUtils.leftPad(" ", COL1+COL2+COL3+5);
						textCorreccions += StringUtils.rightPad(" ", COL3);
						textCorreccions += "[ " + StringUtils.rightPad(StringUtils.abbreviate(df.format(notaNormalitzada(((Lliurament) correccionsArray[i]).getNota())) + " - " + ((Lliurament) correccionsArray[i]).getEstudiant().getCognomsNom(), COL3+COL4-5), COL3+COL4-5) + " ]";
					}
				}
			}
			textCorreccions += System.lineSeparator();
			i++;
		}
		return textCorreccions;
	}

	private double notaNormalitzada(double nota) {
		return nota / this.getPuntuacio() * 10;
	}

	public void desarPreguntes(String fitxer) throws Excepcio {
		XMLEncoder fitxerXML;
		try {
			fitxerXML = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fitxer)));
				fitxerXML.writeObject(preguntes);
			fitxerXML.close();
			System.out.println("CARREGA COMPLETADA");
		} catch (FileNotFoundException e) {
			throw new Excepcio("Examen", "[Examen] El fitxer no existeix" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void carregarPreguntes(String fitxer) throws Excepcio {       
        XMLDecoder preguntesIn;
		try {
			preguntesIn = new XMLDecoder(new BufferedInputStream (new FileInputStream(fitxer)));
			this.preguntes =  (List<Pregunta>) preguntesIn.readObject();
		    preguntesIn.close();
		} catch (FileNotFoundException e) {
			throw new Excepcio("Examen", "[Examen] El fitxer no existeix" + e.getMessage());
		}  
	}

	public void desarAlumnes(String fitxer) throws Excepcio {
		try {
			ObjectOutputStream alumnesOut = new ObjectOutputStream(new FileOutputStream(fitxer));
			alumnesOut.writeObject(alumnes);
			alumnesOut.close();
			System.out.println("CARREGA COMPLETADA");
		} catch (FileNotFoundException e) {
			throw new Excepcio("Examen", "[Examen] El fitxer no existeix" + e.getMessage());
		} catch (IOException e) {
			throw new Excepcio("Examen", "[Examen] Error d'entrada/sortida" + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void carregarAlumnes(String fitxer) throws Excepcio {
		try {
			ObjectInputStream alumnesIn = new ObjectInputStream(new FileInputStream(fitxer));
			this.alumnes = (Set<Estudiant>) alumnesIn.readObject();
			alumnesIn.close();
		} catch (FileNotFoundException e) {
			throw new Excepcio("Examen", "[Examen] El fitxer no existeix" + e.getMessage());
		} catch (IOException e) {
			throw new Excepcio("Examen", "[Examen] Error d'entrada/sortida" + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new Excepcio("Examen", "[Examen] Error d'implementació" + e.getMessage());
		}
	}

	@Override
	public boolean esAvaluable() {
		if (this.getPuntuacio() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String getTitol() {
		return StringUtils.center(" Examen " + unitat.getModul() + " ", AMPLE_ENUNCIAT, "#") + System.lineSeparator()
				+ System.lineSeparator()
				+ StringUtils.rightPad(
						StringUtils.abbreviate("UF" + unitat.getNum() + ": " + unitat.getTitol(), AMPLE_ENUNCIAT),
						AMPLE_ENUNCIAT)
				+ System.lineSeparator();
	}

	@Override
	public double getPuntuacio() {
		double total = 0;
		for (int i = 0; i < getPreguntes().size(); i++) {
			if (this.getPreguntes().get(i) != null) {
				total += this.getPreguntes().get(i).getPuntuacio();
			}
		}
		return total;
	}

	@Override
	public String getEnunciat() {
		String enunciat = StringUtils.rightPad("ENUNCIAT", AMPLE_ENUNCIAT - AMPLE_PUNTUACIO) + StringUtils
				.leftPad((new DecimalFormat("##0.0")).format(this.getPuntuacio()) + " PUNTS", AMPLE_PUNTUACIO);
		enunciat += System.lineSeparator() + StringUtils.repeat("-", AMPLE_ENUNCIAT) + System.lineSeparator()
				+ System.lineSeparator();

		int num = 1;
		for (Pregunta pregunta : preguntes) {
			if (pregunta != null) {
				enunciat += pregunta.getEnunciatPregunta(num) + StringUtils.repeat(System.lineSeparator(), 3);
				num++;
			}
		}
		return enunciat;
	}

	public List<Pregunta> getPreguntes() {
		return preguntes;
	}

	public void setPreguntes(List<Pregunta> preguntes) {
		this.preguntes = preguntes;
	}

	public static int getMaxPreguntes() {
		return MAX_PREGUNTES;
	}

	public static int getAmpleEnunciat() {
		return AMPLE_ENUNCIAT;
	}

	public static int getAmplePuntuacio() {
		return AMPLE_PUNTUACIO;
	}

	public UnitatFormativa getUnitat() {
		return unitat;
	}

	public void setUnitat(UnitatFormativa unitat) {
		this.unitat = unitat;
	}

	public Set<Estudiant> getAlumnes() {
		return alumnes;
	}

	public void setAlumnes(Set<Estudiant> alumnes) {
		this.alumnes = alumnes;
	}

	public Deque<Lliurament> getLliuraments() {
		return lliuraments;
	}

	public void setLliuraments(Deque<Lliurament> lliuraments) {
		this.lliuraments = lliuraments;
	}

	public Deque<Lliurament> getCorreccions() {
		return correccions;
	}

	public void setCorreccions(Deque<Lliurament> correccions) {
		this.correccions = correccions;
	}
	
}
