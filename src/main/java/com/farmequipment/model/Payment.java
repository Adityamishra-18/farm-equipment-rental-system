package com.farmequipment.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(
        name = "payments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_payment_transaction_id",
                        columnNames = "transaction_id"
                ),
                @UniqueConstraint(
                        name = "uk_payment_booking_id",
                        columnNames = "booking_id"
                )
        }
)
public class Payment {

    private static final DateTimeFormatter TIME_FMT =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a");

    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("dd MMM yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Existing booking se payment link karne ke liye
    @Column(name = "booking_id", nullable = false, unique = true)
    private int bookingId;

    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId;

    @Column(name = "equipment_id", nullable = false)
    private int equipmentId;

    // Receipt ke liye equipment details ka snapshot
    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "price_per_day")
    private double pricePerDay;

    @Column(name = "renter_name")
    private String renterName;

    @Column(name = "renter_phone")
    private String renterPhone;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "days", nullable = false)
    private int days;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "status", nullable = false)
    private String status = "PENDING";

    @Column(name = "payment_time")
    private LocalDateTime paymentTime;

    public Payment() {
    }

    // Basic validation
    @Transient
    public boolean isValid() {
        return bookingId > 0
                && equipmentId > 0
                && days > 0
                && startDate != null
                && Double.isFinite(pricePerDay)
                && pricePerDay >= 0
                && Double.isFinite(totalAmount)
                && totalAmount >= 0
                && transactionId != null
                && !transactionId.isBlank();
    }

    // Formatted values for JSP and CSV
    @Transient
    public String getFormattedTime() {
        return paymentTime == null
                ? ""
                : paymentTime.format(TIME_FMT);
    }

    @Transient
    public String getFormattedStartDate() {
        return startDate == null
                ? ""
                : startDate.format(DATE_FMT);
    }

    @Transient
    public String getFormattedEndDate() {
        return startDate == null || days <= 0
                ? ""
                : startDate.plusDays(days - 1L).format(DATE_FMT);
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getRenterPhone() {
        return renterPhone;
    }

    public void setRenterPhone(String renterPhone) {
        this.renterPhone = renterPhone;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }
}