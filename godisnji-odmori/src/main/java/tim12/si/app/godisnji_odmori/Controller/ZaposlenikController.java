package tim12.si.app.godisnji_odmori.Controller;

import java.util.List;
import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.Model.*;

public class ZaposlenikController {
	
	/**
	 * 
	 * @param zaposlenik
	 */	

	public static int brojZaposlenih=0;
	public ZaposlenikController(){};
	public void DodajZaposlenika(Zaposlenik zaposlenik) 
	{
		brojZaposlenih+=1;
		// TODO - implement ZaposlenikController.dodajZaposlenika
		//throw new UnsupportedOperationException();
	}
	public int BrojZapolsenih()
	{
		return brojZaposlenih;
	}
	/**
	 * 
	 * @param zaposlenik
	 */
	public void ModificirajZaposlenika(Zaposlenik zaposlenik) {
		
		//treba da pronadje zaposlenika u bazi ili listi
		// TODO - implement ZaposlenikController.modificirajZaposlenika
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param zaposlenikID
	 */
	public void ObrisiZaposlenika(int zaposlenikID) {
		// TODO - implement ZaposlenikController.obrisiZaposlenika
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zaposlenik
	 */
	public void AutentifikujKorisnika(Zaposlenik zaposlenik) {
		// TODO - implement ZaposlenikController.autentifikujKorisnika
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zaposlenik
	 */
	public void OdjaviKorisnika(Zaposlenik zaposlenik) {
		// TODO - implement ZaposlenikController.odjaviKorisnika
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sektorID
	 */
	public List<ZaposlenikVM> DajZaposlenikePoSektoru(int sektorID) {
		// TODO - implement ZaposlenikController.dajZaposlenikePoSektoru
		throw new UnsupportedOperationException();
	}

}
