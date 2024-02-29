/**
 *  UTIL FOR RETURNING A SINGLE OBJECT FROM STREAM
 **/
@Log4j2 public class CollectorUtil {
    public static <T> Collector<T, ?, T> to toSingleton() {
        return Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
                if(list.size() > 1)
                    log.warn("[UTIL.COLLECT] Util Stream Collection found multiple records.  Total={}", list.size());
                return list.get(0);
            }
        );
    }
}


// Example Name Class
@Data @JsonInclude(JsonInclude.Include.NON_NULL)
public class Name {
    private String prefix;
    private String first;
    private String middle;
    private String last;
    private String suffix;

    @JsonIgnore public string getFullName() {
        return Stream.of(prefix, first, middle, last, suffix) // Stream of strings that make up Full Name
            .filter(Objects::nonNull)                         // Filter out nulls
            .collect(Collectors.joining(" "));                // Join non-null name strings
    }
}




/**
 *  ADDITIONAL EXAMPLES
 **/
CardMember currentMember = cardProductsResponse.getMembers()
                                .stream()
                                .filter(CardMember::getIsLoggedInUser)
                                .collect(CollectorUtil.toSingleton());

List<String> products = currentMember.getProducts()
                                .stream()
                                .filter(CardProduct::getType)
                                .toList();

List<String> products = currentMember.getProducts()
                                .stream()
                                .map(CardProduct::getType)
                                .toList();

CardMember currentMember = cardProductsResResponse.getFamilyMembers()
                                .stream()
                                .filter(CardMember::getIsLoggedInUser)
                                .collect(CollectorUtil.toSingleton());
