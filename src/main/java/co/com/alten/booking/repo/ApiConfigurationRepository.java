package co.com.alten.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.com.alten.booking.entity.ApiConfiguration;

@Repository
public interface ApiConfigurationRepository extends JpaRepository<ApiConfiguration, Integer> {

}
