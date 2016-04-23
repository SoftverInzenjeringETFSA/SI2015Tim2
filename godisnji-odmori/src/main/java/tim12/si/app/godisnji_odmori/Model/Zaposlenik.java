package tim12.si.app.godisnji_odmori.Model;

import java.util.Date;

public class Zaposlenik {
	private int zaposlenik_id;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	private int sektor_id;
	private Date datum_rodjenja;
	private String email;
	private String adresa_stanovanja;
	private String telefon;
	private int broj_dana_godisnjeg;
	private Boolean privilegija;

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

	public String getIme()
	{
		return this.ime;
	}

	/**
	 * 
	 * @param ime
	 */
	public void setIme(String ime)
	{
		this.ime = ime;
	}

	public String getPrezime()
	{
		return this.prezime;
	}

	/**
	 * 
	 * @param prezime
	 */
	public void setPrezime(String prezime)
	{
		this.prezime = prezime;
	}

	public String getUsername()
	{
		return this.username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
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

	public Date getDatum_rodjenja()
	{
		return this.datum_rodjenja;
	}

	/**
	 * 
	 * @param datum_rodjenja
	 */
	public void setDatum_rodjenja(Date datum_rodjenja)
	{
		this.datum_rodjenja = datum_rodjenja;
	}

	public String getEmail()
	{
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getAdresa_stanovanja()
	{
		return this.adresa_stanovanja;
	}

	/**
	 * 
	 * @param adresa_stanovanja
	 */
	public void setAdresa_stanovanja(String adresa_stanovanja)
	{
		this.adresa_stanovanja = adresa_stanovanja;
	}

	public String getTelefon()
	{
		return this.telefon;
	}

	/**
	 * 
	 * @param telefon
	 */
	public void setTelefon(String telefon)
	{
		this.telefon = telefon;
	}

	public int getBroj_dana_godisnjeg()
	{
		return this.broj_dana_godisnjeg;
	}

	/**
	 * 
	 * @param broj_dana_godisnjeg
	 */
	public void setBroj_dana_godisnjeg(int broj_dana_godisnjeg)
	{
		this.broj_dana_godisnjeg = broj_dana_godisnjeg;
	}

	public Boolean getPrivilegija()
	{
		return this.privilegija;
	}

	/**
	 * 
	 * @param privilegija
	 */
	public void setPrivilegija(Boolean privilegija)
	{
		this.privilegija = privilegija;
	}

	public void getAttribute()
	{
		// TODO - implement Zaposlenik.getAttribute
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(int attribute)
	{
		// TODO - implement Zaposlenik.setAttribute
		throw new UnsupportedOperationException();
	}

}
