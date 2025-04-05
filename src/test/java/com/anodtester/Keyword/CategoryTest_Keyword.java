package com.anodtester.Keyword;

import com.anodtester.common.BaseTest;
import com.anodtester.globals.EndPointGlobal;
import com.anodtester.helpers.JsonHelper;
import com.anodtester.keywords.ApiKeyword;
import com.anodtester.listeners.TestListener;
import com.anodtester.utils.LogUtils;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class CategoryTest_Keyword extends BaseTest {

    int CATEGORY_ID;
    String CATEGORY_NAME;

    @Test(priority = 1)
    public void testAddNewCategory() {
        String dataFile = "src/test/resources/testdata/CreateCategory.json";

        Faker faker = new Faker(new Locale("vi"));
        CATEGORY_NAME = faker.job().title();
        System.out.println("CATEGORY_NAME: " + CATEGORY_NAME);

        JsonHelper.updateValueJsonFile(dataFile, "name", CATEGORY_NAME);

        Response response = ApiKeyword.post(EndPointGlobal.EP_CATEGORY, dataFile);

        //response.then().statusCode(200);
        ApiKeyword.verifyStatusCode(response, 200);

        CATEGORY_ID = Integer.parseInt(ApiKeyword.getResponseKeyValue(response, "response.id"));
        CATEGORY_NAME = ApiKeyword.getResponseKeyValue(response, "response.name");

        LogUtils.info("CATEGORY_ID: " + CATEGORY_ID);
        LogUtils.info("CATEGORY_NAME: " + CATEGORY_NAME);

    }

    @Test(priority = 2)
    public void getCategoryById() {

//        RequestSpecification request = given();
//        request.baseUri("https://api.anhtester.com/api")
//                .accept(ContentType.JSON)
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        LogUtils.info("CATEGORY_ID: " + CATEGORY_ID);
        Response response = ApiKeyword.get(EndPointGlobal.EP_CATEGORY + "/" + CATEGORY_ID);

        response.then().statusCode(200);

        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response, "response.name"), CATEGORY_NAME, "The Category Name not match.");

    }
}