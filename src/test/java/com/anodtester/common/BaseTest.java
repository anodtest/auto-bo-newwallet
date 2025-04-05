package com.anodtester.common;

import com.anodtester.globals.EndPointGlobal;
import com.anodtester.globals.TokenGlobal;
import com.anodtester.keywords.ApiKeyword;
import com.anodtester.listeners.TestListener;
import com.anodtester.model.LoginPOJO;
import com.anodtester.model.data.LoginPOJO_Builder;
import com.anodtester.reports.AllureManager;
import com.anodtester.utils.LogUtils;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeTest
    public void loginUser() {
        LoginPOJO loginPOJO = LoginPOJO_Builder.getDataLogin();

        Gson gson = new Gson();

        Response response = ApiKeyword.postNotAuth(EndPointGlobal.EP_LOGIN, gson.toJson(loginPOJO));

        response.then().statusCode(200);

        TokenGlobal.TOKEN = response.getBody().path("token");
        LogUtils.info("Token Global: " + TokenGlobal.TOKEN);
        AllureManager.saveTextLog("Token Global: " + TokenGlobal.TOKEN);
    }
}