package tim12.si.app.godisnji_odmori.Model;

import java.util.Date;

public class Kalendar implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8243371382031140039L;
	private long kalendar_id;
	private Date datum;
	private String mjesec;
	private String dan;
	private Boolean vikend;
	private Boolean praznik;
	private String opis_praznika;
	
	public Kalendar() {}

	public long getkalendar_id()
	{
		return this.kalendar_id;
	}
	
	public void setKalendar_id(long kalendar_id)
	{
		this.kalendar_id = kalendar_id;
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

	public String getMjesec()
	{
		return this.mjesec;
	}

	/**
	 * 
	 * @param mjesec
	 */
	public void setMjesec(String mjesec)
	{
		this.mjesec = mjesec;
	}

	public String getDan()
	{
		return this.dan;
	}

	/**
	 * 
	 * @param dan
	 */
	public void setDan(String dan)
	{
		this.dan = dan;
	}

	public Boolean getVikend()
	{
		return this.vikend;
	}

	/**
	 * 
	 * @param vikend
	 */
	public void setVikend(Boolean vikend)
	{
		this.vikend = vikend;
	}

	public Boolean getPraznik()
	{
		return this.praznik;
	}

	/**
	 * 
	 * @param praznik
	 */
	public void setPraznik(Boolean praznik)
	{
		this.praznik = praznik;
	}

	public String getOpis_praznika()
	{
		return this.opis_praznika;
	}

	/**
	 * 
	 * @param opis_praznika
	 */
	public void setOpis_praznika(String opis_praznika)
	{
		this.opis_praznika = opis_praznika;
	}

	public void getAttribute()
	{
		// TODO - implement Kalendar.getAttribute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(int attribute)
	{
		// TODO - implement Kalendar.setAttribute
		throw new UnsupportedOperationException();
	}
}
