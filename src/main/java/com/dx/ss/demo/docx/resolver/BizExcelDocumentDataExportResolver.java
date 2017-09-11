package com.dx.ss.demo.docx.resolver;

import com.dx.ss.data.beans.DocumentBean;
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
            ArrayList<String> headerNames = new ArrayList<String>(){{
                add("Number");
                add("进入时间");
                add("Exit_Time");
                add("Is_Holiday");
                add("Holiday_Rule");
                add("Total");
            }};
            CellStyle headerCellStyle = doc.headerCellStyle(workbook);
            doc.createHeaderRow(sheet, 0, headerNames, headerCellStyle);
            /*headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            Font font = doc.headerFont(workbook);
            font.setBold(false);
            headerCellStyle.setFont(font);
            sheet.setColumnWidth(0, "number".length()*2*256);
            sheet.setColumnWidth(1, "进入时间".length()*4*256);
            sheet.setColumnWidth(3, "Is_Holiday".length()*2*256);*/
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
