package tim12.si.app.godisnji_odmori.ViewModel;

public class SektorVM {
	
	public String naziv;
	public int godina_osnivanja;
	public int brojUposlenih;
	public int maxBrojOdsutnih;
	public String opis;
	
	public SektorVM (String Naziv, int i,int brojUpo, String max,  String Opis) {
		
		this.naziv=Naziv;
		this.godina_osnivanja=i;
		this.brojUposlenih=brojUpo;
		this.maxBrojOdsutnih=Integer.parseInt(max);
		this.opis=Opis;
		
		
		
	}

}


