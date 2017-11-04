package generated;

import java.util.ArrayList;
import java.util.List;

// default package
// Generated 15 oct. 2017 04:59:00 by Hibernate Tools 5.2.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Team.
 * @see .Team
 * @author Hibernate Tools
 */
@Stateless
public class TeamHome {

	private static final Log log = LogFactory.getLog(TeamHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public TeamHome(EntityManager em) {
		this.entityManager = em;
	}

	public void persist(Team transientInstance) {
		log.debug("persisting Team instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Team persistentInstance) {
		log.debug("removing Team instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Team merge(Team detachedInstance) {
		log.debug("merging Team instance");
		try {
			Team result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Team findById(Integer id) {
		log.debug("getting Team instance with id: " + id);
		try {
			Team instance = entityManager.find(Team.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Team> getAll() {
		try {
			List<Team> instance = (List<Team>) entityManager.createQuery("SELECT a FROM Team a").getResultList();
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Encounter> getEncounters(int id)
	{
		try {		
			List<Encounter> instance = new ArrayList<Encounter>();
			Team t = findById(id);
			if(t!=null)
			{
				instance.addAll(t.getEncountersForIdTeam1());
				instance.addAll(t.getEncountersForIdTeam2());
			}
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
