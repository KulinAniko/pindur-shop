package hu.pindur.backend.repository;

import hu.pindur.backend.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("select a from AppUser a where a.email=:email")
    Optional<AppUser> findByEmail(String email);
}
