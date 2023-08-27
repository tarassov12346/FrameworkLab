package api.rest_api_tests;

import api.Specification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ItemOperationsApiTest {

    private final static String URL = "http://shop.bugred.ru/api/items/";

    private String id ;

    @Test(priority = 1, description = "Create item 'кардиган'")
    public void createItemTest() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());

        Map<String, String> item = new HashMap<>();
        item.put("name", "кардиган");
        item.put("section", "Test");
        item.put("description", "Кардиган или водолазка вот в чем вопрос!");
        item.put("color", "ORANGE");

        Response response = given()
                .body(item)
                .when()
                .post("create/")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        id = jsonPath.get("result.id");
        String status = jsonPath.get("status");

        Assert.assertEquals(status, "ok");
    }

    @Test(priority = 2, description = "Update 'кардиган' price")
    public void updateItemCostTest(){
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());

        Map<String, String> item = new HashMap<>();
        item.put("id", id);
        item.put("name", "кардиган");
        item.put("section", "Test");
        item.put("description", "Кардиган или водолазка вот в чем вопрос!");
        item.put("color", "ORANGE");
        item.put("price", "1033");

        Response response = given()
                .body(item)
                .when()
                .post("update/")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String result = jsonPath.get("result");

        Assert.assertEquals(result, "Товар обновлен!");
    }

    @Test(priority = 3, description = "Delete 'кардиган' from db")
    public void deleteItemTest(){

        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());
        Map<String, String> item = new HashMap<>();
        item.put("id", id);

        Response response = given()
                .body(item)
                .when()
                .delete("delete/")
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String result = jsonPath.get("result");
        String status = jsonPath.get("status");

        Assert.assertEquals(status, "ok");
        Assert.assertTrue(result.contains(id));
    }

}

