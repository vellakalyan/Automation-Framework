package pages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;


public class APIs {

    public static Response restGetCall(String url) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        response = request.get();

        return response;
    }

    public Response restPostCall(String url, String requestBody) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        response = request.body(requestBody).contentType(ContentType.JSON).post();

        return response;
    }

    public Response restPutCall(String url, String requestBody) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        response = request.body(requestBody).contentType(ContentType.JSON).put();

        return response;
    }

    public Response restDeleteCall(String url) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        response = request.delete();

        return response;
    }

    public Response restPatchCall(String url, String requestBody) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        response = request.body(requestBody).contentType(ContentType.JSON).patch();

        return response;
    }

}
