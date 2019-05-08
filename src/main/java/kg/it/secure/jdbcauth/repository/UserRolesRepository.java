package kg.it.secure.jdbcauth.repository;

import kg.it.secure.jdbcauth.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {
}
