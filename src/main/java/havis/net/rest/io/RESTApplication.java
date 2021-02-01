package havis.net.rest.io;

import havis.device.io.IODevice;
import havis.net.rest.io.provider.CommunicationExceptionMapper;
import havis.net.rest.io.provider.ConnectionExceptionMapper;
import havis.net.rest.io.provider.ImplementationExceptionMapper;
import havis.net.rest.io.provider.ParameterExceptionMapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Providers;

public class RESTApplication extends Application {

	private final static String PROVIDERS = Providers.class.getName();
	private final static Map<String, Object> properties = new HashMap<>();

	private final Set<Object> singletons = new HashSet<Object>();

	static {
		properties.put(PROVIDERS, new Class<?>[] { CommunicationExceptionMapper.class, ConnectionExceptionMapper.class, ImplementationExceptionMapper.class,
				ParameterExceptionMapper.class });
	}

	public RESTApplication(IODevice device) {
		singletons.add(new IODeviceService(device));
	}

	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}
}