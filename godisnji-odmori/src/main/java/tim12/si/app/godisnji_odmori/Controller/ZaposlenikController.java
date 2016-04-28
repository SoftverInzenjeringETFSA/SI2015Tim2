package tim12.si.app.godisnji_odmori.Controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.Model.*;

public class ZaposlenikController 
{
	
	private ZaposlenikVM zvm;
	/**
	 * 
	 * @param zaposlenik
	 */	

	public ZaposlenikController(){};
	//dodaje zaposlenika u listu
	public void DodajZaposlenika() 
	{
		try
			{
				//zvm = null;
				String ime= zvm.getIme();
				String prezime= zvm.prezime;
				int sectorID=zvm.sektor_id;;
				Date datumRodjenja=zvm.datumRodjenja;
				String email=zvm.email;
				String adresaStanovanja=zvm.adresaStanovanja;
				String telefon= zvm.telefon;
				int brojDanaGodisnje= zvm.brojDanaGodisnje;
				Boolean privilegija=zvm.privilegija;
				Zaposlenik z= new Zaposlenik(ime,prezime,sectorID,datumRodjenja,email,adresaStanovanja,telefon,brojDanaGodisnje,privilegija);
				Zaposlenik.listaZaposlenika.add(z);
		}
		catch (Exception e)
			{
				System.err.println("Dodavanje zaposlenika nije uspjelo: " + e.getMessage());
			}
	}
	public int BrojZaposlenih()
	{
		return Zaposlenik.listaZaposlenika.size();
	}

	public void ModificirajZaposlenika(Zaposlenik zaposlenik)
	{
		try
		{	
			int index=PronadjiIndexZaposlenika(zaposlenik.getZaposlenik_id());
			Zaposlenik.listaZaposlenika.remove(index);
			//modificiramo sve elemente
			zvm = null;
			String ime= zvm.getIme();
			String prezime= zvm.prezime;
			int sectorID=zvm.sektor_id;;
			Date datumRodjenja=zvm.datumRodjenja;
			String email=zvm.email;
			String adresaStanovanja=zvm.adresaStanovanja;
			String telefon= zvm.telefon;
			int brojDanaGodisnje= zvm.brojDanaGodisnje;
			Boolean privilegija=zvm.privilegija;
			Zaposlenik z= new Zaposlenik(ime,prezime,sectorID,datumRodjenja,email,adresaStanovanja,telefon,brojDanaGodisnje,privilegija);
			//dodaj ga na isti broj indexa
			Zaposlenik.listaZaposlenika.add(index, z);
	}
	catch (Exception e)
		{
			System.err.println("Modificiranje zaposlenika nije uspjelo: " + e.getMessage());
		}
	}

	public int PronadjiIndexZaposlenika(int zaposlenik_id)
	{
		int brojac=0;
		int index=0;
		for(Zaposlenik zaposlenik: Zaposlenik.listaZaposlenika)
		{
			if(zaposlenik.getZaposlenik_id()==zaposlenik_id)
			{
				index=brojac;
				break;
			}
			brojac++;
		}
		return index;
	}
	public void ObrisiZaposlenika(int zaposlenikID)
	{
		try
		{
			Zaposlenik.listaZaposlenika.remove(zaposlenikID);
		}
		catch (Exception e)
		{
			System.err.println("Brisanje zaposlenika nije uspjelo: " + e.getMessage());
		}
		
	}

	public void AutentifikujKorisnika(Zaposlenik zaposlenik)
	{
		
	}
	/**
	 * 
	 * @param zaposlenik
	 */
	public void OdjaviKorisnika(Zaposlenik zaposlenik)
	{

	}
	/**
	 * 
	 * @param sektorID
	 */
	public List<Zaposlenik> DajZaposlenikePoSektoru(int sektorID)
	{
		List<Zaposlenik> listaZ=new ArrayList<Zaposlenik>();
		for(Zaposlenik z:Zaposlenik.listaZaposlenika)
		{
			if(z.getSektor_id()==sektorID)
			{
				listaZ.add(z);
			}
		}
		return listaZ;
		}
}
