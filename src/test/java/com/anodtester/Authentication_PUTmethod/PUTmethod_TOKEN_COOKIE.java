package com.anodtester.Authentication_PUTmethod;

import com.anodtester.common.VerifyDataUserBody;
import com.anodtester.model.LoginPOJO;
import com.anodtester.model.RegisterUserPOJO;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PUTmethod_TOKEN_COOKIE {

    //Khai báo biến toàn tục TOKEN để lưu trữ từ hàm Login
    String TOKEN = "";

    @BeforeMethod
    public void loginUser() {

        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        LoginPOJO loginPOJO = new LoginPOJO("anod", "123456");

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://ticket.ramanaz.net/backoffice/api/v1/invoice")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/searching");
        response.prettyPrint();

        response.then().statusCode(200);

        //Lưu giá trị token vào biến TOKEN nhé
        TOKEN = response.getBody().path("token");
        System.out.println(TOKEN);
    }

    @Test
    public void testEditUser_NoAuth() {

        //Chuẩn bị data
        RegisterUserPOJO registerUserPOJO = new RegisterUserPOJO();
        registerUserPOJO.setUsername("myduyen7");
        registerUserPOJO.setPassword("Demo@123");
        registerUserPOJO.setFirstName("Lê Thị");
        registerUserPOJO.setLastName("Mỹ Duyên");
        registerUserPOJO.setEmail("myduyen7@email.com");
        registerUserPOJO.setPhone("0123456789");
        registerUserPOJO.setUserStatus(1);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://ticket.ramanaz.net/backoffice/api/v1/invoice")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(gson.toJson(registerUserPOJO));

        Response response = request.when().put("/user/3");

        System.out.println(response.getStatusCode());
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");

        VerifyDataUserBody.verifyDataBodyUser(response, registerUserPOJO);

    }

}
