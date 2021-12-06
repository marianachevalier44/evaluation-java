package com.freestack.evaluation;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "end_of_the_booking")
    private Date end_of_the_booking;
    @Column(name = "evaluation")
    private int evaluation;
    @Column(name="start_of_the_booking")
    private Date start_of_the_booking;

    @ManyToOne
    @JoinColumn(name = "id_driver")
    private UberDriver uberDriver;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UberUser uberUser;

    public Booking() {
    }

    public Booking(Long id, Date end_of_the_booking, int evaluation, Date start_of_the_booking) {
        this.id = id;
        this.end_of_the_booking = end_of_the_booking;
        this.evaluation = evaluation;
        this.start_of_the_booking = start_of_the_booking;
    }

    public Long getId() {
        return id;
    }

    public Date getEnd_of_the_booking() {
        return end_of_the_booking;
    }

    public void setEnd_of_the_booking(Date end_of_the_booking) {
        this.end_of_the_booking = end_of_the_booking;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public Date getStart_of_the_booking() {
        return start_of_the_booking;
    }

    public void setStart_of_the_booking(Date start_of_the_booking) {
        this.start_of_the_booking = start_of_the_booking;
    }

    public UberDriver getUberDriver() {
        return uberDriver;
    }

    public void setUberDriver(UberDriver uberDriver) {
        this.uberDriver = uberDriver;
    }

    public UberUser getUberUser() {
        return uberUser;
    }

    public void setUberUser(UberUser uberUser) {
        this.uberUser = uberUser;
    }

    public Object getScore() {
    }
}
