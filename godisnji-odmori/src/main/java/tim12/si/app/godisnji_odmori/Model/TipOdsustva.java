package tim12.si.app.godisnji_odmori.Model;

public class TipOdsustva {
	
	private int id_odsustva;
	private String naziv;
	private String opis;

	public int getId_odsustva()
	{
		return this.id_odsustva;
	}

	/**
	 * 
	 * @param id_odsustva
	 */
	public void setId_odsustva(int id_odsustva)
	{
		this.id_odsustva = id_odsustva;
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

	public void getAttribute()
	{
		// TODO - implement TipOdsustva.getAttribute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(int attribute)
	{
		// TODO - implement TipOdsustva.setAttribute
		throw new UnsupportedOperationException();
	}

}