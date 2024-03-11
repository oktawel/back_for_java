package org.example.DAO;

import jakarta.persistence.Query;
import org.example.HibernateUtil.HibernateUtil;
import org.example.models.Grooup;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GroupDAOImpl implements GroupDAO{

    @Override
    public Grooup getById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Grooup.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Grooup getByName(String name) {
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("FROM Groop WHERE name = :name", Grooup.class);
            query.setParameter("name", name);
            return (Grooup) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Grooup> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Groop", Grooup.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(Grooup group) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Grooup group) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(group);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Grooup group = getById(id);
            if (group != null) {
                session.delete(group);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
