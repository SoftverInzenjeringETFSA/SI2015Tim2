package tim12.si.app.godisnji_odmori.ViewModel;

import java.util.Date;

import antlr.Parser;
import antlr.ParserSharedInputState;
import tim12.si.app.godisnji_odmori.Controller.SektorController;

public class ZaposlenikVM {
	

	public String ime;
	public String prezime;
	public String email;
	public long sektor;
	public String adresaStanovanja;
	public int brojDanaGodisnje;
	public Boolean privilegija;
	public String telefon;
	public Date datumRodjenja;
	public String sektorNaziv;
	public ZaposlenikVM(){}
	public ZaposlenikVM(String ime, String prezime,String email,Date datumRodjenja,String telefon, String adresaStanovanja)
	{
		this.ime= ime;
		this.prezime= prezime;
		this.datumRodjenja=datumRodjenja;
		this.email= email;
		this.telefon= telefon;
		this.adresaStanovanja= adresaStanovanja;
	}
	public ZaposlenikVM(String ime, String prezime, Date datumRodjenja, String email, long sektor,
			String adresaStanovanja, int brojDanaGodisnje, Boolean privilegija, String telefon)
	{
		this.ime=ime;
		this.prezime=prezime;
		this.datumRodjenja=datumRodjenja;
		this.email=email;
		this.sektor=sektor;
		this.adresaStanovanja=adresaStanovanja;
		this.brojDanaGodisnje=brojDanaGodisnje;
		this.privilegija=privilegija;
		this.telefon=telefon;
	}
	
	public ZaposlenikVM(String ime, String prezime,String email,Date datumRodjenja,String telefon, String adresaStanovanja, String sektor, String brojDana,Boolean privilegija)
	{
		SektorController sc = new SektorController();
		this.ime= ime;
		this.prezime= prezime;
		this.datumRodjenja=datumRodjenja;
		this.email= email;
		this.telefon= telefon;
		this.adresaStanovanja= adresaStanovanja;
		this.sektor= sc.dajSektorPoNazivu(sektor).getSektor_id();
		this.brojDanaGodisnje = Integer.parseInt(brojDana);
		this.privilegija=privilegija;
	}
	
	public ZaposlenikVM(String ime, String prezime,String email,Date datumRodjenja,String telefon, String adresaStanovanja, long sektor, int brojDana,Boolean privilegija)
	{
		SektorController sc = new SektorController();
		this.ime= ime;
		this.prezime= prezime;
		this.datumRodjenja=datumRodjenja;
		this.email= email;
		this.telefon= telefon;
		this.adresaStanovanja= adresaStanovanja;
		this.sektor= sektor;
		this.brojDanaGodisnje = brojDana;
		this.privilegija=privilegija;
	}

	
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public ZaposlenikVM(String ime) {
		super();
		this.ime = ime;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getSektor() {
		return sektor;
	}
	public void setSektor(long sektor) {
		this.sektor = sektor;
	}
	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}
	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}
	public int getBrojDanaGodisnje() {
		return brojDanaGodisnje;
	}
	public void setBrojDanaGodisnje(int brojDanaGodisnje) {
		this.brojDanaGodisnje = brojDanaGodisnje;
	}
	public Boolean getPrivilegija() {
		return privilegija;
	}
	public void setPrivilegija(Boolean privilegija) {
		this.privilegija = privilegija;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) throws Exception 
	{
		boolean nemaBrojeva=true;
		for(int i=0;i<ime.length();i++)
		{
			if(Character.isDigit(ime.charAt(i)))
					{
						nemaBrojeva=false; break;
					}
		}
		if(nemaBrojeva==true)
		{
			this.ime = ime;
		}
		else
		{
			throw new Exception("Greska");
		}
	}

	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) throws Exception
	{
		boolean nemaBrojeva=true;
		for(int i=0;i<prezime.length();i++)
		{
			if(!Character.isDigit(prezime.charAt(i)))
					{
						nemaBrojeva=false;
					}
		}
		if(nemaBrojeva==true)
		{
			this.prezime = prezime;
		}
		else
		{
			throw new Exception("Greska");
		}
		
	}



}
