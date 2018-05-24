package storage;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "assoziation")
public class Assoziation {

	private String path; // a path to the object in question

	@XmlElementWrapper(name = "tags")
	@XmlElement(name = "tag")
	public ArrayList<Tag> tags; // a collection of different tags for the
								// object

	public Assoziation() {
	}

	public Assoziation(String path) {
		this.path = path;
		tags = new ArrayList<Tag>();
	}

	@XmlElement(name = "path")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Collection<Tag> getTags() {
		return this.tags;
	}

	public void addTag(String property, double likely) {
		addTag(new Tag(property, likely));
	}

	public void addTag(Tag tag) {
		for (Tag t : this.tags) {
			if (t.equals(tag)) {
				t.setLikely(tag.getLikely());
				return;
			}
		}
		this.tags.add(tag);
	}

	public void removeTag(String property) {
		tags.removeIf(x -> x.getProperty().equals(property));
	}
}
