package pages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;


public class APIs {
//REST Assured, which is a Java library for testing RESTful APIs

    public static Response restGetCall(String url) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        response = request.get();

        return response;
    }

    // Overloaded method to handle GET request with query parameters
    public static Response restGetCall(String url, String paramName, String paramValue) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.queryParam(paramName, paramValue);
        response = request.get();

        return response;
    }

    public static Response restPostCall(String url, String requestBody) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        response = request.body(requestBody).contentType(ContentType.JSON).post();

        return response;
    }

    public static Response restPutCall(String url, String requestBody, String authorization) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Accept", "application/json");
        request.header("Authorization", authorization);
        response = request.body(requestBody).contentType(ContentType.JSON).put();

        return response;
    }

    public static Response restDeleteCall(String url, String authorization) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authorization);
        response = request.delete();

        return response;
    }

    public static Response restPatchCall(String url, String requestBody, String authorization) {
        Response response = null;
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authorization);
        response = request.body(requestBody).contentType(ContentType.JSON).patch();

        return response;
    }

}
