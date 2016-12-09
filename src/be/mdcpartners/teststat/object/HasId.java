package be.mdcpartners.teststat.object;

import be.mdcpartners.teststat.statistics.Type;

public interface HasId {

	long getId();

	void setId(long id);

	Type getType();
}
