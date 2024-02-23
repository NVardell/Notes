// JAX-RS Client with Jersey
// Last modified: March 19, 2019
// by baeldung Java EEREST Jersey


// 1. Overview
//  Jersey is an open source framework for developing RESTFul Web Services. It also has great inbuilt 
//  client capabilities. In this quick tutorial, we will explore the creation of JAX-RS client using Jersey 2.


// 2. Maven Dependencies
//  Let’s begin by adding the required dependencies (for Jersey JAX-RS client) in the pom.xml:
<dependency>
    <groupId>org.glassfish.jersey.core</groupId>
    <artifactId>jersey-client</artifactId>
    <version>2.25.1</version>
</dependency>
// To use Jackson 2.x as JSON provider:
// The latest version of these dependencies can be found at jersey-client and jersey-media-json-jackson.
<dependency>
    <groupId>org.glassfish.jersey.media</groupId>
    <artifactId>jersey-media-json-jackson</artifactId>
    <version>2.25.1</version>
</dependency>



// 3. RESTFul Client in Jersey
//  We will develop a JAX-RS client to consume the JSON and XML REST APIs that we developed here 
//  (we need to make sure that the service is deployed and the URL is accessible).


// 3.1. Resource Representation Class
//  Let’s have a look at the resource representation class:
//  JAXB annotations like @XmlRootElement are required only if XML support is needed.
@XmlRootElement
public class Employee {
    private int id;
    private String firstName;
 
    // standard getters and setters
}



// 3.2. Creating an Instance of a Client
// The first thing we need is an instance of a Client:
Client client = ClientBuilder.newClient();



// 3.3. Creating a WebTarget
// Once we have the Client instance, we can create a WebTarget using the URI of the targeted web resource:
WebTarget webTarget 
  = client.target("http://localhost:8082/spring-jersey");
// Using WebTarget, we can define a path to a specific resource:
WebTarget employeeWebTarget 
  = webTarget.path("resources/employees");



// 3.4. Building an HTTP Request Invocation
// An invocation builder instance is created one of the WebTarget.request() methods:
// For XML format, MediaType.APPLICATION_XML can be used.
Invocation.Builder invocationBuilder 
  = employeeWebTarget.request(MediaType.APPLICATION_JSON);



// 3.5. Invoking HTTP Requests
// Invoking HTTP GET:
Response response 
  = invocationBuilder.get(Employee.class);
// Invoking HTTP POST:
Response response 
  = invocationBuilder
  .post(Entity.entity(employee, MediaType.APPLICATION_JSON));



// 3.6. Sample REST Client
// Let’s begin writing a simple REST client. The getJsonEmployee() method retrieves an Employee object 
// based on the employee id. The JSON returned by the REST Web Service is deserialized to the Employee 
// object before returning. Using the JAX-RS API fluently to create web target, invocation builder and 
// invoking a GET HTTP request:
public class RestClient {
  
    private static final String REST_URI 
      = "http://localhost:8082/spring-jersey/resources/employees";
  
    private Client client = ClientBuilder.newClient();
 
    public Employee getJsonEmployee(int id) {
        return client
          .target(REST_URI)
          .path(String.valueOf(id))
          .request(MediaType.APPLICATION_JSON)
          .get(Employee.class);
    }
    //...
}



// Let’s now add a method for POST HTTP request. The createJsonEmployee() method creates an Employee by 
// invoking the REST Web Service for Employee creation. The client API internally serializes the Employee
// object to JSON before invoking the HTTP POST method:
public Response createJsonEmployee(Employee emp) {
    return client
      .target(REST_URI)
      .request(MediaType.APPLICATION_JSON)
      .post(Entity.entity(emp, MediaType.APPLICATION_JSON));
}



// 4. Testing the Client
// Let’s test our client with JUnit:
public class JerseyClientLiveTest {
  
    public static final int HTTP_CREATED = 201;
    private RestClient client = new RestClient();
 
    @Test
    public void givenCorrectObject_whenCorrectJsonRequest_thenResponseCodeCreated() {
        Employee emp = new Employee(6, "Johny");
 
        Response response = client.createJsonEmployee(emp);
 
        assertEquals(response.getStatus(), HTTP_CREATED);
    }
}



// 5. Conclusion
//  In this article, we have introduced JAX-RS client using Jersey 2 and developed a simple RESTFul Java 
//  client. As always, the full source code is available in this Github project. I just announced the new 
//  Learn Spring course, focused on the fundamentals of Spring 5 and Spring Boot 2:




// https://jersey.github.io/documentation/latest/client.html

// 5.3.2.  Creating and configuring a Client instance
// JAX-RS Client API is a designed to allow fluent programming model. This means, a construction of a 
// Client instance, from which a WebTarget is created, from which a request Invocation is built and 
// invoked can be chained in a single "flow" of invocations. The individual steps of the flow will be 
// shown in the following sections. To utilize the client API it is first necessary to build an instance 
// of a Client using one of the static ClientBuilder factory methods. Here's the most simple example:
Client client = ClientBuilder.newClient();
// The ClientBuilder is a JAX-RS API used to create new instances of Client. In a slightly more advanced 
// scenarios, ClientBuilder can be used to configure additional client instance properties, such as a 
// SSL transport settings, if needed (see ??? below).

// A Client instance can be configured during creation by passing a ClientConfig to the 
// newClient(Configurable) ClientBuilder factory method. ClientConfig implements Configurable and 
// therefore it offers methods to register providers (e.g. features or individual entity providers, 
// filters or interceptors) and setup properties. The following code shows a registration of custom 
// client filters:
ClientConfig clientConfig = new ClientConfig();
clientConfig.register(MyClientResponseFilter.class);
clientConfig.register(new AnotherClientFilter());
Client client = ClientBuilder.newClient(clientConfig);
// In the example, filters are registered using the ClientConfig.register(...) method. There are multiple
// overloaded versions of the method that support registration of feature and provider classes or 
// instances. Once a ClientConfig instance is configured, it can be passed to the ClientBuilder to 
// create a pre-configured Client instance.

// Note that the Jersey ClientConfig supports the fluent API model of Configurable. With that the code 
// that configures a new client instance can be also written using a more compact style as shown below.
Client client = ClientBuilder.newClient(new ClientConfig()
        .register(MyClientResponseFilter.class)
        .register(new AnotherClientFilter()));
// The ability to leverage this compact pattern is inherent to all JAX-RS and Jersey Client API components.

// Since Client implements Configurable interface too, it can be configured further even after it has 
//  been created. Important is to mention that any configuration change done on a Client instance will 
//  not influence the ClientConfig instance that was used to provide the initial Client instance 
//  configuration at the instance creation time. The next piece of code shows a configuration of an 
//  existing Client instance.

client.register(ThirdClientFilter.class);
// Similarly to earlier examples, since Client.register(...) method supports the fluent API style, 
//  multiple client instance configuration calls can be chained:

client.register(FilterA.class)
      .register(new FilterB())
      .property("my-property", true);
// To get the current configuration of the Client instance a getConfiguration() method can be used.

ClientConfig clientConfig = new ClientConfig();
clientConfig.register(MyClientResponseFilter.class);
clientConfig.register(new AnotherClientFilter());
Client client = ClientBuilder.newClient(clientConfig);
client.register(ThirdClientFilter.class);
Configuration newConfiguration = client.getConfiguration();
// In the code, an additional MyClientResponseFilter class and AnotherClientFilter instance are 
//  registered in the clientConfig. The clientConfig is then used to construct a new Client instance. 
//  The ThirdClientFilter is added separately to the constructed Client instance. This does not influence
//  the configuration represented by the original clientConfig. In the last step a newConfiguration is 
//  retrieved from the client. This configuration contains all three registered filters while the original
//  clientConfig instance still contains only two filters. Unlike clientConfig created separately, the 
//  newConfiguration retrieved from the client instance represents a live client configuration view. 
//  Any additional configuration changes made to the client instance are also reflected in the 
//  newConfiguration. So, newConfiguration is really a view of the client configuration and not a 
//  configuration state copy. These principles are important in the client API and will be used in the 
//  following sections too. For example, you can construct a common base configuration for all clients 
//  (in our case it would be clientConfig) and then reuse this common configuration instance to configure
//  multiple client instances that can be further specialized. Similarly, you can use an existing client
//  instance configuration to configure another client instance without having to worry about any side 
//  effects in the original client instance.