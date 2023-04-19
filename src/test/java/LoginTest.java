import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    String expectedSuccessMessage = "You are now logged in as Viktar";
    String expectedErrorMessage = "Wrong password or the account is disabled, or does not exist";
    Logger logger = Logger.getLogger(LoginTest.class);

    @Test
    @Description("TestIncorrectLogin is ......................................")
    @Epic("User stories ...................")
    public void TestIncorrectLogin() {
        logger.info("TestIncorrectLogin started");
        mainPage.fillIncorrectLoginAndSend();
        addAttachmentScreenshot();
        Assert.assertEquals(mainPage.getErrorMessage(), expectedErrorMessage,
                "Actual error message is " + "'" + mainPage.getErrorMessage() + "'");
    }

    @Test
    @Description("TestCorrectLogin is ................................")
    public void TestCorrectLogin() {
        logger.info("TestCorrectLogin started");
        mainPage.fillCorrectLoginAndSend();
        addAttachmentScreenshot();
        Assert.assertEquals(mainPage.getSuccessMessage(), expectedSuccessMessage,
                "Actual success message is " + "'" + mainPage.getSuccessMessage() + "'");
    }
}
