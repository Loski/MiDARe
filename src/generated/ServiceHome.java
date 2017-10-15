package generated;

// default package
// Generated 15 oct. 2017 04:59:00 by Hibernate Tools 5.2.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
/** 
 * Home object for domain model class Service.
 * @see .Service
 * @author Hibernate Tools
 */
@Stateless
public class ServiceHome {

	private static final Log log = LogFactory.getLog(ServiceHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Service transientInstance) {
		log.debug("persisting Service instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Service persistentInstance) {
		log.debug("removing Service instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Service merge(Service detachedInstance) {
		log.debug("merging Service instance");
		try {
			Service result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Service findById(Integer id) {
		log.debug("getting Service instance with id: " + id);
		try {
			Service instance = entityManager.find(Service.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
