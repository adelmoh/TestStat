package be.mdcpartners.teststat.statistics;

import java.util.Set;

import be.mdcpartners.teststat.object.Trial;

public interface ResultTrials extends SomeResult {

	Set<Trial> trials();

	void updateTrials(Set<Trial> trials);

	void retainTrials(Set<Trial> trials);
}
