package com.farmequipment.dao;

import com.farmequipment.model.Booking;
import com.farmequipment.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class BookingDAO {


    // Check whether equipment is available for selected dates
    public boolean isEquipmentAvailable(
            int equipmentId,
            LocalDate startDate,
            LocalDate endDate) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                            "SELECT COUNT(b) FROM Booking b " +
                                    "WHERE b.equipment.id = :equipmentId " +
                                    "AND b.status IN " +
                                    "('PENDING', 'PENDING_PAYMENT', " +
                                    "'APPROVED', 'CONFIRMED', " +
                                    "'CANCEL_REQUESTED') " +
                                    "AND b.startDate <= :endDate " +
                                    "AND b.endDate >= :startDate",
                            Long.class)
                    .setParameter("equipmentId", equipmentId)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();

            return count == 0;

        } catch (Exception e) {
            System.err.println(
                    "Equipment availability check failed: "
                            + e.getMessage()
            );
            return false;
        }
    }


    // Save a new booking
    public boolean saveBooking(Booking booking) {

        if (booking == null) {
            return false;
        }

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(booking);

            transaction.commit();

            return true;

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.err.println(
                    "Booking save failed: " + e.getMessage()
            );

            return false;
        }
    }


    // Get a booking only if it belongs to the specified user
    public Booking getBookingByIdForUser(
            int bookingId,
            int userId) {

        if (bookingId <= 0 || userId <= 0) {
            return null;
        }

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Booking b " +
                                    "WHERE b.id = :bookingId " +
                                    "AND b.userId = :userId",
                            Booking.class)
                    .setParameter("bookingId", bookingId)
                    .setParameter("userId", userId)
                    .uniqueResult();

        } catch (Exception e) {
            System.err.println(
                    "User booking lookup failed: " + e.getMessage()
            );
            return null;
        }
    }


    // Get a booking by ID
    public Booking getBookingById(int bookingId) {

        if (bookingId <= 0) {
            return null;
        }

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.find(Booking.class, bookingId);

        } catch (Exception e) {
            System.err.println(
                    "Booking lookup failed: " + e.getMessage()
            );
            return null;
        }
    }


    // Update booking status
    public boolean updateBookingStatus(
            int bookingId,
            String newStatus) {

        if (bookingId <= 0 || newStatus == null
                || newStatus.isBlank()) {
            return false;
        }

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Booking booking = session.find(Booking.class, bookingId);

            if (booking == null) {
                transaction.rollback();
                return false;
            }

            booking.setStatus(newStatus.trim());

            transaction.commit();

            return true;

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.err.println(
                    "Booking status update failed: " + e.getMessage()
            );

            return false;
        }
    }


    // Get the latest 6 bookings
    public List<Booking> getRecentBookings() {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Booking b " +
                                    "ORDER BY b.createdAt DESC, b.id DESC",
                            Booking.class)
                    .setMaxResults(6)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(
                    "Recent bookings retrieval failed: " + e.getMessage()
            );
            return Collections.emptyList();
        }
    }


    // Get bookings for a particular history page
    public List<Booking> getBookingHistory(
            int page,
            int pageSize) {

        int safePage = Math.max(page, 1);
        int safePageSize = Math.max(1, Math.min(pageSize, 50));

        int firstResult = (safePage - 1) * safePageSize;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Booking b " +
                                    "ORDER BY b.createdAt DESC, b.id DESC",
                            Booking.class)
                    .setFirstResult(firstResult)
                    .setMaxResults(safePageSize)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(
                    "Booking history retrieval failed: " + e.getMessage()
            );
            return Collections.emptyList();
        }
    }


    // Count all bookings
    public long getTotalBookingCount() {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "SELECT COUNT(b) FROM Booking b",
                            Long.class)
                    .getSingleResult();

        } catch (Exception e) {
            System.err.println(
                    "Booking count failed: " + e.getMessage()
            );
            return 0L;
        }
    }


    // Get all bookings, generally for admin use
    public List<Booking> getAllBookings() {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Booking b " +
                                    "ORDER BY b.createdAt DESC, b.id DESC",
                            Booking.class)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(
                    "All bookings retrieval failed: " + e.getMessage()
            );
            return Collections.emptyList();
        }
    }


    // Get bookings belonging to a particular user
    public List<Booking> getBookingsByUserId(int userId) {

        if (userId <= 0) {
            return Collections.emptyList();
        }

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Booking b " +
                                    "WHERE b.userId = :userId " +
                                    "ORDER BY b.createdAt DESC, b.id DESC",
                            Booking.class)
                    .setParameter("userId", userId)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(
                    "User bookings retrieval failed: " + e.getMessage()
            );
            return Collections.emptyList();
        }
    }


    /*
     * Cancel a booking or submit a cancellation request.
     *
     * PENDING / PENDING_PAYMENT:
     *     Can be cancelled directly.
     *
     * CONFIRMED:
     *     Payment may already be completed, so request review
     *     instead of directly cancelling and pretending a refund
     *     has occurred.
     *
     * The booking must belong to the logged-in user.
     */
    public boolean cancelBooking(int bookingId, int userId) {

        if (bookingId <= 0 || userId <= 0) {
            return false;
        }

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Booking booking = session.createQuery(
                            "FROM Booking b " +
                                    "WHERE b.id = :bookingId " +
                                    "AND b.userId = :userId",
                            Booking.class)
                    .setParameter("bookingId", bookingId)
                    .setParameter("userId", userId)
                    .uniqueResult();

            // Booking does not exist or belongs to another user
            if (booking == null) {
                transaction.rollback();
                return false;
            }

            String currentStatus = booking.getStatus();

            if ("PENDING".equals(currentStatus)
                    || "PENDING_PAYMENT".equals(currentStatus)) {

                booking.setStatus("CANCELLED");

            } else if ("CONFIRMED".equals(currentStatus)) {

                booking.setStatus("CANCEL_REQUESTED");

            } else {

                // Already cancelled, delivered, or request already submitted
                transaction.rollback();
                return false;
            }

            transaction.commit();

            return true;

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.err.println(
                    "Booking cancellation failed: " + e.getMessage()
            );

            return false;
        }
    }

    // Count bookings belonging to a specific user
    public long getTotalBookingCountByUserId(int userId) {

        if (userId <= 0) {
            return 0L;
        }

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "SELECT COUNT(b) FROM Booking b " +
                                    "WHERE b.userId = :userId",
                            Long.class)
                    .setParameter("userId", userId)
                    .getSingleResult();

        } catch (Exception e) {
            System.err.println(
                    "User booking history count failed: " + e.getMessage()
            );
            return 0L;
        }
    }


    // Retrieve paginated booking history for a specific user
    public List<Booking> getBookingHistoryByUserId(
            int userId,
            int page,
            int pageSize) {

        if (userId <= 0) {
            return Collections.emptyList();
        }

        int safePage = Math.max(page, 1);
        int safePageSize = Math.max(1, Math.min(pageSize, 50));
        int firstResult = (safePage - 1) * safePageSize;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Booking b " +
                                    "WHERE b.userId = :userId " +
                                    "ORDER BY b.createdAt DESC, b.id DESC",
                            Booking.class)
                    .setParameter("userId", userId)
                    .setFirstResult(firstResult)
                    .setMaxResults(safePageSize)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(
                    "User booking history retrieval failed: " + e.getMessage()
            );
            return Collections.emptyList();
        }
    }



}
