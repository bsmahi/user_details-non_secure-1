package com.consultingfirm.userdetails.cucumber.steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;

public class BenchProfilesStepDefinitions {
    private Response response;
    private String baseUrl = "http://localhost:8083";
    private File testFile;

    @Given("I have a valid excel file with bench profiles data")
    public void i_have_a_valid_excel_file() {
        String projectRoot = System.getProperty("user.dir");
        String relativePath = "src/test/resources/testdata/Team Titans (Govardhan) 2024.xlsx";
        String absolutePath = Paths.get(projectRoot, relativePath).toString();

        try {
            Resource resource = new FileSystemResource(absolutePath);
            testFile = resource.getFile();

            if (testFile.exists() && testFile.canRead()) {
                System.out.println("Test file loaded successfully: " + testFile.getAbsolutePath());
            } else {
                Assert.fail("Test file does not exist or is not readable: " + absolutePath);
            }
        } catch (IOException e) {
            Assert.fail("Failed to load test Excel file: " + e.getMessage() + "\nAttempted path: " + absolutePath);
        }

        // If we've reached this point and testFile is still null, fail the test
        if (testFile == null) {
            Assert.fail("Test file could not be loaded. File object is null.");
        }
    }

    @When("I upload the file to {string}")
    public void i_upload_the_file(String endpoint) {
        File file = new File("src/test/resources/testdata/Team Titans (Govardhan) 2024.xlsx");
        response = RestAssured.given()
                .multiPart("file", file)
                .post(baseUrl + endpoint);
        response.then().log().all(); // Log all response details

        if (response.getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("File upload failed. Status code: " + response.getStatusCode());
        }
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int expectedStatus) {
        Assert.assertEquals(expectedStatus, response.getStatusCode());
    }

    @And("the response body should contain {string}")
    public void the_response_body_should_contain(String expectedContent) {
        Assert.assertTrue(response.getBody().asString().contains(expectedContent));
    }

    @Given("I have valid bench profile data")
    public void i_have_valid_bench_profile_data() {
        // Implement logic to create valid test data
    }

    @When("I send a POST request to {string} with the data")
    public void i_send_a_post_request_with_data(String endpoint) {
        // Implement logic to send POST request with test data
    }

    @And("the response should contain a location header with the new resource URL")
    public void the_response_should_contain_location_header() {
        Assert.assertNotNull(response.getHeader("Location"));
    }

    // Implement other step definitions similarly...
}
