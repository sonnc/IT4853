/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.sie.inpg12.mainclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author sonnguyen
 */
public class MainClass {

    ArrayList arrURL = new ArrayList();
    ArrayList<String> arrFileJSON = new ArrayList<>();
    ArrayList<SiteUrl> arrSite = new ArrayList<>();
    ArrayList<Json> arrJson = new ArrayList<>();
    String query;
    String description;

    // đọc file .txt
    public void readFileTXT() {

        String fileName = "/home/sonnguyen/NetBeansProjects/TKTTTD/File TXT/sachhay.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {//đưa về dạng chuẩn utf8
            stream.forEach(line -> {
                System.out.println(line);
                arrURL.add(line);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(arrURL);
    }

    // xử lý url
    public void processURL() throws IOException {
        for (int i = 0; i < arrURL.size(); i++) {
            if (i == 0) {
                query = arrURL.get(0).toString();
            } else if (i == 1) {
                description = arrURL.get(1).toString();
            } else if (!arrURL.get(i).equals("")) {
                try {
                    String n = arrURL.get(i).toString();
                    String url = n.substring(0, n.length() - 3);
                    int rel = Integer.parseInt(n.substring(n.length() - 2, n.length() - 1));
                    // đọc từ url online
                    Document doc = (Document) Jsoup.connect(url).get();
                    // đọc file html
                    //    File htmlFile = new File(url);
                    //    Document doc = Jsoup.parse(htmlFile, "UTF-8");
                    String title = doc.title();
                    String content = doc.select("body p").text();
                    if (content.length() >= 5600) {
                        content = content.substring(0, 5600);
                    }
                    SiteUrl s = new SiteUrl();
                    s.url = url;
                    s.content = content;
                    s.title = title;
                    s.relevance = rel;
                    arrSite.add(s);
                } catch (Exception e) {
                }

            } else {
                break;
            }
        }
    }

    public void proJSON() {
        JSONObject root = new JSONObject();
        JSONArray collection = new JSONArray();
        JSONObject queryDetail1 = new JSONObject();
        queryDetail1.put("query", query);
        queryDetail1.put("description", description);
        JSONArray q1Site = new JSONArray();
        for (int i = 0; i < arrSite.size(); i++) {
            JSONObject siteDetail = new JSONObject();
            siteDetail.put("url", arrSite.get(i).url);
            siteDetail.put("title", arrSite.get(i).title);
            siteDetail.put("content", arrSite.get(i).content);
            siteDetail.put("relevance", arrSite.get(i).relevance);
            q1Site.add(siteDetail);
            queryDetail1.put("sites", q1Site);
        }
        collection.add(queryDetail1);
        root.put("collection", collection);
        try (FileWriter file = new FileWriter("sachhay.json")) {
            root.writeJSONString(file);  // prefer this form
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void parseQueryDetailObject(JSONObject queryDetail) {

        String query = (String) queryDetail.get("query");
        String description = (String) queryDetail.get("description");
        System.out.printf("query: %s\ndescription:%s\nsites:\n", query, description);
        JSONArray sites = (JSONArray) queryDetail.get("sites");
//        for (Object obj : sites) {
//            JSONObject siteDetail = (JSONObject) obj;
//            String url = (String) siteDetail.get("url");
//            String title = (String) siteDetail.get("title");
//            String content = (String) siteDetail.get("content");
//            boolean relevance = ((Long) siteDetail.get("relevance") == 1);
//            System.out.printf("\turl = %s\n\ttitle = %s\n\tcontent = %s\n\trelevance = %s\n", url, title, content, relevance);
//        }
        Json j = new Json();
        SiteUrl s = new SiteUrl();
        j.query = (String) queryDetail.get("query");
        j.description = (String) queryDetail.get("description");
        j.setArrSites(sites);
        arrJson.add(j);

    }

    public void readJSON(String file) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("JSON/" + file + "")) {
            JSONObject root = (JSONObject) jsonParser.parse(reader);
            JSONArray collection = (JSONArray) root.get("collection");
            collection.forEach(q -> parseQueryDetailObject((JSONObject) q));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void getFileJSON() {
        File dir = new File("/home/sonnguyen/NetBeansProjects/TKTTTD/JSON");
        String[] children = dir.list();
        if (children == null) {
            System.out.println("Either dir does not exist or is not a directory ");
        } else {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];
                readJSON(filename);
            }
        }
    }

    public void mixFileJSON() throws IOException, ParseException {
        JSONObject root = new JSONObject();
        JSONArray collection = new JSONArray();
        for (int i = 0; i < arrJson.size(); i++) {
            JSONObject queryDetail1 = new JSONObject();
            queryDetail1.put("query", arrJson.get(i).query);
            queryDetail1.put("description", arrJson.get(i).description);
            queryDetail1.put("sites", arrJson.get(i).arrSites);
            collection.add(queryDetail1);
        }

        root.put("collection", collection);
        try (FileWriter file = new FileWriter("Group02-INPG12.json")) {
            root.writeJSONString(file);  // prefer this form
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTime() {
        Date d = new Date();
        long miniSecond = d.getTime();
        System.out.println(miniSecond);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        // TODO code application logic here
        MainClass main = new MainClass();
//        main.readFileTXT();
//        main.processURL();
//        main.proJSON();
//        main.readJSON();
//        main.getFileJSON();
//        main.mixFileJSON();
        main.getTime();
    }

}
