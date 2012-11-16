package fr.cem.gitmyhub.comparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONObject;

public class RepositoryComparator implements Comparator<JSONObject> {

	@Override
	public int compare(final JSONObject repo1, final JSONObject repo2) {

		final String dateRepo1 = (String) repo1.get("created");
		final String dateRepo2 = (String) repo2.get("created");
		final String dateFormat = "yyyy-MM-dd";

		if (repo1.get("created") == null || repo1.get("created") == null)
			return 0; // Pas comparable si champs manquant

		// On ne récupère que la date et pas l'heure
		final String[] finalDate1 = dateRepo1.split("T");
		final String[] finalDate2 = dateRepo2.split("T");
		Date repo1Date=null;
		Date repo2Date=null;
		try {
			repo1Date = new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(finalDate1[0]);
			repo2Date = new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(finalDate2[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return repo1Date.compareTo(repo2Date);
	}

}
