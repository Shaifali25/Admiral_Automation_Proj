import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class Android extends HelperFuc{
   static String path="src/Resources/Properties.properties";
    static FileInputStream fis;

    {
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static Properties prop=new Properties();

    static String USERNAME = "nikhilbhojwani_OlIiQz";
    static String AUTOMATE_KEY = "ESF2WcJPxp5GHcq3Zemd";
    static String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    static MutableCapabilities capabilities = new MutableCapabilities();

    {
        try {
            driver = new RemoteWebDriver(new URL(URL), (Capabilities) setAndroidcap());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    static JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeTest
    public static void Wait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public static void Load() throws IOException {
    prop.load(fis);
        try {
            driver.get(""+prop.getProperty("BaseUrl"));
            driver.manage().window().maximize();
            WriteExcel(1,8,"PASS");
        } catch (Exception e) {
            WriteExcel(1,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }

    @Test(priority = 2)
    public static void Logo() throws IOException {
        try {
            boolean logoP = driver.findElement(By.xpath("//div[@id=\"sitelogo\"]/img")).isDisplayed();
            System.out.println(logoP);
            Assert.assertTrue(logoP);
            WriteExcel(2,8,"PASS");
        } catch (Exception e) {
            WriteExcel(2,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }

    @Test(priority=3)
    public static void BlankEmpId() throws IOException {
        prop.load(fis);
        try{
            driver.findElement(By.id("UserName")).clear();
            driver.findElement(By.id("Password")).clear();
            driver.findElement(By.id("Password")).sendKeys(""+readExcel(1,1));
            driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
            String msg=driver.findElement(By.xpath("//div[@class=\"form-group\"]//span[text()]")).getText();
            System.out.println(msg);
            Assert.assertEquals(msg,"The Employee ID field is required.");
            WriteExcel(3,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(3,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=4)
    public static void InvalidEmpId() throws IOException {
        prop.load(fis);
        try{
            driver.findElement(By.id("UserName")).clear();
            driver.findElement(By.id("Password")).clear();
            driver.findElement(By.id("UserName")).sendKeys(""+readExcel(2,0));
            driver.findElement(By.id("Password")).sendKeys(""+readExcel(2,1));
            driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
            String msg1=driver.findElement(By.xpath("//p/span[text()]")).getText();
            System.out.println(msg1);
            Assert.assertEquals(msg1,"LogOn username or password provided is incorrect");
            WriteExcel(4,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(4,8,"PASS");
            TakeScreenshot();
            Assert.fail();
        }

    }
    @Test(priority=5)
    public static void BlankPass() throws IOException {
        prop.load(fis);
        try{
            driver.findElement(By.id("UserName")).clear();
            driver.findElement(By.id("Password")).clear();
            driver.findElement(By.id("UserName")).sendKeys(""+readExcel(3,0));
            driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
            String msg2=driver.findElement(By.xpath("//div[@class=\"form-group\"]//span[text()]")).getText();
            System.out.println(msg2);
            Assert.assertEquals(msg2,"The Employee ID field is required.");
            WriteExcel(5,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(5,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }}
   @Test(priority =6)
      public static void InvalidPass() throws IOException {
        prop.load(fis);
        try{
             driver.findElement(By.id("UserName")).clear();
             driver.findElement(By.id("Password")).clear();
             driver.findElement(By.id("UserName")).sendKeys(""+readExcel(4,0));
             driver.findElement(By.id("Password")).sendKeys(""+readExcel(4,1));
             driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
             String msg3=driver.findElement(By.xpath("//p/span[text()]")).getText();
             System.out.println(msg3);
             Assert.assertEquals(msg3,"LogOn username or password provided is incorrect");
             WriteExcel(6,8,"PASS");
         }
         catch(Exception e){
             WriteExcel(6,8,"FAIL");
             TakeScreenshot();
             Assert.fail();
         }
        }
    @Test(priority =7)
    public static void SiginWithout() throws IOException {
        try{
            driver.findElement(By.id("UserName")).clear();
            driver.findElement(By.id("Password")).clear();
            driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
            String msg4=driver.findElement(By.xpath("//span[@data-valmsg-for=\"Password\"]")).getText();
            System.out.println(msg4);
            Assert.assertEquals(msg4,"The Password field is required.");
            WriteExcel(7,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(7,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority = 8)
    public static void SiginWith() throws IOException {
        prop.load(fis);
        try {
            driver.findElement(By.id("UserName")).clear();
            driver.findElement(By.id("Password")).clear();
            driver.findElement(By.id("UserName")).sendKeys(""+readExcel(5,0));
            driver.findElement(By.id("Password")).sendKeys(""+readExcel(5,1));
            driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
            Thread.sleep(5000);
            String currUrl = driver.getCurrentUrl();
            Assert.assertEquals(currUrl, "https://consultant.admiralindia.com/");
            WriteExcel(8,8,"PASS");
        } catch (Exception e) {
            WriteExcel(8,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority =9)
    public static void Manage() throws IOException {
        try{
            js.executeScript("window.scrollBy(0,250)");
            driver.findElement(By.linkText("Manage")).click();
            WriteExcel(9,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(9,8,"PASS");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority =10)
    public static void Attendnace() throws IOException {
        prop.load(fis);
        try{
            js.executeScript("window.scrollBy(0,250)");
            driver.navigate().to(""+prop.get("Attendance"));
            WriteExcel(10,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(10,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority =11)
    public static void ClickToAdd() throws IOException {
        prop.load(fis);
        try{
            driver.navigate().to(""+prop.get("Attendance"));
            driver.findElement(By.xpath("//input[@id=\"Add_Attendance\"]")).click();
            WriteExcel(12,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(12,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority =12)
    public static void WFHbutton() throws IOException {
        try{
            driver.findElement(By.id("WorkFromHome")).click();
            WriteExcel(13,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(13,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
  /*
    @Test(priority =13)
    public static void dispPlusButton() throws IOException {
        try{
            js.executeScript("window.scrollBy(0,250)");
            boolean disp=driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).isDisplayed();
            System.out.println("+ button "+disp);
            Assert.assertTrue(disp);
            WriteExcel(14,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(14,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority =14)
    public static void ClickpulsButton() throws IOException {
        try{
            driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
            WriteExcel(15,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(15,8,"FAIL");
            Assert.fail();
        }
    }
    @Test(priority =15)
    public static void EmpName() throws IOException {
        try{
            js.executeScript("window.scrollBy(0,250)");
            boolean enab=driver.findElement(By.xpath("//input[@id='EmployeeName']")).isDisplayed();
            System.out.println(enab);
            Assert.assertTrue(enab);
            WriteExcel(16,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(16,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority =16)
    public static void ProjectID() throws IOException {
        try{
            js.executeScript("window.scrollBy(0,250)");
            driver.findElement(By.id("ProjectID")).click();
            boolean enab1=driver.findElement(By.xpath("//select[@id=\"ProjectID\"]")).isDisplayed();
            System.out.println(enab1);
            Assert.assertTrue(enab1);
            WriteExcel(17,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(17,8,"FAIL");
            Assert.fail();
        }
    }
    @Test(priority =17)
    public static void ProjectIDMsg() throws IOException {
        try{
            driver.findElement(By.id("Save_TimeSheet")).click();
            String msg6= driver.findElement(By.xpath("//span[@data-valmsg-for=\"ProjectId\"]")).getText();
            Assert.assertEquals(msg6,"The Project Name field is required.");
            WriteExcel(19,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(19,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=18)
    public static void ProjectComp() throws IOException {
        try{
            boolean enab3=driver.findElement(By.xpath("//select[@id=\"ProjectComponentID\"]")).isDisplayed();
            Assert.assertTrue(enab3);
            WriteExcel(20,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(20,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=19)
    public static void ProjectCompMsg() throws IOException {
        try{
           // driver.findElement(By.id("Save_TimeSheet")).click();
            String msg7= driver.findElement(By.xpath("//span[@data-valmsg-for='ProjectComponentId'][text()]")).getText();
            Assert.assertEquals(msg7,"Please select project component");
            WriteExcel(22,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(22,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }

    @Test(priority=20)
    public static void duration() throws IOException {
        try{
            driver.findElement(By.id("Duration")).click();
            WriteExcel(23,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(23,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=21)
    public static void durationMsg() throws IOException {
        try{
            String msg8= driver.findElement(By.xpath("//span[@data-valmsg-for=\"Duration\"][text()]")).getText();
            Assert.assertEquals(msg8,"Please select duration");
            WriteExcel(25,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(25,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=22)
    public static void durationUpdate() throws IOException {
        try{
            driver.findElement(By.id("Duration")).click();
            driver.findElement(By.id("Duration")).clear();
            driver.findElement(By.id("Duration")).sendKeys("8:00");
            WriteExcel(24,8,"PASS");
        }
        catch(Exception e){
            //System.out.println(e);
            WriteExcel(24,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=23)
    public static void WorkDetMsg() throws IOException {
       try {
           String msg9 = driver.findElement(By.xpath("//span[@data-valmsg-for=\"WorkDetail\"][text()]")).getText();
           Assert.assertEquals(msg9, "Please enter WorkDetail");
           WriteExcel(26,8,"PASS");
       }
       catch(Exception e){
           WriteExcel(26,8,"FAIL");
           TakeScreenshot();
           Assert.fail();
       }
    }
    @Test(priority=24)
    public static void SaveTimesheet() throws IOException {
        try{
            driver.findElement(By.id("ProjectID")).click();
            driver.findElement(By.xpath("//select//option[@value=\"19\"]")).click();
            driver.findElement(By.id("ProjectComponentID")).click();
            driver.findElement(By.xpath("//select//option[@value=\"931\"]")).click();
            driver.findElement(By.id("WorkDetail")).sendKeys("Trainings");
            driver.findElement(By.id("Save_TimeSheet")).click();
            WriteExcel(27,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(27,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
*/
    @Test(priority = 25)
    public static void EditSheet() throws IOException {
        prop.load(fis);
        try {
            driver.findElement(By.xpath("//a[normalize-space()='Manage']")).click();
            Thread.sleep(5000);
            driver.navigate().to(""+prop.get("Timesheet"));
            driver.findElement(By.xpath("//tr[2]//i")).click();
            driver.findElement(By.id("WorkDetail")).clear();
            driver.findElement(By.id("WorkDetail")).sendKeys("Training");
            driver.findElement(By.id("Save_TimeSheet")).click();
            WriteExcel(29,8,"PASS");
            WriteExcel(28,8,"PASS");
        } catch (Exception e) {
            WriteExcel(29,8,"FAIL");
            WriteExcel(28,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }

    @Test(priority = 26)
    public static void ManageWFHDet() throws IOException {
        prop.load(fis);
        try {
            driver.findElement(By.xpath("//div[@class='new-navigation']//a[text()=\"Employment\"]")).click();
            Thread.sleep(5000);
            driver.navigate().to(""+prop.get("wfh"));
            WriteExcel(30,8,"PASS");
        } catch (Exception e) {
            WriteExcel(30,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=27)
    public static void EmpName1() throws IOException {
        try{
            js.executeScript("window.scrollBy(0,250)");
            boolean enab=driver.findElement(By.xpath("//input[@id='EmployeeName']")).isDisplayed();
            System.out.println(enab);
            Assert.assertTrue(enab);
            WriteExcel(31,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(31,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=28)
    public static void YesPowerBackup() throws IOException {
        try {
            driver.findElement(By.xpath("//input[@id=\"HasPowerbackup\"][1]")).click();
            boolean enab = driver.findElement(By.id("AveragePowerOutagess")).isEnabled();
            Assert.assertFalse(enab);
            WriteExcel(32,8,"PASS");
            WriteExcel(33,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(32,8,"FAIL");
            WriteExcel(33,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=29)
    public static void NoPowerBackup() throws IOException {
        try {
            driver.findElement(By.xpath("//input[@id=\"HasPowerbackup\"][2]")).click();
            boolean enab = driver.findElement(By.id("AveragePowerOutagess")).isEnabled();
            Assert.assertTrue(enab);
            WriteExcel(34,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(34,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=30)
    public static void YesPersonalMach() throws IOException {
        try {
            driver.findElement(By.xpath("//input[@id=\"HasPersonalMachine\"][1]")).click();
            boolean enab = driver.findElement(By.xpath("//input[@id=\"PersonalMachine\"][1]")).isEnabled();
            Assert.assertTrue(enab);
            WriteExcel(35,8,"PASS");
            WriteExcel(36,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(35,8,"FAIL");
            WriteExcel(36,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }

    @Test(priority=31)
    public static void NoPersonalMach() throws IOException {
        try {
            driver.findElement(By.xpath("//input[@id=\"HasPersonalMachine\"][2]")).click();
            boolean enab = driver.findElement(By.xpath("//input[@id=\"PersonalMachine\"][1]")).isEnabled();
            Assert.assertFalse(enab);
            WriteExcel(37,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(37,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=32)
    public static void YesInternetConn() throws IOException {
        try {
            driver.findElement(By.xpath("//input[@id=\"HasInternetConnectivity\"][1]")).click();
            boolean enab = driver.findElement(By.xpath("//input[@id=\"ConnectivityType\"][1]")).isEnabled();
            Assert.assertTrue(enab);
            WriteExcel(38,8,"PASS");
            WriteExcel(39,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(38,8,"FAIL");
            WriteExcel(39,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @Test(priority=33)
    public static void NoInternetConn() throws IOException {
        try {
            driver.findElement(By.xpath("//input[@id=\"HasInternetConnectivity\"][2]")).click();
            boolean enab = driver.findElement(By.xpath("//input[@id=\"ConnectivityType\"][1]")).isEnabled();
            Assert.assertFalse(enab);
            WriteExcel(40,8,"PASS");
        }
        catch(Exception e){
            WriteExcel(40,8,"FAIL");
            TakeScreenshot();
            Assert.fail();
        }
    }
    @AfterTest
    public static void Quit(){
        driver.quit();
    }
}

