package com.farmequipment.util;

import com.farmequipment.model.User;
import com.farmequipment.model.Equipment;
import com.farmequipment.model.Booking;
import com.farmequipment.model.Complaint;
import com.farmequipment.model.Review;
import com.farmequipment.model.Payment;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Equipment.class)
                    .addAnnotatedClass(Booking.class)
                    .addAnnotatedClass(Complaint.class)
                    .addAnnotatedClass(Review.class)
                    .addAnnotatedClass(Payment.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println(
                    "SessionFactory creation failed: " + ex
            );

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}