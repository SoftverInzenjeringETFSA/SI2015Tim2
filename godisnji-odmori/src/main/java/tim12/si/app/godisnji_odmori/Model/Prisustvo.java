package tim12.si.app.godisnji_odmori.Model;

import java.util.Date;

public class Prisustvo {
	
	private int zaposlenik_id;
	private Date datum;

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

	public void getAttribute()
	{
		// TODO - implement Prisustvo.getAttribute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(int attribute)
	{
		// TODO - implement Prisustvo.setAttribute
		throw new UnsupportedOperationException();
	}

}
