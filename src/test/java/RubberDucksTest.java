import org.testng.Assert;
import org.testng.annotations.Test;

public class RubberDucksTest extends BaseTest {

    int expectedQuantityDucks = 5;
    String expectedPriceByDate = "$18";
    String expectedPriceByName = "$20";

    @Test
    public void quantityDucksTest() {
        logger.info("quantityDucksTest started");
        rubberDucksPage.clickRubberDucks();
        addAttachmentScreenshot();
        Assert.assertEquals(rubberDucksPage.getQuantityDucks(), expectedQuantityDucks,
                "Actual quantity ducks = " + rubberDucksPage.getQuantityDucks());
    }

    @Test
    public void sortByDateTest() {
        logger.info("sortByDateTest started");
        rubberDucksPage.clickRubberDucks();
        rubberDucksPage.clickDate();
        addAttachmentScreenshot();
        Assert.assertEquals(rubberDucksPage.getPriceByDate(), expectedPriceByDate,
                "Actual first's duck price sorted by Date = " + rubberDucksPage.getPriceByDate());
 /*       Listener list = new Listener();
        list.onTestFailure();
        addAttachmentScreenshot();
  */
    }

    @Test
    public void sortByNameTest() {
        logger.info("sortByNameTest started");
        rubberDucksPage.clickRubberDucks();
        rubberDucksPage.clickName();
        addAttachmentScreenshot();
        Assert.assertEquals(rubberDucksPage.getPriceByName(), expectedPriceByName,
                "Actual first's duck price sorted by Name = " + rubberDucksPage.getPriceByName());
    }
}
