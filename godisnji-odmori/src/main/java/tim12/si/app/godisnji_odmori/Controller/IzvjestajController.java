package tim12.si.app.godisnji_odmori.Controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.Model.*;
import tim12.si.app.godisnji_odmori.View.*;

public class IzvjestajController {
	
	private Session session;
	
	public IzvjestajController(Session session)
	{
		this.session=session;
	}
	
	
	/** 
	 * @param godina
	 */
	public List<IzvjestajVM> dajGodisnjiIzvjestaj(int godina) {
		IzvjestajVM ivm= new IzvjestajVM();
		List<IzvjestajVM> lista = new ArrayList<IzvjestajVM>();
		return lista;
	}

	/**
	 * @param mjesec
	 * @param godina
	 */
	public List<IzvjestajVM> dajMjesecniIzvjestaj(int mjesec, int godina) {
		IzvjestajVM ivm= new IzvjestajVM();
		IzvjestajZapVM izvm= new IzvjestajZapVM();
		List<IzvjestajVM> lista = new ArrayList<IzvjestajVM>();
		return lista;
	}

	public List<IzvjestajZaposlenikBrDanaVM> dajPreostaliBrojDanaPoKorisniku() {
		IzvjestajVM ivm= new IzvjestajVM();
		IzvjestajZaposlenikBrDanaVM izbrdvm= new IzvjestajZaposlenikBrDanaVM();
		List<IzvjestajZaposlenikBrDanaVM> lista = new ArrayList<IzvjestajZaposlenikBrDanaVM>();
		return lista;
	}

}
