import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Listener implements ITestListener {
    Logger logger = Logger.getLogger(Listener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        getScreen();
        logger.info("start getting screenshot");
        addAttachmentScreenshot();
        logger.info("Test failed");
    }

    public void getScreen() {
        String fileName;
        logger.info("start getting screenshot");
        File screenshot = ((TakesScreenshot)
                WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
        fileName = "src/main/resources/screenshots/screen_" + System.currentTimeMillis() + ".png";
        File res = new File(fileName);
        try {
            FileUtils.copyFile(screenshot, res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("screenshot was saved to " + fileName);
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
