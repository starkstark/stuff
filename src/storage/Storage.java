package storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "storage")
public class Storage {

	@XmlElementWrapper(name = "assoziations")
	@XmlElement(name = "assoziation")
	protected Collection<Assoziation> assoziations;

	@XmlElementWrapper(name = "knownTags")
	@XmlElement(name = "knownTag")
	protected Collection<Tag> knownTags;

	protected Storage() {
		init();

	}

	protected Storage(Collection<String> filesPaths) {
		init();
		for (String path : filesPaths) {
			this.assoziations.add(new Assoziation(path));
		}
	}

	private void init() {
		this.assoziations = new LinkedList<Assoziation>();
		this.knownTags = new LinkedList<Tag>();
	}

	protected void addAssoziation(Assoziation asso) {
		this.assoziations.add(asso);
	}

	protected void addNewTag(Tag tag) {
		this.knownTags.add(tag);
	}

	/**
	 * Evaluates a file. See the readme-File for a more detailed description
	 * 
	 * @param tags
	 * @param asso
	 * @return
	 */
	private double evaluate(Collection<String> tagNames, Assoziation asso) {
		double result = 0;
		double counter = 0;
		for (String name : tagNames) {
			for (Tag tag : asso.getTags()) {
				result += tag.match(name);
				counter++;
			}
		}
		return result / counter;
	}

	private Map<Assoziation, Double> evaluateAll(Collection<String> tagNames) {
		HashMap<Assoziation, Double> result = new HashMap<Assoziation, Double>();
		for (Assoziation a : this.assoziations) {
			result.put(a, this.evaluate(tagNames, a));
		}
		return result;
	}

	public List<String> suggestions(Collection<String> tagNames) {

		Map<Assoziation, Double> evaluated = evaluateAll(tagNames);
		ArrayList<Entry<Assoziation, Double>> list = new ArrayList<>(evaluated.entrySet());
		list.sort(Entry.comparingByValue());

		ArrayList<String> result = new ArrayList<>();
		for (Entry<Assoziation, Double> e : list) {
			result.add(e.getKey().getPath());
		}
		return result;
	}

	public static Storage testStorage() {
		Tag tag1 = new Tag("ebony", 0.20);
		Tag tag2 = new Tag("star", 0.50);

		Assoziation asso = new Assoziation("whatever.txt");
		asso.addTag(tag1);
		asso.addTag(tag2);

		Storage storage = new Storage();
		storage.addAssoziation(asso);
		return storage;
	}

	public Collection<Assoziation> getAssoziations() {
		return this.assoziations;
	}

	public Collection<Tag> getKnownTags() {
		return this.knownTags;
	}
}
