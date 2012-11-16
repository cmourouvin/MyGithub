package fr.cem.gitmyhub;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
		
		// Manipulation du JSON résultat avant le retour
		Object o = null;
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;
		JSONArray repos = null;
		
		try {

			o = parser.parse(simpleSearch);
			jsonObj = (JSONObject) o;
			repos = (JSONArray) jsonObj.get("repositories");
			
			// Tri des repository en fonction de leur date de création
//			Collections.sort(repos, new RepositoryComparator());
//
//			Iterator<JSONObject> iterator = repos.iterator();
//			JSONObject tmp=null;
//			while (iterator.hasNext()) {
//				tmp = iterator.next();
//				System.out.println("<< Date: " + tmp.get("created"));
//			}
//			
//			jsonObj.put("repositories",repos);
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return jsonObj.toJSONString();
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
