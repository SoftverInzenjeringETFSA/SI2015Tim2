package tim12.si.app.godisnji_odmori.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Zahtjev implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int zahtjev_id;
	private int podnosilac_id;
	private Date pocetak_odsustva;
	private Date zavrsetak_odsustva;
	private String tip_odsustva;
	private Boolean obradjen;
	private Boolean odluka;
	private String opis;
	private int sektor_id;
	
	public Zahtjev() {}
	
	public static List<Zahtjev> listaZahtjeva = new ArrayList<Zahtjev>();

	public int getZahtjev_id()
	{
		return this.zahtjev_id;
	}

	/**
	 * 
	 * @param zahtjev_id
	 */
	public void setZahtjev_id(int zahtjev_id)
	{
		this.zahtjev_id = zahtjev_id;
	}

	public int getPodnosilac_id()
	{
		return this.podnosilac_id;
	}

	/**
	 * 
	 * @param podnosilac_id
	 */
	public void setPodnosilac_id(int podnosilac_id)
	{
		this.podnosilac_id = podnosilac_id;
	}

	public Date getPocetak_odsustva()
	{
		return this.pocetak_odsustva;
	}

	/**
	 * 
	 * @param pocetak_odsustva
	 */
	public void setPocetak_odsustva(Date pocetak_odsustva)
	{
		this.pocetak_odsustva = pocetak_odsustva;
	}

	public Date getZavrsetak_odsustva()
	{
		return this.zavrsetak_odsustva;
	}

	/**
	 * 
	 * @param zavrsetak_odsustva
	 */
	public void setZavrsetak_odsustva(Date zavrsetak_odsustva)
	{
		this.zavrsetak_odsustva = zavrsetak_odsustva;
	}

	public String getTip_odsustva()
	{
		return this.tip_odsustva;
	}

	/**
	 * 
	 * @param tip_odsustva
	 */
	public void setTip_odsustva(String tip_odsustva)
	{
		this.tip_odsustva = tip_odsustva;
	}

	public Boolean getObradjen()
	{
		return this.obradjen;
	}

	/**
	 * 
	 * @param obradjen
	 */
	public void setObradjen(Boolean obradjen)
	{
		this.obradjen = obradjen;
	}

	public Boolean getOdluka()
	{
		return this.odluka;
	}

	/**
	 * 
	 * @param odluka
	 */
	public void setOdluka(Boolean odluka)
	{
		this.odluka = odluka;
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
		// TODO - implement Zahtjev.getAttribute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(int attribute)
	{
		// TODO - implement Zahtjev.setAttribute
		throw new UnsupportedOperationException();
	}

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

}
