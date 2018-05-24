package main;

import gui.mainframe.MainFrame;
import storage.StorageFactory;

@SuppressWarnings("unused")
public class Main {

	private static final String STORAGE_ROOT_PATH = "C:\\Users\\Manuel\\Videos\\material";

	public static void main(String[] args) {
		Main mayn = new Main();
	}

	public Main() {
		try {
			// Storage storage = StorageFactory.getInstance().getStorage();
			StorageFactory.getInstance().createStorage(STORAGE_ROOT_PATH);
			new MainFrame(StorageFactory.getInstance().getStorage());
			StorageFactory.getInstance().save();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);

		}
	}

}
