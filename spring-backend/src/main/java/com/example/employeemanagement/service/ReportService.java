package com.example.employeemanagement.service;

// Temporarily disabled JasperReports service
// Will be enabled once JasperReports is properly configured

/*
import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.dto.EmployeeDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.BandTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    public byte[] generateEmployeesByDepartmentReport() throws JRException {
        // Create the report design
        JasperDesign jasperDesign = createReportDesign();
        
        // Compile the report
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        
        // Get all departments
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        
        // Create data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(departments);
        
        // Parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Employee Directory by Department");
        
        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        
        // Export to PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private JasperDesign createReportDesign() throws JRException {
        JasperDesign jasperDesign = new JasperDesign();
        jasperDesign.setName("EmployeesByDepartmentReport");
        jasperDesign.setPageWidth(595);
        jasperDesign.setPageHeight(842);
        jasperDesign.setColumnWidth(555);
        jasperDesign.setColumnSpacing(0);
        jasperDesign.setLeftMargin(20);
        jasperDesign.setRightMargin(20);
        jasperDesign.setTopMargin(30);
        jasperDesign.setBottomMargin(30);

        // Add fields
        JRDesignField idField = new JRDesignField();
        idField.setName("id");
        idField.setValueClass(String.class);
        jasperDesign.addField(idField);

        JRDesignField nameField = new JRDesignField();
        nameField.setName("name");
        nameField.setValueClass(String.class);
        jasperDesign.addField(nameField);

        JRDesignField locationField = new JRDesignField();
        locationField.setName("location");
        locationField.setValueClass(String.class);
        jasperDesign.addField(locationField);

        // Add parameter
        JRDesignParameter titleParam = new JRDesignParameter();
        titleParam.setName("ReportTitle");
        titleParam.setValueClass(String.class);
        jasperDesign.addParameter(titleParam);

        // Title band
        JRDesignBand titleBand = new JRDesignBand();
        titleBand.setHeight(50);

        JRDesignStaticText title = new JRDesignStaticText();
        title.setText("Employee Directory by Department");
        title.setX(0);
        title.setY(10);
        title.setWidth(555);
        title.setHeight(30);
        title.setFontSize(16f);
        title.setBold(true);
        title.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        titleBand.addElement(title);

        try {
            jasperDesign.setTitleBand(titleBand);
        } catch (Exception e) {
            // Fallback for older JasperReports versions
        }

        // Detail band
        JRDesignBand detailBand = new JRDesignBand();
        detailBand.setHeight(80);

        // Department name
        JRDesignTextField deptName = new JRDesignTextField();
        deptName.setExpression(new JRDesignExpression("$F{name}"));
        deptName.setX(0);
        deptName.setY(0);
        deptName.setWidth(300);
        deptName.setHeight(20);
        deptName.setFontSize(14f);
        deptName.setBold(true);
        detailBand.addElement(deptName);

        // Department location
        JRDesignTextField deptLocation = new JRDesignTextField();
        deptLocation.setExpression(new JRDesignExpression("\"Location: \" + $F{location}"));
        deptLocation.setX(0);
        deptLocation.setY(25);
        deptLocation.setWidth(300);
        deptLocation.setHeight(15);
        deptLocation.setFontSize(10f);
        detailBand.addElement(deptLocation);

        try {
            jasperDesign.setDetailBand(detailBand);
        } catch (Exception e) {
            // Fallback for older JasperReports versions
        }

        return jasperDesign;
    }
}
*/