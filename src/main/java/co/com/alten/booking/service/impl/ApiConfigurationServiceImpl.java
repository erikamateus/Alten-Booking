package co.com.alten.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.alten.booking.entity.ApiConfiguration;
import co.com.alten.booking.repo.ApiConfigurationRepository;
import co.com.alten.booking.service.ApiConfigurationService;

@Service
public class ApiConfigurationServiceImpl implements ApiConfigurationService {

	@Autowired
	private ApiConfigurationRepository apiConfigurationService;

	public List<ApiConfiguration> getAllApiConfiguration() {
		return apiConfigurationService.findAll();
	}

	public ApiConfiguration getApiConfigurationById(Integer idApi) {
		return apiConfigurationService.findById(idApi).orElse(null);
	}

	public void saveApiConfiguration(ApiConfiguration apiConfiguration) {
		apiConfigurationService.save(apiConfiguration);
	}

	public void deleteApiConfiguration(Integer idApi) {
		apiConfigurationService.deleteById(idApi);
	}

}
