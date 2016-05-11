package tim12.si.app.godisnji_odmori.ViewModel;

import java.util.List;

public class IzvjestajVM {
	
	public List<IzvjestajZapVM> listaZaposlenikaIPodataka;
	public int ukupnoRadni;
	public int ukupnoNeradni;
	
	
	//Konstruktori
	
	public IzvjestajVM (List<IzvjestajZapVM> listaZaposlenikaIPodataka, int ukupnoRadni, int ukupnoNeradni)
	{
		this.listaZaposlenikaIPodataka=listaZaposlenikaIPodataka;
		this.ukupnoRadni=ukupnoRadni;
		this.ukupnoNeradni=ukupnoNeradni;
	}
	
	public IzvjestajVM() {}
	
	//Getteri i setteri
	
	public List<IzvjestajZapVM> getListaZaposlenikaIPodataka() {
		return listaZaposlenikaIPodataka;
	}

	public void setListaZaposlenikaIPodataka(List<IzvjestajZapVM> listaZaposlenikaIPodataka) {
		this.listaZaposlenikaIPodataka = listaZaposlenikaIPodataka;
	}

	public int getUkupnoRadni() {
		return ukupnoRadni;
	}

	public void setUkupnoRadni(int ukupnoRadni) {
		this.ukupnoRadni = ukupnoRadni;
	}

	public int getUkupnoNeradni() {
		return ukupnoNeradni;
	}

	public void setUkupnoNeradni(int ukupnoNeradni) {
		this.ukupnoNeradni = ukupnoNeradni;
	}
}
