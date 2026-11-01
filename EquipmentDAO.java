
        package com.farmequipment.dao;

import com.farmequipment.model.Equipment;
import com.farmequipment.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EquipmentDAO {

    // 1. Add equipment
    public boolean saveEquipment(Equipment equipment) {

        if (equipment == null
                || equipment.getOwnerId() == null
                || equipment.getOwnerId() <= 0) {
            return false;
        }

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.persist(equipment);
            transaction.commit();

            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.err.println("Equipment save failed: " + e.getMessage());
            return false;
        }
    }

    // 2. Get all equipment (for public farmer search/listing)
    public List<Equipment> getAllEquipment() {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Equipment ORDER BY id DESC",
                    Equipment.class
            ).getResultList();

        } catch (Exception e) {
            System.err.println("Equipment retrieval failed: " + e.getMessage());
            return List.of();
        }
    }

    // 3. Get equipment by ID
    public Equipment getEquipmentById(int id) {

        if (id <= 0) {
            return null;
        }

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createSelectionQuery(
                            "FROM Equipment WHERE id = :id",
                            Equipment.class
                    )
                    .setParameter("id", id)
                    .uniqueResult();

        } catch (Exception e) {
            System.err.println("Equipment lookup failed: " + e.getMessage());
            return null;
        }
    }

    // 4. Get equipment belonging to a specific owner
    public List<Equipment> getEquipmentByOwnerId(int ownerId) {

        if (ownerId <= 0) {
            return List.of();
        }

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Equipment e WHERE e.ownerId = :ownerId " +
                                    "ORDER BY e.id DESC",
                            Equipment.class
                    )
                    .setParameter("ownerId", ownerId)
                    .getResultList();

        } catch (Exception e) {
            System.err.println("Owner equipment retrieval failed: "
                    + e.getMessage());
            return List.of();
        }
    }

    // 5. Update equipment only if it belongs to this owner
    public boolean updateEquipment(Equipment updatedEquipment, int ownerId) {

        if (updatedEquipment == null
                || updatedEquipment.getId() <= 0
                || ownerId <= 0) {
            return false;
        }

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Equipment existing = session.createSelectionQuery(
                            "FROM Equipment e WHERE e.id = :id " +
                                    "AND e.ownerId = :ownerId",
                            Equipment.class
                    )
                    .setParameter("id", updatedEquipment.getId())
                    .setParameter("ownerId", ownerId)
                    .uniqueResult();

            if (existing == null) {
                transaction.rollback();
                return false;
            }

            existing.setName(updatedEquipment.getName());
            existing.setCategory(updatedEquipment.getCategory());
            existing.setBrand(updatedEquipment.getBrand());
            existing.setModel(updatedEquipment.getModel());
            existing.setDescription(updatedEquipment.getDescription());
            existing.setPricePerDay(updatedEquipment.getPricePerDay());
            existing.setLocation(updatedEquipment.getLocation());
            existing.setImage(updatedEquipment.getImage());
            existing.setAvailability(updatedEquipment.getAvailability());

            // Do not change existing.ownerId from form input
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.err.println("Equipment update failed: " + e.getMessage());
            return false;
        }
    }

    // 6. Delete equipment only if it belongs to this owner
    public boolean deleteEquipment(int equipmentId, int ownerId) {

        if (equipmentId <= 0 || ownerId <= 0) {
            return false;
        }

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Equipment equipment = session.createSelectionQuery(
                            "FROM Equipment e WHERE e.id = :id " +
                                    "AND e.ownerId = :ownerId",
                            Equipment.class
                    )
                    .setParameter("id", equipmentId)
                    .setParameter("ownerId", ownerId)
                    .uniqueResult();

            if (equipment == null) {
                transaction.rollback();
                return false;
            }

            session.remove(equipment);
            transaction.commit();

            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.err.println("Equipment deletion failed: " + e.getMessage());
            return false;
        }
    }

    // 7. Search equipment across all owners for farmers
    public List<Equipment> searchEquipment(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            return getAllEquipment();
        }

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
                    FROM Equipment e
                    WHERE LOWER(e.name) LIKE :keyword
                       OR LOWER(e.category) LIKE :keyword
                       OR LOWER(e.brand) LIKE :keyword
                       OR LOWER(e.model) LIKE :keyword
                       OR LOWER(e.location) LIKE :keyword
                    ORDER BY e.id DESC
                    """;

            return session.createQuery(hql, Equipment.class)
                    .setParameter(
                            "keyword",
                            "%" + keyword.trim().toLowerCase() + "%"
                    )
                    .getResultList();

        } catch (Exception e) {
            System.err.println("Equipment search failed: " + e.getMessage());
            return List.of();
        }
    }

    // 8. Update availability only if equipment belongs to this owner
    public boolean updateAvailability(
            int equipmentId, int ownerId, String availability) {

        if (equipmentId <= 0
                || ownerId <= 0
                || availability == null
                || !("available".equalsIgnoreCase(availability)
                || "unavailable".equalsIgnoreCase(availability))) {
            return false;
        }

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Equipment equipment = session.createSelectionQuery(
                            "FROM Equipment e WHERE e.id = :id " +
                                    "AND e.ownerId = :ownerId",
                            Equipment.class
                    )
                    .setParameter("id", equipmentId)
                    .setParameter("ownerId", ownerId)
                    .uniqueResult();

            if (equipment == null) {
                transaction.rollback();
                return false;
            }

            equipment.setAvailability(availability.toLowerCase());

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.err.println("Availability update failed: " + e.getMessage());
            return false;
        }

    }

    // 9. Admin can update availability of any equipment
    public boolean adminUpdateAvailability(
            int equipmentId, String availability) {

        if (equipmentId <= 0
                || availability == null
                || !("available".equalsIgnoreCase(availability)
                || "unavailable".equalsIgnoreCase(availability))) {
            return false;
        }

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Equipment equipment = session.get(
                    Equipment.class, equipmentId
            );

            if (equipment == null) {
                transaction.rollback();
                return false;
            }

            equipment.setAvailability(availability.toLowerCase());

            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.err.println(
                    "Admin availability update failed: " + e.getMessage()
            );
            return false;
        }
    }

    public List<Equipment> searchEquipmentByOwnerId(
            String keyword, int ownerId) {

        if (ownerId <= 0) {
            return List.of();
        }

        if (keyword == null || keyword.isBlank()) {
            return getEquipmentByOwnerId(ownerId);
        }

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
                FROM Equipment e
                WHERE e.ownerId = :ownerId
                  AND (
                       LOWER(e.name) LIKE :keyword
                    OR LOWER(e.category) LIKE :keyword
                    OR LOWER(e.brand) LIKE :keyword
                    OR LOWER(e.model) LIKE :keyword
                    OR LOWER(e.location) LIKE :keyword
                  )
                ORDER BY e.id DESC
                """;

            return session.createQuery(hql, Equipment.class)
                    .setParameter("ownerId", ownerId)
                    .setParameter(
                            "keyword",
                            "%" + keyword.trim().toLowerCase() + "%"
                    )
                    .getResultList();

        } catch (Exception e) {
            System.err.println(
                    "Owner equipment search failed: " + e.getMessage()
            );
            return List.of();
        }
    }


}
