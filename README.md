# Leanplum app automation [![Leanplum Automation Tests](https://github.com/v-genchev/leanplum-automation/actions/workflows/maven.yml/badge.svg)](https://github.com/v-genchev/leanplum-automation/actions/workflows/maven.yml)

Framework for the Lenplum app. Uses the common-framework as a submodule 

## Requirements

1. Java 11
2. Compatible version of chrome/chromedriver or firefox/geckodriver
    ```diff
     At the moment ChromeDriver version 103 has some problems with Chrome 103
     leading to flaky tests. The last version of chromedriver that this was 
     tesed on was 102 but 104 onwards should also be fine
    ```
   - Issue is mentioned [here](https://bugs.chromium.org/p/chromedriver/issues/detail?id=4121)
   - If you really cannot downgrade your Chrome version, it is recommended to use geckodriver and firefox, otherwise
     you would need to set the headless option to true in config.properties


3. There are some [good implementations](https://github.com/bonigarcia/webdrivermanager) throughout the internet 
for managing the drivers without the need for downloading them manually. 
If requested, implementation will be adjusted accordingly.

## Setup

1. Clone the repo
   ```
    git clone https://github.com/v-genchev/leanplum-automation.git
    ```
2. To add the submodule execute the following command where .gitmodules is located
    ```
    git submodule update --init --recursive
    ```
3. Import the main pom.xml to your IDE

4. Specify needed values in config.properties
   ```properties
       drivers.dir = where_drivers_are_located_on_your_machine
       chrome.driver.path=${drivers.dir}/chromedriver.exe
       gecko.driver.path = ${drivers.dir}/geckodriver.exe
   ```

## Running test cases - .feature files
1. You have two options since the email and password are not commited to the repo
   - Execute the test cases through TestNG or the cucumber runner (TestNG is recommended)
     - You should update [config.properties](../leanplum-automation/automation/src/main/resources/config.properties) 
     with your email and password used to access the Leanplum app:
   ```properties
   user.email = your_email_goes_here
   user.password = your_password_goes_here
   ```
   - Execute the test cases through maven and pass them as system variables 
   ```
   mvn -Duser.email=MyMail -Duser.password=MyPassword clean test
   ```
3. If you want to execute a scenario with specified cucumber tag
   - Through maven - 
   ```
   mvn test -Dcucumber.options="--tags @myCustomTag"
   ```
   - You can specify custom tags in config.properties file - cucumber.tags=not @skip and @myCustomTag - all features with these tags will be executed
   - Reports produced by TestNG and Cucumber can be found in the target folder after execution.

## Running test cases on a Docker container
1. Basic RemoteWebdriver support is added to the common-framework. For now only the latest version of chrome-standalone was tested.
2. If you want to run these tests on a docker container - read [this](https://github.com/SeleniumHQ/docker-selenium). 
   You should also update the config.properties like this:
   ```properties
   env.execution = remote
   browser=chrome
   headless = true
   remote.driver.url = http://localhost:4444/wd/hub
   ```
   Keep in mind that the remote.driver.url property may be different depending on how you set up your container.

### Plans for future implementation
   - Integrate Allure reports to use instead of the ones that Cucumber and TestNG produce
   - Ability to upload the reports to GitHub Pages after the workflow execution
