import static com.baeldung.assertj.custom.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.guava.api.Assertions.assertThat;
import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import static org.xmlunit.assertj.XmlAssert.assertThat;

assertThat(selectAlbumQueryResult.getCoverArt().getFrontCoverArtUrl()).isEqualTo("http://fakeurl-0");
assertThat(selectAlbumQueryResult.getCoverArt().getBackCoverArtUrl()).isEqualTo("http://fakeurl-1");
assertThat(selectAlbumQueryResult.getCoverArt().getFrontCoverArtUrl()).isEqualTo("http://fakeurl-2");
assertThat(selectAlbumQueryResult.getCoverArt().getBackCoverArtUrl()).isEqualTo("http://fakeurl-3");
assertThat(message.getContent().toString()).isEqualToNormalizingNewlines("<html>test title, http://127.0.0.1:8080, john</html>\n");
assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/reverseString/HelloWorld", String.class)).isEqualTo("dlroWolleH");
assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/greeter/BaeldungUser", String.class)).isEqualTo("Hello BaeldungUser, and welcome to Spring Cloud Function!!!");
assertThat(result).hasXPath("//*[contains(@customer, 'false')]");
assertThat(result).hasXPath("//*[contains(@customer, 'false')]");
assertThat(ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request)).isNotNull();
assertThat(resp3.getHeader("Location")).isEqualTo("http://localhost:8080/invalidSession.html");
assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/", String.class)).isNotEmpty();
assertThat(resp4.getHeader("Location")).isEqualTo("http://localhost:8080/sessionExpired.html");
assertThat(Input.fromFile(new File(classLoader.getResource("teachers.xml").getFile())), hasXPath("//teachers"));
assertThat(Input.fromFile(new File(classLoader.getResource("teachers.xml").getFile())), hasXPath("//teacher"));
assertThat(Input.fromFile(new File(classLoader.getResource("teachers.xml").getFile())), hasXPath("//subject"));
assertThat(Input.fromFile(new File(classLoader.getResource("teachers.xml").getFile())), hasXPath("//@department"));
assertThat(Input.fromFile(new File(classLoader.getResource("teachers.xml").getFile())), not(hasXPath("//sujet")));
assertThat(nestedInstance.nestedPublicMethod()).isTrue();
assertThat(from(json).getList("publications").size()).isEqualTo(author.getItems().size());
assertThat(api.publicHello()).isEqualTo("Hello Public");
assertThat(api.publicHello()).isEqualTo(HELLO_PUBLIC);
assertThat(from(result).getList("publications")).isNotNull();
Assert.assertThat(accumulator.get(0), is("Call to getInfoFromService"));
Assert.assertThat(accumulator.get(1), is("Method called successfully: getInfoFromService"));
Assert.assertThat(accumulator.size(), is(2));
Assert.assertThat(actualBakedGoodsMap, IsMapContaining.hasValue(equalTo(mUtil.buildInnerMap(batterList))));
Assert.assertThat(countOfEmployees, CoreMatchers.is(1));
Assert.assertThat(decrementedAddress.toString(), equalTo("/127.0.0.4"));
Assert.assertThat(filteredList.size(), Matchers.is(nameFilter.size()));
Assert.assertThat(filteredList.size(), Matchers.is(nameFilterSet.size()));
Assert.assertThat(hashCode.toString(), equalTo("495be649"));
Assert.assertThat(hashmap.keySet(), Matchers.containsInAnyOrder(1, 2, 3));
Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
Assert.assertThat(jsonProperties.getTopics(), Matchers.is(Arrays.asList("spring", "boot")));
Assert.assertThat(profileCache.size(), equalTo(1));
Assert.assertThat(results.size(), equalTo(3));
Assert.assertThat(reverseSortedHumans.get(0), equalTo(new Human("Sarah", 10)));
Assert.assertThat(reverseSortedLetters.get(0), equalTo("C"));
Assert.assertThat(sessionCache.size(), equalTo(1));
Assert.assertThat(sortedHumans.get(0), equalTo(new Human("Jack", 12)));
Assert.assertThat(sortedLetters.get(0), equalTo("A"));
Assert.assertThat(testUrl, is(encodedURL));
Assert.assertThat(testUrlWithPath, is(encodedURL));
Assert.assertThat(total, Matchers.equalTo(result));
Assert.assertThat(treemap.keySet(), Matchers.contains(1, 2, 3));
Assert.assertThat(url.getHost(), is("www.baeldung.com"));
Assert.assertThat(url.getProtocol(), is("http"));
Assert.assertThat(url.getQuery(), is("key1=value+1&key2=value%40%21%242&key3=value%253"));
Assert.assertThat(user.toString(), equalTo("Administrator{id=12, name=John Doe, age=25}"));
Assert.assertThat(user.toString(), equalTo("User{id=12, name=John Doe, age=25}"));
Assert.assertThat(users, Matchers.containsInAnyOrder(USER2, USER3));
Assert.assertThat(users.join(Joiner.on("; ")),
Assert.assertThat(users.size(), equalTo(2));
Assert.assertThat(users.size(), equalTo(3));
Assert.assertThat(users.size(), equalTo(4));
Assertions.assertThat(actualCar).isEqualTo(fordMustang);
Assertions.assertThat(address.getHouseNumber()).isEqualTo(23);
Assertions.assertThat(address.getId()).isEqualTo(2);
Assertions.assertThat(addresses).containsExactlyElementsOf(this.expectedAddresses);
Assertions.assertThat(car.getClass().getInterfaces()[0].isSealed()).isEqualTo(true);
Assertions.assertThat(car.getClass().getInterfaces()[0].permittedSubclasses())
Assertions.assertThat(car.getClass().getSuperclass().isSealed()).isEqualTo(true);
Assertions.assertThat(car.getClass().getSuperclass().permittedSubclasses())
Assertions.assertThat(car.getClass().isSealed()).isEqualTo(false);
Assertions.assertThat(cars).containsExactlyElementsOf(FastList.newListWith("Volkswagen", "Toyota", "Mercedes"));
Assertions.assertThat(getPropertyTraditionalWay(car)).isEqualTo(4);
Assertions.assertThat(getPropertyTraditionalWay(car)).isEqualTo(5);
Assertions.assertThat(getPropertyTraditionalWay(truck)).isEqualTo(16000);
Assertions.assertThat(getPropertyTraditionalWay(truck)).isEqualTo(19000);
Assertions.assertThat(getPropertyViaPatternMatching(car)).isEqualTo(4);
Assertions.assertThat(getPropertyViaPatternMatching(car)).isEqualTo(5);
Assertions.assertThat(getPropertyViaPatternMatching(truck)).isEqualTo(16000);
Assertions.assertThat(getPropertyViaPatternMatching(truck)).isEqualTo(19000);
Assertions.assertThat(greaterThanThirty).containsExactly(31, 38, 41);
Assertions.assertThat(independentMatcher.matches()).isFalse();
Assertions.assertThat(independentMatcher.matches()).isTrue();
Assertions.assertThat(invColorMap).hasSameSizeAs(colorMap).containsKeys(this.colorMap.values().toArray(new String[size])).containsValues(this.colorMap.keySet().toArray(new String[size]));
Assertions.assertThat(lastNames).containsAll(Lists.mutable.with("Hopkins", "Adams", "Rodriguez"));
Assertions.assertThat(lastNames).containsExactly("Hopkins", "Adams");
Assertions.assertThat(leftOptional).isEmpty();
Assertions.assertThat(notGreaterThanThirty).containsExactlyElementsOf(this.expectedList);
Assertions.assertThat(pairs).containsExactlyElementsOf(this.expectedPairs);
Assertions.assertThat(person.getId()).isEqualTo(2);
Assertions.assertThat(person.getName()).isEqualTo("devender");
Assertions.assertThat(queue.capacity()).isEqualTo(16);
Assertions.assertThat(queue.offer(i)).isTrue();
Assertions.assertThat(queue.offer(queue.capacity())).isFalse();
Assertions.assertThat(queue.offer(queue.capacity())).isTrue();
Assertions.assertThat(result).isEmpty();
Assertions.assertThat(result).isEqualTo(41);
Assertions.assertThat(result).isEqualTo(Direction.EAST);
Assertions.assertThat(result).isEqualTo(Month.JANUARY);
Assertions.assertThat(result).isEqualTo(Optional.of(Month.JANUARY));
Assertions.assertThat(result).isEqualTo(Weekday.MONDAY);
Assertions.assertThat(result).isNull();
Assertions.assertThat(rightOptional).hasValue("Okay");
Assertions.assertThat(session.contains(address)).isFalse();
Assertions.assertThat(session.contains(address)).isTrue();
Assertions.assertThat(session.contains(person)).isFalse();
Assertions.assertThat(session.contains(person)).isTrue();
Assertions.assertThat(smallerThanThirty).containsExactly(1, 5, 8, 17, 23);
Assertions.assertThat(StartupApplicationListenerExample.counter).isEqualTo(1);
Assertions.assertThat(text).isNotEmpty();
Assertions.assertThat(truck.getClass().getInterfaces()[0].isSealed()).isEqualTo(true);
Assertions.assertThat(truck.getClass().getInterfaces()[0].permittedSubclasses())
Assertions.assertThat(truck.getClass().getSuperclass().isSealed()).isEqualTo(true);
Assertions.assertThat(truck.getClass().getSuperclass().permittedSubclasses())
Assertions.assertThat(truck.getClass().isSealed()).isEqualTo(false);
Assertions.assertThat(urlMatcher.group(1)).isEqualTo("/Ending-path");
Assertions.assertThat(urlMatcher.matches()).isFalse();
Assertions.assertThat(urlMatcher.matches()).isTrue();
Assertions.assertThat(version).isEqualTo(expectedVersion);
Assertions.assertThatExceptionOfType(WithdrawLimitException.class)
Assertions.assertThatThrownBy(() -> urlMatcher.group(1)).isInstanceOf(IndexOutOfBoundsException.class);
MatcherAssert.assertThat(ACTUAL_JSON, json()
MatcherAssert.assertThat(ACTUAL_JSON, matcher);
MatcherAssert.assertThat(container.getId(), is(not(null)));
MatcherAssert.assertThat(containerResponse.getId(), Is.is(container.getId()));
MatcherAssert.assertThat(first, Matchers.containsInAnyOrder(second.toArray()));
MatcherAssert.assertThat(imageResponse.getId(), Is.is(image.getId()));
MatcherAssert.assertThat(namedVolume.getName(), is(not(null)));
MatcherAssert.assertThat(network.getName(), is(networkName));
MatcherAssert.assertThat(powerSet, IsCollectionWithSize.hasSize((1 << set.size())));
MatcherAssert.assertThat(powerSet, Matchers.containsInAnyOrder(
MatcherAssert.assertThat(unnamedVolume.getName(), is(not(null)));

