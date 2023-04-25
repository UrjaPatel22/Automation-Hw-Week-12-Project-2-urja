package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMyAccountOptions(String option){
        List<WebElement> registerList = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));//list with two options(do multi select)
        try {
            for (WebElement option1 : registerList) {
                System.out.println(option1.getText());
                if (option1.getText().equals(option)) {
                    option1.click();
                    //break;
                }
            }

        } catch (StaleElementReferenceException e) {
            registerList = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));
        }
    }
    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully()throws InterruptedException{

        Thread.sleep(2000);
        //1.1
        clickOnElement(By.xpath("//i[@class='fa fa-user']"));
        //1.2
        selectMyAccountOptions("Register");
        //1.3
        String expectedRegister="Register Account";

        String actualRegister=getTextFromElement(By.xpath("//h1[normalize-space()='Register Account']"));
        Assert.assertEquals("Error Message displayed",expectedRegister,actualRegister);

    }


    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully()throws InterruptedException{
        Thread.sleep(2000);
        //2.1
        clickOnElement(By.xpath("//i[@class='fa fa-user']"));
        //2.2
        selectMyAccountOptions("Login");
        //2.3
        String expectedLogin="Returning Customer";
        String actualLogin=getTextFromElement(By.xpath("//h2[normalize-space()='Returning Customer']"));
        Assert.assertEquals("Error Message displayed",expectedLogin,actualLogin);
    }
    @Test
    public void verifyThatUserRegisterAccountSuccessfully()throws InterruptedException{

        Thread.sleep(2000);
        //3.1
        clickOnElement(By.xpath("//i[@class='fa fa-user']"));
        //3.2
        selectMyAccountOptions("Register");
        //3.3
        sendTextToElement(By.xpath("//input[@id='input-firstname']"),"John");
        //3.4
        sendTextToElement(By.xpath("//input[@id='input-lastname']"),"Smith");
        //3.5
        sendTextToElement(By.xpath("//input[@id='input-email']"),"john1234567@gmail.com");
        //3.6
        sendTextToElement(By.xpath("//input[@id='input-telephone']"),"123456789");
        //3.7
        sendTextToElement(By.xpath("//input[@id='input-password']"),"abc123");
        //3.8
        sendTextToElement(By.xpath("//input[@id='input-confirm']"),"abc123");
        //3.9
        Thread.sleep(2000);
        clickOnElement(By.xpath("//label[normalize-space()='Yes']"));
        //3.10
        clickOnElement(By.xpath("//input[@name='agree']"));
        //3.11
        clickOnElement(By.xpath("//input[@value='Continue']"));
        //3.12
        Thread.sleep(2000);

        String expectedRegisterMessage="Your Account Has Been Created!";
        String actualRegisterMessage=getTextFromElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"));
        Assert.assertEquals("Error Message displayed",expectedRegisterMessage,actualRegisterMessage);


        //3.13
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

        //3.14
        clickOnElement(By.xpath("//i[@class='fa fa-user']"));

        //3.15
        selectMyAccountOptions("Logout");

        //3.16

        String expectedLogout="Account Logout";
        String actualLogout=getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']"));
        Assert.assertEquals("Error Message displayed",expectedLogout,actualLogout);


        //3.17
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

    }
    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully()throws InterruptedException{
        Thread.sleep(2000);
        //4.1
        clickOnElement(By.xpath("//i[@class='fa fa-user']"));
        //4.2
        selectMyAccountOptions("Login");
        //4.3
        sendTextToElement(By.xpath("//input[@id='input-email']"),"john1234567@gmail.com");
        //4.4
        sendTextToElement(By.xpath("//input[@id='input-password']"),"abc123");
        //4.6
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@value='Login']"));
        //4.7


        String expectedAccount="My Account";
        String actualAccount=getTextFromElement(By.xpath("//h2[normalize-space()='My Account']"));
        Assert.assertEquals("Error Message displayed",expectedAccount,actualAccount);


        //4.8
        clickOnElement(By.xpath("//i[@class='fa fa-user']"));

        //4.9
        selectMyAccountOptions("Logout");

        //4.10



        String expectedLogout="Account Logout";
        String actualLogout=getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']"));
        Assert.assertEquals("Error Message displayed",expectedLogout,actualLogout);



        //4.11
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

    }



    @After
    public void tearDown() {

        closeBrowser();
    }


}
