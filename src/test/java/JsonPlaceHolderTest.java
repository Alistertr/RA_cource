import config.TestConfig;
import org.testng.annotations.Test;

import static constants.Constants.Actions.*;
import static io.restassured.RestAssured.given;

public class JsonPlaceHolderTest extends TestConfig {
    @Test
    public void  GET(){
        given().queryParam("postId",1).log().uri().when().
        get(JSON_PLACE_HOLDER_GET_COMMENTS).
        then().spec(responseSpecificationForGet).log().body();
    }

    @Test
    public void PUT(){

        String putBodyJson = "{\n" +
                "      \"id\": 1,\n" +
                "      \"title\": \"foo\",\n" +
                "      \"body\": \"bar\",\n" +
                "      \"userId\": 1\n" +
                "    }";
        given().body(putBodyJson).log().uri()
                .when().put(JSON_PLACE_HOLDER_PUT_POSTS)
                .then().log().body().statusCode(200);
    }

    @Test
    public void DELETE(){
        given().log().uri()
                .when().delete(JSON_PLACE_HOLDER_DELETE_POSTS)
                .then().log().body().statusCode(200);
    }

    @Test
    public void PostWithJson(){
        String putBodyJson = "{\n" +
                "\n" +
                "    \"title\" : foo,\n" +
                "    \"body\" : bar,\n" +
                "    \"userId\" : 1\n" +
                "\n" +
                "}";
        given().body("").log().uri().
                when().post(JSON_PLACE_HOLDER_POST_POSTS).
                then().spec(responseSpecificationForPost).log().body();

    }
    @Test
    public void PostWithXml(){
        // для данного запроса используется спека requestSpecificationUdemyCourseXML со своим урлом
        String postXMLBody ="<Company>\n" +
                "  <Employee>\n" +
                "      <FirstName>Tanmay</FirstName>\n" +
                "      <LastName>Patil</LastName>\n" +
                "      <ContactNo>1234567890</ContactNo>\n" +
                "      <Email>tanmaypatil@xyz.com</Email>\n" +
                "      <Address>\n" +
                "           <City>Bangalore</City>\n" +
                "           <State>Karnataka</State>\n" +
                "           <Zip>560212</Zip>\n" +
                "      </Address>\n" +
                "  </Employee>\n" +
                "</Company>";

        given().spec(requestSpecificationUdemyCourseXML).body(postXMLBody).log().all().
                when().post("").
                then().log().body().statusCode(200);
    }

}
