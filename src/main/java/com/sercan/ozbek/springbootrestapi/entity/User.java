package com.sercan.ozbek.springbootrestapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "`KULLANİCİLAR`")
@Data
public class User extends BaseEntity {

    @Id
    @SequenceGenerator(name="user_seq_gen",sequenceName = "user_gen",initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @Column(name="ID")
    private Long id;

    @Column(name="ISIM",length = 100)
    private String firstName;

    @Column(name="SOYISIM",length = 100)
    private String lastName;

}
