package com.project.tz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Role {

    @Id
    @GeneratedValue
    long id;

    @Column(unique = true)
    String roleName;


    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("roles")
    Set<User> users;
}
