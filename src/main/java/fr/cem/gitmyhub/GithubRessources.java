package fr.cem.gitmyhub;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.WebResource;

import fr.cem.gitmyhub.tools.GitApiHelper;

/**
 * Services utilisisants l'api GitHub
 * @author CMO3233
 *
 */
@Path("/myapi")
public class GithubRessources {

	@GET  @Path("/search/{key}")
	@Produces("application/json")
	public String searchRespositoriesByKeywords(@PathParam("key") String key) {
		
		WebResource serviceGithub = GitApiHelper.getWebResourceBase();

		final String simpleSearch = serviceGithub.
				path("legacy").
				path("repos").
				path("search").
				path(key).accept("application/json").get(String.class);
		
		// Manipulation du JSON résultat avant le retour
		Object o = null;
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;

		try {
			o = parser.parse(simpleSearch);
			jsonObj = (JSONObject) o;
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		return jsonObj.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	@GET  @Path("/owner/{oname}/project/{pname}")
	@Produces("application/json")
	public String getCommitersAndStatsByProject(@PathParam("oname") String owner, @PathParam("pname") String projectName) {
		
		WebResource serviceGithub = GitApiHelper.getWebResourceBase();

		// Récupérations des collaborateurs (commiters) d'un repository
		final String commiters = serviceGithub.
				path("repos").
				path(owner).
				path(projectName).
				path("collaborators").accept("application/json").get(String.class);
		
		// Récupérations des collaborateurs (commiters) d'un repository
		final String commits = serviceGithub.
				path("repos").
				path(owner).
				path(projectName).
				path("commits").accept("application/json").get(String.class);
		
		// Manipulation du JSON résultat avant le retour
		Object objCommiters = null;
		Object objCommits = null;
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		JSONArray commitersArray = null;
		JSONArray commitsArray = null;
		
		try {
				// Préparation des commiters pour l'objet final
				objCommiters = parser.parse(commiters);
				commitersArray = (JSONArray)objCommiters ;
				jsonObj.put("commiters", commitersArray);
				
				// Préparation du tableau des commits
				objCommits = parser.parse(commits);
				commitsArray = (JSONArray)objCommits ;
				jsonObj.put("commits", commitsArray);
				
				// Récupération des stats des commits par collaborateur
				jsonObj.put("stats", countCommitsByCommiters(commitersArray,commitsArray));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
		return jsonObj.toJSONString();
	}
	
	/**
	 * Retour un tableau JSON correspondant au nombre de % de commit pour chaque collaborateur
	 * On adment que traite des collaborateurs et commits sur un projet unique
	 * @param collaborators
	 * @param commits
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private JSONArray countCommitsByCommiters(JSONArray collaborators, JSONArray commits) {
		
		JSONArray results = new JSONArray();
		Map<String, Integer> mapUserNumberCommits = new HashMap<String, Integer>();
		
		// Initialisation de la map avec les collloborateurs à 0 commit
		Iterator<JSONObject> iteratorCollaborators = collaborators.iterator();
		JSONObject tmp=null;
		String tmpLogin=null;
		while (iteratorCollaborators.hasNext()) {
			tmp = iteratorCollaborators.next();
			tmpLogin = (String)tmp.get("login");
			mapUserNumberCommits.put(tmpLogin, 0);
		}
		
		// Décompte des commits par collaborateurs
		Iterator<JSONObject> iteratorCommits = commits.iterator();
		JSONObject tmpObjCommit = null;
		Integer oldValue = null;
		while (iteratorCommits.hasNext()) {
			tmp = iteratorCommits.next();
			tmpObjCommit = (JSONObject)tmp.get("committer");
			if ( tmpObjCommit != null )
			{
				tmpLogin = (String)tmpObjCommit.get("login");
				oldValue = mapUserNumberCommits.get(tmpLogin) == null ? 0 : mapUserNumberCommits.get(tmpLogin) ;
				oldValue++;
				mapUserNumberCommits.put(tmpLogin, oldValue);
			}
		}
		
		for ( String login : mapUserNumberCommits.keySet() )
		{
			tmp = new JSONObject();
			tmp.put("commiter", login);
			tmp.put("commits", mapUserNumberCommits.get(login));
			results.add(tmp);
		}

		return results;
	}
	
}
