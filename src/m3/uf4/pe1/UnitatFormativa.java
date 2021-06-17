package m3.uf4.pe1;

public class UnitatFormativa {
	private String cicle;
	private String modul;
	private int num;
	private String titol;
	private int hores;

	public UnitatFormativa(String cicle, String modul, int num, String titol, int hores) {
		this.cicle = cicle==null?"":cicle;
		this.modul = modul==null?"":modul;
		this.num = num<1?1:num;
		this.titol = titol==null?"":titol;
		this.hores = hores<10?10:hores;
	}

	public String getCicle() {
		return cicle;
	}

	public void setCicle(String cicle) {
		if (cicle == null) return;
		this.cicle = cicle;
	}

	public String getModul() {
		return modul;
	}

	public void setModul(String modul) {
		if (modul == null) return;
		this.modul = modul;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		if (titol == null) return;
		this.titol = titol;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		if (num < 1) return;
		this.num = num;
	}

	public int getHores() {
		return hores;
	}

	public void setHores(int hores) {
		if (hores < 10) return;
		this.hores = hores;
	}
}
