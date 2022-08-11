package abstracts;

public enum Shape {
    SPHERE("Sphere"),
    CUBE("Cube"),
    FLAT("Flat");
	private String name;
	private Shape(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}

}
