package com.spring.deptproject.controllers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.spring.deptproject.entities.StudentDetail;
import com.spring.deptproject.utils.PDFGenerator;

@RestController
@RequestMapping("/barcodes")
public class QRCode {
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	private String FILE_DIR = "/home/regent/Portfolio/";
	
	public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception{
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
		return MatrixToImageWriter.toBufferedImage(bitMatrix);		
	}
	
	@PostMapping(value="/zxing/qrcode", produces=MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> zxingQRCode(@RequestBody String barcode) throws Exception{
//		return okResponse(generateQRCodeImage(barcode));
		return new ResponseEntity<BufferedImage>(generateQRCodeImage(barcode), HttpStatus.OK);
	}
	
	@PostMapping(value="/zxing/qrcode/json", produces=MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> jsonrequestQRCode(@RequestBody Map<String, Object> map) throws Exception{
		StudentDetail studentDetail = new StudentDetail();
		studentDetail.setLastName((String) map.get("lastName"));
		studentDetail.setFirstName((String)map.get("firstName"));
		studentDetail.setRegistrationNumber((String)map.get("registrationNumber"));
		studentDetail.setLevel((String) map.get("level"));
		Map<String, Object> courseMap = new HashMap<String, Object>();
		Map<String,Object> tempCourseMap = (Map<String, Object>) map.get("courses");
		for (Entry<String, Object> entry : tempCourseMap.entrySet()) {
			courseMap.put(entry.getKey(), entry.getValue());
		}
		studentDetail.setCourses(courseMap);
		String barcodeRequest = studentDetail.toString();
		String filePath = FILE_DIR+map.get("firstName")+".pdf";
		Image image = generateQRCodeImage(barcodeRequest);
		pdfGenerator.generateItinerary(studentDetail, filePath, image);
		return new ResponseEntity<BufferedImage>(generateQRCodeImage(barcodeRequest), HttpStatus.OK);
	}
	
	
	private ResponseEntity<BufferedImage> okResponse(BufferedImage image){
		return new ResponseEntity<BufferedImage>(image, HttpStatus.OK);
	}
}
