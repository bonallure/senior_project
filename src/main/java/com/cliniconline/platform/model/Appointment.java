package com.cliniconline.platform.model;

import javax.persistence.*;

/**
 * Created by bonallure on 10/8/21
 */
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Doctor doctor;

    @OneToOne
    private Patient patient;

    public Long getId() {
        return id;
    }
}
