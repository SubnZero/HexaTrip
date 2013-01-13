package entities;

public enum HexagonType {
	HEX_DEFAULT("res/default.png"), HEX_RAMP("res/ramp.png");
	public final String location;
	HexagonType( String location) {
		this.location = location;
	}
}