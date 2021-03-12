import ResponseModels.BookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBookAPITest {
    @Test
    public void verifyGetBookByID() {
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().queryParam("ID", "1235999")
                .header("Content-Type", "application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println("response: ----" + response.asString());
        Assert.assertEquals
                (response.asString(), "[{\"book_name\":\"Learn Automation\",\"isbn\":\"1235\",\"aisle\":\"999\",\"author\":\"Nil Chandlelr\"}]");
    }

    @Test
    public void verifyGetBookByIDwithDeserialization() {
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().queryParam("ID", "sonal1228")
                .header("Content-Type", "application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract()
                .response();

        BookResponse[] book = response.as(BookResponse[].class);
        Assert.assertEquals(book[0].getAuthor(), "John foe", "Test Failed as Author name is incorrect");
    }


}
