package br.com.larazos.lazarosserver.repository;

import br.com.larazos.lazarosserver.model.Profile;
import br.com.larazos.lazarosserver.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

  @Query("""
          SELECT
            CASE
              WHEN COUNT(p) > 0
                THEN TRUE
                ELSE FALSE
            END
          FROM Profile p
          WHERE UPPER(p.description) = UPPER(:description)
        """)
  boolean existsByDescription(String description);

  @Query("""
          SELECT p
          FROM Profile p
          WHERE UPPER(p.description) = UPPER(:profile)
        """)
  Optional<Profile> findByDescription(String profile);
}
