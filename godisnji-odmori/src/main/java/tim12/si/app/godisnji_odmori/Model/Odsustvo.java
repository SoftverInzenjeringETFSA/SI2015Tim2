package tim12.si.app.godisnji_odmori.Model;

import java.util.Date;

public class Odsustvo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4093850039178622334L;
	private long odsustvo_id;
	private long zaposlenik_id;
	private Date datum;
	private long tip;
	private String opis;

	
	public Odsustvo ()
	{}
	
	public long getZaposlenik_id()
	{
		return this.zaposlenik_id;
	}

	/**
	 * 
	 * @param zaposlenik_id
	 */
	public void setZaposlenik_id(long zaposlenik_id)
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

	public long getTip()
	{
		return this.tip;
	}

	/**
	 * 
	 * @param tip
	 */
	public void setTip(long tip)
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

	public long getOdsustvo_id() {
		return odsustvo_id;
	}

	public void setOdsustvo_id(long odsustvo_id) {
		this.odsustvo_id = odsustvo_id;
	}

}
