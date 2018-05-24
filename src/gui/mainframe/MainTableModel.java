package gui.mainframe;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import storage.Assoziation;
import storage.Storage;

class MainTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6362097283170649470L;

	private Storage storage;
	private ArrayList<Assoziation> assoziations;

	public MainTableModel() {
		storage = Storage.testStorage();
		this.assoziations = new ArrayList<Assoziation>(this.storage.getAssoziations());
	}

	public MainTableModel(Storage storage) {
		this.storage = storage;
		this.assoziations = new ArrayList<Assoziation>(storage.getAssoziations());
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
		return storage.getAssoziations().size();
	}

	@Override
	public Object getValueAt(int x, int y) {
		if (x == 0)
			return this.assoziations.get(y).getPath();

		try {
			return this.assoziations.get(y).tags.get(x);
		} catch (IndexOutOfBoundsException e) {
			return "";
		}

	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public String getColumnName(int col) {
		if (col == 0)
			return "Path";
		else
			return null;
	}

}
