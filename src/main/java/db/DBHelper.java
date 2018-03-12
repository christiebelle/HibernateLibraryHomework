package db;

import models.Book;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

    public static void save(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteAll(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static <T> List<T> getAll(String itemName){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        try{
            transaction = session.beginTransaction();
            String hql = "from " + itemName;
            results = session.createQuery(hql).list();
            transaction.commit();
        } catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static List<Book> getBooks(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Book> books = null;
        try{
            transaction = session.beginTransaction();
            String hql = "from Book WHERE author_id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            books = query.list();
            transaction.commit();
        } catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return books;
    }


    public static void deleteById(String className, int id){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            String hql = "delete from " + className + "WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> Object findById(String className, int id){
        session = HibernateUtil.getSessionFactory().openSession();
        Object foundobject = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from " + className + " WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            foundobject = query.uniqueResult();
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return foundobject;
    }
}

