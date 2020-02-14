package TextEditor;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Editor extends JFrame{
	private JTextArea textArea = new JTextArea(20, 60);
	private JFileChooser fc = new JFileChooser();
	
	//Constructor for our window
	public Editor() {
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//Let add filter for text files
		FileFilter txtFilter = new FileNameExtensionFilter("Plain text","txt");
		fc.setFileFilter(txtFilter);
		
		//Add menu and menu items
		add(scrollPane);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu file = new JMenu("File");
		menuBar.add(file);
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		
		//Adding our actions to file menu items (We will add them little lower from constructor)
		file.add(Open);
		file.add(Save);
		file.add(New);
		file.addSeparator();
		file.add(Exit);
		edit.add(Cut);
		edit.add(Copy);
		edit.add(Paste);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	Action Open = new AbstractAction("Open file") {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				openFile(fc.getSelectedFile().getAbsolutePath());	//WE'll add openFile later
			}
			
		}
		
	};
	Action Save = new AbstractAction("Save File") {

		@Override
		public void actionPerformed(ActionEvent e) {
			saveFile();
			
		}
		
	};
	//Create a new empty file
	Action New = new AbstractAction("New") {

		@Override
		public void actionPerformed(ActionEvent e) {
			newFile();
			
		}
		
	};
	Action Exit = new AbstractAction("Exit") {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
		
	};
	//Cut
	Action Cut = new AbstractAction("Cut") {

		@Override
		public void actionPerformed(ActionEvent e) {
			cut();
				
			}
	};
	//Copy
	Action Copy = new AbstractAction("Copy") {

		@Override
		public void actionPerformed(ActionEvent e) {
			copy();
				
			}
	};	
	//Paste
	Action Paste = new AbstractAction("Paste") {

		@Override
		public void actionPerformed(ActionEvent e) {
			paste();
				
			}
			
	};
	
	
	//Method here
	public void openFile(String fileName) {
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
			textArea.read(fr, null);
			fr.close();
			setTitle(fileName);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveFile() {
		if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			FileWriter fw = null;
			try {
				fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
				textArea.write(fw);
				fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void newFile() {
		try {
			File file = new File("Empty File");
			file.createNewFile();
			textArea.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cut() {
		textArea.cut();
	}
	
	public void copy() {
		textArea.copy();
	}
	
	public void paste() {
		textArea.paste();
	}
	
	public static void main(String[]args) {
		new Editor();
	}
}
