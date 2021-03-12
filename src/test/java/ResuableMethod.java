import io.restassured.path.json.JsonPath;

public class ResuableMethod {
    public static JsonPath rawToJson(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }
    public static String uniqueISBN(){
        int count = 1;
        String uniqueIsbn = "isbn_"+count++;
        return uniqueIsbn;
    }
}
