package alphasights.apps.delivery.tests;

import alphasights.apps.delivery.pages.*;
import alphasights.apps.delivery.pages.whiteboardAndProjs.projectCreationPage;
import alphasights.apps.delivery.pages.whiteboardAndProjs.projectDetailsPage;
import alphasights.apps.utilities.googleAuth;
import com.codeborne.selenide.SelenideElement;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static alphasights.apps.delivery.pages.NPSOptions.ON_COMPLETION;

public class whiteboardAndProjects extends googleAuth {

    deliveryBasePage BasePage = new deliveryBasePage();
    dashboardPage DashboardPage = new dashboardPage();
    projectCreationPage ProjectCreationPage = new projectCreationPage();
    projectDetailsPage ProjectDetailsPage = new projectDetailsPage();

    //region Variables
    public SelenideElement clientInstructions;

    //endregion

    @Test (groups = {"Delivery", "Delivery_Proj_Setup"})
    public void createProjectStandard()
    {
        DashboardPage
                .pageLoad()
                .clickNewProjectBtn();
        ProjectCreationPage
                .createProject(ON_COMPLETION, "Alpha Test", "Competitors", "Test Competitor Angle")
                .clickCreateProjects();
        ProjectDetailsPage
                .verifyProjectCreated();
    }

    public whiteboardAndProjects() throws IOException, ParseException {
    }
}
