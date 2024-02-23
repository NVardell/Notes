// Class Example
//
@AllArgsConstructor(staticName="of")
@Data @Builder public Class Name {
    String firstName;
    String lastName;
}

// Usage
Name name = Name.of("firstName", "lastName");


