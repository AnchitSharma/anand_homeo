package com.model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.utility.MyTableModel;
import com.utility.Patient;

public class PdfModel {

	
	private Document document;
	private Date date = new Date();
	private DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);

	

	public void prepareReceipt(String invoice_num, Patient patient) {
		document = new Document();
		String doc_name =  "resource/"+invoice_num + ".pdf";
		try {
			PdfWriter.getInstance(document, new FileOutputStream(doc_name));
			Rectangle one = new Rectangle(700, 440);
			document.setPageSize(one);
			document.setMargins(20, 20, 20, 20);
			document.open();
			/*Image image = Image.getInstance("images/rename.png");
			image.setAlignment(Element.ALIGN_CENTER);
			
			document.add(image);*/
			Paragraph datepara = new Paragraph("Date :"+patient.getReg_date());
			datepara.setAlignment(Element.ALIGN_CENTER);
			document.add(datepara);
			document.add(Chunk.NEWLINE);
			PdfPTable table = new PdfPTable(2);
			table.setSplitRows(true);
			table.addCell("Invoice No.   "+invoice_num);
			table.addCell("Patient ID.   "+patient.getP_id());
			table.addCell("Name          "+patient.getP_name());
			table.addCell("Mobile        "+patient.getP_mobile());
			table.addCell("Address       "+patient.getP_add());
			table.addCell("");
			document.add(table);
			
			PdfPTable pricetable = new PdfPTable(2);
			pricetable.setSpacingBefore(20);
			pricetable.addCell("Amount ");
			pricetable.addCell(patient.getP_bill_amt());
			/*pricetable.addCell("GST ");
			pricetable.addCell("18%");
			pricetable.addCell("Payment ");
			pricetable.addCell(patient.getAmt_paid());*/
			document.add(pricetable);
			/*Paragraph footer = new Paragraph("powered by Onistech Info Systems", 
					FontFactory.getFont(FontFactory.TIMES_ROMAN,15,BaseColor.CYAN));
			footer.setAlignment(Element.ALIGN_RIGHT);
			document.add(footer);*/
			document.close();

			if (Desktop.isDesktopSupported()) {
				File file = new File(doc_name);
				Desktop.getDesktop().open(file);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void prepareReceipt(String invoice_num,List<MedicineModel> model,Patient patient) {
	
		document = new Document();
		String doc_name =  "resource/"+invoice_num + ".pdf";
		double amt = 0;
		try {
			PdfWriter.getInstance(document, new FileOutputStream(doc_name));
			
			document.setMargins(20, 20, 20, 20);
			document.open();
			
			/*Image image = Image.getInstance("images/rename.png");
			image.setAlignment(Element.ALIGN_CENTER);
			
			document.add(image);*/
			document.add(Chunk.NEWLINE);
			PdfPTable dataTable = new PdfPTable(2);
			dataTable.addCell(getCell("Invoice no. "+invoice_num, PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell("Date: "+getDate(), PdfPCell.ALIGN_RIGHT));
			
			dataTable.addCell(getCell("Registration No.: "+patient.getP_id(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
			
			dataTable.addCell(getCell("Name: "+patient.getP_name(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
			
			dataTable.addCell(getCell("Mobile: "+patient.getP_mobile(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
			
			dataTable.addCell(getCell("Occupation: "+patient.getOccupation(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
			
			dataTable.addCell(getCell("Sex: "+patient.getGender(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
			
			dataTable.addCell(getCell("Address: "+patient.getP_add(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
			
			dataTable.addCell(getCell("District: "+patient.getDistrict(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
			
			dataTable.addCell(getCell("Pincode: "+patient.getPincode(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell("", PdfPCell.ALIGN_RIGHT));
			
			dataTable.addCell(getCell("Refrence Name: "+new SearchModels().searchNameMobile(patient.getRefer_name()), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
			
			dataTable.addCell(getCell("Refernce Mobile: "+patient.getRefer_name(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
			
			document.add(dataTable);
			document.add(Chunk.NEWLINE);
			Paragraph title = new Paragraph("Prescription",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 15, BaseColor.BLUE));//**??//
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(Chunk.NEWLINE);
			PdfPTable table = new PdfPTable(3);//4

			table.addCell(getCell("Med. Name"));
			table.addCell(getCell("Count * Price"));
			table.addCell(getCell("Total Item Cost"));
			table.setHeaderRows(1);
			for(int i =0;i<model.size();i++) {
				table.addCell(model.get(i).getMed_name());
				table.addCell(model.get(i).getTabCount()+"*"+model.get(i).getPrice_per_piece());
				table.addCell(model.get(i).getMed_price());
				amt = amt + Double.parseDouble(model.get(i).getMed_price());
			}
			document.add(table);
			
			
			PdfPTable table1 = new PdfPTable(2);
			table1.setSpacingBefore(20);
			table1.addCell("amount");
			table1.addCell(String.format("%.2f", amt));
			document.add(table1);
			document.close();
			
			if (Desktop.isDesktopSupported()) {
				File file = new File(doc_name);
				Desktop.getDesktop().open(file);
			}
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void courierReceipt(String invoice,Patient pat) {
		document = new Document();
		String doc_name =  "resource/"+invoice + ".pdf";
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(doc_name));
			/*Rectangle one = new Rectangle(700, 600);
			document.setPageSize(one);*/
			document.setMargins(20, 20, 20, 20);
			document.open();
			document.add(getParagraph("NOT FOR SALE", Element.ALIGN_CENTER));
			document.add(Chunk.NEWLINE);
			document.add(getParagraph("To", Element.ALIGN_LEFT));
			document.add(getParagraph(pat.getP_name(), Element.ALIGN_CENTER));
			document.add(getParagraph(pat.getP_add(), Element.ALIGN_CENTER));
			document.add(getParagraph(pat.getDistrict()+"----"+pat.getPincode(), Element.ALIGN_CENTER));
			document.add(getParagraph(pat.getP_mobile(), Element.ALIGN_CENTER));
			document.add(getParagraph("From", Element.ALIGN_LEFT));
			document.add(getParagraph("THE PRAKRATI SPARSH FOUNDATION", Element.ALIGN_CENTER));
			document.add(getParagraph("C/O ANAND HOMEO HALL", Element.ALIGN_CENTER));
			document.add(getParagraph("BEHIND MANAGALAM GUEST HOUSE", Element.ALIGN_CENTER));
			document.add(getParagraph("SHANTI NAGAR, SAROJINI NAGAR, LKO-226008", Element.ALIGN_CENTER));
			document.add(getParagraph("MOB- 7897711568 & 7376066161", Element.ALIGN_CENTER));
			document.close();
			
			if (Desktop.isDesktopSupported()) {
				File file = new File(doc_name);
				Desktop.getDesktop().open(file);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
											//appointment Model					medicine model
	public void printHistory(Patient patient,List<Appointment> app,List<MedicineModel> med) {
		document = new Document();
		String doc_name =  "resource/"+"565225248787" + ".pdf";
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(doc_name));
			document.setMargins(20, 20, 20, 20);
			document.open();
			
			/*Image image = Image.getInstance("images/rename.png");
			image.setAlignment(Element.ALIGN_CENTER);
			
			document.add(image);*/
			document.add(Chunk.NEWLINE);
			PdfPTable dataTable = new PdfPTable(2);
//			dataTable.addCell(getCell("Invoice no. "+invoice_num, PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell("Date: "+getDate(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell("Name: "+patient.getP_name(), PdfPCell.ALIGN_RIGHT));
			dataTable.addCell(getCell("Mobile: "+patient.getP_mobile(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell("Occupation: "+patient.getOccupation(), PdfPCell.ALIGN_RIGHT));
			dataTable.addCell(getCell("Sex: "+patient.getGender(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell("Address: "+patient.getP_add(), PdfPCell.ALIGN_RIGHT));
			dataTable.addCell(getCell("District: "+patient.getDistrict(), PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell("Pincode: "+patient.getPincode(), PdfPCell.ALIGN_RIGHT));
			dataTable.addCell(getCell("", PdfPCell.ALIGN_LEFT));
			dataTable.addCell(getCell("Refrence Name: "+new SearchModels().searchNameMobile(patient.getRefer_name()), PdfPCell.ALIGN_RIGHT));
			dataTable.addCell(getCell("Refernce Mobile: "+patient.getRefer_name(), PdfPCell.ALIGN_LEFT));
			document.add(dataTable);
			document.add(Chunk.NEWLINE);
			
			Paragraph title = new Paragraph("Appointment History",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 15, BaseColor.BLACK));//**??//
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(4);//4

			table.addCell(getCell("Date"));
			table.addCell(getCell("Type"));
			table.addCell(getCell("Status"));
			table.addCell(getCell("Remarks"));
			table.setHeaderRows(1);
			
			for(Appointment a: app) {
				table.addCell(getCell(a.getDate()));
				table.addCell(getCell(a.getType()));
				table.addCell(getCell(a.getStatus()));
				table.addCell(getCell(a.getRemarks()));
			}
			document.add(table);
			Paragraph t2 = new Paragraph("Prescription History",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 15, BaseColor.BLACK));//**??//
			t2.setAlignment(Element.ALIGN_CENTER);
			document.add(t2);
			document.add(Chunk.NEWLINE);
			

			PdfPTable table2 = new PdfPTable(4);//4

			table2.addCell(getCell("Date"));
			table2.addCell(getCell("Name"));
			table2.addCell(getCell("Drop by Patient"));
			table2.addCell(getCell("Drop by Doctor"));
			table2.setHeaderRows(1);
			
			for(MedicineModel m: med) {
				table2.addCell(getCell(m.getDate()));
				table2.addCell(getCell(m.getMed_name()));
				table2.addCell(getCell(m.getDrop_by_patient()));
				table2.addCell(getCell(m.getDrop_by_doctor()));
			}
			document.add(table2);
			document.close();
			if (Desktop.isDesktopSupported()) {
				File file = new File(doc_name);
				Desktop.getDesktop().open(file);
			}
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void printPatientList(String[] header,List<Patient> patList) {
		int colCount = header.length;
		document = new Document();
		String doc_name =  "resource/"+"878788" + ".pdf";
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(doc_name));
			document.setMargins(20, 20, 20, 20);
			document.open();
			Paragraph title = new Paragraph("Patient List",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 15, BaseColor.BLACK));//**??//
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(colCount);//4
			for(String str:header) {
				table.addCell(getCell(str));
			}
			
			
			table.setHeaderRows(1);
			
			for(Patient a: patList) {
				table.addCell(getCell(a.getP_id()));
				table.addCell(getCell(a.getReg_date()));
				table.addCell(getCell(a.getP_name()));
				table.addCell(getCell(a.getP_mobile()));
				table.addCell(getCell(a.getP_add()));
				table.addCell(getCell(a.getDistrict()));
				table.addCell(getCell(a.getRefer_name()));
				table.addCell(getCell(a.getReg_date()));
				
			}
			
			table.setTotalWidth(PageSize.A4.getWidth());
			//table.setLockedWidth(true);
			/*PdfContentByte canvas = writer.getDirectContent();
			PdfTemplate template = canvas.createTemplate(table.getTotalWidth(), table.getTotalHeight());
			table.writeSelectedRows(0, -1, 0, table.getTotalHeight(), template);
			Image img = Image.getInstance(template);
			img.scaleToFit(PageSize.A4.getWidth(),PageSize.A4.getHeight());
			img.setAbsolutePosition(0, (PageSize.A4.getHeight() - table.getTotalHeight()) / 2);
		    document.add(img);*/
			document.add(table);
			document.close();
			if (Desktop.isDesktopSupported()) {
				File file = new File(doc_name);
				Desktop.getDesktop().open(file);
			}
		}catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getDate() {
		String str = dateFormat.format(this.date);
		return str;
	}
	
	public PdfPCell getCell(String text, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text,
	    		FontFactory.getFont(FontFactory.TIMES_BOLD,15,BaseColor.BLACK)));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	   
	    return cell;
	}
	
	public Paragraph getParagraph(String text, int aligment) {
		Paragraph title = new Paragraph(text,
				FontFactory.getFont(FontFactory.TIMES_BOLD, 15, BaseColor.BLACK));//**??//
		title.setAlignment(aligment);
		return title;
	}
	public PdfPCell getCell(String text) {
		 PdfPCell cell = new PdfPCell();
	        cell.setMinimumHeight(15);
	        cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        cell.addElement(new Paragraph(text,FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK)));
	        
	        return cell;
	}
}
