/**********************************************************************************************************************
***************************************************      URLS      ****************************************************
***********************************************************************************************************************/
* Spring Data Docs
    + https://docs.spring.io/spring-data/mongodb/docs/2.2.4.RELEASE/reference/html
    + https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#reference
* Baeldung
    + https://www.baeldung.com/spring-data-mongodb-tutorial
    + https://www.baeldung.com/queries-in-spring-data-mongodb
    + https://www.baeldung.com/spring-data-mongodb-index-annotations-converter
    + https://www.baeldung.com/cascading-with-dbref-and-lifecycle-events-in-spring-data-mongodb
* Mkyong
    + https://mkyong.com/mongodb/spring-data-mongodb-aggregation-grouping-example/



/**********************************************************************************************************************
************************************************      ANNOTATIONS      ************************************************
***********************************************************************************************************************/
* The mapping metadata infrastructure is defined in a separate spring-data-commons project that is technology agnostic.
* Specific subclasses are using in the MongoDB support to support annotation based metadata. 
* The MappingMongoConverter can use metadata to drive the mapping of objects to documents with the annotations below.
* @Id
    + Applied at the field level to mark the field used for identity purpose.
    + Tells the mapper which property you want to use for the MongoDB '_id' property.
* @MongoId
    + Applied at the field level to mark the field used for identity purpose.
    + Accepts an optional FieldType to customize id conversion.
* @Document
    + Applied at the class level to indicate this class is a candidate for mapping to the database. 
    + You can specify the name of the collection where the data will be stored.
* @DBRef
    + Applied at the field to indicate it is to be stored using a 'com.mongodb.DBRef'.
* @Indexed
    + Applied at the field level to describe how to index the field.
    + Tells the mapping framework to call 'createIndex()' on that property of your document,
      making searches faster.
    + Automatic index creation is only done for types annotated with @Document.
* @CompoundIndex (repeatable)
    + Applied at the type level to declare Compound Indexes.
    + Example - Creates a compound index of lastName in ascending order and age in descending order:
        @Document
        @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}")
        @CompoundIndex(name = "...") // REPEATABLE
        public class Person {
            @Id private ObjectId id;
            private Integer age;
            private String firstName;
            private String lastName;
        }
* @GeoSpatialIndexed
    + Applied at the field level to describe how to geoindex the field.
* @TextIndexed
    + Applied at the field level to mark the field to be included in the text index.
* @HashIndexed
    + Applied at the field level for usage within a hashed index to partition data across a sharded cluster.
* @Language
    + Applied at the field level to set the language override property for text index.
* @Transient
    + By default all private fields are mapped to the document, this annotation excludes the field where it is 
      applied from being stored in the database
    + This annotation excludes the field from being persisted in the database.
* @PersistenceConstructor
    + Marks a given constructor - even a package protected one - to use when instantiating the object from the database. 
    + Constructor arguments are mapped by name to the key values in the retrieved Document.
* @Value
    + Part of the Spring Framework. 
    + Within the mapping framework it can be applied to constructor arguments. 
    + Lets you use a Spring Expression Language statement to transform a key’s value retrieved in the database before 
      it is used to construct a domain object. In order to reference a property of a given document one has to use 
      expressions like: @Value("#root.myProperty") where root refers to the root of the given document.
* @Field
    + Applied at the field level it allows to describe the name and type of the field as it will be represented in 
      the MongoDB BSON document thus allowing the name and type to be different than the fieldname of the class 
      as well as the property type.
    + Indicates the key to be used for the field in the JSON document.
* @Version
    + Applied at field level is used for optimistic locking and checked for modification on save operations. 
    + Makes sure updates are only applied to documents with a matching version.
    + The initial value is zero (one for primitive types) which is bumped automatically on every update.
* @Query (Wasn't in list with all of the others.... Not a mapping annotation... I don't think. Rest are. )
    + By adding the org.springframework.data.mongodb.repository.Query annotation to your repository query methods, 
      you can specify a MongoDB JSON query string to use instead of having the query be derived from the method name, 
      as the following example shows:
        public interface PersonRepository extends MongoRepository<Person, String> {
            @Query("{ 'firstname' : ?0 }")
            List<Person> findByThePersonsFirstname(String firstname);
        }




/**********************************************************************************************************************
***************************************************      NOTES      ***************************************************
***********************************************************************************************************************/
* MongoTemplate
    + Follows the standard template pattern in Spring & provides a ready to go basic API to the underlying persistence engine.
    + Doesnt always need to be defined in Configuration class.
        - For example, when extending AbstractMongoConfiguration, it already has a definition for it.
    + Used by Spring Data MongoDB to execute the queries.
* ReactiveMongoTemplate
    + Is thread-safe and can be reused across multiple instances.
* Using DBRefs
    + IMPORTANT NOTE
        - The mapping framework does not handle cascading saves. 
        - If you change an Account object that is referenced by a Person object, you must save the Account object separately. 
        - Calling save on the Person object does not automatically save the Account objects in the accounts property.
    + The mapping framework doesnt support storing parent-child relations and embedded documents within other documents. 
        - What we can do though is – we can store them separately and use a DBRef to refer to the documents.
    + The mapping framework does not have to store child objects embedded within the document.
    + You can also store them separately and use a DBRef to refer to that document.
    + When the object is loaded from MongoDB, those references are eagerly resolved so that you get back a mapped 
      object that looks the same as if it had been stored embedded within your master document.
    + The following example uses a DBRef to refer to a specific document that exists independently of the object in 
      which it is referenced (both classes are shown in-line for brevity’s sake):  
            @Document
            public class Account {
                @Id 
                private ObjectId id;
                private Float total;
            }
            @Document
            public class Person {
                @Id      private ObjectId id;
                @Indexed private Integer ssn;
                @DBRef   private List<Account> accounts;
            }
    + You need not use @OneToMany or similar mechanisms because the List of objects tells the mapping framework that 
      you want a one-to-many relationship.
    + When the object is stored in MongoDB, there is a list of DBRefs rather than the Account objects themselves.
    + When it comes to loading collections of DBRefs it is advisable to restrict references held in collection types 
      to a specific MongoDB collection. This allows bulk loading of all references, whereas references pointing to 
      different MongoDB collections need to be resolved one by one.
    + DBRefs can also be resolved lazily.
        - In this case the actual Object or Collection of references is resolved on first access of the property. 
        - Use the lazy attribute of @DBRef to specify this. Required properties that are also defined as lazy 
          loading DBRef and used as constructor arguments are also decorated with the lazy loading proxy making 
          sure to put as little pressure on the database and network as possible.
* Lifecycle Events
    + Spring Data MongoDB publishes some very useful life cycle events – such as:
        - onBeforeConvert
        - onBeforeSave
        - onAfterSave
        - onAfterLoad
        - onAfterConvert
    + To intercept one of the events, we need to register a subclass of AbstractMappingEventListener and override 
      one of the methods here. When the event is dispatched, our listener will be called and domain object passed in.
    + Basic Cascade Save
            // Lets look at this email example:
                @DBRef private EmailAddress emailAddress;
                @Document
                public class EmailAddress {
                    @Id private String id;
                    private String value;
                }
            // We can listen to the onBeforeConvert event which will be called before a domain object goes into the converter.
                public class UserCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {
                    @Autowired private MongoOperations mongoOperations;
                    @Override public void onBeforeConvert(BeforeConvertEvent<Object> event) { 
                        Object source = event.getSource(); 
                        if ((source instanceof User) && (((User) source).getEmailAddress() != null))
                            mongoOperations.save(((User) source).getEmailAddress());
                    }
                }
            // Now we just need to register the listener into MongoConfig.
                @Bean
                public UserCascadeSaveMongoEventListener userCascadingMongoEventListener() {
                    return new UserCascadeSaveMongoEventListener();
                }
    + Generic Cascade Implementation
        // Improving on above basic example by making the cascade more generic.
            // 1. Start by defining a new Custom Annotation
                @Retention(RetentionPolicy.RUNTIME)
                @Target(ElementType.FIELD)
                public @interface CascadeSave {
                    //
                }
            // 2. Create a new Custom Listener to handle these fields generically & not have to cast to any particular object.
                public class CascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {
                    @Autowired private MongoOperations mongoOperations;
                    @Override public void onBeforeConvert(BeforeConvertEvent<Object> event) { 
                        Object source = event.getSource(); 
                        ReflectionUtils.doWithFields(source.getClass(), new CascadeCallback(source, mongoOperations));
                    }
                }
            // 3. Using Springs reflection utility, we're running our callback on all fields that meet our criteria.
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    ReflectionUtils.makeAccessible(field);
                    if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
                        Object fieldValue = field.get(getSource());
                        if (fieldValue != null) {
                            FieldCallback callback = new FieldCallback();
                            ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
                            getMongoOperations().save(fieldValue);
                        }
                    }
                }
            // 4. We're looking for fields that have both the DBRef annotation, as well as CascadeSave.  
            //    Once we find those fields, we save the child entity.
            //    Below in the FieldCallback class you can see we're checking if the child has a @Id annotation.    
                public class FieldCallback implements ReflectionUtils.FieldCallback {
                    private boolean idFound;
                    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                        ReflectionUtils.makeAccessible(field);
                        if (field.isAnnotationPresent(Id.class))
                            idFound = true;
                    }
                    public boolean isIdFound()
                        return idFound;
                }
            // 5. Finally, to make it all work together, we need to update the annotations on our emailAddress field
                @DBRef @CascadeSave private EmailAddress emailAddress;
            // 6. Testing out Cascade
                // We save a User with emailAddress & the save operation cascades to this embedded entity automatically
                User user = new User();
                user.setName("Brendan");
                EmailAddress emailAddress = new EmailAddress();
                emailAddress.setValue("b@gmail.com");
                user.setEmailAddress(emailAddress);
                mongoTemplate.insert(user);
                // And the DB should now show
                {
                    "_id" : ObjectId("55cee9cc0badb9271768c8b9"), "name" : "Brendan", "age" : null,
                    "email" : { "value" : "b@gmail.com" }
                }




/**********************************************************************************************************************
*************************************************      KEYWORDS      **************************************************
***********************************************************************************************************************/
* Supported keywords for query methods
    KEYWORD                          SAMPLE                                                        LOGICAL RESULT
    -----------------------------------------------------------------------------------------------------------------------------
    After                            findByBirthdateAfter(Date date)                               {"birthdate" : {"$gt" : date}}
    GreaterThan                      findByAgeGreaterThan(int age)                                 {"age" : {"$gt" : age}}
    GreaterThanEqual                 findByAgeGreaterThanEqual(int age)                            {"age" : {"$gte" : age}}
    Before                           findByBirthdateBefore(Date date)                              {"birthdate" : {"$lt" : date}}
    LessThan                         findByAgeLessThan(int age)                                    {"age" : {"$lt" : age}}
    LessThanEqual                    findByAgeLessThanEqual(int age)                               {"age" : {"$lte" : age}}
    Between                          findByAgeBetween(int from, int to)                            {"age" : {"$gt" : from, "$lt" : to}} lower / upper bounds ($gt / $gte & $lt / $lte) according to Range
    In                               findByAgeIn(Collection ages)                                  {"age" : {"$in" : [ages…​]}}
    NotIn                            findByAgeNotIn(Collection ages)                               {"age" : {"$nin" : [ages…​]}}
    IsNotNull, NotNull               findByFirstnameNotNull()                                      {"firstname" : {"$ne" : null}}
    IsNull, Null                     findByFirstnameNull()                                         {"firstname" : null}
    Like, StartingWith, EndingWith   findByFirstnameLike(String name)                              {"firstname" : name} (name as regex)
    NotLike, IsNotLike               findByFirstnameNotLike(String name)                           {"firstname" : { "$not" : name }} (name as regex)
    Containing on String             findByFirstnameContaining(String name)                        {"firstname" : name} (name as regex)
    NotContaining on String          findByFirstnameNotContaining(String name)                     {"firstname" : { "$not" : name}} (name as regex)
    Containing on Collection         findByAddressesContaining(Address address)                    {"addresses" : { "$in" : address}}
    NotContaining on Collection      findByAddressesNotContaining(Address address)                 {"addresses" : { "$not" : { "$in" : address}}}
    Regex                            findByFirstnameRegex(String firstname)                        {"firstname" : {"$regex" : firstname }}
    (No keyword)                     findByFirstname(String name)                                  {"firstname" : name}
    Not                              findByFirstnameNot(String name)                               {"firstname" : {"$ne" : name}}
    Near                             findByLocationNear(Point point)                               {"location" : {"$near" : [x,y]}}
    Near                             findByLocationNear(Point point, Distance max)                 {"location" : {"$near" : [x,y], "$maxDistance" : max}}
    Near                             findByLocationNear(Point point, Distance min, Distance max)   {"location" : {"$near" : [x,y], "$minDistance" : min, "$maxDistance" : max}}
    Within                           findByLocationWithin(Circle circle)                           {"location" : {"$geoWithin" : {"$center" : [ [x, y], distance]}}}
    Within                           findByLocationWithin(Box box)                                 {"location" : {"$geoWithin" : {"$box" : [ [x1, y1], x2, y2]}}}
    IsTrue, True                     findByActiveIsTrue()                                          {"active" : true}
    IsFalse, False                   findByActiveIsFalse()                                         {"active" : false}
    Exists                           findByLocationExists(boolean exists)                          {"location" : {"$exists" : exists }}




/**********************************************************************************************************************
*************************************************      EXCEPTIONS      ************************************************
***********************************************************************************************************************/
* 




/**********************************************************************************************************************
**************************************************      EXAMPLES      *************************************************
***********************************************************************************************************************/
* Aggregating Data by Groups w/ Spring Data & MongoDB Aggregation
    + Test Data (domain.json)
        { "_id" : 1, "domainName" : "test1.com", "hosting" : "hostgator.com" }
        { "_id" : 5, "domainName" : "test5.com", "hosting" : "aws.amazon.com" }
        ...
        { "_id" : 9, "domainName" : "test9.com", "hosting" : "cloud.google.com" }
        { "_id" : 100, "domainName" : "test3.com", "hosting" : "aws.amazon.com" }
    + MongoDB Aggregation Example
        // Example to sum the total number of domain hostings
        db.domain.aggregate({
                $match : {_id:{$lt:10}}},{ 
                $group : {_id : "$hosting", total : { $sum : 1 }}},{
                $sort : {total : -1}
        });
        // Output
        { "_id" : "aws.amazon.com", "total" : 2 }
        { "_id" : "hostgator.com", "total" : 1 }
        { "_id" : "cloud.google.com", "total" : 1 }
    + Spring-Data MongoDB equivalent to above 
        - DomainDaoImpl.java
            import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
            import org.springframework.beans.factory.annotation.Autowired;
            import org.springframework.data.domain.Sort;
            import org.springframework.data.mongodb.*;
            import org.springframework.stereotype.Repository;
            import com.mkyong.core.domain.model.Domain;
            import com.mkyong.core.hosting.model.HostingCount;
            import java.util.List;
            @Repository
            public class DomainDaoImpl implements DomainDao {
                @Autowired MongoTemplate mongoTemplate;
                public List<HostingCount> getHostingCount() {
                    Aggregation agg = newAggregation(
                        match(Criteria.where("_id").lt(10)),
                        group("hosting").count().as("total"),
                        project("total").and("hosting").previousOperation(),
                        sort(Sort.Direction.DESC, "total")
                    );  //Convert the aggregation result into a List
                    AggregationResults<HostingCount> groupResults = mongoTemplate.aggregate(agg, Domain.class, HostingCount.class);
                    List<HostingCount> result = groupResults.getMappedResults();
                    return result;
                }
            }
        - HostingCount.java
            package com.mkyong.core.hosting.model;
            public class HostingCount {
                private String hosting;
                private long total;
            }

