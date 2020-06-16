package com.project.tz.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @Column
    private String password;

    @Column
    private String name;


    @ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JsonIgnoreProperties("users")
    Set<Role> roles;
}