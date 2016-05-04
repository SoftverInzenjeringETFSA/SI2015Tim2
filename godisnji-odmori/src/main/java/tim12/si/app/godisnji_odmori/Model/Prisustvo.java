package tim12.si.app.godisnji_odmori.Model;

import java.util.Date;

public class Prisustvo implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1232344220590121681L;
	private long prisustvo_id;
	public long getPrisustvo_id() {
		return prisustvo_id;
	}

	public void setPrisustvo_id(long prisustvo_id) {
		this.prisustvo_id = prisustvo_id;
	}

	private long zaposlenik_id;
	private Date datum;
	
	public Prisustvo() {}

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
