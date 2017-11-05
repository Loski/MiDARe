package generated;

import java.util.List;

// default package
// Generated 15 oct. 2017 04:59:00 by Hibernate Tools 5.2.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Sport.
 * @see .Sport
 * @author Hibernate Tools
 */
@Stateless
public class SportHome {

	private static final Log log = LogFactory.getLog(SportHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public SportHome(EntityManager em) {
		this.entityManager = em;
	}

	public void persist(Sport transientInstance) {
		log.debug("persisting Sport instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Sport persistentInstance) {
		log.debug("removing Sport instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Sport merge(Sport detachedInstance) {
		log.debug("merging Sport instance");
		try {
			Sport result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Sport findById(Integer id) {
		log.debug("getting Sport instance with id: " + id);
		try {
			Sport instance = entityManager.find(Sport.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sport> getAll() {
        try {
            List<Sport> instance = (List<Sport>) entityManager.createQuery("SELECT a FROM Sport a").getResultList();
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}
