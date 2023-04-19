import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pageOblects.MainPage;
import pageOblects.RubberDucksPage;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import static com.codeborne.selenide.Selenide.open;

@Listeners(Listener.class)
public class BaseTest {
    protected String baseURL = "https://litecart.stqa.ru/en/";
    protected WebDriver webDriver;
    protected MainPage mainPage;

    protected RubberDucksPage rubberDucksPage;
    Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        //{"browserName":"chrome","
        // goog:chromeOptions":{"args":["--remote-allow-origins=*"]},"platformName":"Windows 10"}

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setPlatform(Platform.ANY);


        logger.info("Before test started");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        //       webDriver = new RemoteWebDriver(new URL("http://192.168.1.131:4444/wd/hub"), caps);
        webDriver.manage().window().maximize();
        WebDriverRunner.setWebDriver(webDriver);
        mainPage = new MainPage(webDriver);
        rubberDucksPage = new RubberDucksPage(webDriver);
        logger.info("Before test ended");
    }

    @BeforeMethod
    public void beforeMethod() {
        logger.info("beforeMethod deleting cookies");
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        logger.info("Opening " + baseURL);
        open(baseURL);
    }

    @AfterClass
    public void afterClass() throws InterruptedException {
        logger.info("Tests ended");
        //      webDriver.manage().timeouts().wait(3000);
        WebDriverRunner.getWebDriver().quit();
    }

    public void addAttachmentScreenshot() {
        ByteArrayInputStream screenshot = new ByteArrayInputStream(((TakesScreenshot)
                WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES));
        try {
            Allure.addAttachment("Screenshot_" + System.currentTimeMillis() + ".png",
                    screenshot);
        } catch (WebDriverException e) {
            throw new RuntimeException(e);
        }
    }
}
