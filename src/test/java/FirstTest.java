import config.TestConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static constants.Constants.Actions.REQRES_GET_PEOPLE;

import static constants.Constants.Actions.REQRES_GET_UNKNOWN_USERS;
import static constants.Constants.Path.REQRES_PATH;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class FirstTest extends TestConfig {

    @Test
    public void myFirstTest(){
        given().log().uri()
                .when()
                .get(REQRES_GET_PEOPLE + "2")
                .then().log().body()
                .statusCode(201);
    }
    @Test
    public void getSomeFieldInResponseAssertion(){
        given().spec(requestSpecificationForSwapiTests).log().uri()
                .when().get(REQRES_PATH + REQRES_GET_PEOPLE + "1").
                then().body("data.first_name",equalTo("George")).log();
                

    }

    @Test
    public void getListUnknownUsers(){
        given().spec(requestSpecificationForSwapiTests).log().uri()
                .when().get(REQRES_PATH + REQRES_GET_UNKNOWN_USERS).
                then()
                .body("page", equalTo(1))
                .body("data[0].name", equalTo("cerulean"))
                .log().all();
    }

    @Test
    public void getAllDataFromRequest(){
        Response response =
                given().spec(requestSpecificationForSwapiTests).log().uri().
                        when().get(REQRES_PATH).
                        then().extract().response();

        String responseAsString = response.asString();
        System.out.println(responseAsString);
    }

    @Test
    public void getCookieFromResponse(){
        Response response =
                given().spec(requestSpecificationForSwapiTests).log().uri().
                        when().get(REQRES_PATH).
                        then().extract().response();
        Map<String, String> allCookie = response.getCookies();
        System.out.println(allCookie);

        String someCookie = response.getCookie("__cfduid");
        System.out.println(someCookie);
    }

    @Test
    public void getHedersfromResponse(){
        Response response =
                given().spec(requestSpecificationForSwapiTests).log().uri().
                        when().get(REQRES_PATH).
                        then().extract().response();
        Headers headers = response.getHeaders();
        System.out.println(headers);
        String contentType = response.getContentType();
        System.out.println("Content Type ---> " + contentType);
    }
    @Test
    public void validateXMLScheme(){
        given().log().uri()
                .when().get("https://maps.googleapis.com/maps/api/place/findplacefromtext/xml?parameters%0A&key=AIzaSyBItdPsthjw1l5fl58tAx_lO2IMM9cAtr0&input=New York&inputtype=textquery&fields=business_status,formatted_address,geometry,icon,name,permanently_closed,photos,place_id,plus_code,types&language=ru")
                .then().body(matchesXsdInClasspath("xmlSchema.xsd")).log().body();
    }

    @Test
    public void validateJsonExample(){
        given().log().uri()
                .when().get("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?parameters%0A&key=AIzaSyBItdPsthjw1l5fl58tAx_lO2IMM9cAtr0&input=New York&inputtype=textquery&fields=business_status,formatted_address,geometry,icon,name,permanently_closed,photos,place_id,plus_code,types&language=ru")
                .then().body(matchesJsonSchemaInClasspath("jsonSchema.json")).log().body();

    }

    @Test
    public void getMapOfElementsWithSomekey(){

        // из массива data достаем объект, из объекта достаем имя
        Response response = given().spec(requestSpecificationForSwapiTests).log().uri()
                .when().get(REQRES_PATH + REQRES_GET_UNKNOWN_USERS);
        System.out.println("Response -> " + response.asString());

        //В маре сохранятся все параметры, которые относятся к искомому имени

        Map<String, ?> someObject = response.path("data.find {it.name = 'cerulean'}");

        System.out.println("someObject--> " + someObject);
    }

    @Test
    public void getSingleElementWithSomeKey(){
        Response response = given().spec(requestSpecificationForSwapiTests).log().uri()
                .when().get(REQRES_PATH + REQRES_GET_UNKNOWN_USERS);
        String color = response.path("data.find {it.name = 'cerulean'}.color");

        System.out.println("color-->"  + color);
    }

    @Test
    public void getAllElementsWithSomeKey(){
        Response response = given().spec(requestSpecificationForSwapiTests).log().uri()
                .when().get(REQRES_PATH + REQRES_GET_UNKNOWN_USERS);

        //проходим по массиву data и из каждого элемента где есть name получаем color
        List fields = response.path("data.findAll {it.name}.color");

        System.out.println(fields);
    }
}
