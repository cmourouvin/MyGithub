package fr.cem.gitmyhub;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sun.jersey.api.client.WebResource;

import fr.cem.gitmyhub.tools.GitApiTools;

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
		
		WebResource serviceGithub = GitApiTools.getWebResourceBase();
		
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
		
		WebResource serviceGithub = GitApiTools.getWebResourceBase();

		final String simpleSearch = serviceGithub.
				path("legacy").
				path("repos").
				path("search").
				path(key).accept("application/json").get(String.class);
		
		return simpleSearch;
	}
	
	@GET  @Path("/owner/{oname}/project/{pname}")
	@Produces("application/json")
	public String getCommitersByProject(@PathParam("oname") String owner, @PathParam("pname") String projectName) {
		
		WebResource serviceGithub = GitApiTools.getWebResourceBase();

		final String Commiters = serviceGithub.
				path("repos").
				path(owner).
				path(projectName).
				path("collaborators").accept("application/json").get(String.class);
		
		return Commiters;
	}

	
}
