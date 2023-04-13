package alphasights.apps.utilities;

import alphasights.apps.delivery.pages.whiteboardAndProjs.projectCreationPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class jdbcOperations {

    //region Variables
    projectCreationPage ProjectCreationPage = new projectCreationPage();
    private final String userDetails = "/Users/user/Documents/GitHub/as_UIAutomation_QA/resources/userDetails.json";
    JSONParser jsonParser = new JSONParser();
    Object obj = jsonParser.parse(new FileReader(userDetails));
    JSONObject jsonObject = (JSONObject) obj;
    String clientPlatformUsername = (String)jsonObject.get("clientPlatformUsername");
    String clientPlatformPassword = (String)jsonObject.get("clientPlatformPassword");
    public String googleUser = (String) jsonObject.get("googleUser");
    public String qaClientPlatformDB = "jdbc:postgresql://client-portal-qa-staging-db.alphasights.com:5432/production";
    public String qaPistachioDB = "jdbc:postgresql://pistachio-qa-staging-db.alphasights.com:5432/pistachio";
    public String dbUserClientPlatform = (String) jsonObject.get("clientPlatformDBUser");
    public String dbUserClientPlatformPW = (String) jsonObject.get("clientPlatformDBPassword");
    public String dbUserPistachio = (String) jsonObject.get("pistachioDBUser");
    public String dbUserPistachioPW = (String) jsonObject.get("pistachioDBPassword");
    public String vikiPW = null;
    public String deleteProfileDetailsAndInvites =
            "delete from client_profile_updates\n"+
                    "where client_profile_id in (select id from client_profiles where email like 'automationtester%');"+
                    "delete from client_profiles\n" +
                    "where email like 'automationtester%';\n" +
                    "delete from invitations\n" +
                    "where email like 'automationtester%';";

    public String verifyIfProjCodeNameExists =
            "select *\n"+
                    "from projects\n"+
                    "where codename = " + "'" + ProjectCreationPage.codeNameVal + "'" + ";";

    public String verifyUserExists =
            "select *\n"+
                    "from client_contacts\n"+
                    "where \"name\" = 'Automation Tester A'";

    public String disableOldProjects =
            "update pistachio.public.projects\n" +
                    "set codename = concat(codename, ' - DISABLED'), state = 'idle'\n" +
                    "where creator_id = '6568668' and state != 'idle';\n"+
                    "update pistachio.public.projects\n" +
                    "set codename = concat(codename, 'I')\n"+
                    "where creator_id = '6568668' and (codename like '%OLD AUTOMATION' or codename  like '%I')";


    public static boolean projectExists;
    public static boolean userExists;
    //endregion

    //region Locators
    //endregion

    //region Methods
    public void ensureInvitationAndClientProfileDataCleared() throws SQLException, ClassNotFoundException {
        Connection con = DriverManager.getConnection(qaClientPlatformDB, dbUserClientPlatform, dbUserClientPlatformPW);
        Class.forName("org.postgresql.Driver");
        Statement stmt = con.createStatement();
        stmt.executeUpdate(deleteProfileDetailsAndInvites);
        System.out.println("Newly created contact has been deleted.");
    }

    public void ensureProjectDoesNotExist() throws ClassNotFoundException, SQLException {

        Connection con = DriverManager.getConnection(qaPistachioDB, dbUserPistachio, dbUserPistachioPW);
        Class.forName("org.postgresql.Driver");
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //System.out.println("Query sent:\n" + verifyIfProjCodeNameExists);
        ResultSet resultSet = stmt.executeQuery(verifyIfProjCodeNameExists);
        if (!resultSet.isBeforeFirst()) {
            System.out.println("No data exists.");
            projectExists = false;
        }
        else{
            System.out.println("Project codename already exists.");
            projectExists = true;
        }
    }

    public void disableExistingProj(String dynamicPistachioPW) throws SQLException, ClassNotFoundException {
        Connection con = DriverManager.getConnection(qaPistachioDB, googleUser, dynamicPistachioPW);
        Class.forName("org.postgresql.Driver");
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = stmt.executeQuery(disableOldProjects);
        if (!resultSet.isBeforeFirst()) {
            System.out.println("No data exists.");
            projectExists = false;
        }
        else{
            System.out.println("Project status has been marked Idle and had DISABLED appended to the title.");
            projectExists = true;
        }

    }

    public void ensureUserDoesNotExist() throws SQLException, ClassNotFoundException {
        Connection con = DriverManager.getConnection(qaPistachioDB, dbUserPistachio, dbUserPistachioPW);
        Class.forName("org.postgresql.Driver");
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = stmt.executeQuery(verifyUserExists);
        if (!resultSet.isBeforeFirst()) {
            System.out.println("No data exists.");
            userExists = false;
        }
        else{
            System.out.println("Client contact already exists.");
            userExists = true;
        }
    }
    //endregion

    public jdbcOperations() throws IOException, ParseException {
    }
}
