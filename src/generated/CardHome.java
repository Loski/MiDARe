package generated;
// Generated 10 nov. 2017 23:49:32 by Hibernate Tools 5.2.6.Final

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Card.
 * @see generated.Card
 * @author Hibernate Tools
 */
@Stateless
public class CardHome {

	private static final Log log = LogFactory.getLog(CardHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public CardHome(EntityManager em) {
		this.entityManager=em;
	}

	public void persist(Card transientInstance) {
		log.debug("persisting Card instance");
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.getTransaction().commit();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			entityManager.getTransaction().rollback();
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Card persistentInstance) {
		log.debug("removing Card instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Card merge(Card detachedInstance) {
		log.debug("merging Card instance");
		try {
			Card result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Card findById(Integer id) {
		log.debug("getting Card instance with id: " + id);
		try {
			Card instance = entityManager.find(Card.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAll() {
		try {
			List<Account> instance = (List<Account>) entityManager.createQuery("SELECT a FROM Card a").getResultList();
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public int getNumberOfCards()
	{
		try
		{
			return this.getAll().size();
		}catch(Exception e)
		{
			return 0;
		}
	}

	public void refresh(Card card) {
		try {
			this.entityManager.refresh(card);
		} catch (RuntimeException re) {
			log.error("refresh failed", re);
			throw re;
		}
	}
}
