package storage;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("static-access")
public class StorageFactory {

	private static final String XML_OUTPUT_PATH = "storage.xml";
	private static StorageFactory instance = null;
	private static Storage storage;

	protected StorageFactory() {
	}

	public static StorageFactory getInstance() {
		if (instance == null) {
			instance = new StorageFactory();
		}
		return instance;
	}

	public void save() throws Exception {
		JAXBContext context = JAXBContext.newInstance(Storage.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(storage, new File(XML_OUTPUT_PATH));
	}

	public Storage getStorage() throws Exception {
		if (storage == null) {
			load();
		}
		return storage;
	}

	private void load() throws Exception {
		JAXBContext context = JAXBContext.newInstance(Storage.class);
		Unmarshaller um = context.createUnmarshaller();
		storage = (Storage) um.unmarshal(new FileReader(XML_OUTPUT_PATH));
	}

	public void createStorage(final String rootPath) throws Exception {
		this.storage = new Storage();
		TraverseFile tra = new TraverseFile(rootPath);
		for (String path : tra.getFoundFiles()) {
			this.storage.addAssoziation(new Assoziation(path));
		}
	}

	private class TraverseFile {

		private List<String> foundFiles = new LinkedList<String>();

		public List<String> getFoundFiles() {
			return this.foundFiles;
		}

		public TraverseFile(String rootPath) {
			traverseFiles(new File(rootPath));
		}

		public void traverseFiles(File file) {
			File[] list = file.listFiles();
			if (list != null) {
				for (File fil : list) {
					if (fil.isDirectory()) {
						traverseFiles(fil);
					} else {
						foundFiles.add(fil.getAbsolutePath());
					}
				}
			}
		}
	}

}
