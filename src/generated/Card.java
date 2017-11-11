package generated;
// Generated 10 nov. 2017 23:49:31 by Hibernate Tools 5.2.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Card generated by hbm2java
 */
@Entity
@Table(name = "card", catalog = "sanglier")
public class Card implements java.io.Serializable {

	public static final String URL = "http://ffxivtriad.com/assets/images/cards/{id}_t.png";
	public static final String BIG_URL = "http://ffxivtriad.com/assets/images/cards/{id}.png";
	
	private Integer idCard;
	private String cardName;
	private String cardDescription;
	private Integer number;
	private Deck deck;
	@JsonIgnore
	private Set<Bet> betsForIdCardCreator = new HashSet(0);
	@JsonIgnore
	private Set<Bet> betsForIdCardOppenent = new HashSet(0);
	@JsonIgnore
	private Set<Inventory> inventories = new HashSet(0);

	public Card() {
	}

	public Card(String cardName) {
		this.cardName = cardName;
	}

	public Card(Deck deck,String cardName, String cardDescription, Set<Bet> betOnWinsForIdCardCreator,
			Set<Bet> betOnWinsForIdCardOppenent, Set<Inventory> inventories) {
		this.cardName = cardName;
		this.cardDescription = cardDescription;
		this.betsForIdCardCreator = betOnWinsForIdCardCreator;
		this.betsForIdCardOppenent = betOnWinsForIdCardOppenent;
		this.inventories = inventories;
		this.deck = deck;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_card", unique = true, nullable = false)
	public Integer getIdCard() {
		return this.idCard;
	}

	public void setIdCard(Integer idCard) {
		this.idCard = idCard;
	}

	@Column(name = "card_name", nullable = false, length = 40)
	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_deck")
	public Deck getDeck() {
		return this.deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	@Column(name = "card_description", length = 150)
	public String getCardDescription() {
		return this.cardDescription;
	}

	public void setCardDescription(String cardDescription) {
		this.cardDescription = cardDescription;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "creatorCard")
	public Set<Bet> getBetsForIdCardCreator() {
		return this.betsForIdCardCreator;
	}

	public void setBetsForIdCardCreator(Set<Bet> betsForIdCardCreator) {
		this.betsForIdCardCreator = betsForIdCardCreator;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "opponentCard")
	public Set<Bet> getBetsForIdCardOppenent() {
		return this.betsForIdCardOppenent;
	}

	public void setBetsForIdCardOppenent(Set<Bet> betsForIdCardOppenent) {
		this.betsForIdCardOppenent = betsForIdCardOppenent;
	}
	
	@Column(name = "number")
	public Integer getNumber()
	{
		return this.number;
	}
	
	public void setNumber(Integer number)
	{
		this.number = number;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "card")
	public Set<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}
	
	@Transient
	public String getURL()
	{
		String id = "";
		
		if(this.idCard<100)
			id+="0";
		
		if(this.idCard<10)
			id+="0";
		
		id+=this.idCard;
		
		return URL.replace("{id}", id);
	}
	
	@Transient
	public String getURLBig()
	{
		String id = "";
		
		if(this.idCard<100)
			id+="0";
		
		if(this.idCard<10)
			id+="0";
		
		id+=this.idCard;
		
		return BIG_URL.replace("{id}", id);
	}

	@Override
	public String toString() {
		return "Card [idCard=" + idCard + ", cardName=" + cardName +", betsForIdCardCreator=" + betsForIdCardCreator + ", betsForIdCardOppenent=" + betsForIdCardOppenent
				+ "]";
	}

}
