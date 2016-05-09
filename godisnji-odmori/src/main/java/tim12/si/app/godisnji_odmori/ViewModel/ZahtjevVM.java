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
	private String nazivSektora;
	private Long brojRadnihDana;
	private Long idZahtjeva;
	
	public ZahtjevVM(String podnosilacIme, String podnosilacPrezime, String nazivSektora, Long idZahtjeva /*Long brojRadnihDana*/ /*, Date pocetakOddsustva, Date zavrsetakOdsustva, String tipOdsustva, Boolean obradjen, Boolean odluka, String opis*/)
	{
		this.podnosilacIme = podnosilacIme;
		this.podnosilacPrezime = podnosilacPrezime;
		this.nazivSektora = nazivSektora;
		this.idZahtjeva = idZahtjeva;
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
	public String getNazivSektora() {
		return nazivSektora;
	}
	public void setNazivSektora(String nazivSektora) {
		this.nazivSektora = nazivSektora;
	}
	public Long getBrojRadnihDana() {
		return brojRadnihDana;
	}
	public void setBrojRadnihDana(Long brojRadnihDana) {
		this.brojRadnihDana = brojRadnihDana;
	}
	public Long getIdZahtjeva() {
		return idZahtjeva;
	}
	public void setIdZahtjeva(Long idZahtjeva) {
		this.idZahtjeva = idZahtjeva;
	}

}
