package tim12.si.app.godisnji_odmori.ViewModel;

import java.util.Date;

public class ZaposlenikVM {
	
	public String ime;
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
	public String prezime;
	public String getPrezime() {
		return ime;
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
	public Date datumRodjenja;
	public String email;
	public String sektor;
	public String adresaStanovanja;
	public int brojDanaGodisnje;
	public Boolean privilegija;
	public String telefon;


}
