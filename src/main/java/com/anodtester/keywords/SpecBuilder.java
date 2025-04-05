package com.anodtester.keywords;

import com.anodtester.globals.ConfigsGlobal;
import com.anodtester.globals.TokenGlobal;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpecBuilder() {
        return new RequestSpecBuilder().
                setBaseUri(ConfigsGlobal.BASE_URI).
                setBasePath(ConfigsGlobal.BASE_PATH).
                addHeader("Authorization", "Bearer " + TokenGlobal.TOKEN).
                setContentType(ContentType.JSON).
                setAccept(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                //addFilter(new RequestLoggingFilter()).
                //addFilter(new ResponseLoggingFilter()).
                log(LogDetail.BODY).
                build();
    }

    public static ResponseSpecification getResponseSpecBuilder() {
        return new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                log(LogDetail.BODY).
                build();
    }

    public static RequestSpecification getRequestNotAuthSpecBuilder() {
        return new RequestSpecBuilder().
                setBaseUri(ConfigsGlobal.BASE_URI).
                setBasePath(ConfigsGlobal.BASE_PATH).
                setContentType(ContentType.JSON).
                setAccept(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                //addFilter(new RequestLoggingFilter()).
                //addFilter(new ResponseLoggingFilter()).
                log(LogDetail.BODY).
                build();
    }
}