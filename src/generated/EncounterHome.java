package generated;

// default package
// Generated 15 oct. 2017 04:59:00 by Hibernate Tools 5.2.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Encounter.
 * @see .Encounter
 * @author Hibernate Tools
 */
@Stateless
public class EncounterHome {

	private static final Log log = LogFactory.getLog(EncounterHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Encounter transientInstance) {
		log.debug("persisting Encounter instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Encounter persistentInstance) {
		log.debug("removing Encounter instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Encounter merge(Encounter detachedInstance) {
		log.debug("merging Encounter instance");
		try {
			Encounter result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Encounter findById(Integer id) {
		log.debug("getting Encounter instance with id: " + id);
		try {
			Encounter instance = entityManager.find(Encounter.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
