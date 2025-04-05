package com.anodtester.Keyword;

import com.anodtester.common.BaseTest;
import com.anodtester.globals.EndPointGlobal;
import com.anodtester.keywords.ApiKeyword;
import com.anodtester.utils.LogUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class BookTest_Keyword extends BaseTest {

    @Test
    public void testGetBooks(){
        Response response = ApiKeyword.get(EndPointGlobal.EP_BOOKS);
        ApiKeyword.verifyStatusCode(response, 200);
        LogUtils.info(ApiKeyword.getResponseKeyValue(response, "response[0].name"));
    }
}
