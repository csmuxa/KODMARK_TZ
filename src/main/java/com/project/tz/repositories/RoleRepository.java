package com.project.tz.repositories;

import com.project.tz.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findById(long id);
}
