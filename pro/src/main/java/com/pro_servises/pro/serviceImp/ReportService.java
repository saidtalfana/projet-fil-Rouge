package com.pro_servises.pro.serviceImp;

import com.pro_servises.pro.dto.ProductReportDTO;
import com.pro_servises.pro.repository.ProductRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DataSource dataSource;

    public JasperPrint generateProductReport(Long enterpriseId) throws JRException {
        // Load and compile the report
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/reports/products_report.jrxml");

        // Get product data
        List<ProductReportDTO> productReports = productRepository.getProductReport(enterpriseId);

        // Create data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productReports);

        // Parameters can be passed here (if needed)
        Map<String, Object> parameters = new HashMap<>();
        // Add any parameters to the map if needed

        // Fill the report
        return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    }
}
