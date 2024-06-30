package emial;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
    public static void main(String[] args) throws Exception {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D://Atomation Testing Project//chromedriver-win64//chromedriver-win64//chromedriver.exe");

        // Configure ChromeOptions
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-debugging-port=9222");
        chromeOptions.addArguments("--no-sandbox");

        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Storing the Application URL in the String variable
        String url = "https://www.gmail.com";

        // Launch the Gmail website
        driver.get(url);

        driver.manage().window().maximize();
        // Perform Gmail login
        driver.findElement(By.id("identifierId")).sendKeys("testing.example1996@gmail.com", Keys.ENTER);
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".whsOnd.zHQkBf")).sendKeys("Maddy06@", Keys.ENTER);

        // Wait for login to complete
        Thread.sleep(30000);

        // Compose a new email
        driver.findElement(By.cssSelector(".T-I.T-I-KE.L3")).click();
        Thread.sleep(30000);
        driver.findElement(By.name("to")).sendKeys("testing.example1996@gmail.com");
        driver.findElement(By.name("subjectbox")).sendKeys("Test Mail");
        driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).sendKeys("Test Email Body");

        // Label the email as "Social"
        driver.findElement(By.cssSelector(".aB.gQ.pE")).click(); // Click on "More options"
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@role='menuitem'][contains(text(),'Label')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(text(),'Social')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@role='button' and text()='Apply']")).click();

        // Send the email
        driver.findElement(By.xpath("//div[@role='button' and text()='Send']")).click();
        Thread.sleep(5000);

        // Wait for the email to arrive in the inbox
        driver.findElement(By.cssSelector(".aAU")).click(); // Click on "Inbox"
        Thread.sleep(10000);

        // Mark the email as starred
        driver.findElement(By.cssSelector(".zA.zE")).click(); // Open the email
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".T-KT")).click(); // Click on the star

        // Verify the email
        Thread.sleep(2000);
        WebElement labelElement = driver.findElement(By.cssSelector(".aoD.az6 span[email]"));
        String label = labelElement.getAttribute("email");
        String subject = driver.findElement(By.cssSelector(".hP")).getText();
        String body = driver.findElement(By.cssSelector(".a3s.aiL")).getText();

        assert label.equals("Social") : "Label is incorrect!";
        assert subject.equals("Test Mail") : "Subject is incorrect!";
        assert body.contains("Test Email Body") : "Body is incorrect!";

        // Close the browser
        driver.quit();
    }
}