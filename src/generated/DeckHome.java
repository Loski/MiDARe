package generated;
// Generated 11 nov. 2017 17:04:27 by Hibernate Tools 5.2.6.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Deck.
 * @see generated.Deck
 * @author Hibernate Tools
 */
@Stateless
public class DeckHome {

	private static final Log log = LogFactory.getLog(DeckHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public DeckHome(EntityManager em) {
		this.entityManager=em;
	}

	public void persist(Deck transientInstance) {
		log.debug("persisting Deck instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Deck persistentInstance) {
		log.debug("removing Deck instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Deck merge(Deck detachedInstance) {
		log.debug("merging Deck instance");
		try {
			Deck result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Deck findById(Integer id) {
		log.debug("getting Deck instance with id: " + id);
		try {
			Deck instance = entityManager.find(Deck.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
