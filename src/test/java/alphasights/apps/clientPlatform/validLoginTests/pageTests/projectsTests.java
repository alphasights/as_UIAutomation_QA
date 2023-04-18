package alphasights.apps.clientPlatform.validLoginTests.pageTests;

import alphasights.apps.clientPlatform.pages.projectsPage;
import alphasights.apps.clientPlatform.validLoginTests.validLogins;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class projectsTests extends validLogins {

    projectsPage ProjectsPage = new projectsPage();

    //region Create Interaction


    //endregion


    //#region View Projects & related data
    @Test(groups = {"ClientPlatform", "Standard Login", "ProjectsTests"})
    public void viewAProject()
    {
        //Feel free to set the Project Title to whatever you'd like to test.  You can also set this up to follow a config file or DataFactory.
        ProjectsPage
                .clickProject("Interaction Upgrades [DEMO]");
    }
    //endregion

    //region Update Interactions

    //endregion

    //region Delete Interactions and related data

    //endregion

    public projectsTests() throws IOException, ParseException {
    }
}
