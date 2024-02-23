// https://www.baeldung.com/jackson-xml-serialization-and-deserialization

1. Overview
In this tutorial, we’re going to look at how to serialize Java objects to XML data using Jackson 2.x and deserialize it back to a POJO.

We’ll focus on the basic operation that doesn’t require a lot of complexity or customization.

2. XmlMapper Object
XmlMapper is the main class from Jackson 2.x that helps us in serialization, so we shall need to create an instance of it:

XmlMapper mapper = new XmlMapper();
This mapper is available in jackson-dataformat-xml jar, so we have to add it as a dependency to our pom.xml:

<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
    <version>2.9.8</version>
</dependency>
Please check the latest version of Jackson dependency in here.

3. Serialize Java to XML
XmlMapper is a subclass of ObjectMapper which is used in JSON serialization. However, it adds some XML specific tweaks to the parent class.

We can now look at how to use it to do the actual serialization. Let’s create a Java class first:

class SimpleBean {
    private int x = 1;
    private int y = 2;
     
    //standard setters and getters
}
3.1. Serialize to the XML String
We can serialize our Java object into the XML String:

@Test
public void whenJavaSerializedToXmlStr_thenCorrect() throws JsonProcessingException {
    XmlMapper xmlMapper = new XmlMapper();
    String xml = xmlMapper.writeValueAsString(new SimpleBean());
    assertNotNull(xml);
}
As a result we’ll get:

<SimpleBean>
    <x>1</x>
    <y>2</y>
</SimpleBean>
3.2. Serialize to the XML file
We can also serialize our Java object to the XML file:

@Test
public void whenJavaSerializedToXmlFile_thenCorrect() throws IOException {
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.writeValue(new File("simple_bean.xml"), new SimpleBean());
    File file = new File("simple_bean.xml");
    assertNotNull(file);
}
And below we can see the content of the resulting file named simple_bean.xml:

<SimpleBean>
    <x>1</x>
    <y>2</y>
</SimpleBean>
4. Deserialize XML to Java
In this section, we’ll look at how to obtain Java objects from XML.

4.1. Deserialize From the XML String
As with serialization, we can also deserialize an XML String back to a Java object:

@Test
public void whenJavaGotFromXmlStr_thenCorrect() throws IOException {
    XmlMapper xmlMapper = new XmlMapper();
    SimpleBean value
      = xmlMapper.readValue("<SimpleBean><x>1</x><y>2</y></SimpleBean>", SimpleBean.class);
    assertTrue(value.getX() == 1 && value.getY() == 2);
}
4.2. Deserialize From the XML File
Likewise, if we have an XML file, we can convert it back to a Java object.

Here, we first read the file into an input stream and then convert the input stream to a String with a simple utility method.

The rest of the code is similar to the one from section 4.1:

@Test
public void whenJavaGotFromXmlFile_thenCorrect() throws IOException {
    File file = new File("simple_bean.xml");
    XmlMapper xmlMapper = new XmlMapper();
    String xml = inputStreamToString(new FileInputStream(file));
    SimpleBean value = xmlMapper.readValue(xml, SimpleBean.class);
    assertTrue(value.getX() == 1 && value.getY() == 2);
}
The utility method:
public String inputStreamToString(InputStream is) throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    while ((line = br.readLine()) != null) {
        sb.append(line);
    }
    br.close();
    return sb.toString();
}
5. Handling Capitalised Elements
In this section, we’ll look at how to handle scenarios where we either have XML with capitalized elements to deserialize or we need to serialize Java objects to XML with one or more elements capitalized.

5.1. Deserialize From the XML String
Let’s say we have an XML with one field capitalized:

<SimpleBeanForCapitalizedFields>
    <X>1</X>
    <y>2</y>
</SimpleBeanForCapitalizedFields>
In order to correctly handle capitalized elements, we need to annotate the “x” field with the @JsonProperty annotation:

class SimpleBeanForCapitalizedFields {
    @JsonProperty("X")
    private int x = 1;
    private int y = 2;
 
    // standard getters, setters
}
We can now correctly deserialize an XML String back to a Java object:

@Test
public void whenJavaGotFromXmlStrWithCapitalElem_thenCorrect() throws IOException {
    XmlMapper xmlMapper = new XmlMapper();
    SimpleBeanForCapitalizedFields value
      = xmlMapper.readValue(
      "<SimpleBeanForCapitalizedFields><X>1</X><y>2</y></SimpleBeanForCapitalizedFields>",
      SimpleBeanForCapitalizedFields.class);
    assertTrue(value.getX() == 1 && value.getY() == 2);
}
5.2. Serialize to the XML String
By annotating required fields with @JsonProperty, we can correctly serialize a Java object into an XML String with one or more capitalized elements:

@Test
public void whenJavaSerializedToXmlFileWithCapitalizedField_thenCorrect()
  throws IOException {
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.writeValue(new File("target/simple_bean_capitalized.xml"),
      new SimpleBeanForCapitalizedFields());
    File file = new File("target/simple_bean_capitalized.xml");
    assertNotNull(file);
}
6. Serialize List to XML
The XmlMapper is able to serialize an entire Java bean into a document. To convert Java object to XML, we’ll take a simple example with the nested object and arrays.

Our intent is to serialize a Person object, along with its composed Address object, into XML.

Our final XML will look something like:

<Person>
    <firstName>Rohan</firstName>
    <lastName>Daye</lastName>
    <phoneNumbers>
        <phoneNumbers>9911034731</phoneNumbers>
        <phoneNumbers>9911033478</phoneNumbers>
    </phoneNumbers>
    <address>
        <streetName>Name1</streetName>
        <city>City1</city>
    </address>
    <address>
        <streetName>Name2</streetName>
        <city>City2</city>
    </address>
</Person>
Notice that our phone numbers are encapsulated in a phoneNumbers wrapper while our address is not. 

We can express this nuance via the @JacksonXMLElementWrapper annotation in our Person class:

public final class Person {
    private String firstName;
    private String lastName;
    private List<String> phoneNumbers = new ArrayList<>();
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Address> address = new ArrayList<>();
 
    //standard setters and getters
}
Actually, we can change the wrapping element name with @JacksonXmlElementWrapper(localName = ‘phoneNumbers’). Or, if we don’t want to wrap our elements, we can disable the mapping with @JacksonXmlElementWrapper(useWrapping = false).

And then let’s define our Address type:

public class Address {
    String streetName;
    String city;
    //standard setters and getters
}
Jackson takes care of the rest for us. Like before, we can simply call writeValue again:

private static final String XML = "<Person>...</Person>";
 
@Test
public void whenJavaSerializedToXmlFile_thenSuccess() throws IOException {
    XmlMapper xmlMapper = new XmlMapper();
    Person person = testPerson(); // test data
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    xmlMapper.writeValue(byteArrayOutputStream, person); 
    assertEquals(XML, byteArrayOutputStream.toString()); 
}
7. Deserialize XML to List
Jackson can read XML that contains lists of objects, too.

If we take our same XML as before, the readValue method does just fine:

@Test
public void whenJavaDeserializedFromXmlFile_thenCorrect() throws IOException {
    XmlMapper xmlMapper = new XmlMapper();
    Person value = xmlMapper.readValue(XML, Person.class);
    assertEquals("City1", value.getAddress().get(0).getCity());
    assertEquals("City2", value.getAddress().get(1).getCity());
}
8. Conclusion
This simple article illustrated how to serialize a simple POJO to XML and obtain a POJO from basic XML data.

We have also looked into how to serialize and deserialize complex beans that contain collections.

The source code that accompanies this article is available on GitHub.