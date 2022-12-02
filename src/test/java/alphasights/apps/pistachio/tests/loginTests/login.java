package alphasights.apps.pistachio.tests.loginTests;

import alphasights.apps.delivery.pages.deliveryBasePage;
import alphasights.apps.utilities.googleAuth;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeGroups;

import java.io.IOException;

public class login extends googleAuth{

    deliveryBasePage DeliveryBasePage = new deliveryBasePage();

    @BeforeGroups(groups = {"Pistachio"})
    public void navigateToPTO()
    {
        DeliveryBasePage
                .clickUserNavDropdown();
    }
    public login() throws IOException, ParseException {
    }
}
