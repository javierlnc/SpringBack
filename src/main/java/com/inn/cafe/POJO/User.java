package com.inn.cafe.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@NamedQuery(name = "User.findByIdentityDocument", query = "select u from User where u.identityDocument=:identityDocument")
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name="user")
public class User implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name = "identityDocument")
    private String identityDocument;
    @Column(name="name")
    private  String name;
    @Column(name="contactNumber")
    private  String contactNumber;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;
    @Column(name = "status")
    private String status;
}
