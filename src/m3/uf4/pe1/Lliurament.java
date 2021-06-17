package m3.uf4.pe1;

public class Lliurament {

	private Estudiant estudiant;
	private Examen examen;
	private double nota;
	
	public Lliurament(Estudiant estudiant, Examen examen) {
		this.estudiant = estudiant != null ? estudiant : new Estudiant("", "", 0);
		this.examen = examen != null ? examen : new Examen(new UnitatFormativa("", "", 1, "", 0));
		this.nota = 0;
	}
	
	public Estudiant getEstudiant() {
		return estudiant;
	}

	public void setEstudiant(Estudiant estudiant) {
		if (estudiant == null) return;
		this.estudiant = estudiant;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		if (examen == null) return;
		this.examen = examen;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		if (nota < 0 || nota > examen.getPuntuacio()) return;
		this.nota = nota;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estudiant == null) ? 0 : estudiant.hashCode());
		result = prime * result + ((examen == null) ? 0 : examen.hashCode());
		long temp;
		temp = Double.doubleToLongBits(nota);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Lliurament other = (Lliurament) obj;
		if (estudiant == null) {
			if (other.estudiant != null)
				return false;
		} else if (!estudiant.equals(other.estudiant))
			return false;
		if (examen == null) {
			if (other.examen != null)
				return false;
		} else if (!examen.equals(other.examen))
			return false;
		return true;
	}
	
}
