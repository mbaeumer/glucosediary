package se.mbaeumer.glucometriq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.mbaeumer.glucometriq.models.GlucoseMeasurement;
import se.mbaeumer.glucometriq.models.User;

import java.util.List;

/**
 * Created by martinbaumer on 28/07/16.
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserNameAndPassword(String userName, String password);
}
