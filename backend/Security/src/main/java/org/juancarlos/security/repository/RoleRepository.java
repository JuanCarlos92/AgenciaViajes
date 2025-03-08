package org.juancarlos.security.repository;

import org.juancarlos.security.entity.RoleEntity;
import org.juancarlos.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByUser(String username);
}
