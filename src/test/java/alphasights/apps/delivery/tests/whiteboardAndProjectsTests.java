package alphasights.apps.delivery.tests;

import alphasights.apps.delivery.pages.basePage;
import alphasights.apps.delivery.pages.projectCreationPage;
import alphasights.apps.general.googleAuth;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class whiteboardAndProjectsTests extends googleAuth {

    basePage BasePage = new basePage();
    projectCreationPage ProjectCreationPage = new projectCreationPage();

    @Test (groups = {"Delivery"})
    public void createProjectStandard()
    {
        BasePage
                .clickNewProject();
        ProjectCreationPage
                .enterClientInstructions("");
    }

    public whiteboardAndProjectsTests() throws IOException, ParseException {
    }
}
