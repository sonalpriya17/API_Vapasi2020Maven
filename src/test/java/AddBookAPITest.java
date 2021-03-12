import RequestModel.AddBookRequest;
import ResponseModels.AddBookResponse;
import ResponseModels.BookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class AddBookAPITest {
    @Test
    public void testAddBook() {
  /*      RestAssured.baseURI = "http://216.10.245.166";

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("Learn Automation");
        addBookRequest.setIsbn("350isbn");
        addBookRequest.setAisle("790asil");
        addBookRequest.setAuthor("Nil Chandlel");

        Response
         = given().log().all().header("Content-Type", "application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response();
        AddBookResponse addBookResponse = response.body().as(AddBookResponse.class);
        System.out.println("addBookResponse.getMsg() :" + addBookResponse.getId());*/

        RestAssured.baseURI = "http://216.10.245.166";

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("Learning API Automation Test");
        addBookRequest.setAuthor("Dileep");
        addBookRequest.setIsbn("9821");
        addBookRequest.setAisle("2341");
        Response responseAddBook = given().header("Content-Type", "application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php")
                .then().log().all()
                .assertThat().statusCode(200).extract().response();
        AddBookResponse addBookResponse = responseAddBook.body().as(AddBookResponse.class);

        //System.out.println("addBookResponse.getMsg() : "+ addBookResponse.getMsg());
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added","AddBook success message is incorrect");

    }

    /*Q:
1. Add a book [POST]
2. get the book added in step 1 [GET]


-----------------------
1. define the BaseURI
2. Hit POST api with following details
- api path
-Body/Payload [Object classes / Request Classe]
3. Store the Response [as String/ as Response Class]
4. Parse the id from the response saved in Step 3 : (Store it in a variable)
-----------ID------------
5. Hit GET Book Api
- query param [ID]
6. Store the Response
7. Assert on 'Author Name'*/

    @Test
    public void getBookwithID() {
        //Add a book
       RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("Learn Automation using RestAssured");
        addBookRequest.setIsbn("22334455663");
        addBookRequest.setAisle("11223344553");  //can u see my screen?? I can hear everything
        addBookRequest.setAuthor("Archana Shrisat");

        Response response = given().log().all().header("Content-Type", "application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response();
        System.out.println("response:----"+ response.asString());

        AddBookResponse addBookResponse = response.body().as(AddBookResponse.class);
        System.out.println("addBookResponse.getId() :" + addBookResponse.getId());

        //get Book with the above obtained id
        Response responseForGetBook = given().queryParam("ID", addBookResponse.getId())
                .header("Content-Type", "application/json")
                .when()
                .get("/Library/GetBook.php")
                .then()
                .statusCode(200)
                .extract()
                .response();

        BookResponse[] book = responseForGetBook.as(BookResponse[].class);
        System.out.println(" book[0].getAuthor() : " + book[0].getAuthor());
        Assert.assertEquals(book[0].getAuthor(),"Sonal Priya","Author Name for the provided BookID is incorrect");


    }
}
