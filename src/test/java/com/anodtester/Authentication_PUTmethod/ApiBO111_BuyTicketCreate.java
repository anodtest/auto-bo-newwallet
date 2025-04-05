package com.anodtester.Authentication_PUTmethod;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileWriter;
import java.io.IOException;

public class ApiBO111_BuyTicketCreate {
    public static void main(String[] args) {
        // BaseTest
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbm9kIiwicm9sZSI6IkFETUlOIiwiaXAiOiIxNjYuODguMTIwLjE0NSIsIngtdG9rZW4iOiJiNjdjYjgzOThiODI2NWZhMzI1YWEwZDg0MzIxZTM5NCIsImV4cCI6MTczNzgyMTc2NSwidXNlcklkIjoxNSwiYnJhbmNoIjoicmFtYSIsImlhdCI6MTczNzc4NTc2NSwidXNlcm5hbWUiOiJhbm9kIn0.2XUgIcyLEqVIyXZx54yLOTRDrpngeIqweaFNuFGHYT6g2-OVqFcNLrCP_5eKidU3y5gGBhowW_lVXkIJ9jyshg";

        String urlPostDeposit = "https://ticket.ramanaz.net/backoffice/api/v1/bankaccount/deposit";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Thực hiện POST Request tới Bank Account Deposit
            HttpPost postRequestDeposit = new HttpPost(urlPostDeposit);
            postRequestDeposit.setHeader("Authorization", "Bearer " + token);
            postRequestDeposit.setHeader("Content-Type", "application/json");

            // Payload JSON cho POST request
            String jsonBodyDeposit = "{\n" +
                    "  \"accountNumber\": \"123456789\",\n" +
                    "  \"amount\": 1000.50,\n" +
                    "  \"currency\": \"USD\",\n" +
                    "  \"description\": \"Test deposit\",\n" +
                    "  \"transactionDate\": \"2025-01-25T15:30:00\",\n" +
                    "  \"transactionType\": \"DEPOSIT\"\n" +
                    "}";

            StringEntity entityDeposit = new StringEntity(jsonBodyDeposit);
            postRequestDeposit.setEntity(entityDeposit);

            HttpResponse postResponseDeposit = client.execute(postRequestDeposit);
            int postStatusCodeDeposit = postResponseDeposit.getStatusLine().getStatusCode();

            // In ra Status Code cho POST Bank Account Deposit
            System.out.println("Bank Account Deposit Post: " + postStatusCodeDeposit);

            if (postStatusCodeDeposit == 200) {
                System.out.println("Bank Account Deposit Post: 200 OK");

                // Lấy nội dung JSON từ Response
                String jsonResponseDeposit = EntityUtils.toString(postResponseDeposit.getEntity());
                System.out.println("Response JSON (Bank Account Deposit):");
                System.out.println(jsonResponseDeposit);

                // Ghi JSON vào file
                saveJsonToFile("BankAccountDepositResponse.json", jsonResponseDeposit);
            } else {
                System.out.println("Bank Account Deposit Post failed: " + postStatusCodeDeposit);
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
