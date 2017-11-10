package generated;
// Generated 10 nov. 2017 23:49:32 by Hibernate Tools 5.2.6.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class BetOnWin.
 * @see generated.BetOnWin
 * @author Hibernate Tools
 */
@Stateless
public class BetOnWinHome {

	private static final Log log = LogFactory.getLog(BetOnWinHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public BetOnWinHome(EntityManager em) {
		this.entityManager = em;
	}
	
	public void persist(BetOnWin transientInstance) {
		log.debug("persisting BetOnWin instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(BetOnWin persistentInstance) {
		log.debug("removing BetOnWin instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public BetOnWin merge(BetOnWin detachedInstance) {
		log.debug("merging BetOnWin instance");
		try {
			BetOnWin result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public BetOnWin findById(Integer id) {
		log.debug("getting BetOnWin instance with id: " + id);
		try {
			BetOnWin instance = entityManager.find(BetOnWin.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
