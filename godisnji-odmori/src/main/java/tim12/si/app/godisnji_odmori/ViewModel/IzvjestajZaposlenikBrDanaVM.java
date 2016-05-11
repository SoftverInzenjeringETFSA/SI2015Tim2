package tim12.si.app.godisnji_odmori.ViewModel;

import java.util.List;

public class IzvjestajZaposlenikBrDanaVM {
	
	public List<ZaposlenikBrDana> listaZaposlenikaIPodataka;
	public int ukupnoPreostalo;
	
	//Konstruktori
	public IzvjestajZaposlenikBrDanaVM (List<ZaposlenikBrDana> listaZaposlenikaIPodataka, int ukupnoPreostalo)
	{
		this.listaZaposlenikaIPodataka=listaZaposlenikaIPodataka;
		this.ukupnoPreostalo=ukupnoPreostalo;
	}
	
	public IzvjestajZaposlenikBrDanaVM() {}
	
	//Getteri i setteri
	
	public List<ZaposlenikBrDana> getListaZaposlenikaIPodataka() {
		return listaZaposlenikaIPodataka;
	}

	public void setListaZaposlenikaIPodataka(List<ZaposlenikBrDana> listaZaposlenikaIPodataka) {
		this.listaZaposlenikaIPodataka = listaZaposlenikaIPodataka;
	}

	public int getUkupnoPreostalo() {
		return ukupnoPreostalo;
	}

	public void setUkupnoPreostalo(int ukupnoPreostalo) {
		this.ukupnoPreostalo = ukupnoPreostalo;
	}
}
