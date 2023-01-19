package alphasights.apps.redelivery.tests;

import alphasights.apps.delivery.pages.*;
import alphasights.apps.redelivery.pages.quickLaunchNewProj;
import alphasights.apps.utilities.googleAuth;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class newProjectCreation extends googleAuth {

    deliveryBasePage BasePage = new deliveryBasePage();
    dashboardPage DashboardPage = new dashboardPage();
    quickLaunchNewProj QuickLaunchNewProj = new quickLaunchNewProj();

        @Test (groups = {"New Proj Creation"})
        public void negativeTest1()
        {
            QuickLaunchNewProj
                    .clickNextBtn();
        }

        public newProjectCreation() throws IOException, ParseException {

        }

}
