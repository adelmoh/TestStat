package be.mdcpartners.teststat.object;

import be.mdcpartners.teststat.statistics.Type;

public class Location extends AbstractHasIdImpl {

	private final Province province;
	private final String name;

	public Location(long id, String name, Province province) {
		setId(id);
		this.name = name;
		this.province = province;
	}

	public String getName() {
		return name;
	}

	public Province getProvince() {
		return province;
	}

	@Override
	public Type getType() {
		return Type.LOCATION;
	}
}
