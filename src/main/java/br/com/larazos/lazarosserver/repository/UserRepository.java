package br.com.larazos.lazarosserver.repository;

import br.com.larazos.lazarosserver.model.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  boolean existsByName(String name);
}
