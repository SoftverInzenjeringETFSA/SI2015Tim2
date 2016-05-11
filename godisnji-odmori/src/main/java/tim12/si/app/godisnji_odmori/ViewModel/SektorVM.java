package tim12.si.app.godisnji_odmori.ViewModel;

public class SektorVM {
	
	public String naziv;
	public int godina_osnivanja;
	public int brojUposlenih;
	public int maxBrojOdsutnih;
	public String opis;
	
	public SektorVM (String Naziv, String godina,int brojUpo, String max,  String Opis) {
		
		this.naziv=Naziv;
		this.godina_osnivanja=Integer.parseInt(godina);
		this.brojUposlenih=brojUpo;
		this.maxBrojOdsutnih=Integer.parseInt(max);
		this.opis=Opis;
		
		
		
	}

}


