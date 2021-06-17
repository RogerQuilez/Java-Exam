package m3.uf4.pe1;

import java.io.Serializable;

public class Estudiant implements Comparable<Estudiant>, Serializable {

	private static final long serialVersionUID = 1L;
	private final static int MIN_EDAT = 18;
	private String nom;
	private String cognoms;
	private int edat;
	
	public Estudiant() {
		
	}
	
	public Estudiant(String nom, String cognoms, int edat) {
		this.nom = nom != null ? nom : "";
		this.cognoms = cognoms != null ? cognoms : "";
		this.edat = edat >= MIN_EDAT ? edat : 0;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		if (nom == null) return;
		this.nom = nom;
	}

	public String getCognoms() {
		return cognoms;
	}

	public void setCognoms(String cognoms) {
		if (cognoms == null) return;
		this.cognoms = cognoms;
	}

	public int getEdat() {
		return edat;
	}
	
	public void setEdat(int edat) {
		if (edat < MIN_EDAT) return;
		this.edat = edat;
	}
	
	public String getCognomsNom() {
		String cognomsNom = "";
		cognomsNom = this.getCognoms() + " " + this.getNom();
		return cognomsNom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognoms == null) ? 0 : cognoms.hashCode());
		result = prime * result + edat;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Estudiant other = (Estudiant) obj;
		if (cognoms == null) {
			if (other.cognoms != null)
				return false;
		} else if (!cognoms.equals(other.cognoms))
			return false;
		if (edat != other.edat)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Estudiant estudiant) {
		return this.getCognomsNom().compareTo(estudiant.getCognomsNom());
	}

}
