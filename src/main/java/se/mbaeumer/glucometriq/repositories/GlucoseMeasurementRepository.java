package se.mbaeumer.glucometriq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.mbaeumer.glucometriq.models.GlucoseMeasurement;

/**
 * Created by martinbaumer on 28/07/16.
 */
public interface GlucoseMeasurementRepository extends JpaRepository<GlucoseMeasurement, String> {
}
