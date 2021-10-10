package com.cliniconline.platform.model;

import javax.persistence.*;

/**
 * Created by bonallure on 10/9/21
 */
@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Doctor doctor;

    @OneToOne
    private AdultPatient adultPatient;

    public Long getId() {
        return id;
    }
}
