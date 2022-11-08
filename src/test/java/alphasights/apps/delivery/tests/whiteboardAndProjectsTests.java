package alphasights.apps.delivery.tests;

import alphasights.apps.delivery.pages.NPSOptions;
import alphasights.apps.delivery.pages.basePage;
import alphasights.apps.delivery.pages.projectCreationPage;
import alphasights.apps.utilities.googleAuth;
import com.codeborne.selenide.SelenideElement;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static alphasights.apps.delivery.pages.NPSOptions.ON_COMPLETION;

public class whiteboardAndProjectsTests extends googleAuth {

    basePage BasePage = new basePage();
    projectCreationPage ProjectCreationPage = new projectCreationPage();

    //region Variables
    public SelenideElement clientInstructions;

    //endregion

    @Test (groups = {"Delivery"})
    public void createProjectStandard()
    {
        BasePage
                .clickNewProject();
        ProjectCreationPage
                .createProject(ON_COMPLETION);
    }

    public whiteboardAndProjectsTests() throws IOException, ParseException {
    }
}
