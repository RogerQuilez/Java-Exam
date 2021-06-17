class UnitatFormativa
!!!132347.java!!!	UnitatFormativa(in cicle : String, in modul : String, in num : int, in titol : String, in hores : int)
		this.cicle = cicle==null?"":cicle;
		this.modul = modul==null?"":modul;
		this.num = num<1?1:num;
		this.titol = titol==null?"":titol;
		this.hores = hores<10?10:hores;
!!!132475.java!!!	getCicle() : String
		return cicle;
!!!132603.java!!!	setCicle(in cicle : String) : void
		if (cicle == null) return;
		this.cicle = cicle;
!!!132731.java!!!	getModul() : String
		return modul;
!!!132859.java!!!	setModul(in modul : String) : void
		if (modul == null) return;
		this.modul = modul;
!!!132987.java!!!	getTitol() : String
		return titol;
!!!133115.java!!!	setTitol(in titol : String) : void
		if (titol == null) return;
		this.titol = titol;
!!!133243.java!!!	getNum() : int
		return num;
!!!133371.java!!!	setNum(in num : int) : void
		if (num < 1) return;
		this.num = num;
!!!133499.java!!!	getHores() : int
		return hores;
!!!133627.java!!!	setHores(in hores : int) : void
		if (hores < 10) return;
		this.hores = hores;
