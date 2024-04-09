/**********************************************************************************************************************
***************************************************      URLS      ****************************************************
***********************************************************************************************************************/
• https://www.baeldung.com/spring-autowire
• https://www.baeldung.com/spring-annotations-resource-inject-autowire



/**********************************************************************************************************************
************************************************      ANNOTATIONS      ************************************************
***********************************************************************************************************************/
• @SpringBootApplication
    + Convenient annotation that adds all of the following annotations
        - @Configuration
        - @EnableAutoConfiguration
        - @ComponentScan
• @Configuration
    + Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean 
      definitions and service requests for those beans at runtime
    + is meta-annotated with @Component, therefore @Configuration classes are candidates for component scanning
• @Component
    + Indicates that an annotated class is a "component". Such classes are considered as candidates for auto-detection when using 
      annotation-based configuration and classpath scanning.
• @Resource
    + Belongs to the Java Extension package 'javax.annotation.Resource'
    + Follows execution path of:
        1. Match by Name 
            @Resource(name="namedFile") private File defaultFile;
            @Configuration @Bean(name="namedFile") public File namedFile() { ... }
        2. Match by Type
            @Resource private File defaultFile;
            @Configuration @Bean public File namedFile() { ... }
        3. Match by Qualifier
    + Applicable to both setter & field injection.
• @Inject
    + Belongs to the Java Extension package 'javax.annotation.Inject'
    + Follows execution path of:
        1. Match by Type
        2. Match by Qualifier
        3. Match by Name
    + Applicable to both setter & field injection.
    + In order to access the @inject annotation, the 'javax.inject' library has to be declared as a Gradle/Maven Dependency.
        <dependency>
            <groupId>javax.inject</groupId>
            <artifactId>javax.inject</artifactId>
            <version>1</version>
        </dependency>
    + Unlike @Resource, which resolves dependencies by name first, the default behavior of @Inject resolves by type. 
      This means that even if a class reference variable name differs from the bean name, the dependency will still be resolved.
• @Autowired
    + Belongs to the package 'org.springframework.beans.factory.annotation' package
    + Similar to @Inject, only real difference being it is apart of the Spring framework.
    + Has the same execution paths as @inject
        1. Match by Type
        2. Match by Qualifier
        3. Match by Name
    + Applicable to both setter & field injection.
• @RequestParam & @PathVariable
    + Used to extract values from the request URI
    + Differences
        - @RequestParam extracts values from the query string
            + Example
                @GetMapping("/foo") public String getById(@RequestParam String id){ ... }       // ~/foo/?id=abc
        - @PathVariable extracts values from the URI path
            + Example
                @GetMapping("/foo/{id}") public String getById(@PathVariable String id){ ... }  // ~/foo/abc




/**********************************************************************************************************************
***************************************************      NOTES      ***************************************************
***********************************************************************************************************************/
• Annotations related to dependency injection provide classes with a declarative way to resolve dependencies.
    + These annotations provide classes with a declarative way to resolve dependencies.
        - @Resource
        - @Inject
        - @Autowired
    + For Example:
        - @Autowired ArbitraryClass arbObject;
    + As oppose to instantiating them directly (the imperative way)
        - ArbitraryClass arbObject = new ArbitraryClass();
    + Each of these annotations can resolve dependencies either by field injection or by setter injection.
• Setter Injection
    + Recommended for optional dependencies.
    + The execution paths taken when injecting dependencies on a field are applicable to setter-based injection.
    + Resolving dependencies by setter injection is done by annotating a reference variable's corresponding setter method. 
      Pass the name of the bean dependency as an attribute value to the @Resource annotation.
    + The bean name and the corresponding @Resource attribute value must match.
    + Example:
        private File defaultFile;
        @Resource(name="namedFile")
        protected void setDefaultFile(File defaultFile) { this.defaultFile = defaultFile; }
• Dependency Injection
    + The dependency injection design pattern separates the creation of class dependencies from the class itself transferring this 
    responsibility to a class injector allowing the program design to be loosely coupled and to follow the Single responsibility 
    and Dependency inversion principles (again SOLID).        
• Dependency Injection - What Annotation to use?
    + The answer depends on the design scenario faced by the application in question, and how the developer wishes to leverage polymorphism
      on the default execution paths of each annotation.
    + Application-Wide use of Singletons Through Polymorphism
        - If the design is such that app behaviors are based on implementation of an interface or abstract class, and these behaviors
          are used throughout the app, then the @Inject or @Autowired annotations should be used.
        - The benefit of this approach is that when the app is upgraded, or a patch needs to be applied, in order to fix a bug, classes
          can be swapped out with minimal impact.
        - Primary execution path is 'match-by-type'.
    + Comparisons:
        Scenario                                                                    @Resource   @Inject   @Autowired
        Application-wide use of singletons through polymorphism                     ✗           ✔       ✔
        Fine-grained application behavior configuration through polymorphism        ✔          ✗         ✗
        Dependency injection should be handled solely by the Jakarta EE platform    ✔          ✔        ✗
        Dependency injection should be handled solely by the Spring Framework       ✗           ✗         ✔
• Constructor Injection
    + Recommended for required dependencies allowing them to be immutable and preventing them from being null.
    + The only way to declare immutable dependencies is by using Constructor-based dependency injection.
    + In Constructor-based dependency injection, the class constructor is annotated with @Autowired & included a variable number of args
      with the objects to be injected.
    + The main advantage of constructor-based injection is that you can declare your injected fields 'final', as they will be initiated 
      during class instantiation.  This is convenient for required dependencies.
    + Example:
        @Component
        public class ConstructorBasedInjection {
            private final InjectedBean injectedBean;
            @Autowired
            public ConstructorBasedInjection(InjectedBean injectedBean) { this.injectedBean = injectedBean; }
        }
• Field Injection
    + Main reason to use field injection is to reduce boilerplate code for getters/setters or creating constructors
    + Has a lot of drawbacks and triggers a warning in IntelliJ - Spring Framework also suggests staying away from it even though it is a clean approach.
    + Drawbacks:
        - Disallows immutable field declaration
            * Won't work on fields that are declared final/immutable (Since the fields must be instantiated at class instantiation.)
            * The only way to declare immutable dependencies is by using Constructor-based dependency injection.
        - Eases single responsibility principle violation
            * It indirectly attributes to breaking the 'single responsibility' principle based on SOLID, which defines 5 design principles for writing
              understandable, flexible, and maintainable code.
            * Having a constructor with multiple arguments would more clearly show that a class has too many collaborators, and should be broken down.
        - Tightly couple with dependency injection container
            * The only way these fields can be set are by Spring container instantiating the class and injecting them using reflection, 
            otherwise the fields will remain null and your class will be broken/useless.
            * Making the class useless outside of a Spring container.
            * This means that if you want to use your class outside the application container, for example for unit testing, you are forced to use a 
            Spring container to instantiate your class as there is no other possible way (but reflection) to set the autowired fields.
        - Hidden dependencies
            * When using a dependency injection pattern, affected classes should clearly expose these dependencies using a public interface either by 
            exposing the the required dependencies in the constructor or the optional ones using methods (setters).
            * When using field-based dependency injection, the class is inherently hiding this dependencies to the outside world.




/**********************************************************************************************************************
*************************************************      EXCEPTIONS      ************************************************
***********************************************************************************************************************/
• Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: 
    + Can be fixed by Autowire Disambiguation
        - By default, Spring resolves @Autowired entries by type. If more than one beans of the same type are available in the container, the 
        framework will throw a fatal exception indicating that more than one bean is available for autowiring.
        - Autowiring by @Qualifier
            * The @Qualifier annotation can be used to hint at and narrow down the required bean
            * Example
                @Component("fooFormatter")
                public class FooFormatter implements Formatter {
                    public String format() { return "foo"; }
                }

                @Component("barFormatter")
                public class BarFormatter implements Formatter {
                    public String format() { return "bar"; }
                }

                public class FooService {
                    @Autowired @Qualifier("fooFormatter") private Formatter formatter;
                }
        - Autowiring by Name
            * As a fallback Spring uses the bean name as a default qualifier value.
            * So by defining the bean property name, in this case as fooFormatter, Spring matches that to the FooFormatter implementation 
              and injects that specific implementation when FooService is constructed:
            * Example:
                public class FooService {
                    @Autowired private Formatter fooFormatter;
                }            
• org.springframework.beans.factory.NoUniqueBeanDefinitionException:
    + This exception is thrown because the application context has found two bean definitions of a specified type, 
    and it is confused as to which bean should resolve the dependency.
    + Resolved by adding '@Qualifier("bean")' above '@Resource' annotation.
    + Example:
        @Resource @Qualifier("defaultFile") private File dependency1;
        @Resource @Qualifier("namedFile") private File dependency2;

        @Configuration
        public class ApplicationContextTestResourceQualifier {
            @Bean(name="defaultFile") public File defaultFile() { return new File("defaultFile.txt"); }
            @Bean(name="namedFile") public File namedFile() { return new File("namedFile.txt"); }
        }




/**********************************************************************************************************************
**************************************************      EXAMPLES      *************************************************
***********************************************************************************************************************/
• 
