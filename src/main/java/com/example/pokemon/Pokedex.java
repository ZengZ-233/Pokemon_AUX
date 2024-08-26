package com.example.pokemon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.*;

public class Pokedex {
    @FXML
    private TextField inputField;

    @FXML
    private TextArea displayArea;

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tool.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void find(ActionEvent actionEvent) {
        String inputText = inputField.getText();
        String urlString = "https://wiki.52poke.com/wiki/%E5%AE%9D%E5%8F%AF%E6%A2%A6%E5%88%97%E8%A1%A8%EF%BC%88%E6%8C%89%E5%85%A8%E5%9B%BD%E5%9B%BE%E9%89%B4%E7%BC%96%E5%8F%B7%EF%BC%89";

        configureSSL();

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", "UTF-8");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                String htmlContent = content.toString();
                Pattern pattern = Pattern.compile("<td\\s+class=\"rdexn-name\">\\s*<a\\s+href=\"(.*?)\"\\s+title=\"(.*?)\">.*?</a>\\s*</td>");
                Matcher matcher = pattern.matcher(htmlContent);

                boolean found = false;
                while (matcher.find()) {
                    String href = matcher.group(1);
                    href = "https://wiki.52poke.com/" + href;
                    String title = matcher.group(2);

                    if (title.equalsIgnoreCase(inputText)) {
                        displayArea.setText("-----------宝可梦-----------\n" +
                                title + "\n" +
                                "-----------维基百科链接-----------\n" +
                                href + "\n");
                        fetchTitlesFromTable(href);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    displayArea.setText("没有该Pokemon发现: " + inputText);
                }

            } else {
                displayArea.setText("Error: HTTP response code " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
            displayArea.setText("未能够获取网络信息");
        }
    }

    private void fetchTitlesFromTable(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", "UTF-8");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                String htmlContent = content.toString();
                Pattern tablePattern = Pattern.compile("<table\\s+class=\"roundy bgwhite fulltable\"[^>]*>(.*?)</table>", Pattern.DOTALL);
                Matcher tableMatcher = tablePattern.matcher(htmlContent);

                int tableCount = 0;
                String tableContent = null;

                while (tableMatcher.find()) {
                    tableCount++;
                    if (tableCount == 2) {
                        tableContent = tableMatcher.group(1);
                        break;
                    }
                }

                if (tableContent != null) {
                    Pattern aPattern = Pattern.compile("<a\\s+href=\"[^\"]*\"\\s+title=\"([^\"]*)\"[^>]*>.*?</a>", Pattern.DOTALL);
                    Matcher aMatcher = aPattern.matcher(tableContent);

                    StringBuilder titles = new StringBuilder();
                    while (aMatcher.find()) {
                        String title = aMatcher.group(1);
                        String title1 = title.replaceAll("\\(.*?\\)", "").trim();
                        title1 = title1.replaceAll("（.*?）", "").trim();
                        if (!title1.isEmpty()) {
                            titles.append(title1).append("\n");
                        }
                    }

                    if (titles.length() > 0) {
                        displayArea.appendText("-----------属性-----------\n" +
                                titles.toString() + "\n");
                    } else {
                        displayArea.appendText("没有找到任何属性");
                    }
                } else {
                    displayArea.appendText("没有找到符合条件的内容");
                }

            } else {
                displayArea.appendText("Error: HTTP response code " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
            displayArea.appendText("未能够获取网络信息");
        }
    }

    private void configureSSL() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");

            TrustManager[] trustAll = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                    }
            };

            sslContext.init(null, trustAll, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
