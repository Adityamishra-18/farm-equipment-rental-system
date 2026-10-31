package com.farmequipment.dao;

import com.farmequipment.model.User;
import com.farmequipment.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {

    // Register a new user
    public boolean registerUser(User user) {
        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(user);

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();
            return false;
        }
    }

    // Find a user using email
    public User findByEmail(String email) {
        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM User WHERE email = :email",
                            User.class
                    )
                    .setParameter("email", email)
                    .uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM User ORDER BY id DESC",
                    User.class
            ).list();
        }
    }

    // Search users by name or email
    public List<User> searchUsers(String keyword) {
        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            String searchText =
                    keyword == null ? "" : keyword.trim().toLowerCase();

            return session.createQuery(
                            "FROM User WHERE " +
                                    "lower(name) LIKE :keyword OR " +
                                    "lower(email) LIKE :keyword " +
                                    "ORDER BY id DESC",
                            User.class
                    )
                    .setParameter("keyword", "%" + searchText + "%")
                    .list();
        }
    }

    // Block or unblock a user
    public boolean updateUserStatus(int id, String status) {

        if (status == null ||
                !("ACTIVE".equalsIgnoreCase(status) ||
                        "BLOCKED".equalsIgnoreCase(status))) {
            return false;
        }

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = session.createSelectionQuery(
                            "FROM User WHERE id = :id",
                            User.class
                    ).setParameter("id", id)
                    .uniqueResult();

            if (user == null ||
                    "ADMIN".equalsIgnoreCase(user.getRole())) {
                transaction.rollback();
                return false;
            }

            user.setStatus(status.toUpperCase());

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();
            return false;
        }
    }
}