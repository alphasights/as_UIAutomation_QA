package alphasights.apps.pistachio.tests.loginTests;

import alphasights.apps.delivery.pages.dashboardPage;
import alphasights.apps.utilities.googleAuth;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeGroups;

import java.io.IOException;

public class loginTests extends googleAuth{

    dashboardPage dashboardPage = new dashboardPage();

    @BeforeGroups(groups = {"Pistachio"})
    public void navigateToPTO()
    {
        dashboardPage
                .clickUserNavDropdown();
    }
    public loginTests() throws IOException, ParseException {
    }
}
