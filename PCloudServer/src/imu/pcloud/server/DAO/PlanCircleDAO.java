package imu.pcloud.server.DAO;

import imu.pcloud.server.HibernateSessionFactory;
import imu.pcloud.server.been.PlanCircle;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for PlanCircle entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see imu.pcloud.server.DAO.PlanCircle
  * @author MyEclipse Persistence Tools 
 */
public class PlanCircleDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PlanCircleDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String COVER_IMAGE_ID = "coverImageId";



    
    public void save(PlanCircle transientInstance) {
        log.debug("saving PlanCircle instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
        ////getSession().flush();
        getSession().beginTransaction();
        getSession().getTransaction().commit();
        getSession().close();
    }
    
	public void delete(PlanCircle persistentInstance) {
        log.debug("deleting PlanCircle instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
        ////getSession().flush();
        getSession().beginTransaction();
        getSession().getTransaction().commit();
        getSession().close();
    }
    
    public PlanCircle findById( java.lang.Integer id) {
    	getSession().flush();
    	getSession().clear();
    	HibernateSessionFactory.closeSession();
        log.debug("getting PlanCircle instance with id: " + id);
        try {
            PlanCircle instance = (PlanCircle) getSession()
                    .get("imu.pcloud.server.been.PlanCircle", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PlanCircle instance) {
    	getSession().flush();
    	getSession().clear();
    	HibernateSessionFactory.closeSession();
        log.debug("finding PlanCircle instance by example");
        try {
            List results = getSession()
                    .createCriteria("imu.pcloud.server.been.PlanCircle")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding PlanCircle instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PlanCircle as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List findByCoverImageId(Object coverImageId
	) {
		return findByProperty(COVER_IMAGE_ID, coverImageId
		);
	}
	

	public List findAll() {
		log.debug("finding all PlanCircle instances");
		try {
			String queryString = "from PlanCircle";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PlanCircle merge(PlanCircle detachedInstance) {
        log.debug("merging PlanCircle instance");
        try {
            PlanCircle result = (PlanCircle) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PlanCircle instance) {
        log.debug("attaching dirty PlanCircle instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PlanCircle instance) {
        log.debug("attaching clean PlanCircle instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}