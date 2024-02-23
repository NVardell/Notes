// Error Handling
// The HttpResponse object has a few handler methods that can be chained to deal with success and failure:

//      ifSuccess(Consumer<HttpResponse<T>> response) will be called if the response was a 200-series response and any body processing (like json or Object was successful.
//      ifFailure(Consumer<HttpResponse> response will be called if the status was 400+ or body processing failed.

// Putting them together might look like this:
Unirest.get("http://somewhere")
        .asJson()
        .ifSuccess(response -> someSuccessMethod(response))
        .ifFailure(response -> {
            log.error("Oh No! Status" + response.getStatus());
            response.getParsingError().ifPresent(e -> {
                log.error("Parsing Exception: ", e);
                log.error("Original body: " + e.getOriginalBody());
            });
        });