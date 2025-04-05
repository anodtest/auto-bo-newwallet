package com.anodtester.UploadFile;

import com.anodtester.common.BaseTest;
import com.anodtester.globals.TokenGlobal;
import com.anodtester.helpers.SystemHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;

public class DemoUploadFile extends BaseTest {
    @Test
    public void testUploadFile() {
        File file = new File(SystemHelper.getCurrentDir() + "src/test/resources/testdata/sen.jpg");

        RestAssured.baseURI = "https://api.anhtester.com/api";
        String endpoint = "/image";

        given().header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .multiPart("image", file) //Key "image" phụ thuộc vào hệ thống cung cấp thông qua API document
                .contentType(ContentType.MULTIPART)
                .when().post(endpoint)
                .then().log().all()
                .statusCode(200)
                .body("response", hasKey("path"))
                .body("response", hasKey("id"));

    }
}