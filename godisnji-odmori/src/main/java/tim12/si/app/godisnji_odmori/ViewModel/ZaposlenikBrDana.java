package tim12.si.app.godisnji_odmori.ViewModel;

public class ZaposlenikBrDana {
	
	private String sektor;
	private String zaposlenikIme;
	private String zaposlenikPrezime;
	private Long radniDani;
	private int neradniDani;
	private int preostaloSlobodnih;
	
	public ZaposlenikBrDana(String sektor,String zaposlenikIme, String zaposlenikPrezime, Long radniDani/*, int neradniDani, int preostaloSlobodnih*/)
	{
		this.sektor = sektor;
		this.zaposlenikIme = zaposlenikIme;
		this.zaposlenikPrezime = zaposlenikPrezime;
		this.radniDani = radniDani;
		/*this.neradniDani = neradniDani;
		this.preostaloSlobodnih = preostaloSlobodnih;*/
	}
	public String getSektor() {
		return sektor;
	}
	public void setSektor(String sektor) {
		this.sektor = sektor;
	}
	public String getZaposlenikIme() {
		return zaposlenikIme;
	}
	public void setZaposlenikIme(String zaposlenikIme) {
		this.zaposlenikIme = zaposlenikIme;
	}
	public String getZaposlenikPrezime() {
		return zaposlenikPrezime;
	}
	public void setZaposlenikPrezime(String zaposlenikPrezime) {
		this.zaposlenikPrezime = zaposlenikPrezime;
	}
	public Long getRadniDani() {
		return radniDani;
	}
	public void setRadniDani(Long radniDani) {
		this.radniDani = radniDani;
	}
	public int getNeradniDani() {
		return neradniDani;
	}
	public void setNeradniDani(int neradniDani) {
		this.neradniDani = neradniDani;
	}
	public int getPreostaloSlobodnih() {
		return preostaloSlobodnih;
	}
	public void setPreostaloSlobodnih(int preostaloSlobodnih) {
		this.preostaloSlobodnih = preostaloSlobodnih;
	}

}
