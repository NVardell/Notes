/****************************************************************************************************************************************************
***********************************************************     HELPFUL DOCUMENTATION     ***********************************************************
*****************************************************************************************************************************************************/
* Cucumber Links
    + Homepage - https://cucumber.io/
    + Overview - https://cucumber.io/docs/guides/overview/
    + Data Tables API - https://github.com/cucumber/cucumber/tree/master/datatable
    + Runner Options - https://cucumber.io/docs/cucumber/api/#list-configuration-options
* Gherkin Links
    + Reference - https://cucumber.io/docs/gherkin/reference/
    + Keywords - https://cucumber.io/docs/gherkin/reference/#keywords
    + Tags - https://cucumber.io/docs/cucumber/api/#tags



/****************************************************************************************************************************************************
*******************************************************************     NOTES     *******************************************************************
*****************************************************************************************************************************************************/
* Cucumber
    + Cucumber is a JUnit extension!  It is launched by running JUnit from you build tool or IDE.
        - In order to use the Cucumber extension, the Cucumber-JUnit dependency is required.
                    <dependency>
                        <groupId>io.cucumber</groupId>
                        <artifactId>cucumber-junit</artifactId>
                        <version>${cucumber.version}</version>
                        <scope>test</scope>
                    </dependency>
    + Running Cucumber
        - Command Line (Most Common Option - According to Official Docs page......)
            * Run using a CLI Runner (Command Line Interface Runner), that is an executable Java class that can be from from command line.
                    java cucumber.api.cli.Main
            * By default, Cucumber will treat anything ending in '.java' under root as a step definition file.
        - JUnit
            1. Add 'cucumber-junit' dependency to pom file.
            2. Create an empty test class that uses the Cucumber JUnit Runner.
               This will execute all scenarios in same package as the runner.
               By default, 'glue' clode is also assumed to be in the same package.
                    package com.example;

                    import cucumber.api.CucumberOptions;
                    import cucumber.api.junit.Cucumber;
                    import org.junit.runner.RunWith;

                    @RunWith(Cucumber.class)
                    @CucumberOptions()  // Can be used to provide additional configuration to the runner. (Link at top. Some examples below.)
                    @CucumberOptions(plugin = {"pretty", "html:target/cucumber"})   // Use the two formatter plugins pretty and html
                    @CucumberOptions(dryRun=true)   // If you want to check whether all feature file steps have corresponding step definitions
                    @CucumberOptions(monochrome=true)   // If you want console output from Cucumber in a readable format
                    @CucumberOptions(strict=false)  // If you want to skip undefined steps from execution
                    public class RunCucumberTest {
                    }

    + Step Definitions
        - In order for Cucumber to comprehend the Gherkin, Step Definitions are required for mapping the Gherkin steps to actual code.
        - Step definitions hardwire the specification to the implementation.
        - The definitions can be written in many programming languages, below is an example in JavaScript.
                When("{maker} starts a game", function(maker) {
                    maker.startGameWithWord({ word: "whale" })
                })
* Gherkin
    + Gherkin documents are stored in '.feature' files and are typically versioned in source control alongside software
    + Gherkin is a Domain Specific Language (DSL).
    + Primary Keywords
        - Feature
        - Rule
        - Example (or Scenario)
        - Given, When, Then, And, But (Scenario Steps)
        - Background
        - Scenario Outline (or Scenario Template)
        - Examples
    + Secondary Keywords
        - Doc String  =  """
        - Data Tables =  |  
        - Tags        =  @
        - Comments    =  #  
    + Feature Keyword
        - Provides a high-level description of a software feature, and to group related scenarios.
        - Will always be the first keyword in a feature file, followed by a ':' and a mini description of the feature (Example Below)
                Feature: Quite the Description
        - You can add free-form text underneath the 'Feature' primary keyword to add further descriptions.
            * The name & description have no special meaning to Cucumber.
            * These description lines are ignored by Cucumber at runtime, but are available by default in generated html report. (Example Below)
                    Feature: Quite the Description
                        Right here you can put more words to further describe the description
                        above that is simply so quite.
            * The free form description ends when a new line is started with a keyword such as 'Rule', 'Example', or 'Scenario Outline'.
        - You can place tags above 'Feature' to group related features, independent of your file & directory structure.
    + Rule Keyword (Added in Gherkin v6)
        - Purpose is to represent one 'business rule' that should be implemented.
        - Provides additional info for a feature.
        - Used to group together several scenarios that belong to the same 'business rule'.
        - A 'Rule' should contain one or more scenarios that illustrate the particular rule.
        - Cannot contain a 'Background'.
        - Example of a 'Rule'
                # -- FILE: features/gherkin.rule_example.feature
                Feature: Highlander

                  Rule: There can be only One

                    Example: Only One -- More than one alive
                      Given there are 3 ninjas
                      And there are more than one ninja alive
                      When 2 ninjas meet, they will fight
                      Then one ninja dies (but not me)
                      And there is one ninja less alive

                    Example: Only One -- One alive
                      Given there is only 1 ninja alive
                      Then he (or she) will live forever ;-)

                  Rule: There can be Two (in some cases)

                    Example: Two -- Dead and Reborn as Phoenix
                      ...
    + Tags '@'
        * Tags can be placed above any of the following Gherkin keywords/elements
            + Feature
            + Scenario
            + Scenario Outline
            + Examples
        * A feature or scenario can have as many tags as you want, just separate them with spaces. (Example Below)
                @billing @bicker @annoy
                Feature: Verify billing
        * Tag Inheritance
            + Tags are inherited by child elements.
            + Tags listed above a 'Feature' will be inherited by 'Scenario', 'Scenario Outline', & 'Examples'.
            + Tags above a 'Scenario Outline' will be inherited by 'Examples'.
        * Tag Expressions
            + A tag expression is simply an infix boolean expression. Examples Below.
                    Expression          Description
                    @fast               Scenarios tagged with @fast
                    @wip and not @slow  Scenarios tagged with @wip that aren’t also tagged with @slow
                    @smoke and @fast    Scenarios tagged with both @smoke and @fast
                    @gui or @database   Scenarios tagged with either @gui or @database
        * There are two purposes for tags
            + Running a subset of scenarios
                - You can tell Cucumber to only run scenarios with a particular tag, and can be accomplished in many ways.
                    * JVM System Property (Example Below)
                        mvn test -Dcucumber.options='--tags "@smoke and @fast"'
                    * Environment Variables (Example Below)
                        # Linux / OS X:
                        CUCUMBER_OPTIONS='--tags "@smoke and @fast"' mvn test
                        # Windows:
                        set CUCUMBER_OPTIONS='--tags "@smoke and @fast"'; mvn test
                    * Changing JUnit Runner Class (Examples Below)
                        @CucumberOptions(tags = "not @smoke")
                        @CucumberOptions(tags = "@smoke and @fast")
                        @CucumberOptions(tags = "(@smoke or @ui) and (not @slow)")
            + Scoping hooks to a subset of scenarios
                - Hooks can be conditionally selected for execution based on the tags of the scenario.
                - To run a particular hook only for certain scenarios, you can associate a 'Before' or 'After' Hook with a tag expression (Examples Below)
                        Annotated Method Style:
                            @After("@browser and not @headless")
                            public void doSomethingAfter(Scenario scenario){ ... }
                        Lambda Style:
                            After("@browser and not @headless", (Scenario scenario) -> { ... });
    + Example
        - Concrete example that illustrates a business rule, and consists of a list of steps.
        - Keyword 'Example' is a synonym for keyword 'Scenario'.
        - Examples follow the same given pattern every time
            * Describe an initial context (Given steps)
            * Describe an event (When steps)
            * Describe an expected outcome (Then steps)
    + Steps
        - Steps start with keywords such as:
            * Given
                + Used to describe the initial context; typically, something that happened in the past.
                + The purpose of this step is to 'PUT THE SYSTEM IN A KNOWN STATE' before any further interactions occur.
            * When
                + When steps are used to describe an event or action.
                + STRONGLY recommended to only have ONE 'When' step per 'Scenario'.
            * Then
                + Then steps used to describe EXPECTED outcome.
                + Should use an assertion to compare expected an actual results.
                + Don't do assertions for DATABASE stuff - ONLY observable outcomes that users can witness.
            * And / But
                + Can be used to replace multiple 'Given's, 'When's, or 'Then's so make scenarios more legible.
        - Cucumber executes them in order, one at a time, executing a matching step definition every time.
        - Keywords are NOT taken into account when looking for step definitions.  This means you cannot have multiple 
          steps with the same text. Cucumber considers the following steps duplicates. (Example Below)
                Given there is money in my account
                Then there is money in my account
    + Background
        - Allows you to add some context to the scenarios in a feature.
        - Should be used when test cases are repeating a lot of initial 'given' steps.
        - Backgrounds are run before each scenario, but AFTER any 'Before Hooks'.
        - In the Feature file, put the background before the first 'Scenario'.
    + Scenario Outline (aka - Scenario Template)
        - Used to run the same Scenario multiple times with different combinations of values.
        - They allow us to more concisely express scenarios through the use of a template with '< >' delimited parameters.
        - Allows us to go from this:
                Scenario: eat 5 out of 12
                  Given there are 12 cucumbers
                  When I eat 5 cucumbers
                  Then I should have 7 cucumbers

                Scenario: eat 5 out of 20
                  Given there are 20 cucumbers
                  When I eat 5 cucumbers
                  Then I should have 15 cucumbers
        - To this:
                Scenario Outline: eating
                  Given there are <start> cucumbers
                  When I eat <eat> cucumbers
                  Then I should have <left> cucumbers

                  Examples:
                    | start | eat | left |
                    |    12 |   5 |    7 |
                    |    20 |   5 |   15 |
        - The 'Scenario' is run once for each row in the template/outline.
    + Step Arguments
        - When you want to pass more data to a step than fits on a single line.
        - Gherkin has two ways to accomplish this.
            * Doc Strings
                + Will be passed to the step definition as the last argument.
                + The text should be offset by delimiters consisting of three double-quote marks on lines of their own. (Example Below)
                        """
                        Here is the first paragraph of my blog post.
                        """
            * Data Tables
                + Will be passed to the step definition as the last argument.
                + Cucumber provides an API for manipulating tables from within step definitions. (Link at top.)
                + Handy for passing a list of values to a step definition (Example Below)
                        Given the following users exist:
                          | name   | email              | twitter         |
                          | Aslak  | aslak@cucumber.io  | @aslak_hellesoy |
                          | Julien | julien@cucumber.io | @jbpros         |
                          | Matt   | matt@cucumber.io   | @mattwynne      |



/****************************************************************************************************************************************************
*******************************************************************     Q & A     *******************************************************************
*****************************************************************************************************************************************************/
* What is Cucumber?
    - Cucumber is a tool that supports BDD (Behavior Driven Development).
* What Cucumber does?
    - Reads executable specifications written in plain text and validates that the software does what those specifications say.
    - Specifications can be made up of multiple scenarios that each have their own list of steps for Cucumber to work through.
    - Cucumber verifies that the software conforms with the specs & then generates a report with success/failure for each scenario.
* How is Cucumber able to understand the scenarios?
    - They must follow basic syntax rules called Gherkin.
* What is Gherkin?
    - Gherkin is a simple set of grammar rules that makes plain text structured enough for Cucumber to understand.
* What purposes does Gherkin serve?
    - Unambiguous executable specifications
    - Automated testing using Cucumber
    - Documents how the system actually behaves by the reports it generates
* Who should write step definitions?
    - Whoever writes the Gherkin should be writing the step definitions; which in general, should be the developers.
* What will always be the first primary keyword in a Gherkin 'feature' file?
    - Feature.
* 
    - 
* 
    - 





/****************************************************************************************************************************************************
*************************************************     INTELLIJ AUTO-GEN STEPS FROM FEATURE FILE     *************************************************
*****************************************************************************************************************************************************/
/**
 * JAVA 8 Version
 * @author Nate Vardell
 * @since 7/2/2019
 */
public class MyStepdefs {
    public MyStepdefs() {
        Given("^there are <size> integers in the array$", () -> {
        });
        When("^I look for element <k>$", () -> {
        });
        Then("^I should see message <msg>$", () -> {
        });
    }
}


/**
 * JAVA Version
 * @author Nate Vardell
 * @since 7/2/2019
 */
public class MyStepdefs {
    @cucumber.api.java.en.Given("there are <size> integers in the array")
    public void thereAreSizeIntegersInTheArray() {
        
    }

    @cucumber.api.java.en.When("I look for element <k>")
    public void iLookForElementK() {
        
    }

    @cucumber.api.java.en.Then("I should see message <msg>")
    public void iShouldSeeMessageMsg() {
    }
}
