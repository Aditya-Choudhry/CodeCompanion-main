package com.example.employeemanagement.controller;

// Temporarily disabled JasperReports controller
// Will be enabled once JasperReports is properly configured

/*
import com.example.employeemanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/employees-by-department")
    public ResponseEntity<byte[]> generateEmployeesByDepartmentReport() {
        try {
            byte[] pdfReport = reportService.generateEmployeesByDepartmentReport();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "employees-by-department.pdf");
            headers.setContentLength(pdfReport.length);
            
            return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
*/