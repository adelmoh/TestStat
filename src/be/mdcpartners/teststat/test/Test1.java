package be.mdcpartners.teststat.test;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import be.mdcpartners.teststat.object.Country;
import be.mdcpartners.teststat.object.Location;
import be.mdcpartners.teststat.object.Province;
import be.mdcpartners.teststat.object.Site;
import be.mdcpartners.teststat.object.Trial;
import be.mdcpartners.teststat.statistics.ResultTrials;
import be.mdcpartners.teststat.statistics.StatisticsResult;
import be.mdcpartners.teststat.statistics.impl.EnrollmentStatistics;
import be.mdcpartners.teststat.statistics.impl.EnrollmentStatistics.Data;
import be.mdcpartners.teststat.statistics.impl.ResultContainerImpl;
import be.mdcpartners.teststat.statistics.impl.ResultTrialsImpl;
import be.mdcpartners.teststat.statistics.impl.TimeInterval;

public class Test1 {

	public static void main(String[] args) {
		new Test1().test1();
	}

	private void test1() {
		Trial t1 = new Trial("T1");
		t1.setEnrollment(100);
		t1.setPhase('A');
		t1.setStudyStart(new Date());
		List<Site> sites = new LinkedList<>();
		t1.setSites(sites);

		Location antwerp = new Location(1, "Antwerp", new Province(1, "Antwerp", new Country(1, "Belgium")));
		Site s1 = new Site("Middelheim Hospital", antwerp);
		sites.add(s1);

		ResultContainerImpl rci = new ResultContainerImpl();
		ResultTrials trials = new ResultTrialsImpl();
		trials.updateTrials(Collections.singleton(t1));
		rci.addResult(trials);

		EnrollmentStatistics es = new EnrollmentStatistics(TimeInterval.YEARLY, 2010, 2017);
		StatisticsResult res = es.compute(rci);
		@SuppressWarnings("unchecked")
		Map<Integer, Data> map = (Map<Integer, Data>) res.getResult();
		for (Entry<Integer, Data> e : map.entrySet()) {
			System.out.println("Year " + e.getKey() + ": " + e.getValue());
		}
	}
}
