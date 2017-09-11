package com.dx.ss.demo.docx.main;

import com.dx.ss.data.doc.ExcelDocument;
import com.dx.ss.data.doc.XlsxDocumentation;
import com.dx.ss.data.enums.WorkerTypeEnums;
import com.dx.ss.data.exception.ResourceException;
import com.dx.ss.data.holder.DocumentDataHolder;
import com.dx.ss.data.holder.ExcelDocumentDataHolder;
import com.dx.ss.data.main.DocumentParseDelegate;
import com.dx.ss.data.parse.ExcelDocumentParser;
import com.dx.ss.demo.docx.beans.ParkFee;
import com.dx.ss.demo.docx.resolver.BizExcelDocumentDataExportResolver;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportDemo {

    public static void main(String[] args) throws ResourceException, IOException {

        ExcelDocument document = new XlsxDocumentation();
        document.setDataResource(new FileSystemResource("E:\\tmp\\export_park_fee.xlsx"));
        ExcelDocumentDataHolder holder = new ExcelDocumentDataHolder();
        holder.setDocumentation(document);
        holder.setDataList(new ArrayList<ParkFee>(){{
            ParkFee parkFee = new ParkFee();
            parkFee.setEntryTime(new Date());
            parkFee.setExitTime(new Date());
            parkFee.setHolidayRule(9876543);
            parkFee.setIsHoliday(true);
            parkFee.setTotal(new BigDecimal(987.25));
            add(parkFee);
        }});
        DocumentParseDelegate delegate = new DocumentParseDelegate.Builder(WorkerTypeEnums.EXPORT_WORKER)
                .exportResolver(new BizExcelDocumentDataExportResolver())
                .dataHolder(holder)
                .parser(ExcelDocumentParser.getParserInstance())
                .build();
        List<?> dataList = delegate.parse();
        System.out.println(dataList.toString());
    }
}
