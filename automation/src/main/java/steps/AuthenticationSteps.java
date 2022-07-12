package steps;

import drivermanager.DynamicDriverManager;
import helpers.VariableTransformer;
import io.cucumber.java.en.And;
import org.apache.commons.lang3.StringUtils;
import pageobjects.LoginPage;

public class AuthenticationSteps {

    public DynamicDriverManager driverManger;

    public AuthenticationSteps(DynamicDriverManager driverManager) {
        this.driverManger = driverManager;
    }

    @And("^I login with email (.*) and password (.*?)(?:( saving my credentials))?$")
    public void login(String email, String password, String saveCredentials) {
        email = VariableTransformer.transformSingleValue(email);
        password = VariableTransformer.transformSingleValue(password);
        boolean remember = StringUtils.isNotBlank(saveCredentials);
        new LoginPage(driverManger).login(email, password, remember);
    }
}
