package generated;

import java.util.ArrayList;

// default package
// Generated 15 oct. 2017 04:58:57 by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Account generated by hbm2java 
 */
@Entity
@Table(name = "account", catalog = "sanglier", uniqueConstraints = @UniqueConstraint(columnNames = "pseudo"))
public class Account implements java.io.Serializable {

	private Integer idUser;
	private String pseudo;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String mail;
	private Integer zipCode;
	private String city;
	private String adress;
	@JsonIgnore
	private Set<Bet> betsForCreator = new HashSet<Bet>(0);
	@JsonIgnore
	private Set<Bet> betsForOpponent = new HashSet<Bet>(0);
	@JsonIgnore
	private Set<Inventory> inventories = new HashSet(0);

	public Account() {
	}

	public Account(String pseudo, String password) {
		this.pseudo = pseudo;
		this.password = password;
	}

	public Account(String pseudo, String password, String mail, Integer zipCode, String city, String adress,
			Set<Bet> betsForCreator, Set<Bet> betsForOpponent, Set<Inventory> invetories) {
		this.pseudo = pseudo;
		this.password = password;
		this.mail = mail;
		this.zipCode = zipCode;
		this.city = city;
		this.adress = adress;
		this.betsForCreator = betsForCreator;
		this.betsForOpponent = betsForOpponent;
		this.inventories = inventories;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_user", unique = true, nullable = false)
	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Column(name = "pseudo", unique = true, nullable = false, length = 20)
	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Column(name = "password", nullable = false, length = 255)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "mail", length = 50)
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "zip_code")
	public Integer getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "adress", length = 50)
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
	public Set<Bet> getBetsForCreator() {
		return this.betsForCreator;
	}

	public void setBetsForCreator(Set<Bet> bets) {
		this.betsForCreator = bets;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "opponent")
	public Set<Bet> getBetsForOpponent() {
		return this.betsForOpponent;
	}

	public void setBetsForOpponent(Set<Bet> bets) {
		this.betsForOpponent = bets;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
	public Set<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}
	
	@Transient
	public List<Card> getCards()
	{
		List<Card> cards = new ArrayList<Card>();
		
		for(Inventory inv : inventories)
		{
			cards.add(inv.getCard());
		}
		
		return cards;
	}

	@Override
	public String toString() {
		return "Account [idUser=" + idUser + ", pseudo=" + pseudo + ", password=" + password + ", mail=" + mail
				+ ", zipCode=" + zipCode + ", city=" + city + ", adress=" + adress + "]";
	}
	
	

}
