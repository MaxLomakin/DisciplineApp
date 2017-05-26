package com.olts.discipline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * o.tsoy
 * 25.04.2017
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 2082395294433209579L;

    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "created_when")
    @Type(type = "timestamp")
    private Date createdWhen;

    private String username;

    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "task_score")
    private Integer taskScore;

    @Column(name = "habit_score")
    private Integer habitScore;

    private Integer score;

    @JsonIgnore
    @OneToMany(mappedBy = "habitUser", fetch = FetchType.LAZY)
    private Set<Habit> habits = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "taskUser", fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Transient
    private String passwordConfirm;

    @Override // can be generated by lombok
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createdWhen=" + createdWhen +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", taskScore=" + taskScore +
                ", habitScore=" + habitScore +
                ", score=" + score +
                '}';
    }}