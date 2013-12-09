package de.avgl.dmp.persistence.service.resource.test;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.avgl.dmp.persistence.model.resource.Configuration;
import de.avgl.dmp.persistence.service.resource.ConfigurationService;
import de.avgl.dmp.persistence.service.test.IDBasicJPAServiceTest;


public class ConfigurationServiceTest extends IDBasicJPAServiceTest<Configuration, ConfigurationService, Long> {

	private static final org.apache.log4j.Logger	LOG					= org.apache.log4j.Logger.getLogger(ConfigurationServiceTest.class);

	private final ObjectMapper						objectMapper = injector.getInstance(ObjectMapper.class);

	public ConfigurationServiceTest() {

		super("configuration", ConfigurationService.class);
	}

	@Test
	public void testSimpleConfiguration() {

		Configuration configuration = createObject();

		configuration.setName("my configuration");
		configuration.setDescription("configuration description");

		final ObjectNode parameters = new ObjectNode(objectMapper.getNodeFactory());
		final String parameterKey = "fileseparator";
		final String parameterValue = ";";
		parameters.put(parameterKey, parameterValue);

		configuration.setParameters(parameters);

		updateObjectTransactional(configuration);

		Configuration updatedConfiguration = getObject(configuration);

		Assert.assertNotNull("the configuration name of the updated resource shouldn't be null", updatedConfiguration.getName());
		Assert.assertEquals("the configuration' names of the resource are not equal", configuration.getName(), updatedConfiguration.getName());
		Assert.assertNotNull("the configuration description of the updated resource shouldn't be null", updatedConfiguration.getDescription());
		Assert.assertEquals("the configuration descriptions of the resource are not equal", configuration.getDescription(), updatedConfiguration.getDescription());
		Assert.assertNotNull("the configuration parameters of the updated resource shouldn't be null", updatedConfiguration.getParameters());
		Assert.assertEquals("the configurations parameters of the resource are not equal", configuration.getParameters(), updatedConfiguration.getParameters());
		Assert.assertNotNull("the parameter value shouldn't be null", configuration.getParameter(parameterKey));
		Assert.assertEquals("the parameter value should be equal", configuration.getParameter(parameterKey).asText(), parameterValue);

		String json = null;

		try {
			json = objectMapper.writeValueAsString(updatedConfiguration);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOG.debug("configuration json: " + json);

		// clean up DB
		deletedObject(configuration.getId());
	}
}