package org.example.DAO;

import org.example.models.Role;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.HibernateUtil.HibernateUtil;

import jakarta.persistence.Query;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    @Override
    public Role getById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Role.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Role> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Role", Role.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(Role role) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(role);
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
    public boolean update(Role role) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(role);
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
            Role role = getById(id);
            if (role != null) {
                session.delete(role);
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
