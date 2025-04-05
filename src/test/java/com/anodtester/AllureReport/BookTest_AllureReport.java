package com.anodtester.AllureReport;

import com.anodtester.common.BaseTest;
import com.anodtester.globals.EndPointGlobal;
import com.anodtester.keywords.ApiKeyword;
import com.anodtester.utils.LogUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class BookTest_AllureReport extends BaseTest {

    @Test
    @Severity(SeverityLevel.MINOR)
    @Epic("Regression Test")
    @Feature("Book Test")
    @Story("Test Get Gook")
    @Description("Get All Books")
    @Link("https://jira.com/anhtester/apitest/books/12")
    public void testGetBooks(){
        Response response = ApiKeyword.get(EndPointGlobal.EP_BOOKS);
        ApiKeyword.verifyStatusCode(response, 200);
        LogUtils.info(ApiKeyword.getResponseKeyValue(response, "response[0].name"));
    }
}
