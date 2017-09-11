package com.dx.ss.demo.docx.resolver;

import com.dx.ss.data.doc.ExcelDocument;
import com.dx.ss.data.holder.ExcelDocumentDataHolder;
import com.dx.ss.data.resolver.ExcelDocumentDataImportResolver;
import com.dx.ss.data.util.ExcelDocumentUtil;
import com.dx.ss.demo.docx.beans.ParkFee;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @Author Eric Lau
 * @Date 2017/9/11.
 */
public class BizExcelDocumentDataImportResolver extends ExcelDocumentDataImportResolver<ExcelDocumentDataHolder> {

    public ExcelDocumentDataHolder resolveByImport(ExcelDocument excelDocument) {

        ExcelDocumentDataHolder holder = new ExcelDocumentDataHolder();
        try {
            Map<String, Object> extras = new HashMap<String, Object>() {{
                put("date_pattern", "yyyy-MM-dd HH:mm:ss");
            }};
            List<ParkFee> dataList = new ArrayList<ParkFee>();
            Workbook workbook = excelDocument.workbook();
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int index = 1; index <= lastRowNum; index++) {
                Row row = sheet.getRow(index);
                ParkFee data = new ParkFee();
                dataList.add(data);
                ExcelDocumentUtil.copyPropertiesByRow(row, data, data.propertiesAssembly(), 1, extras);
            }
            holder.setDataList(dataList);
            holder.setDocumentation(excelDocument);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holder;
    }
}
