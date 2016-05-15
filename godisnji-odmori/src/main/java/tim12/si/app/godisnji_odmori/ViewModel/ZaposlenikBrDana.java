package tim12.si.app.godisnji_odmori.ViewModel;

public class ZaposlenikBrDana {
	
	private String sektor;
	private String zaposlenikIme;
	private String zaposlenikPrezime;
	private Long radniDani;
	private int neradniDani;
	private Long preostaloSlobodnih;
	private Long daniBolovanja;
	private Long daniNeplaniranog;
	private Long iskoristeniGodisnji;
	private Long zaposlenik_id;
	public ZaposlenikBrDana(){}
	public ZaposlenikBrDana(String sektor,String zaposlenikIme, String zaposlenikPrezime, Long radniDani/*, int neradniDani*/, Long preostaloSlobodnih)
	{
		this.sektor = sektor;
		this.zaposlenikIme = zaposlenikIme;
		this.zaposlenikPrezime = zaposlenikPrezime;
		this.radniDani = radniDani;
		/*this.neradniDani = neradniDani;*/
		this.preostaloSlobodnih = preostaloSlobodnih;
	}
	public ZaposlenikBrDana(String sektor,String zaposlenikIme,Long id, String zaposlenikPrezime, Long radniDani, Long preostaloSlobodnih, Long daniBolovanja, Long daniNeplaniranog, Long iskoristeniGodisnji)
	{
		this.sektor = sektor;
		this.zaposlenikIme = zaposlenikIme;
		this.zaposlenikPrezime = zaposlenikPrezime;
		this.radniDani = radniDani;
		this.preostaloSlobodnih = preostaloSlobodnih;
		this.daniBolovanja = daniBolovanja;
		this.daniNeplaniranog = daniNeplaniranog;
		this.iskoristeniGodisnji = iskoristeniGodisnji;
		this.zaposlenik_id = id;
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
	public Long getPreostaloSlobodnih() {
		return preostaloSlobodnih;
	}
	public void setPreostaloSlobodnih(Long preostaloSlobodnih) {
		this.preostaloSlobodnih = preostaloSlobodnih;
	}
	public Long getDaniNeplaniranog() {
		return daniNeplaniranog;
	}
	public void setDaniNeplaniranog(Long daniNeplaniranog) {
		this.daniNeplaniranog = daniNeplaniranog;
	}
	public Long getDaniBolovanja() {
		return daniBolovanja;
	}
	public void setDaniBolovanja(Long daniBolovanja) {
		this.daniBolovanja = daniBolovanja;
	}
	public Long getIskoristeniGodisnji() {
		return iskoristeniGodisnji;
	}
	public void setIskoristeniGodisnji(Long iskoristeniGodisnji) {
		this.iskoristeniGodisnji = iskoristeniGodisnji;
	}
	public Long getZaposlenik_id() {
		return zaposlenik_id;
	}
	public void setZaposlenik_id(Long zaposlenik_id) {
		this.zaposlenik_id = zaposlenik_id;
	}

}
