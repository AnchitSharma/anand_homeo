package com.model;

import java.awt.Desktop;
import java.io.File;

public class OpenFile {
	
	public void getFileByPath(String filepath) {
		if (Desktop.isDesktopSupported()) {
			try {
			File file = new File(filepath);
			Desktop.getDesktop().open(file);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
