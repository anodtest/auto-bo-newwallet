package com.anodtester.Authentication_PUTmethod;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileWriter;
import java.io.IOException;

public class ApiBO111_SellTicketCreate {
    public static void main(String[] args) {
        // BaseTest
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbm9kIiwicm9sZSI6IkFETUlOIiwiaXAiOiIxNjYuODguMTIwLjE0NSIsIngtdG9rZW4iOiJiNjdjYjgzOThiODI2NWZhMzI1YWEwZDg0MzIxZTM5NCIsImV4cCI6MTczNzgyMTc2NSwidXNlcklkIjoxNSwiYnJhbmNoIjoicmFtYSIsImlhdCI6MTczNzc4NTc2NSwidXNlcm5hbWUiOiJhbm9kIn0.2XUgIcyLEqVIyXZx54yLOTRDrpngeIqweaFNuFGHYT6g2-OVqFcNLrCP_5eKidU3y5gGBhowW_lVXkIJ9jyshg";

        String urlPostWithdraw = "https://ticket.ramanaz.net/backoffice/api/v1/bankaccount/withdraw";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Thực hiện POST Request tới Bank Account Withdraw
            HttpPost postRequestWithdraw = new HttpPost(urlPostWithdraw);
            postRequestWithdraw.setHeader("Authorization", "Bearer " + token);
            postRequestWithdraw.setHeader("Content-Type", "application/json");

            // Payload JSON cho POST request
            String jsonBodyWithdraw = "{\n" +
                    "  \"accountNumber\": \"987654321\",\n" +
                    "  \"amount\": 500.75,\n" +
                    "  \"currency\": \"USD\",\n" +
                    "  \"description\": \"Test withdraw\",\n" +
                    "  \"transactionDate\": \"2025-01-25T16:00:00\",\n" +
                    "  \"transactionType\": \"WITHDRAW\"\n" +
                    "}";

            StringEntity entityWithdraw = new StringEntity(jsonBodyWithdraw);
            postRequestWithdraw.setEntity(entityWithdraw);

            HttpResponse postResponseWithdraw = client.execute(postRequestWithdraw);
            int postStatusCodeWithdraw = postResponseWithdraw.getStatusLine().getStatusCode();

            // In ra Status Code cho POST Bank Account Withdraw
            System.out.println("Bank Account Withdraw Post: " + postStatusCodeWithdraw);

            if (postStatusCodeWithdraw == 200) {
                System.out.println("Bank Account Withdraw Post: 200 OK");

                // Lấy nội dung JSON từ Response
                String jsonResponseWithdraw = EntityUtils.toString(postResponseWithdraw.getEntity());
                System.out.println("Response JSON (Bank Account Withdraw):");
                System.out.println(jsonResponseWithdraw);

                // Ghi JSON vào file
                saveJsonToFile("BankAccountWithdrawResponse.json", jsonResponseWithdraw);
            } else {
                System.out.println("Bank Account Withdraw Post failed: " + postStatusCodeWithdraw);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm lưu JSON vào file
    private static void saveJsonToFile(String fileName, String jsonContent) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonContent);
            System.out.println("Response JSON has been saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving JSON to file: " + e.getMessage());
        }
    }
}
