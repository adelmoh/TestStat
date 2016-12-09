package be.mdcpartners.teststat.object;

import be.mdcpartners.teststat.statistics.Type;

public class Site extends AbstractHasIdImpl {
	private final String name;
	private final Location location;

	public Site(String name, Location location) {
		this.location = location;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}

	@Override
	public Type getType() {
		return Type.SITE;
	}
}
