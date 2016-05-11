package tim12.si.app.godisnji_odmori.ViewModel;


public class ZaposlenikAccountVM {
	

	private String username;
	
	private String password;
	private Boolean privilegija;
	
	public ZaposlenikAccountVM(String _username, String _password, Boolean _privilegija)
	{
		password = _password;
		username = _username;
		privilegija = _privilegija;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getPrivilegija() {
		return privilegija;
	}
	public void setPrivilegija(Boolean privilegija) {
		this.privilegija = privilegija;
	}

}
