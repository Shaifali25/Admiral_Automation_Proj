import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.MutableCapabilities;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class HelperFuc implements CapabilitiesInterFace {
    static String path="src/Resources/Intranet_Cases.xlsx";
    static WebDriver driver;
    static MutableCapabilities capabilities = new MutableCapabilities();


    public Object setAndroidcap(){

        capabilities.setCapability("browserName", "chrome");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("osVersion", "12.0");
        browserstackOptions.put("deviceName", "Samsung Galaxy S21");
        browserstackOptions.put("local", "false");
        capabilities.setCapability("bstack:options", browserstackOptions);
        return capabilities;
    }

    public  Object setWindowscap(){
//        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "10");
        browserstackOptions.put("browserVersion", "112.0");
        browserstackOptions.put("local", "false");
        browserstackOptions.put("seleniumVersion", "3.14.0");
        capabilities.setCapability("bstack:options", browserstackOptions);
        return capabilities;
    }
    public Object setIoscap(){
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("osVersion", "16");
        browserstackOptions.put("deviceName", "iPhone 14");
        browserstackOptions.put("local", "false");
        capabilities.setCapability("bstack:options", browserstackOptions);
        return capabilities;
    }

    public static String readExcel(int row,int cell) throws IOException {
        FileInputStream fis=new FileInputStream(path);
        XSSFWorkbook wrkbk=new XSSFWorkbook(fis);
        XSSFSheet sheet1= wrkbk.getSheet("Sheet2");
        XSSFRow row1=sheet1.getRow(row);
        String x="";
        CellType type= row1.getCell(cell).getCellType();
        switch (type){
            case STRING:
                x=row1.getCell(cell).getStringCellValue();
                break;
            case NUMERIC:
                Integer n=(int)row1.getCell(cell).getNumericCellValue();
                x=""+n;
                break;
        }
        return x;
    }
    public static void WriteExcel(int i,int j,String msg) throws IOException {
        FileInputStream fs = new FileInputStream(path);
        Workbook wb = new XSSFWorkbook(fs);
        Sheet sheet1 = wb.getSheetAt(0);
        Row row = sheet1.getRow(i);
        Cell cell = row.createCell(j);
        cell.setCellValue(msg);
        CellStyle cs = wb.createCellStyle();
        cs.setWrapText(true);
        cell.setCellStyle(cs);
        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos);
        fos.close();
    }
    public static void TakeScreenshot() throws IOException {
        Date date=new Date();
        String d=""+date;
        d=d.replaceAll(":","-");
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(file, new File("src/Resources/"+d+".jpeg"));
    }
}
