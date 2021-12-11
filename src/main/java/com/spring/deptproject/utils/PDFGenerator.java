package com.spring.deptproject.utils;

import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.spring.deptproject.entities.StudentDetail;

@Component
public class PDFGenerator {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);

	public void generateItinerary(StudentDetail studentDetail, String filePath, java.awt.Image image) throws IOException {
//		LOGGER.info("Inside generateItinerary()");
		
		com.itextpdf.kernel.pdf.PdfWriter pdfWriter = new com.itextpdf.kernel.pdf.PdfWriter(new FileOutputStream(filePath));
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);

		com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);
		
		document.add(generateTable(studentDetail));
		ImageData data = ImageDataFactory.create(image, null);
		com.itextpdf.layout.element.Image image2 = new com.itextpdf.layout.element.Image(data);
		Paragraph paragraph = new Paragraph();
//		paragraph.add("QR Code");
		paragraph.add(image2);
		document.add(paragraph);
		document.close();
	}

	private Table generateTable(StudentDetail studentDetail) {
		float[] pointColumnWidths = {200F, 200F}; 
		Table table = new Table(pointColumnWidths);
		
//		Cell cell = new Cell();
		
		table.addCell("Student Detail");
		table.addCell("");
		
		table.addCell("FirstName: ");
		table.addCell(studentDetail.getFirstName());
		
		table.addCell("LastName");
		table.addCell(studentDetail.getLastName());
		
		table.addCell("Registration Number");
		table.addCell(studentDetail.getRegistrationNumber());
		
		table.addCell("Examination Detail").setTextAlignment(TextAlignment.CENTER);
		table.addCell("").setBorder(Border.NO_BORDER);
		
		table.addCell("Level");
		table.addCell(studentDetail.getLevel());
		
		table.addCell("Courses Registered");
		table.addCell("");
		
				
//		Map<String, Object> courseMap = new HashMap<String, Object>();
		Map<String,Object> tempCourseMap = (Map<String, Object>) studentDetail.getCourses();
		for (Entry<String, Object> entry : tempCourseMap.entrySet()) {
			table.addCell(entry.getKey());
			table.addCell(entry.getValue().toString());
//			courseMap.put(entry.getKey(), entry.getValue());
		}
				
		return table;
	}
}
