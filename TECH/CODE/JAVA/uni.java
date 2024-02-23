// Print RAW response from UniRest Request
HttpResponse<String> response = Unirest
                .post("http://myserver.com/file")
                .header("Content-Type", "application/json")
                .body("{some JSON body}")
                .asString();
System.out.println(response.getBody());





AuthSession authSession =  Unirest.post(loginUri)
                .header("Content-Type", "application/json")
                .proxy(proxyHost, proxyPort)
                .body(AuthLogin.builder().username(user).password(pass).build())
                .asObject(AuthSession.class)
                .ifFailure(e-> {
                    log.info(e);
                    log.info("Error Status = {}", e.getStatus());
                    log.info("Error Status Message = {}", e.getStatusText());
                    log.info("Error Body = {}", e.getBody());
                })
                .getBody();


HttpResponse<String> response = Unirest
                .get(authorizeUri)
                .proxy(proxyHost, proxyPort)
                .queryString("sessionToken", authSession.getSessionToken())
                .queryString("response_type", "code")
                .queryString("code_challenge_method", "S256")
                .queryString("code_challenge", "8bO1j4brcIYY9rKzR-xiThXxL02dZaJrdsvJhXWYSLE")
                .queryString("client_id", "tWkJWsIBpzqDeU1ziYSx")
                .queryString("state", "1234")
                .queryString("redirect_uri", "com.oktapreview.a-mycigna:/callback")
                .asString();
String locationCode = response.getHeaders().get("location").get(0).split("code=")[1].split("&")[0];


AuthToken authToken = Unirest.post(authTokenUri)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field(CLIENT_ID, "tWkJWsIBpzqDeU1ziYSx")
                .field(GRANT_TYPE, "authorization_code")
                .field("redirect_uri", "com.oktapreview.a-mycigna:/callback")
                .field("code", locationCode)
                .field("code_verifier", "9G5C9W_eqE4YTKwAyMivxYgMo6jQbqvXjoDa64-nIuA")
                .proxy(proxyHost, proxyPort)
                .asObject(AuthToken.class)
                .getBody();