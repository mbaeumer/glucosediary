package se.mbaeumer.glucometriq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.mbaeumer.glucometriq.models.UserType;

/**
 * Created by martinbaumer on 31/07/16.
 */
public interface UserTypeRepository extends JpaRepository<UserType, String> {
}
