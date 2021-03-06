package com.dx.ss.demo.docx.resolver;

import com.dx.ss.data.beans.DocumentBean;
import com.dx.ss.data.beans.DocumentHeader;
import com.dx.ss.data.doc.ExcelDocument;
import com.dx.ss.data.doc.XlsxDocumentation;
import com.dx.ss.data.holder.ExcelDocumentDataHolder;
import com.dx.ss.data.resolver.ExcelDocumentDataExportResolver;
import com.dx.ss.data.util.ExcelDocumentUtil;
import com.dx.ss.demo.docx.beans.ParkFee;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.FileSystemResource;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class BizExcelDocumentDataExportResolver extends ExcelDocumentDataExportResolver<ExcelDocument> {

    public ExcelDocument resolveByExport(ExcelDocumentDataHolder excelDocumentDataHolder) {

        ExcelDocument doc = (ExcelDocument) excelDocumentDataHolder.getDocumentation();
        if (doc == null) {
            doc = new XlsxDocumentation();
            doc.setDataResource(new FileSystemResource("E:\\tmp\\export_park_fee.xls"));
        }
        List<ParkFee> dataList = (List<ParkFee>) excelDocumentDataHolder.getDataList();
        if (dataList == null) {
            dataList = new ArrayList<ParkFee>();
        }
        ArrayList<String> properties = new ParkFee().propertiesAssembly();
        properties.add(0, DocumentBean.SERIAL_PROPERTY_NAME);
        try {
            Workbook workbook = doc.newWorkbook();
            Sheet sheet = doc.createSheet(workbook, "sheet-1");
            ArrayList<String> headerNames = new ArrayList<String>(6){{
                add("Number");
                add("车辆所属单位");
                add("Exit_Time");
                add("Is_Holiday");
                add("Holiday_Rule");
                add("Total");
            }};
            ArrayList<DocumentHeader> headers = new ArrayList<DocumentHeader>(headerNames.size());
            for (String name : headerNames) {
                DocumentHeader header = new DocumentHeader(name, name.length() * 2, Locale.CHINESE);
                header.setCellStyle(doc.headerCellStyle(workbook));
                headers.add(header);
            }
            CellStyle headerCellStyle = doc.headerCellStyle(workbook);
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            Font font = doc.headerFont(workbook);
            font.setBold(false);
            headerCellStyle.setFont(font);
            doc.createHeaderRow(sheet, 0, 23, headers);

            ExcelDocumentUtil.fillData(sheet, 1, properties, dataList, doc.defaultCellStyle(workbook), new HashMap<String, Object>(){{
                put("date_pattern", "yyyy-MM-dd hh:mm:ss");
            }});

            workbook.write(new FileOutputStream(doc.getDataResource().getFile()));
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
}
