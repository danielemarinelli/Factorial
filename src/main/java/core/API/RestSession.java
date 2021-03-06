package core.API;

import core.API.APIMethod;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.net.MalformedURLException;
import java.net.URL;


public class RestSession {

    public Response sendRequest(APIMethod apiMethod) throws Exception {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(ContentType.JSON);
        return sendSpec(apiMethod, reqBuilder);
    }

    public Response sendRequest(APIMethod apiMethod, Object payload) throws Exception {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(ContentType.JSON);
        reqBuilder.setBody(payload,ObjectMapperType.GSON);
        return sendSpec(apiMethod, reqBuilder);
    }

    private Response sendSpec(APIMethod methodPath, RequestSpecBuilder builder) throws MalformedURLException {
        URL requestUrl = new URL(methodPath.getHostUrl() + "/" + methodPath.getRestMethodPath());
        Response response = null;
        builder.setBaseUri(methodPath.getHostUrl());
        builder.setBasePath(methodPath.getRestMethodPath());
        RequestSpecification requestSpecification = builder.build();
        requestSpecification.log().all(true);
        ResponseSpecification spec = RestAssured.given().urlEncodingEnabled(false).spec(requestSpecification).response();

        switch (methodPath.getRestHttpMethodType()) {
            case GET:
                response = spec.when().get(requestUrl);
                break;
            case POST:
                response = spec.when().post(String.valueOf(requestUrl));
                break;
            /*case PUT:
                response = spec.when().put(requestUrl);
                break;
            case PATCH:
                response = spec.when().patch(requestUrl);
                break;
            case DELETE:
                response = spec.when().delete(requestUrl);
                break;
            case HEAD:
                response = spec.when().head(requestUrl);
                break;*/
        }
        response.then().log().all(true);
        return response;
    }


}
