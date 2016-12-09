package be.mdcpartners.teststat.statistics;

import java.util.List;

import be.mdcpartners.teststat.object.Site;

public interface ResultSites extends SomeResult {

	List<Site> sites();

	void updateSites(List<Site> sites);

	/**
	 * definitely no compute here; but if an updateSites has already taken
	 * place, we'll only do a retain!
	 *
	 * @param sites
	 */
	void retainSites(List<Site> sites);

}
