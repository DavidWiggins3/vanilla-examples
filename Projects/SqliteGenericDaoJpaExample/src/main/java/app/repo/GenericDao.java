package app.repo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import app.pojo.PersistentEntity;

/**
 * Provides a common interface to basic CRUD queries and session management.
 * Based loosely on the org.springframework.data.jpa.repository.support.SimpleJpaRepository class found in spring-data-commons-1.11.4.RELEASE.jar
 *  
 * @param <T> The type of Entity this instance of GenericDao is to manage
 * @param <ID> The type of the Identifier column for the Entity that this instance of GenericDao is to manage
 */
public class GenericDao<T extends PersistentEntity<?>, ID extends Serializable> {
	protected Session session;
	protected Transaction tx;
	protected EntityManager em;

	final Class<T> typeParameterClass;
	final Class<ID> idParameterClass;

	public GenericDao(Class<T> typeParameterClass, Class<ID> idParameterClass) {
		this.typeParameterClass = typeParameterClass;
		this.idParameterClass = idParameterClass;
	}

	public void delete(T entity) {
        try {
            session.delete(entity);
        } catch (HibernateException e) {
            handleException(e);
        }
	}

	public void delete(Iterable<? extends T> entities) {
		for (T entity : entities) {
			delete(entity);
		}
	}

	public void deleteAll() {
		for (T element : findAll()) {
			delete(element);
		}
	}

	public T find(ID id) {
        T obj = null;
        try {
            obj = (T) session.load(typeParameterClass, id);
        } catch (HibernateException e) {
            handleException(e);
        }
        return obj;
    }
	
    public List<T> findAll() {
        List<T> objects = null;
        try {
            Query query = session.createQuery("from " + typeParameterClass.getName());
            objects = query.list();
        } catch (HibernateException e) {
            handleException(e);
        }
        return objects;
    }

	public long count() {
		long count = 0;
        try {
            Query query = session.createQuery("select COUNT(*) from " + typeParameterClass.getName());
            Number result = (Number) query.uniqueResult();
            count = result.longValue();
        } catch (HibernateException e) {
            handleException(e);
        }
        return count;
	}

	public <S extends T> S save(S entity) {
		session.saveOrUpdate(entity);
		return entity;
	}

	public <S extends T> List<S> save(Iterable<S> entities) {
		List<S> result = new ArrayList<S>();

		if (entities == null) {
			return result;
		}

		for (S entity : entities) {
			result.add(save(entity));
		}
		return result;
	}

	public void flush() {
		session.flush();
	}
	
    public void startOperation() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        em = session.getEntityManagerFactory().createEntityManager();
    }
    
    public void close(){
    	session.close();
    }
    
    public void commit(){
    	tx.commit();
    }
	
    private void handleException(HibernateException e) throws DataAccessLayerException {
    	tx.rollback();
        throw new DataAccessLayerException(e);
    }
    
}