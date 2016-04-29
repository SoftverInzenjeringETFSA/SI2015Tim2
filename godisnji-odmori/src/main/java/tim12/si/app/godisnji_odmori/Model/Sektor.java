package tim12.si.app.godisnji_odmori.Model;

public class Sektor implements java.io.Serializable {
	private int sektor_id;
	private String naziv;
	private int godina_osnivanja;
	private int broj_uposlenih;
	private int max_broj_odsutnih;
	private String opis;
	
	public Sektor() {}

	public int getSektor_id()
	{
		return this.sektor_id;
	}

	/**
	 * 
	 * @param sektor_id
	 */
	public void setSektor_id(int sektor_id)
	{
		this.sektor_id = sektor_id;
	}

	public String getNaziv()
	{
		return this.naziv;
	}

	/**
	 * 
	 * @param naziv
	 */
	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}

	public int getGodina_osnivanja()
	{
		return this.godina_osnivanja;
	}

	/**
	 * 
	 * @param godina_osnivanja
	 */
	public void setGodina_osnivanja(int godina_osnivanja)
	{
		this.godina_osnivanja = godina_osnivanja;
	}

	public int getBroj_uposlenih()
	{
		return this.broj_uposlenih;
	}

	/**
	 * 
	 * @param broj_uposlenih
	 */
	public void setBroj_uposlenih(int broj_uposlenih)
	{
		this.broj_uposlenih = broj_uposlenih;
	}

	public int getMax_broj_odsutnih()
	{
		return this.max_broj_odsutnih;
	}

	/**
	 * 
	 * @param max_broj_odsutnih
	 */
	public void setMax_broj_odsutnih(int max_broj_odsutnih)
	{
		this.max_broj_odsutnih = max_broj_odsutnih;
	}

	public String getOpis()
	{
		return this.opis;
	}

	/**
	 * 
	 * @param opis
	 */
	public void setOpis(String opis)
	{
		this.opis = opis;
	}

}
