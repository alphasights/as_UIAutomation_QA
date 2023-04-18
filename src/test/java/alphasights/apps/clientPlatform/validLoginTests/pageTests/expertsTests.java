package alphasights.apps.clientPlatform.validLoginTests.pageTests;

import alphasights.apps.clientPlatform.pages.clientPlatformBasePage;
import alphasights.apps.clientPlatform.pages.expertsPage;
import alphasights.apps.clientPlatform.validLoginTests.validLogins;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.IOException;

public class expertsTests extends validLogins{

    expertsPage ExpertPages = new expertsPage();
    projectsTests ProjectsTests = new projectsTests();
    clientPlatformBasePage ClientPlatformBasePage = new clientPlatformBasePage();

    @DataProvider(name = "searchQuery")
    public Object[][] searchExpert(){
        return new Object[][] {{"Oskar Reynolds"}, {"Company"}, {"CEO"}};
    }

    @DataProvider(name = "statusName")
    public Object[][] chooseStatus() { return new Object[][] {{"available"}, {"requested"}, {"scheduled"}, {"completed"}};}


    @BeforeGroups(groups = {"SearchExperts"})
    public void clearSearchQuery(){
        ExpertPages
                .clearExpertsSearch();
    }

    @Test(groups = {"ClientPlatform", "Standard Login", "ExpertTests", "Interactions"}, dependsOnMethods = "alphasights.apps.clientPlatform.validLoginTests.pageTests.projectsTests.viewAProject")
    public void viewInteraction()
    {
        ExpertPages
                .clickExpertName(3);
    }

    @Test(groups = {"ClientPlatform", "Standard Login", "ExpertTests", "SearchExperts"}, dataProvider = "searchQuery", dependsOnMethods = "alphasights.apps.clientPlatform.validLoginTests.pageTests.projectsTests.viewAProject")
    public void searchExpertQuery(String query)
    {
        ExpertPages
                .searchExperts(query);
    }

    @Test(groups = {"ClientPlatform", "Standard Login", "ExpertTests" } , dataProvider = "statusName" ,dependsOnMethods = "alphasights.apps.clientPlatform.validLoginTests.pageTests.projectsTests.viewAProject")
    public void selectStatus(String statusNm)
    {
        ExpertPages
                .clickStatusButton()
                .selectStatusOption(statusNm);
    }

    @AfterMethod(groups = {})
    public void refreshBrowser()
    {
        ExpertPages
                .refreshBrowser();
    }

    public expertsTests() throws IOException, ParseException {
    }
}
