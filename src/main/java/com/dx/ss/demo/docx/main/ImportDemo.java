package com.dx.ss.demo.docx.main;

import com.dx.ss.data.enums.WorkerTypeEnums;
import com.dx.ss.data.main.DocumentParseDelegate;
import com.dx.ss.data.parse.ExcelDocumentParser;
import com.dx.ss.demo.docx.resolver.BizExcelDocumentDataImportResolver;

import java.util.List;

public class ImportDemo {
    public static void main(String[] args) throws Exception{

        DocumentParseDelegate delegate = new DocumentParseDelegate.Builder(WorkerTypeEnums.IMPORT_WORKER)
                .importResolver(new BizExcelDocumentDataImportResolver())
                .parser(ExcelDocumentParser.getParserInstance())
                .locations(new String[]{"E:\\tmp\\parking_fee.xlsx"})
                .build();
        List<?> dataList = delegate.parse();
        System.out.println(dataList.toString());
    }
}
