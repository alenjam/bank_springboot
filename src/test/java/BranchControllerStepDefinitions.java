import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.tortil.bankdetails.service.BranchService;

import static org.junit.Assert.*;

public class BranchControllerStepDefinitions {

    @Autowired
    private BranchService branchService;
    private ResponseEntity<?> response;

    @Given("a valid IFSC code {string}")
    public void aValidIfscCode(String ifscCode) {
        // Do nothing, just for clarity in the scenario
    }

    @When("I request the branch details for the IFSC code")
    public void iRequestTheBranchDetailsForTheIfscCode() {
        response = branchService.getBranch("ABC123");
    }

    @Then("the response status should be {int} OK")
    public void theResponseStatusShouldBeOK(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    // Add other step definitions for remaining scenarios
}
