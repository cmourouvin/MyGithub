package fr.cem.gitmyhub.tools;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class GitApiHelper {

	private static final String GIT_API_URL = "https://api.github.com";
	
	public static URI getBaseURI() {
		return UriBuilder.fromUri(GIT_API_URL).build();
	}

	public static WebResource getWebResourceBase() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource serviceGithub = client.resource(getBaseURI());
		return serviceGithub;
	}

}
