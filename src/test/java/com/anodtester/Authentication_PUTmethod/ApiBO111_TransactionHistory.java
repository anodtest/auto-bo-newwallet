package com.anodtester.Authentication_PUTmethod;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileWriter;
import java.io.IOException;

public class ApiBO111_TransactionHistory {
    public static void main(String[] args) {
        // BaseTest
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbm9kIiwicm9sZSI6IkFETUlOIiwiaXAiOiIxNjYuODguMTIwLjE0NSIsIngtdG9rZW4iOiJmNGUwY2ZiODE3MmZlZTNkZGFhYWQ3ZjllODhiYjVkNiIsImxhbmd1YWdlIjoidmkiLCJleHAiOjE3NDM3NjkxMDgsInVzZXJJZCI6MTUsImJyYW5jaCI6InJhbWEiLCJpYXQiOjE3NDM3MzMxMDgsInVzZXJuYW1lIjoiYW5vZCJ9.wFCspeSom7_UClX4mHtx1x2s_BXi3um9jL02ELW6pOrETaxsU8ACvD9xdUXFFIp6K_pizVM55X1AbaoZALzU0A";

        String urlPostInvoice = "https://ticket.ramanaz.net/backoffice/api/v1/invoice/searching";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Thực hiện POST Request tới Invoice Searching
            HttpPost postRequestInvoice = new HttpPost(urlPostInvoice);
            postRequestInvoice.setHeader("Authorization", "Bearer " + token);
            postRequestInvoice.setHeader("Content-Type", "application/json");

            // Payload JSON cho POST request
            String jsonBodyInvoice = "{\n" +
                    "  \"cardSerial\": \"\",\n" +
                    "  \"ip\": \"\",\n" +
                    "  \"searchText\": \"\",\n" +
                    "  \"bankSearchText\": \"\",\n" +
                    "  \"walletType\": \"\",\n" +
                    "  \"withNote\": false,\n" +
                    "  \"withBankTranCode\": false,\n" +
                    "  \"withInvoiceId\": false,\n" +
                    "  \"withUUID\": false,\n" +
                    "  \"withUsername\": false,\n" +
                    "  \"withFullName\": false,\n" +
                    "  \"withUserId\": true,\n" +
                    "  \"withUserUUID\": false,\n" +
                    "  \"page\": 1,\n" +
                    "  \"size\": 20,\n" +
                    "  \"fromDate\": \"2024-12-26 14:03:00\",\n" +
                    "  \"toDate\": \"2025-01-25 23:59:00\",\n" +
                    "  \"actions\": [\"WITHDRAW\", \"DEPOSIT\"],\n" +
                    "  \"statuses\": null,\n" +
                    "  \"userTypes\": [\"USER\"],\n" +
                    "  \"affIds\": null,\n" +
                    "  \"withUsernameSuggestion\": false,\n" +
                    "  \"withFromAccName\": false,\n" +
                    "  \"withToAccName\": false,\n" +
                    "  \"withAutoNote\": false,\n" +
                    "  \"withHasDocument\": false,\n" +
                    "  \"withNoDocument\": false,\n" +
                    "  \"referAccounting\": \"\",\n" +
                    "  \"updatedBy\": \"\",\n" +
                    "  \"methods\": null,\n" +
                    "  \"cardProviders\": null,\n" +
                    "  \"bankBrCodes\": [\"AEE\", \"BIG\", \"WNP\", \"BOB\", \"USDT\", \"CHEAT\", \"Cashback\", \"ARREAR\", \"BC116111\", \"OTHERS\"],\n" +
                    "  \"operator\": \"gte\",\n" +
                    "  \"withVip\": false,\n" +
                    "  \"withFirstTime\": false,\n" +
                    "  \"sortBy\": \"created_time\",\n" +
                    "  \"withProcessBy\": [\"Auto\", \"Sup\", \"HTV\", \"OTHERS\"],\n" +
                    "  \"withCreatedFrom\": [\"BO\", \"Game\"],\n" +
                    "  \"createdBy\": \"\",\n" +
                    "  \"appId\": \"\",\n" +
                    "  \"type\": \"\",\n" +
                    "  \"verifyPhone\": false\n" +
                    "}";

            StringEntity entityInvoice = new StringEntity(jsonBodyInvoice);
            postRequestInvoice.setEntity(entityInvoice);

            HttpResponse postResponseInvoice = client.execute(postRequestInvoice);
            int postStatusCodeInvoice = postResponseInvoice.getStatusLine().getStatusCode();

            // In ra Status Code cho POST Invoice Searching
            System.out.println("Invoice Searching Post: " + postStatusCodeInvoice);

            if (postStatusCodeInvoice == 200) {
                System.out.println("Invoice Searching Post: 200 OK");

                // Lấy nội dung JSON từ Response
                String jsonResponseInvoice = EntityUtils.toString(postResponseInvoice.getEntity());
                System.out.println("Response JSON (Invoice Searching):");
                System.out.println(jsonResponseInvoice);

                // Ghi JSON vào file
                saveJsonToFile("InvoiceSearchingResponse.json", jsonResponseInvoice);
            } else {
                System.out.println("Invoice Searching Post failed: " + postStatusCodeInvoice);
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
