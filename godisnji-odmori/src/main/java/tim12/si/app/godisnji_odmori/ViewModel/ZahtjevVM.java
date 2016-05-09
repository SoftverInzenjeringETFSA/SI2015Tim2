package tim12.si.app.godisnji_odmori.ViewModel;

import java.util.Date;

public class ZahtjevVM {
	
	private String podnosilacIme;
	private String podnosilacPrezime;
	private Date pocetakOdsustva;
	private Date zavrsetakOdsustva;
	private String tipOdsustva;
	private Boolean obraden;
	private Boolean odluka;
	private String opis;
	
	public ZahtjevVM(String podnosilacIme, String podnosilacPrezime /*, Date pocetakOddsustva, Date zavrsetakOdsustva, String tipOdsustva, Boolean obradjen, Boolean odluka, String opis*/)
	{
		this.podnosilacIme = podnosilacIme;
		this.podnosilacPrezime = podnosilacPrezime;
	}
	public String getPodnosilacIme() {
		return podnosilacIme;
	}
	public void setPodnosilacIme(String podnosilacIme) {
		this.podnosilacIme = podnosilacIme;
	}
	public String getPodnosilacPrezime() {
		return podnosilacPrezime;
	}
	public void setPodnosilacPrezime(String podnosilacPrezime) {
		this.podnosilacPrezime = podnosilacPrezime;
	}
	public Date getPocetakOdsustva() {
		return pocetakOdsustva;
	}
	public void setPocetakOdsustva(Date pocetakOdsustva) {
		this.pocetakOdsustva = pocetakOdsustva;
	}
	public Date getZavrsetakOdsustva() {
		return zavrsetakOdsustva;
	}
	public void setZavrsetakOdsustva(Date zavrsetakOdsustva) {
		this.zavrsetakOdsustva = zavrsetakOdsustva;
	}
	public String getTipOdsustva() {
		return tipOdsustva;
	}
	public void setTipOdsustva(String tipOdsustva) {
		this.tipOdsustva = tipOdsustva;
	}
	public Boolean getObraden() {
		return obraden;
	}
	public void setObraden(Boolean obraden) {
		this.obraden = obraden;
	}
	public Boolean getOdluka() {
		return odluka;
	}
	public void setOdluka(Boolean odluka) {
		this.odluka = odluka;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}

}
