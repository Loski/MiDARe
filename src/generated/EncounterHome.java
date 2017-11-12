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
 * Home object for domain model class Encounter.
 * @see .Encounter
 * @author Hibernate Tools
 */
@Stateless
public class EncounterHome {

	private static final Log log = LogFactory.getLog(EncounterHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public EncounterHome(EntityManager em) {
		this.entityManager = em;
	}
	
	public void persist(Encounter transientInstance) {
		log.debug("persisting Encounter instance");
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
	
	/** get all no matter state_encounter */
	@SuppressWarnings("unchecked")
	public List<Encounter> getAll() {
        try {
            List<Encounter> instance = (List<Encounter>) entityManager.createQuery("SELECT a FROM Encounter a").getResultList();
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
	
	@SuppressWarnings("unchecked")
	public List<Encounter> findByIdApi(String id, int idsport){
		try {
			List<Encounter> instance = (List<Encounter>) entityManager.createQuery("SELECT a FROM Encounter a where id_encounter_api = :id and id_sport = :idsport")
					.setParameter("id", id).setParameter("idsport", idsport).getResultList();
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
