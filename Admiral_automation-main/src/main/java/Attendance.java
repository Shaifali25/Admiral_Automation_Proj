import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class Attendance {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //logo
        boolean logoP=driver.findElement(By.xpath("//div[@id=\"sitelogo\"]/img")).isDisplayed();
        System.out.println(logoP);
        Assert.assertTrue(logoP);
        //Blankempid//load
        //        driver.get("https://consultant.admiralindia.com/Account/LogOn?ReturnUrl=%2f");
        //        driver.manage().window().maximize();
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys("Nikhil1");
        driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
        String msg=driver.findElement(By.xpath("//div[@class=\"form-group\"]//span[text()]")).getText();
        System.out.println(msg);
        Assert.assertEquals(msg,"The Employee ID field is required.");
        //InvalidEmpid
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("UserName")).sendKeys("Nikhil");
        driver.findElement(By.id("Password")).sendKeys("Nikhil1");
        driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
        String msg1=driver.findElement(By.xpath("//p/span[text()]")).getText();
        System.out.println(msg1);
        Assert.assertEquals(msg1,"LogOn username or password provided is incorrect");
        //BlankPass
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("UserName")).sendKeys("Nikhil");
        driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
        String msg2=driver.findElement(By.xpath("//div[@class=\"form-group\"]//span[text()]")).getText();
        System.out.println(msg2);
        Assert.assertEquals(msg2,"The Password field is required.");
        //InvalidPass
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("UserName")).sendKeys("Nikhil");
        driver.findElement(By.id("Password")).sendKeys("Nikhil1");
        driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
        String msg3=driver.findElement(By.xpath("//p/span[text()]")).getText();
        System.out.println(msg3);
        Assert.assertEquals(msg3,"LogOn username or password provided is incorrect");
        //SigninWithout
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
        String msg4=driver.findElement(By.xpath("//span[@data-valmsg-for=\"Password\"]")).getText();
        System.out.println(msg4);
        Assert.assertEquals(msg4,"The Password field is required.");
        //signInWith
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("UserName")).sendKeys("9962");
        driver.findElement(By.id("Password")).sendKeys("Gautam1");
        driver.findElement(By.xpath("//input[@value=\"Sign in \"]")).click();
        Thread.sleep(5000);
        String currUrl=driver.getCurrentUrl();
        Assert.assertEquals(currUrl,"https://consultant.admiralindia.com/");
        //manage
        driver.findElement(By.linkText("Manage")).click();
        //attendance
        driver.findElement(By.linkText("Attendance")).click();
        //ClickToADD
        driver.findElement(By.xpath("//input[@id=\"Add_Attendance\"]")).click();
        //WFH
        driver.findElement(By.id("WorkFromHome")).click();
        //Disp+button
        boolean disp=driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).isDisplayed();
        System.out.println("+ button "+disp);
        Assert.assertTrue(disp);
        //click+button
        driver.findElement(By.cssSelector(".glyphicon.glyphicon-plus")).click();
        //EmpName
        boolean enab=driver.findElement(By.xpath("//input[@id='EmployeeName']")).isDisplayed();
        System.out.println(enab);
        Assert.assertTrue(enab);
        //ProId
        driver.findElement(By.id("ProjectID")).click();
        boolean enab1=driver.findElement(By.xpath("//select[@id=\"ProjectID\"]")).isDisplayed();
        System.out.println(enab1);
        Assert.assertTrue(enab1);
        //ProIdMsg
        driver.findElement(By.id("Save_TimeSheet")).click();
        String msg6= driver.findElement(By.xpath("//span[@data-valmsg-for=\"ProjectId\"]")).getText();
        Assert.assertEquals(msg6,"The Project Name field is required.");
        //proIdClick
        driver.findElement(By.xpath("//select//option[@value=\"19\"]")).click();
        //ProComp
        driver.findElement(By.id("ProjectComponentID")).click();
        boolean enab3=driver.findElement(By.xpath("//select[@id=\"ProjectComponentID\"]")).isDisplayed();
        Assert.assertTrue(enab3);
        //ProCompMsg
        driver.findElement(By.id("Save_TimeSheet")).click();
        String msg7= driver.findElement(By.xpath("//span[@data-valmsg-for='ProjectComponentId'][text()]")).getText();
        Assert.assertEquals(msg7,"Please select project component");
       //ProCompClick
        driver.findElement(By.xpath("//select//option[@value=\"931\"]")).click();
        //Duration
        driver.findElement(By.id("Duration")).click();
        //DurationMsg
        driver.findElement(By.id("Save_TimeSheet")).click();
        String msg8= driver.findElement(By.xpath("//span[@data-valmsg-for=\"Duration\"][text()]")).getText();
        Assert.assertEquals(msg8,"Please select duration");
        //DurationUpdate
        driver.findElement(By.id("Duration")).click();
        for (int i = 0; i < 8; i++) {
            driver.findElement(By.xpath("//*[@title='Increment Hour']")).click();
        }
       //WorkMsg
        driver.findElement(By.id("Save_TimeSheet")).click();
        String msg9= driver.findElement(By.xpath("//span[@data-valmsg-for=\"WorkDetail\"'][text()]")).getText();
        Assert.assertEquals(msg9,"Please select WorkDetail");
      //Save
        driver.findElement(By.id("ProjectID")).click();
        driver.findElement(By.xpath("//select//option[@value=\"19\"]")).click();
        driver.findElement(By.id("ProjectComponentID")).click();
        driver.findElement(By.xpath("//select//option[@value=\"931\"]")).click();
        driver.findElement(By.id("WorkDetail")).sendKeys("Trainings");
        driver.findElement(By.id("Save_TimeSheet")).click();
        //EditSheet
        driver.findElement(By.linkText("Manage")).click();
        driver.findElement(By.linkText("Time Sheet")).click();
        driver.findElement(By.xpath("//tr[2]//i")).click();
        driver.findElement(By.id("WorkDetail")).clear();
        driver.findElement(By.id("WorkDetail")).sendKeys("Training");
        driver.findElement(By.id("Save_TimeSheet")).click();
    }
}
