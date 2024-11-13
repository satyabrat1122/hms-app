package com.hms.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;


import java.io.FileOutputStream;

@Service
public class pdfService {
    public void generateBookingPdf(String filePath){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Hello World", font);
            document.add(chunk);
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
