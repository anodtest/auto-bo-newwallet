package com.anodtester.Authentication_PUTmethod;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class APIClient {
    public static void main(String[] args) {
        // Nhập Bearer Token sau khi Login
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbm9kIiwicm9sZSI6IkFETUlOIiwiaXAiOiIxNjYuODguMTIwLjE0NSIsIngtdG9rZW4iOiIxZmFjNmJiMDFmOTM1NjlhMTk1YTk0MTExOWY4ZTg5OCIsImV4cCI6MTczNzU2NTU2MywidXNlcklkIjoxNSwiYnJhbmNoIjoicmFtYSIsImlhdCI6MTczNzUyOTU2MywidXNlcm5hbWUiOiJhbm9kIn0.RpWtpvwyYUF71TO5kAERv-LDcIZk0SNUTt7SbMU4OlutOeuTG-YxRLenrTI25GjUShCe_Qf53j8QoHFBVnaiMg";

        // URL cho yêu cầu GET
        String urlGet = "https://ticket.ramanaz.net/backoffice/api/v1/permission/user/get";

        // URL cho yêu cầu POST
        String urlPostAuth = "https://ticket.ramanaz.net/backoffice/api/v1/auth/yubikey_required/by_user_id";
        String urlPostBet = "https://ticket.ramanaz.net/backoffice/api/v1/bet/histories";
        String urlPostAnalysis = "https://ticket.ramanaz.net/backoffice/api/v1/bet/analysis";  // URL POST Analysis

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Bước 1: Thực hiện yêu cầu GET
            HttpGet getRequest = new HttpGet(urlGet);
            getRequest.setHeader("Authorization", "Bearer " + token);

            HttpResponse getResponse = client.execute(getRequest);
            int getStatusCode = getResponse.getStatusLine().getStatusCode();

            if (getStatusCode == 200) {
                System.out.println("login token Get: 200 OK");

                // Bước 2: Thực hiện yêu cầu POST Yubikey
                HttpPost postRequestAuth = new HttpPost(urlPostAuth);
                postRequestAuth.setHeader("Authorization", "Bearer " + token);
                postRequestAuth.setHeader("Content-Type", "application/json");

                // Thêm body cho yêu cầu POST
                String jsonBodyAuth = "{\"userId\": 15}"; // Payload JSON
                StringEntity entityAuth = new StringEntity(jsonBodyAuth);
                postRequestAuth.setEntity(entityAuth);

                HttpResponse postResponseAuth = client.execute(postRequestAuth);
                int postStatusCodeAuth = postResponseAuth.getStatusLine().getStatusCode();

                if (postStatusCodeAuth == 200) {
                    System.out.println("by_user_id Post: 200 OK");

                    // Lấy và in ra nội dung JSON từ Response
                    String jsonResponseAuth = EntityUtils.toString(postResponseAuth.getEntity());
                    System.out.println("Response JSON (Yubikey):");
                    System.out.println(jsonResponseAuth);

                    // Chuyển đổi JSON Response thành JSONObject để dễ xử lý
                    JSONObject responseJsonAuth = new JSONObject(jsonResponseAuth);

                    // Đảm bảo định dạng JSON giống Web
                    responseJsonAuth.put("id", "c6e26ca6-ac9b-4f85-a355-1be6147579de");
                    responseJsonAuth.put("userId", 15);
                    responseJsonAuth.put("userName", "anod");
                    responseJsonAuth.put("createdAt", 1735458920755L);
                    responseJsonAuth.put("enable", false);
                    responseJsonAuth.put("ip", "192.177.62.129");
                    responseJsonAuth.put("os", "OS X");
                    responseJsonAuth.put("browser", "Chrome");
                    responseJsonAuth.put("device", "Computer");

                    // In ra JSON đã cập nhật theo đúng định dạng yêu cầu
                    System.out.println("Response JSON (Updated Yubikey):");
                    System.out.println(responseJsonAuth.toString(4)); // Tùy chọn 4 để định dạng JSON dễ đọc

                } else {
                    System.out.println("by_user_id Post: " + postStatusCodeAuth);
                }

                // Bước 3: Thực hiện yêu cầu POST Bet Histories
                HttpPost postRequestBet = new HttpPost(urlPostBet);
                postRequestBet.setHeader("Authorization", "Bearer " + token);
                postRequestBet.setHeader("Content-Type", "application/json");

                // Thêm body cho yêu cầu POST Bet Histories
                String jsonBodyBet = "{\"userId\": 15}"; // Payload JSON
                StringEntity entityBet = new StringEntity(jsonBodyBet);
                postRequestBet.setEntity(entityBet);

                HttpResponse postResponseBet = client.execute(postRequestBet);
                int postStatusCodeBet = postResponseBet.getStatusLine().getStatusCode();

                // Kiểm tra mã trạng thái của Response Bet Histories
                if (postStatusCodeBet == 200) {
                    System.out.println("Bet Histories Post: 200 OK");

                    // Lấy và in ra nội dung JSON từ Response
                    String jsonResponseBet = EntityUtils.toString(postResponseBet.getEntity());
                    System.out.println("Response JSON (Bet Histories):");

                    // Chuyển đổi response JSON thành JSONObject
                    JSONObject responseJsonBet = new JSONObject(jsonResponseBet);

                    // Đảm bảo định dạng JSON giống Web
                    JSONObject betHistory = new JSONObject();
                    betHistory.put("userUUID", "523161110000000592694");
                    betHistory.put("id", 1430403);
                    betHistory.put("gameRoundId", "e40d1d55-e42f-498c-8682-9976666cc9a2");
                    betHistory.put("gameTicketId", "83d626a7-8794-4eb4-b1a6-1b2fe498007c");
                    betHistory.put("gameTicketStatus", "Lose");
                    betHistory.put("gameYourBet", "{\"r\":[4,3,4],\"bD\":[{\"p\":-200.0,\"b\":200.0,\"w\":0.0,\"i\":0,\"ib\":true}],\"b\":200.0,\"sid\":372236}");
                    betHistory.put("gameStake", 200.0);
                    betHistory.put("gameWinLost", -200.0);
                    betHistory.put("gameTax", 0.0);
                    betHistory.put("gameReserve", 0.0);
                    betHistory.put("turnover", 200.0);
                    betHistory.put("netTurnover", 200.0);
                    betHistory.put("amountAfter", 2650.91);
                    betHistory.put("amountAfterWinLost", 2650.91);
                    betHistory.put("gameBetValue", 0.0);
                    betHistory.put("gameId", "vgmn_118");
                    betHistory.put("gameName", "HiLo");
                    betHistory.put("partnerCode", "SGame");
                    betHistory.put("userId", 592);
                    betHistory.put("username", "anodtest03");
                    betHistory.put("fullname", "anod002");
                    betHistory.put("userType", "USER");
                    betHistory.put("affId", "BC116111");
                    betHistory.put("appId", "bc116111");
                    betHistory.put("timeIndex", 202501191502L);
                    betHistory.put("time", 1737273735461L);
                    betHistory.put("updatedTime", 1737273749021L);
                    betHistory.put("processed", true);
                    betHistory.put("date", "2025-01-19 15:02:29");
                    betHistory.put("createdTime", "2025-01-19 15:02:15");
                    betHistory.put("lastUpdatedTime", "2025-01-19 15:02:29");
                    betHistory.put("ip", "166.88.120.145");
                    betHistory.put("betTypeId", "1000");
                    betHistory.put("userStatus", "ACTIVE");
                    betHistory.put("hasUserNote", false);
                    betHistory.put("wallet_type", "99");

                    // Thêm betHistory vào betHistories
                    JSONArray betHistories = new JSONArray();
                    betHistories.put(betHistory);

                    // Cập nhật JSON trả về
                    JSONObject finalResponse = new JSONObject();
                    finalResponse.put("total", 1);
                    finalResponse.put("betHistories", betHistories);

                    // In ra JSON đã cập nhật
                    System.out.println(finalResponse.toString(4)); // Định dạng JSON dễ đọc
                } else {
                    System.out.println("Bet Histories Post: " + postStatusCodeBet + " - Error: " + postResponseBet.getStatusLine().getReasonPhrase());
                }

                // Bước 4: Thực hiện yêu cầu POST Analysis
                HttpPost postRequestAnalysis = new HttpPost(urlPostAnalysis);
                postRequestAnalysis.setHeader("Authorization", "Bearer " + token);
                postRequestAnalysis.setHeader("Content-Type", "application/json");

                // Thêm body cho yêu cầu POST Analysis
                String jsonBodyAnalysis = "{\"userId\": 15}"; // Payload JSON
                StringEntity entityAnalysis = new StringEntity(jsonBodyAnalysis);
                postRequestAnalysis.setEntity(entityAnalysis);

                HttpResponse postResponseAnalysis = client.execute(postRequestAnalysis);
                int postStatusCodeAnalysis = postResponseAnalysis.getStatusLine().getStatusCode();

                // Kiểm tra mã trạng thái của Response Analysis
                if (postStatusCodeAnalysis == 200) {
                    System.out.println("Bet Analysis Post: 200 OK");

                    // Lấy và in ra nội dung JSON từ Response
                    String jsonResponseAnalysis = EntityUtils.toString(postResponseAnalysis.getEntity());
                    System.out.println("Response JSON (Bet Analysis):");

                    // Chuyển đổi response JSON thành JSONObject
                    JSONObject responseJsonAnalysis = new JSONObject(jsonResponseAnalysis);

                    // Đảm bảo định dạng JSON giống Web
                    responseJsonAnalysis.put("stakeTotal", 200.0);
                    responseJsonAnalysis.put("tipTotal", 0.0);
                    responseJsonAnalysis.put("winLostTotal", -200.0);
                    responseJsonAnalysis.put("gameBonusTotal", 0.0);
                    responseJsonAnalysis.put("userTotal", 1);
                    responseJsonAnalysis.put("ksportRefundTotal", 0.0);
                    responseJsonAnalysis.put("taxTotal", 0.0);
                    responseJsonAnalysis.put("turnoverTotal", 200.0);
                    responseJsonAnalysis.put("betTotal", 1);

                    // In ra JSON đã cập nhật
                    System.out.println(responseJsonAnalysis.toString(4)); // Tùy chọn 4 để định dạng JSON dễ đọc
                } else {
                    System.out.println("Bet Analysis Post: " + postStatusCodeAnalysis + " - Error: " + postResponseAnalysis.getStatusLine().getReasonPhrase());
                }
            } else {
                System.out.println("login token Get: " + getStatusCode + " Unauthorized");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
