package com.pro_servises.pro.controller;

import com.pro_servises.pro.serviceImp.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/products/{enterpriseId}")
    public ResponseEntity<byte[]> generateReport(@PathVariable Long enterpriseId) {
        try {
            JasperPrint jasperPrint = reportService.generateProductReport(enterpriseId);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
        } catch (JRException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
