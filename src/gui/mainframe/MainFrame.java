package gui.mainframe;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import storage.Storage;

public class MainFrame {

	private final String title = "MovieSearch";
	private final int startWidth = 512;
	private final int startHeight = 512;

	protected JFrame frame;
	protected JTable table;

	private MainTableModel tableModel;

	public MainFrame(Storage storage) {
		this.tableModel = new MainTableModel(storage);

		this.table = new JTable(this.tableModel);
		JScrollPane scroll = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(startWidth, startHeight));

		this.frame = new JFrame();
		this.frame.add(scroll);
		this.frame.setTitle(this.title);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setPreferredSize(new Dimension(this.startWidth, this.startHeight));
		this.frame.setSize(new Dimension(this.startWidth, this.startHeight));
		this.frame.setLocationRelativeTo(null);
	}

}
