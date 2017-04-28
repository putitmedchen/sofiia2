package com.good.www1.win.utils;



import com.good.www1.win.testNG.FirefoxTestCase;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DownloadBBContent extends FirefoxTestCase {

    private static final int BUFFER_SIZE = 1024 * 8;
    public String contentBuidPath;
    public String buildNum;

    //@Test
    public void downloadContentBAccessBuild() {
        activateTrustManager();
        setHostnameVerifier();

        String releaseArtifactUrl = getArtifactReleaseUrl();
        buildNum = getMaxBuildNumber(releaseArtifactUrl);
        String releaseExe = getBlackAccessExe().replace("*", buildNum);
        String releaseUrl = getBlackAccessUrl().replace("*", buildNum);
        String latestExeUrl = releaseUrl + "/" + releaseExe;
        contentBuidPath = getBulidSavePath();

        try {
            downloadFile(latestExeUrl, contentBuidPath);
            contentBuidPath = contentBuidPath + "/" + releaseExe;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void downloadContentBWorkSBuild() {
        activateTrustManager();
        setHostnameVerifier();

        String releaseArtifactUrl = getBlackWorkArtifact();
        buildNum = getMaxBuildNumber(releaseArtifactUrl);
        String releaseCrx = getBlackWorkCrx();
        String releaseUrl = getBlackWorkUrl().replace("*", buildNum);
        String latestCrxUrl = releaseUrl + "/" + releaseCrx;
        contentBuidPath = getBulidSavePath();

        try {
            downloadFile(latestCrxUrl, contentBuidPath);
            contentBuidPath = contentBuidPath + "/" + releaseCrx;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getMaxBuildNumber(String httpURL) {
        int responseCode;
        String maxBuildNumber = null;
        try {
            URL url = new URL(httpURL);
            HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
            responseCode = httpsConn.getResponseCode();

            String responseMessage = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader((httpsConn.getInputStream())));
                StringBuilder sb = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
                responseMessage = sb.toString();
            }

            Pattern pattern = Pattern.compile("#[0-9]{3}");
            Matcher matcher = pattern.matcher(responseMessage);
            while (matcher.find()) {
                if (!matcher.group().isEmpty()) {
                    maxBuildNumber = matcher.group().replace("#", "");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxBuildNumber;
    }

    private void setHostnameVerifier() {
        HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {
                    public boolean verify(String hostname,
                                          javax.net.ssl.SSLSession sslSession) {
                        return true;
                    }
                });
    }

    private void activateTrustManager() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
            }
        }};

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }
    }

    private static void downloadFile(String fileURL, String saveDir)
            throws IOException {
        URL url = new URL(fileURL);
        HttpsURLConnection httpConn = (HttpsURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + fileName;
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}


