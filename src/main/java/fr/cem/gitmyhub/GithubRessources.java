package fr.cem.gitmyhub;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Path("/myapi")
public class GithubRessources {

	@GET @Path("/all1")
    @Produces("application/xml")
    public String getAll() {
        return "<xml>Tous les projets GitHub !</xml>";
    }
	
	@GET  @Path("/all2")
	@Produces("application/json")
	public String getFromGithubApi() {
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource serviceGithub = client.resource(GithubRessources.getBaseURI());
		
		final String simpleSearch = serviceGithub.
				path("legacy").
				path("repos").
				path("search").
				path("springPoke").accept("application/json").get(String.class);
		
		return simpleSearch;
	}
	
	@GET  @Path("/search/{key}")
	@Produces("application/json")
	public String searchByKeywords(@PathParam("key") String key) {
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource serviceGithub = client.resource(GithubRessources.getBaseURI());
		
		final String simpleSearch = serviceGithub.
				path("legacy").
				path("repos").
				path("search").
				path(key).accept("application/json").get(String.class);
		
		return simpleSearch;
	}

	private static URI getBaseURI() {
	    return UriBuilder.fromUri("https://api.github.com").build();
	  }
	
}
