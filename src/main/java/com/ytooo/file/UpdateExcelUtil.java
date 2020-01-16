package com.ytooo.file;

import com.ytooo.exception.ServiceException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateExcelUtil {
    private final static String xls = "xls";

    private final static String xlsx = "xlsx";

    private static Integer rowLength;

    private static Integer colLength;

    private static Workbook workbook;

    private static Logger logger = LoggerFactory.getLogger(UpdateExcelUtil.class);

    public static Map<String, String[]> readExcel(MultipartFile excelFile, Integer targetKeyAt, Integer[] notNullArr) {
        if (null == excelFile) {
            logger.error("文件不存在");
            throw new ServiceException(1, null, "文件不存在");
        }
        //判断文件是否是excel文件
        String fileName = excelFile.getOriginalFilename();
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            logger.error(fileName + "文件格式错误");
            throw new ServiceException(1, null, "文件格式错误");
        }

        Map<String, String[]> resultMap = new HashMap<String, String[]>();
        InputStream inputStream = null;
        try {
            inputStream = excelFile.getInputStream();
            logger.info("excelFile长度" + excelFile.getSize());
            if (fileName.endsWith(xls)) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            logger.error("读取文件报错");
            throw new ServiceException(1, null, "读取文件报错");
        }

        Sheet sheet = workbook.getSheetAt(0);
        rowLength = sheet.getPhysicalNumberOfRows();
        Row row = sheet.getRow(0);
        colLength = row.getPhysicalNumberOfCells();

        for (int i = 1; i < rowLength; i++) {
            row = sheet.getRow(i);
            if (notNullArr != null) {
                for (Integer notNullIndex : notNullArr) {
                    if (row.getCell(notNullIndex).equals("") || null == row.getCell(notNullIndex)) {
                        logger.error("第" + row + "行，第" + notNullIndex + "列为空");
                        throw new ServiceException(1, "第" + row + "行，第" + notNullIndex + "列为空", "非空字段有空值");
                    }
                }
            }
            String[] contentArr = new String[colLength];
            for (int j = 0; j < colLength; j++) {
                //如果是数字转成String
                if (row.getCell(j).getCellType().getCode() == 0) {
                    contentArr[j] = String.valueOf(row.getCell(j).getNumericCellValue());
                } else {
                    contentArr[j] = row.getCell(j).getStringCellValue();
                }
                if (contentArr[j].contains(".") && contentArr[j].contains("E")) {
                    StringBuilder mobile =
                            new StringBuilder(contentArr[j])
                                    .delete(contentArr[j].lastIndexOf("E"), contentArr[j].length())
                                    .replace(0, 2, "1");
                    if (mobile.length() < 11) {
                        mobile.append("0");
                    }
                    contentArr[j] = mobile.toString();
                }
                if (contentArr[j].contains(".")) {
                    contentArr[j] = contentArr[j].substring(0, contentArr[j].lastIndexOf("."));
                }
            }
            if (row.getCell(targetKeyAt).getCellType().getCode() == 0) {
                resultMap.put(String.valueOf(row.getCell(targetKeyAt).getNumericCellValue()), contentArr);
            } else {
                resultMap.put(row.getCell(targetKeyAt).getStringCellValue(), contentArr);
            }
        }
        logger.info(String.valueOf(resultMap.size()));
        return resultMap;
    }

    public static List<String[]> readExcel(MultipartFile excelFile, Integer[] notNullArr) {
        if (null == excelFile) {
            logger.error("文件不存在");
            throw new ServiceException(1, null, "文件不存在");
        }
        //判断文件是否是excel文件
        String fileName = excelFile.getOriginalFilename();
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            logger.error(fileName + "文件格式错误");
            throw new ServiceException(1, null, "文件格式错误");
        }

        List<String[]> resultList = new ArrayList<String[]>();
        InputStream inputStream = null;
        try {
            inputStream = excelFile.getInputStream();
            logger.info("excelFile长度" + excelFile.getSize());
            if (fileName.endsWith(xls)) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            logger.error("读取文件报错");
            throw new ServiceException(1, null, "读取文件报错");
        }

        Sheet sheet = workbook.getSheetAt(0);
        rowLength = sheet.getPhysicalNumberOfRows();
        Row row = sheet.getRow(0);
        colLength = row.getPhysicalNumberOfCells();

        for (int i = 1; i < rowLength; i++) {
            row = sheet.getRow(i);
            if (notNullArr != null) {
                for (Integer notNullIndex : notNullArr) {
                    if (row.getCell(notNullIndex).equals("") || null == row.getCell(notNullIndex)) {
                        logger.error("第" + row + "行，第" + notNullIndex + "列为空");
                        throw new ServiceException(1, "第" + row + "行，第" + notNullIndex + "列为空", "非空字段有空值");
                    }
                }
            }
            String[] contentArr = new String[colLength];
            for (int j = 0; j < colLength; j++) {
                if (row.getCell(j) == null) {
                    continue;
                }
                //如果是数字转成String
                if (row.getCell(j).getCellType().getCode() == 0) {
                    contentArr[j] = String.valueOf(row.getCell(j).getNumericCellValue());
                } else {
                    contentArr[j] = row.getCell(j).getStringCellValue();
                }
                if (contentArr[j].contains(".") && contentArr[j].contains("E")) {
                    StringBuilder mobile =
                            new StringBuilder(contentArr[j])
                                    .delete(contentArr[j].lastIndexOf("E"), contentArr[j].length())
                                    .replace(0, 2, "1");
                    if (mobile.length() < 11) {
                        mobile.append("0");
                    }
                    contentArr[j] = mobile.toString();
                }
                if (contentArr[j].contains(".")) {
                    contentArr[j] = contentArr[j].substring(0, contentArr[j].lastIndexOf("."));
                }
            }
            resultList.add(contentArr);
        }
        return resultList;
    }
}