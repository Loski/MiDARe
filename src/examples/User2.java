package examples;

public class User2 {
	
	private String pseudo;
	private String password;
	private String mail;
	private String city;
	private int codePostal;
	
	public User2(String pseudo, String password, String mail, String city, int codePostal) {
		super();
		this.pseudo = pseudo;
		this.password = password;
		this.mail = mail;
		this.city = city;
		this.codePostal = codePostal;
	}
	
	@Override
	public String toString() {
		return "User2 [pseudo=" + pseudo + ", password=" + password + ", mail=" + mail + ", city=" + city
				+ ", codePostal=" + codePostal + "]";
	}

	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

}

