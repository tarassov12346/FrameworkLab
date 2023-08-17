package rest_api_tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CorrectSearchRestApiMethodTest {

    @DataProvider
    public Object[][] dataProviderMethodForName() {
        return new Object[][]{
                {"Пальто"},
                {"Шорты"},
                {"Товар"}
        };
    }

    @Test(description = "checks the selected items names correspond to search request", dataProvider = "dataProviderMethodForName")
    public void doSelectedItemsNamesCorrespondToSearchRequest(String data) {
        String url = String.format("http://shop.bugred.ru/api/items/search?query=%s", data);
        Response response =
                given()
                        .header("Accept", "application/json")
                        .when()
                        .get(url)
                        .then()
                        .extract()
                        .response();
        int numberOfItemsInResponse = response.jsonPath().getList("result").size();
        if (numberOfItemsInResponse > 0) {
            if (numberOfItemsInResponse < 10) {
                RestAssured.
                        when().get(url).
                        then().assertThat().statusCode(200).
                        and().body("result.name", everyItem(containsStringIgnoringCase(data)));
            } else {
                RestAssured.
                        when().get(url).
                        then().assertThat().statusCode(200).
                        and().body("result.title", everyItem(containsStringIgnoringCase(data)));
            }
        }
    }

    @DataProvider
    public Object[][] dataProviderMethodForColor() {
        return new Object[][]{
                {"Товар", "Синий"},
                {"Товар", "Черный"},
                {"Товар", "Красный"},
        };
    }

    @Test(description = "checks the selected items with names containing queryValue string to be of given color", dataProvider = "dataProviderMethodForColor")
    public void doSelectedItemsCorrespondToFilterConditionsOfColor(String queryValue, String color) {
        String url = "http://shop.bugred.ru/api/items/search?query=" + queryValue + "&color=" + color;
        Response response =
                given()
                        .header("Accept", "application/json")
                        .when()
                        .get(url)
                        .then()
                        .extract()
                        .response();
        int numberOfItemsInResponse = response.jsonPath().getList("result").size();
        if (numberOfItemsInResponse > 0) {
            if (numberOfItemsInResponse < 10) {
                RestAssured.
                        when().get(url).
                        then().assertThat().statusCode(200).
                        and().body("result.color", everyItem(containsStringIgnoringCase(color)));
            } else {
                response.jsonPath().getList("result.last_id").forEach(itemLastId -> {
                    Response responseToGet =
                            given()
                                    .header("Accept", "application/json")
                                    .when()
                                    .get("http://shop.bugred.ru/api/items/get?id= " + Integer.valueOf(itemLastId.toString()))
                                    .then()
                                    .extract()
                                    .response();
                    Assert.assertTrue(responseToGet.jsonPath().getString("result.color").contains(color));
                });
            }
        }
    }

    @DataProvider
    public Object[][] dataProviderMethodForSize() {
        return new Object[][]{
                {"Товар", "42"},
                {"Товар", "30"},
                {"т", "44"},
        };
    }

    @Test(description = "checks the selected items with names containing queryValue string to be of given size", dataProvider = "dataProviderMethodForSize")
    public void doSelectedItemsCorrespondToFilterConditionsOfSize(String queryValue, String size) {
        String url = "http://shop.bugred.ru/api/items/search?query=" + queryValue + "&size=" + size;
        Response response =
                given()
                        .header("Accept", "application/json")
                        .when()
                        .get(url)
                        .then()
                        .extract()
                        .response();
        int numberOfItemsInResponse = response.jsonPath().getList("result").size();
        if (numberOfItemsInResponse > 0) {
            if (numberOfItemsInResponse < 10) {
                RestAssured.
                        when().get(url).
                        then().assertThat().statusCode(200).
                        and().body("result.size", everyItem(containsStringIgnoringCase(size)));
            } else {
                response.jsonPath().getList("result.last_id").forEach(itemLastId -> {
                    Response responseToGet =
                            given()
                                    .header("Accept", "application/json")
                                    .when()
                                    .get("http://shop.bugred.ru/api/items/get?id= " + Integer.valueOf(itemLastId.toString()))
                                    .then()
                                    .extract()
                                    .response();
                    Assert.assertTrue(responseToGet.jsonPath().getString("result.size").contains(size));
                });
            }
        }
    }

    @DataProvider
    public Object[][] dataProviderMethodForPriceRank() {
        return new Object[][]{
                {"Товар", "100", "400"},
                {" ", "100", "800"},
                {"а", "100", "800"},
                {"Платье", "100", "800"},
        };
    }

    @Test(description = "checks the selected items with names containing queryValue string to be of given price rank", dataProvider = "dataProviderMethodForPriceRank")
    public void doSelectedItemsCorrespondToFilterConditionsOfPriceRank(String queryValue, String lowerPrice, String upperPrice) {
        double lowerPriceDoubleValue = Double.parseDouble(lowerPrice);
        double upperPriceDoubleValue = Double.parseDouble(upperPrice);
        List<String> itemsPriceList = new ArrayList<>();
        String url = "http://shop.bugred.ru/api/items/search?query=" + queryValue + "&price_from=" + lowerPrice + "&price_to=" + upperPrice;
        Response response =
                given()
                        .header("Accept", "application/json")
                        .when()
                        .get(url)
                        .then()
                        .extract()
                        .response();
        int numberOfItemsInResponse = response.jsonPath().getList("result").size();
        if (numberOfItemsInResponse > 0) {
            if (numberOfItemsInResponse < 10) {
                response.jsonPath().getList("result.price").forEach(itemPrice -> itemsPriceList.add(itemPrice.toString()));
                itemsPriceList.forEach(itemPrice ->
                        Assert.assertTrue(Double.parseDouble(itemPrice) >= lowerPriceDoubleValue
                                && Double.parseDouble(itemPrice) <= upperPriceDoubleValue));
            } else {
                response.jsonPath().getList("result.last_id").forEach(itemLastId -> {
                    Response responseToSqlQuery =
                            given()
                                    .header("Accept", "application/json")
                                    .when()
                                    .get("http://shop.bugred.ru/api/items/select?sql_query=select * from items where last_id = " + itemLastId.toString() + ";")
                                    .then()
                                    .extract()
                                    .response();
                    responseToSqlQuery.jsonPath().getList("result.price").forEach(itemPrice -> itemsPriceList.add(itemPrice.toString()));
                });
                itemsPriceList.forEach(itemPrice ->
                        Assert.assertTrue(Double.parseDouble(itemPrice) >= lowerPriceDoubleValue
                                && Double.parseDouble(itemPrice) <= upperPriceDoubleValue));
            }
        }
    }
}
