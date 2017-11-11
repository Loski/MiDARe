package generated;
// Generated 11 nov. 2017 17:04:27 by Hibernate Tools 5.2.6.Final

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

	public void persist(Card transientInstance) {
		log.debug("persisting Card instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
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
}
