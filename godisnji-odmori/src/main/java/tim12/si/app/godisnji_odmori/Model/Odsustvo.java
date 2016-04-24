package tim12.si.app.godisnji_odmori.Model;

import java.util.Date;

public class Odsustvo {
	private int zaposlenik_id;
	private Date datum;
	private int tip;
	private String opis;

	
	public Odsustvo ()
	{
		
	}
	public int getZaposlenik_id()
	{
		return this.zaposlenik_id;
	}

	/**
	 * 
	 * @param zaposlenik_id
	 */
	public void setZaposlenik_id(int zaposlenik_id)
	{
		this.zaposlenik_id = zaposlenik_id;
	}

	public Date getDatum()
	{
		return this.datum;
	}

	/**
	 * 
	 * @param datum
	 */
	public void setDatum(Date datum)
	{
		this.datum = datum;
	}

	public int getTip()
	{
		return this.tip;
	}

	/**
	 * 
	 * @param tip
	 */
	public void setTip(int tip)
	{
		this.tip = tip;
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
		// TODO - implement Odsustvo.getAttribute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(int attribute)
	{
		// TODO - implement Odsustvo.setAttribute
		throw new UnsupportedOperationException();
	}

}
