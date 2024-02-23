/**********************************************************************************************************************
************************************************      DEPENDENCY      *************************************************
***********************************************************************************************************************/
* Maven
    + Jackson Core (Jan. 05, 2020)
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.10.2</version>
        </dependency>
    + Jackson Databind  (Jan. 05, 2020)
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.10.2</version>
        </dependency>
    + Jackson Annotations  (Jan. 05, 2020)
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.10.2</version>
        </dependency>



/**********************************************************************************************************************
**************************************************      URLS      *****************************************************
***********************************************************************************************************************/
* Baeldung
    + https://www.baeldung.com/jackson-annotations
    + https://www.baeldung.com/jackson-advanced-annotations
    + https://www.baeldung.com/jackson-inheritance
    + https://www.baeldung.com/jackson-object-mapper-tutorial
    + https://www.baeldung.com/jackson-name-of-property



/**********************************************************************************************************************
***********************************************      ANNOTATIONS      *************************************************
***********************************************************************************************************************/
* @JsonAnyGetter
    + Allows the flexibility of using a Map field as standard properties.
    + Example:
        private Map<String, String> properties;
        @JsonAnyGetter public Map<String, String> getProperties() { return properties; }
* @JsonPropertyOrder
    + Allows you to specify the order of properties on serialization.
    + Example:
        @JsonPropertyOrder({ "name", "id" })
        public class MyBean { public int id; public String name; }

        Outputs: { "name":"My bean", "id":1 }
* @JsonRawValue
    + Instructs Jackson to serialize a property exactly as is.
    + Can be used to embed a json object inside of another object.
    + Example:
        public class RawBean {
            public String name;
         
            @JsonRawValue
            public String json;
        }
        Output: {
                    "name":"My bean",
                    "json":{
                        "attr":false
                    }
                }
* @JsonValue
    + Indicates a single method that will be used to serialize the entire object.
    + For example, in an Enum, we annotate the 'getName' method so that any entity is serialized via its name.
    + Example:
        public enum TypeEnumWithValue {
            TYPE1(1, "Type A"), TYPE2(2, "Type 2");
         
            private Integer id;
            private String name;
         
            // standard constructors
         
            @JsonValue
            public String getName() {
                return name;
            }
        }
    + Example:
        public enum Statistic {
            @JsonProperty("min") MIN, @JsonProperty("max") MAX, @JsonProperty("average") AVERAGE;

            /** Error handling for improper enum inputs  */
            @JsonCreator
            static Statistic fromString(String value){
                return Arrays.stream(Statistic.values())
                    .filter(stat -> stat.name().equalsIgnoreCase(value))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT,
                        "The statistic you tried to calculate (" + value + ") is not currently our cup of tea."));
            }
          }
* @JsonRootName
    + Used when wrapping is enabled, to specify the name of the root wrapper to be used.
    + Example:
        @JsonRootName(value = "user")
        public class UserWithRoot {
            public int id;
            public String name;
        }
        Output:
        {
            "User": {
                "id": 1,
                "name": "John"
            }
        }
* @JsonAlias
    + Defines one or more alternative names for a property during deserialization.
    + Example:
        * Allows us to deserialize jsons with values such as fName, f_name, and firstName into
          the firstName variable of the POJO.
        public class AliasBean {
            @JsonAlias({ "fName", "f_name" })
            private String firstName;   
            private String lastName;
        }
* @JsonIgnoreProperties
    + A class-level annotation that marks a property or a list of properties that Jackson will ignore.
    + To ignore all unknown properties in JSON input, we can set ignoreUnknown=true.
    + Example:
        @JsonIgnoreProperties({ "id" })
        public class BeanWithIgnore {
            public int id;
            public String name;
        }
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class BeanWithIgnore {
            public int id;
            public String name;
        }
* @JsonIgnore
    + Used to mark a property to be ignored at the field level.
    + Example:
        public class BeanWithIgnore {
            @JsonIgnore
            public int id;
            public String name;
        }
* @JsonIgnoreType
    + Marks all properties of an annotated type to be ignored.
    + Example:
        public class User {
            public int id;
            public Name name;
         
            @JsonIgnoreType
            public static class Name {
                public String firstName;
                public String lastName;
            }
        }
    + Test Case:
        @Test
        public void whenSerializingUsingJsonIgnoreType_thenCorrect() throws JsonProcessingException, ParseException {
            User.Name name = new User.Name("John", "Doe");
            User user = new User(1, name);
            String result = new ObjectMapper().writeValueAsString(user);
            assertThat(result, containsString("1"));
            assertThat(result, not(containsString("name")));
            assertThat(result, not(containsString("John")));
        }
* @JsonInclude
    + Can be used to exclude properties with empty/null/default values.
    + Example: (Excludeds Nulls from Serialization)
        @JsonInclude(Include.NON_NULL)
        public class MyBean {
            public int id;
            public String name;
        }
    + Test Case:
        public void whenSerializingUsingJsonInclude_thenCorrect() throws JsonProcessingException {
            MyBean bean = new MyBean(1, null);
            String result = new ObjectMapper().writeValueAsString(bean);
            assertThat(result, containsString("1"));
            assertThat(result, not(containsString("name")));
        }
* @JsonAutoDetect
    + Can override the default semantics of which properties are visible and which are not.
    + Example: (Enable Serialization of private properties.)
        @JsonAutoDetect(fieldVisibility = Visibility.ANY)
        public class PrivateBean {
            private int id;
            private String name;
        }
    + Test Case:
        @Test
        public void whenSerializingUsingJsonAutoDetect_thenCorrect() throws JsonProcessingException {
            PrivateBean bean = new PrivateBean(1, "My bean");
            String result = new ObjectMapper().writeValueAsString(bean);
            assertThat(result, containsString("1"));
            assertThat(result, containsString("My bean"));
        }
* Jackson Polymorphic Type Handling Annotations
    + @JsonTypeInfo
        - Indicates details of what type information to include in serialization.
    + @JsonSubTypes
        - Indicates sub-types of the annotated type.
    + @JsonTypeName
        - Defines a logical type name to use for annotated classes.
    + Group Example - Using all 3 annotations above.
        public class Zoo {
            public Animal animal;
            @JsonTypeInfo(
                use = JsonTypeInfo.Id.NAME, 
                include = As.PROPERTY, 
                property = "type")
            @JsonSubTypes({
                @JsonSubTypes.Type(value = Dog.class, name = "dog"),
                @JsonSubTypes.Type(value = Cat.class, name = "cat")})
            public static class Animal { public String name; }
            @JsonTypeName("dog")
            public static class Dog extends Animal { public double barkVolume; }
            @JsonTypeName("cat")
            public static class Cat extends Animal { boolean likesCream; public int lives; }
        }

        - When we do serialization:
            @Test
            public void whenSerializingPolymorphic_thenCorrect() throws JsonProcessingException {
                Zoo.Dog dog = new Zoo.Dog("lacy");
                Zoo zoo = new Zoo(dog);
                String result = new ObjectMapper().writeValueAsString(zoo);
                assertThat(result, containsString("type"));
                assertThat(result, containsString("dog"));
            }
            * Serializing Zoo w/ Dog =
                {  "animal": {"type": "dog","name": "lacy","barkVolume": 0}  }
            * Deserializing w/ Input:
                { "animal":{ "name":"lacy", "type":"cat" } }
                - Unmarshall to a Zoo instance:
                @Test
                public void whenDeserializingPolymorphic_thenCorrect() throws IOException {
                    String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";
                    Zoo zoo = new ObjectMapper().readerFor(Zoo.class).readValue(json);
                    assertEquals("lacy", zoo.animal.name);
                    assertEquals(Zoo.Cat.class, zoo.animal.getClass());
                }
* @JsonProperty (General Annotation)
    + Used to indicate the property name in JSON for both serialization & deserialization.
    + Commonly used when you are dealing with non-standard getters & setters.
    + Example:
        public class MyBean {
            public int id; private String name;
         
            @JsonProperty("name")
            public void setTheName(String name) { this.name = name; }
         
            @JsonProperty("name")
            public String getTheName() { return name; }
        }
    + Test:
        @Test
        public void whenUsingJsonProperty_thenCorrect() throws IOException {
            MyBean bean = new MyBean(1, "My bean");
            String result = new ObjectMapper().writeValueAsString(bean);
            assertThat(result, containsString("My bean"));
            assertThat(result, containsString("1"));
            MyBean resultBean = new ObjectMapper().readerFor(MyBean.class).readValue(result);
            assertEquals("My bean", resultBean.getTheName());
        }
    + To customize the output, to get a value different than JSON value, we need to annotate the getter method.
    + Example:
        // Serializing the following JSON String
        {"stringValue":"some value"}
        // Instead of 'stringValue' we want 'strVal'
        @JsonProperty("strVal")
        public String getStringValue() {  return stringValue;  }
        // Now on serialization we would get:
        {"strVal":"some value"}
        // Simple unit test to verify
        @Test
        public void givenNameOfFieldIsChanged_whenSerializing_thenCorrect() throws JsonParseException, IOException {
            MyDtoFieldNameChanged dtoObject = new MyDtoFieldNameChanged();
            dtoObject.setStringValue("a");
            String dtoAsString = (new ObjectMapper()).writeValueAsString(dtoObject);
            assertThat(dtoAsString, not(containsString("stringValue")));
            assertThat(dtoAsString, containsString("strVal"));
        }
* @JsonFormat
    + Specifies a format when serializing Date/Time values.
    + Example:
        public class EventWithFormat {
            public String name;
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss") public Date eventDate;
        }
    + Test:
        @Test
        public void whenSerializingUsingJsonFormat_thenCorrect() throws JsonProcessingException, ParseException {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            String toParse = "20-12-2014 02:30:00";
            Date date = df.parse(toParse);
            EventWithFormat event = new EventWithFormat("party", date);
            assertThat(new ObjectMapper().writeValueAsString(event), containsString(toParse));
        }
* @JsonUnwrapped
    + Defines values that should be unwrapped/flattened when serialized/deserialized.
    + Example:
        public class UnwrappedUser {
            public int id;

            @JsonUnwrapped public Name name;
            public static class Name { public String firstName; public String lastName;  }
        }
    + Test:
        @Test
        public void whenSerializingUsingJsonUnwrapped_thenCorrect()
          throws JsonProcessingException, ParseException {
            UnwrappedUser.Name name = new UnwrappedUser.Name("John", "Doe");
            UnwrappedUser user = new UnwrappedUser(1, name);
            String result = new ObjectMapper().writeValueAsString(user);
            assertThat(result, containsString("John"));
            assertThat(result, not(containsString("name")));
        }
        - Output: (The fields of the static nested class unwrapped along with the other field.)
            {
                "id":1,
                "firstName":"John",
                "lastName":"Doe"
            }
* @JsonView
    + Indicates the View in which the property will be included for serialization/deserialization.
    + Example:
        public class Views {
            public static class Public {}
            public static class Internal extends Public {}
        }
        // Item entity using the views:
        public class Item {
            @JsonView(Views.Public.class)
            public int id;
         
            @JsonView(Views.Public.class)
            public String itemName;
         
            @JsonView(Views.Internal.class)
            public String ownerName;
        }
        // Finally - The full test:
        @Test
        public void whenSerializingUsingJsonView_thenCorrect() throws JsonProcessingException {
            Item item = new Item(2, "book", "John");
            String result = new ObjectMapper().writerWithView(Views.Public.class).writeValueAsString(item);
            assertThat(result, containsString("book"));
            assertThat(result, containsString("2"));
            assertThat(result, not(containsString("John")));
        }
* @JsonManagedReference & @JsonBackReference
    + Used to handle parent/child relationships.
    + Example:
        // We use @JsonManagedReference & @JsonBackReference to serialize our ItemWithRef Entity:
        public class ItemWithRef {
            public int id;
            public String itemName;
         
            @JsonManagedReference public UserWithRef owner;
        }
        // UserRef Entity
        public class UserWithRef {
            public int id;
            public String name;
         
            @JsonBackReference
            public List<ItemWithRef> userItems;
        }
        // Our Test
        @Test
        public void whenSerializingUsingJacksonReferenceAnnotation_thenCorrect() throws JsonProcessingException {
            UserWithRef user = new UserWithRef(1, "John");
            ItemWithRef item = new ItemWithRef(2, "book", user);
            user.addItem(item);
         
            String result = new ObjectMapper().writeValueAsString(item);
            assertThat(result, containsString("book"));
            assertThat(result, containsString("John"));
            assertThat(result, not(containsString("userItems")));
        }
* @JsonIdentityInfo
    + Indicates that Object Identity should be used when serializing/deserializing values.
        - For instance, to deal with inginite recursion type of problems.
    + Example:
        // We have an ItemWithIdentity entity w/ a bi-directional relationship with the UserWithIdentity entity.
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
        public class ItemWithIdentity {
            public int id;
            public String itemName;
            public UserWithIdentity owner;
        }
        // The UserWithIdentity:
        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
        public class UserWithIdentity {
            public int id;
            public String name;
            public List<ItemWithIdentity> userItems;
        }
        // See how the infinite recursion problem is handled:
        @Test
        public void whenSerializingUsingJsonIdentityInfo_thenCorrect() throws JsonProcessingException {
            UserWithIdentity user = new UserWithIdentity(1, "John");
            ItemWithIdentity item = new ItemWithIdentity(2, "book", user);
            user.addItem(item);
         
            String result = new ObjectMapper().writeValueAsString(item);
            assertThat(result, containsString("book"));
            assertThat(result, containsString("John"));
            assertThat(result, containsString("userItems"));
        }
        // Output
        {
            "id": 2,
            "itemName": "book",
            "owner": { 
                "id": 1, "name": "John", "userItems": [ 2 ]
            }
        }
* @JsonFilter
    + Specifies a filter to use during serialization.
    + Example:
        @JsonFilter("myFilter")
        public class BeanWithFilter {
            public int id;
            public String name;
        }
        // We define the filter - Which excludes all other properties except the name from serialization.
        @Test
        public void whenSerializingUsingJsonFilter_thenCorrect() throws JsonProcessingException {
            BeanWithFilter bean = new BeanWithFilter(1, "My bean");
            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("myFilter", SimpleBeanPropertyFilter
                        .filterOutAllExcept("name"));
         
            String result = new ObjectMapper().writer(filters).writeValueAsString(bean);
            assertThat(result, containsString("My bean"));
            assertThat(result, not(containsString("id")));
        }
* @JacksonAnnotationInside
    + Allows you to create a custom Jackson annotation.
    + Example:
        // Create New Annotation
        @Retention(RetentionPolicy.RUNTIME)
        @JacksonAnnotationsInside
        @JsonInclude(Include.NON_NULL)
        @JsonPropertyOrder({ "name", "id", "dateCreated" })
        public @interface CustomAnnotation {}
        // Use New Annotation
        @CustomAnnotation
        public class BeanWithCustomAnnotation {
            public int id;
            public String name;
            public Date dateCreated;
        }
        // Test New Annotation
        @Test
        public void whenSerializingUsingCustomAnnotation_thenCorrect() throws JsonProcessingException {
            BeanWithCustomAnnotation bean = new BeanWithCustomAnnotation(1, "My bean", null);
            String result = new ObjectMapper().writeValueAsString(bean);
            assertThat(result, containsString("My bean"));
            assertThat(result, containsString("1"));
            assertThat(result, not(containsString("dateCreated")));
        }
        // Output of the serialization process:
        {
            "name":"My bean",
            "id":1
        }
* @JsonPropertyDescription
    + Allows a human readable description to be added to the created JSON schema by providing a description field.
    + Example:
        public class PropertyDescriptionBean {
            private int id;
            @JsonPropertyDescription("This is a description of the name property") 
            private String name;
        }
        // Method for generating a JSON Schema with the addition of the description field
        @Test
        public void whenGeneratingJsonSchema_withAdditionOfDescriptionFields_thenCorrect() throws JsonProcessingException {
            SchemaFactoryWrapper wrapper = new SchemaFactoryWrapper();
            mapper.acceptJsonFormatVisitor(PropertyDescriptionBean.class, wrapper);
            JsonSchema jsonSchema = wrapper.finalSchema();
            String jsonString = mapper.writeValueAsString(jsonSchema);
            assertThat(jsonString, containsString("This is a description of the name property"));
        }
        // Output of Schema
        {
            "type": "object",
            "id": "urn:jsonschema:com:baeldung:jackson:annotation:extra:PropertyDescriptionBean",
            "properties": {
                "name": { "type": "string", "description": "This is a description of the name property"},
                "id": {"type": "integer"}
            }  
        }
* @JsonPOJOBuilder
    + Used to configure a builder class to customize deserialization of a JSON document to recover POJOs
      when the naming convention is different from the default.
    + Example:
        // Suppose we need to deserialize the following JSON
        { "id": 5, "name": "POJO Builder Bean"  }
        // The JSON source will be used to create an instance of the POJOBuilderBean
        // The names of the bean's properties are different from those of the fields in JSON string.
        @JsonDeserialize(builder = BeanBuilder.class)
            public class POJOBuilderBean {
            private int identity;
            private String beanName;
        }



/**********************************************************************************************************************
**************************************************      NOTES      ****************************************************
***********************************************************************************************************************/
* 




/**********************************************************************************************************************
*************************************************      KEYWORDS      **************************************************
***********************************************************************************************************************/
* 




/**********************************************************************************************************************
************************************************      EXCEPTIONS      *************************************************
***********************************************************************************************************************/
* 


/**********************************************************************************************************************
**************************************************      EXAMPLES      *************************************************
***********************************************************************************************************************/
* 
