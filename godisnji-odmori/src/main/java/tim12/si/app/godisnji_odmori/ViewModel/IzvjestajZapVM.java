package tim12.si.app.godisnji_odmori.ViewModel;

public class IzvjestajZapVM {

	public String sektor;
	public String zaposlenikIme;
	public String zaposlenikPrezime;
	public int radniDani;
	public int neradniDani;
	
	//Konstruktori
	public IzvjestajZapVM (String sektor, String zaposlenikIme, String zaposlenikPrezime, int radniDani, int neradniDani)
	{
		this.sektor=sektor;
		this.zaposlenikIme=zaposlenikIme;
		this.zaposlenikPrezime=zaposlenikPrezime;
		this.radniDani=radniDani;
		this.neradniDani=neradniDani;
	}
	
	public IzvjestajZapVM() {}
	
	//Getteri i setteri
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

	public int getRadniDani() {
		return radniDani;
	}

	public void setRadniDani(int radniDani) {
		this.radniDani = radniDani;
	}

	public int getNeradniDani() {
		return neradniDani;
	}

	public void setNeradniDani(int neradniDani) {
		this.neradniDani = neradniDani;
	}
}