package generated;
// Generated 11 nov. 2017 17:04:27 by Hibernate Tools 5.2.6.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Inventory.
 * @see generated.Inventory
 * @author Hibernate Tools
 */
@Stateless
public class InventoryHome {

	private static final Log log = LogFactory.getLog(InventoryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public InventoryHome(EntityManager em) {
		this.entityManager=em;
	}

	public void persist(Inventory transientInstance) {
		log.debug("persisting Inventory instance");
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(transientInstance);
			entityManager.getTransaction().commit();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			entityManager.getTransaction().rollback();
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Inventory persistentInstance) {
		log.debug("removing Inventory instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Inventory merge(Inventory detachedInstance) {
		log.debug("merging Inventory instance");
		try {
			Inventory result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Inventory findById(InventoryId id) {
		log.debug("getting Inventory instance with id: " + id);
		try {
			Inventory instance = entityManager.find(Inventory.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
