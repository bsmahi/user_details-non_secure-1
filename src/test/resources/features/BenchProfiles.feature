Feature: Bench Profiles API

  Scenario: Upload bench profiles excel file
    Given I have a valid excel file with bench profiles data
    When I upload the file to "/api/non-secure/benchprofiles/upload-bench-profiles-excel"
    Then the response status should be 200
    And the response body should contain "Excel data uploaded and inserted into database successfully."

  Scenario: Create a new bench profile
    Given I have valid bench profile data
    When I send a POST request to "/api/non-secure/benchprofiles/create-bench-profiles" with the data
    Then the response status should be 201
    And the response should contain a location header with the new resource URL

  Scenario: Update an existing bench profile
    Given there is an existing bench profile with id 1
    And I have updated bench profile data
    When I send a PUT request to "/api/non-secure/benchprofiles/1" with the updated data
    Then the response status should be 200
    And the response body should contain "User details updated successfully."

  Scenario: Fetch all bench profiles
    Given there are bench profiles in the system
    When I send a GET request to "/api/non-secure/benchprofiles/fetch-users"
    Then the response status should be 200
    And the response body should contain a list of bench profiles

  Scenario: Fetch a specific bench profile
    Given there is an existing bench profile with id 1
    When I send a GET request to "/api/non-secure/benchprofiles/fetch-users/1"
    Then the response status should be 200
    And the response body should contain the details of the bench profile

  Scenario: Fetch a non-existent bench profile
    When I send a GET request to "/api/non-secure/benchprofiles/fetch-users/999"
    Then the response status should be 404
    And the response body should contain "User not found with id: 999"

  Scenario: Delete all bench profiles
    When I send a DELETE request to "/api/non-secure/benchprofiles/delete-all-users"
    Then the response status should be 204

  Scenario: Delete a specific bench profile
    Given there is an existing bench profile with id 1
    When I send a DELETE request to "/api/non-secure/benchprofiles/delete-users/1"
    Then the response status should be 204