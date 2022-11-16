package alphasights.apps.delivery.tests;

import alphasights.apps.delivery.pages.*;
import alphasights.apps.utilities.googleAuth;
import com.codeborne.selenide.SelenideElement;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static alphasights.apps.delivery.pages.NPSOptions.ON_COMPLETION;

public class whiteboardAndProjectsTests extends googleAuth {

    basePage BasePage = new basePage();
    dashboardPage DashboardPage = new dashboardPage();
    projectCreationPage ProjectCreationPage = new projectCreationPage();
    projectDetailsPage ProjectDetailsPage = new projectDetailsPage();

    //region Variables
    public SelenideElement clientInstructions;

    //endregion

    @Test (groups = {"Delivery"})
    public void createProjectStandard()
    {
        DashboardPage
                .pageLoad()
                .clickNewProject();
        ProjectCreationPage
                .createProject(ON_COMPLETION, "Alpha Test", "Competitors", "Test Competitor Angle")
                .clickCreateProjects()
                .verifyProjectCreated();
    }

    public whiteboardAndProjectsTests() throws IOException, ParseException {
    }
}
