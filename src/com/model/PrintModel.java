package com.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import com.utility.Patient;

public class PrintModel implements Printable {

	Patient patient;

	public PrintModel(Patient patient) {
		this.patient = patient;
		printDemo();
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(),
				pageFormat.getImageableY());
		// Now we perform our rendering
		
		graphics.drawString("Invoice No.", 100, 100);
		graphics.drawString(patient.getP_id(), 250, 100);
		graphics.drawString("Registration No.", 400, 100);
		graphics.drawString(patient.getP_id(), 650, 100);
		graphics.drawString("Name ", 100, 150);
		graphics.drawString(patient.getP_name(), 150, 150);
		graphics.drawString("Doctor Name.", 200, 150);
		graphics.drawString(patient.getP_doc_name(), 250, 150);

		// tell the caller that this page is part
		// of the printed document

		return PAGE_EXISTS;
	}
	
	private void printDemo() {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			}catch(PrinterException e) {
				e.printStackTrace();
			}
		}
	}

}
