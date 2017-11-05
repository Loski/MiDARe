package generated;

import java.util.List;

// default package
// Generated 15 oct. 2017 04:59:00 by Hibernate Tools 5.2.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import api.EntityHandler;

/**
 * Home object for domain model class Account.
 * @see .Account
 * @author Hibernate Tools
 */
@Stateless
public class AccountHome {

	private static final Log log = LogFactory.getLog(AccountHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public AccountHome(EntityManager em) {
		this.entityManager = em;
	}

	public void persist(Account transientInstance) {
		log.debug("persisting Account instance");
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(transientInstance);
			log.debug("persist successful");
			entityManager.getTransaction().commit();
		} catch (RuntimeException re) {
			entityManager.getTransaction().rollback();
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Account persistentInstance) {
		log.debug("removing Account instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Account merge(Account detachedInstance) {
		log.debug("merging Account instance");
		try {
			Account result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Account findById(Integer id) {
		log.debug("getting Account instance with id: " + id);
		try {
			Account instance = entityManager.find(Account.class, id);
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
			List<Account> instance = (List<Account>) entityManager.createQuery("SELECT a FROM Account a").getResultList();
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAccountWithPseudo(String pseudo) {
		try {
			List<Account> instance = (List<Account>) entityManager.createQuery("SELECT a FROM Account a WHERE a.pseudo LIKE :inputPseudo")
					.setParameter("inputPseudo", pseudo).getResultList();
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
