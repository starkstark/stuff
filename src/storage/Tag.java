package storage;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tag")
public class Tag {

	@XmlElement(name = "property")
	private String property;

	private double likely;

	public Tag() {
	}

	public Tag(String property, double likely) {
		this.property = property;
		if (likely <= 0) {
			this.likely = 0;
		} else if (likely >= 1) {
			this.likely = 1;
		} else
			this.likely = likely;
	}

	public void setLikely(double likely) {
		this.likely = likely;
	}

	@XmlElement(name = "likely")
	public double getLikely() {
		return this.likely;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProerty(String property) {
		this.property = property;
	}

	public double match(String property) {
		if (this.property.equals(property))
			return likely;
		else
			return 0;
	}

	public boolean equals(Tag other) {
		return this.property.equals(other.property) && Math.abs(this.likely - other.getLikely()) <= 0.00001;
	}

}
