package com.utility;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class AutoComplete extends JComboBox implements
		JComboBox.KeySelectionManager {

	private String searchFor;
	private long lap;

	public class CBDocument extends PlainDocument {

		@Override
		public void insertString(int offset, String str, AttributeSet a)
				throws BadLocationException {
			// TODO Auto-generated method stub
			if (str == null)
				return;
			super.insertString(offset, str, a);
			if (!isPopupVisible() && str.length() != 0)
				fireActionEvent();
		}
	}

	public AutoComplete(Object[] items) {
		super(items);
		lap = new Date().getTime();
		setKeySelectionManager(this);
		JTextField tf;
		if (getEditor() != null) {
			tf = (JTextField) getEditor().getEditorComponent();
			if (tf != null) {
				tf.setDocument(new CBDocument());
				addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						JTextField tf = (JTextField) getEditor()
								.getEditorComponent();
						String text = tf.getText();
						ComboBoxModel model = getModel();
						String current;
						for (int i = 0; i < model.getSize(); i++) {
							current = model.getElementAt(i).toString();
							if (current.toLowerCase().startsWith(
									text.toLowerCase())) {
								tf.setText(current);
								tf.setSelectionStart(text.length());
								tf.setSelectionEnd(current.length());
								break;
							}

						}
					}
				});
			}
		}
	}

	@Override
	public int selectionForKey(char aKey, ComboBoxModel model) {
		// TODO Auto-generated method stub
		long now = new Date().getTime();
		if (searchFor != null && aKey == KeyEvent.VK_BACK_SPACE
				&& searchFor.length() > 0) {
			searchFor = searchFor.substring(0, searchFor.length() - 1);

		} else {
			if (lap + 1000 < now) {
				searchFor = "" + aKey;
			} else {
				searchFor = searchFor + aKey;
			}
		}
		lap = now;
		String current;
		for (int i = 0; i < model.getSize(); i++) {
			current = model.getElementAt(i).toString().toLowerCase();
			if (current.toLowerCase().startsWith(searchFor.toLowerCase())) {
				return i;
			}

		}
		return -1;
	}

	@Override
	protected void fireActionEvent() {
		// TODO Auto-generated method stub
		super.fireActionEvent();
	}

	/*public static void main(String[] args) {
		JFrame f = new JFrame("Auto Completion");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300, 180);
		Container cp = f.getContentPane();
		cp.setLayout(new GridLayout(2, 1));

		Locale[] locales = Locale.getAvailableLocales();
		JComboBox<Object> cBox = new AutoComplete(locales);
		List<String> items = new ArrayList<String>();
		for (int i = 0; i < locales.length; i++) {
			items.add(locales[i].toString());
		}

		// JComboBox 2 with strict input
		JComboBox<String> cBox2 = new AutoComboBox(items);
		cBox.setBounds(50, 50, 100, 21);
		cBox.setEditable(true);
		
		cp.add(cBox);
		cp.add(cBox2);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
	}*/
}
