package hust.sie.inpg12.mainclass;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Index all text files under a directory.
 * <p>
 * This is a command-line application demonstrating simple Lucene indexing. Run
 * it with no command-line arguments for usage information.
 */
public class IndexFiles {

    static ArrayList<Json> arrJson = new ArrayList<>();
    static ArrayList<JsonUpdate> arrJsonUpdates = new ArrayList<>();
    static int arrSize = 0;
    static int arrSiteSize = 0;
    static boolean checkArrSize;
    static boolean checkArrSite;
    static boolean isDirectory;
    static JSONArray site = new JSONArray();
    static ArrayList<SiteUrl> arrSite = new ArrayList<>();

    private IndexFiles() {
    }

    /**
     * Index all text files under a directory.
     */
    public static void main(String[] args) {
        String usage = "java org.apache.lucene.demo.IndexFiles"
                + " [-index INDEX_PATH] [-docs DOCS_PATH] [-update]\n\n"
                + "This indexes the documents in DOCS_PATH, creating a Lucene index"
                + "in INDEX_PATH that can be searched with SearchFiles";
        String indexPath = "index";
        String docsPath = null;
        boolean create = true;
        for (int i = 0; i < args.length; i++) {
            if ("-index".equals(args[i])) {
                indexPath = args[i + 1];
                i++;
            } else if ("-docs".equals(args[i])) {
                docsPath = args[i + 1];
                i++;
            } else if ("-update".equals(args[i])) {
                create = false;
            }
        }

        if (docsPath == null) {
            System.err.println("Usage: " + usage);
            System.exit(1);
        }

        final Path docDir = Paths.get(docsPath);
        if (!Files.isReadable(docDir)) {
            System.out.println("Document directory '" + docDir.toAbsolutePath() + "' does not exist or is not dable, please check the path");
            System.exit(1);
        }

        Date start = new Date();
        try {
            System.out.println("Indexing to directory '" + indexPath + "'...");

            Directory dir = FSDirectory.open(Paths.get(indexPath));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

            if (create) {
                // Create a new index in the directory, removing any
                // previously indexed documents:
                iwc.setOpenMode(OpenMode.CREATE);
            } else {
                // Add new documents to an existing index:
                iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            }

            // Optional: for better indexing performance, if you
            // are indexing many documents, increase the RAM
            // buffer.  But if you do this, increase the max heap
            // size to the JVM (eg add -Xmx512m or -Xmx1g):
            //
            // iwc.setRAMBufferSizeMB(256.0);
            IndexFiles i = new IndexFiles();
            i.readJSON();
//            i.createFile();
            IndexWriter writer = new IndexWriter(dir, iwc);
//            indexDocs(writer, docDir);
            indexDocs(writer);

            // NOTE: if you want to maximize search performance,
            // you can optionally call forceMerge here.  This can be
            // a terribly costly operation, so generally it's only
            // worth it when your index is relatively static (ie
            // you're done adding documents to it):
            //
            // writer.forceMerge(1);
            writer.close();

            Date end = new Date();
            System.out.println(end.getTime() - start.getTime() + " total milliseconds");

        } catch (IOException e) {
            System.out.println(" caught a " + e.getClass()
                    + "\n with message: " + e.getMessage());
        }
    }

    /**
     * Indexes the given file using the given writer, or if a directory is
     * given, recurses over files and directories found under the given
     * directory.
     *
     * NOTE: This method indexes one document per input file. This is slow. For
     * good throughput, put multiple documents into your input file(s). An
     * example of this is in the benchmark module, which can create "line doc"
     * files, one document per line, using the
     * <a href="../../../../../contrib-benchmark/org/apache/lucene/benchmark/byTask/tasks/teLineDocTask.html"
     * >WriteLineDocTask</a>.
     *
     * @param writer Writer to the index where the given file/dir info will be
     * stored
     * @param path The file to index, or the directory to recurse into to find
     * files to index
     * @throws IOException If there is a low-level I/O error
     */
    //path  là Docs
//    public static void indexDocs(final IndexWriter writer, Path path) throws IOException {
//        if (Files.isDirectory(path)) {
//            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
//                @Override
//                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                    for (int i = arrSize; i < arrJson.size(); i++) {
//                        arrSize = arrSize + 1;
//                        site = (JSONArray) arrJson.get(i).arrSites;
//                        Iterator<JSONObject> iterator = site.iterator();
//                        while (iterator.hasNext()) {
//                            SiteUrl s = new SiteUrl();
//                            JSONObject factObj = (JSONObject) iterator.next();
//                            s.url = (String) factObj.get("url");
//                            s.title = (String) factObj.get("title");
//                            s.content = (String) factObj.get("content");
//                            arrSite.add(s);
//                        }
//                    }
//                    for (int j = arrSiteSize; j < arrSite.size(); j++) {
//                        arrSiteSize = arrSiteSize + 1;
//                        indexDoc(writer, 
//                                file,
//                                arrSite.get(j).url,
//                                arrSite.get(j).title,
//                                arrSite.get(j).content,
//                                Files.getLastModifiedTime(path).toMillis());
//                    }
//                    return FileVisitResult.CONTINUE;
//                }
//
//            });
//        } else {
//            //      indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
//        }
//    }
//
//    /**
//     * Indexes a single document
//     */
//    public static void indexDoc(IndexWriter writer, Path path, String url, String title, String contents, long lastModified) throws IOException {
//        try (InputStream stream = Files.newInputStream(path)) {
//            Document doc = new Document();
//            Field urlField = new StringField("url", url.toString(), Field.Store.YES);
//            doc.add(urlField);
//            Field titleField = new StringField("title", title, Field.Store.YES);
//            doc.add(titleField);
//            Field contentField = new StringField("contents", contents, Field.Store.YES);
//            doc.add(contentField);
//            doc.add(new LongPoint("modified", lastModified));
//            doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));
//            if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
//                System.out.println("adding " + url);
//                writer.addDocument(doc);
//            } else {
//                System.out.println("updating " + url);
//                writer.updateDocument(new Term("url", url.toString()), doc);
//            }
//        }
//    }
// lấy file JSON
    private void parseQueryDetailObject(JSONObject queryDetail) {
        /**
         * String query = (String) queryDetail.get("query"); String description
         * = (String) queryDetail.get("description"); System.out.printf("query:
         * %s\ndescription:%s\nsites:\n", query, description); JSONArray sites =
         * (JSONArray) queryDetail.get("sites"); Json j = new Json(); SiteUrl s
         * = new SiteUrl(); j.query = (String) queryDetail.get("query");
         * j.description = (String) queryDetail.get("description");
         * j.setArrSites(sites); arrJson.add(j);
         */

        JsonUpdate jsonUpdate = new JsonUpdate();
        jsonUpdate.setContent((String) queryDetail.get("content"));
        jsonUpdate.setId((int) (long) queryDetail.get("id"));
        jsonUpdate.setModified((String) queryDetail.get("modified"));
        jsonUpdate.setTitle((String) queryDetail.get("title"));
        jsonUpdate.setUrl((String) queryDetail.get("url"));
        arrJsonUpdates.add(jsonUpdate);
    }

    public void readJSON() {
        //JSON parser object to parse read file
//        JSONParser jsonParser = new JSONParser();
//        try (FileReader reader = new FileReader("Group02.json")) {
//            JSONObject root = (JSONObject) jsonParser.parse(reader);
//            JSONArray collection = (JSONArray) root.get("collection");
//            collection.forEach(q -> parseQueryDetailObject((JSONObject) q));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("All.json")) {
            JSONArray r = (JSONArray) jsonParser.parse(reader);
            JSONObject r1 = (JSONObject) r.get(0);
            JSONArray documents = (JSONArray) r1.get("documents");
//            JSONArray documents = (JSONArray) r3.get("documents");
            documents.forEach(q -> parseQueryDetailObject((JSONObject) q));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void createFile() throws FileNotFoundException, IOException {
        int x = 0;
        for (int i = arrSize; i < arrJson.size(); i++) {
            arrSize = arrSize + 1;
            site = (JSONArray) arrJson.get(i).arrSites;
            Iterator<JSONObject> iterator = site.iterator();
            while (iterator.hasNext()) {
                JSONObject factObj = (JSONObject) iterator.next();
                String url = (String) factObj.get("url");
                String title = (String) factObj.get("title");
                String content = (String) factObj.get("content");
                String fileContent = url + "\r\n" + title + "\r\n" + content;
//                SiteUrl s = new SiteUrl();
//                JSONObject factObj = (JSONObject) iterator.next();
//                s.url = (String) factObj.get("url");
//                s.title = (String) factObj.get("title");
//                s.content = (String) factObj.get("content");
                try {
                    x = x + 1;
                    File file = new File("Docs/Doc" + x + ".txt");
                    try (FileOutputStream fop = new FileOutputStream(file)) {
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        byte[] contentWrite = fileContent.getBytes();
                        fop.write(contentWrite);
                        fop.close();
                        System.out.println("Done ");
                    } catch (Exception e) {
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    // tạo chỉ mục trực tiếp từ file Json. Không tạo các file .TXT trong thư mục DOCS
    static void indexDocs(final IndexWriter writer) throws IOException {
        Date d = new Date();
        long miniSecond = d.getTime();
        for (int i = 0; i < arrJsonUpdates.size(); i++) {
            arrSize = arrSize + 1;
            String url = (String) arrJsonUpdates.get(i).getUrl();
            String title = (String) arrJsonUpdates.get(i).getTitle();
            String content ;
            if (arrJsonUpdates.get(i).getContent().length() >= 5600) {
                content = (String) arrJsonUpdates.get(i).getContent().substring(0, 5600);
            } else {
                content = (String) arrJsonUpdates.get(i).getContent();
            }
            String modified = (String) arrJsonUpdates.get(i).getModified();
            int id = (int) arrJsonUpdates.get(i).getId();

            String fileContent = url + "\n" + title;
            Document doc = new Document();
            Field urlField = new StringField("url", url, Field.Store.YES);
            doc.add(urlField);
            Field titleField = new StringField("title", title, Field.Store.YES);
            doc.add(titleField);
            Field contentField = new StringField("content", content, Field.Store.YES);
            doc.add(contentField);
            doc.add(new LongPoint("modified", miniSecond));
            doc.add(new TextField("contents", fileContent, Field.Store.YES));

            if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
                System.out.println("adding " + url);
                writer.addDocument(doc);
                System.out.println(id);
            } else {
                System.out.println("updating " + url);
                writer.updateDocument(new Term("url", url.toString()), doc);
            }

        }
//        for (int i = arrSize; i < arrJson.size(); i++) {
//            arrSize = arrSize + 1;
//            site = (JSONArray) arrJson.get(i).arrSites;
//            Iterator<JSONObject> iterator = site.iterator();
//            while (iterator.hasNext()) {
//                JSONObject factObj = (JSONObject) iterator.next();
//                String url1 = (String) factObj.get("url");
//                String title1 = (String) factObj.get("title");
//                String content1 = (String) factObj.get("content");
//                String fileContent = url1 + "\n" + title1 + "\n" + content1;
//                Document doc = new Document();
//                Field urlField = new StringField("url", url1, Field.Store.YES);
//                doc.add(urlField);
//                Field titleField = new StringField("title", title1, Field.Store.YES);
//                doc.add(titleField);
//                Field contentField = new StringField("content", content1, Field.Store.YES);
//                doc.add(contentField);
//                doc.add(new LongPoint("modified", miniSecond));
//                doc.add(new TextField("contents", fileContent, Field.Store.YES));
//
//                if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
//                    System.out.println("adding " + url1);
//                    writer.addDocument(doc);
//                } else {
//                    System.out.println("updating " + url1);
//                    writer.updateDocument(new Term("url", url1.toString()), doc);
//                }
//            }
//        }
    }

//    static void indexDocs(final IndexWriter writer, Path path) throws IOException {
//        if (Files.isDirectory(path)) {
//            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
//                @Override
//                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                    try {
//                        indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
//                    } catch (IOException ignore) {
//                        // don't index files that can't be read.
//                    }
//                    return FileVisitResult.CONTINUE;
//                }
//            });
//        } else {
//            indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
//        }
//        
//    }
//    
//    static void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException {
//        try (InputStream stream = Files.newInputStream(file)) {
//            // make a new, empty document
//            Document doc = new Document();
//
//            // Add the path of the file as a field named "path".  Use a
//            // field that is indexed (i.e. searchable), but don't tokenize
//            // the field into separate words and don't index term frequency
//            // or positional information:
//            Field pathField = new StringField("path", file.toString(), Field.Store.YES);
//            doc.add(pathField);
//            
//            BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(file), StandardCharsets.UTF_8));
//            String line;
//            String url = null, title = null, content = null;
//            int l = 0;
//            while ((line = buff.readLine()) != null) {
//                if (l == 0) {
//                    url = line;
//                    l = 1;
//                } else if (l == 1) {
//                    title = line;
//                    l = 2;
//                } else {
//                    content = line;
//                }
//            }
//            
//            buff.close();
//            
//            if (url == null) {
//                url = "null";
//            }
//            if (title == null) {
//                title = "null";
//            }
//            if (content == null) {
//                content = "null";
//            }
//            Field urlField = new StringField("url", url, Field.Store.YES);
//            doc.add(urlField);
//            Field titleField = new StringField("title", title, Field.Store.YES);
//            doc.add(titleField);
//            Field contentField = new StringField("content", content, Field.Store.YES);
//            doc.add(contentField);
//
//            // Add the last modified date of the file a field named "modified".
//            // Use a LongPoint that is indexed (i.e. efficiently filterable with
//            // PointRangeQuery).  This indexes to milli-second resolution, which
//            // is often too fine.  You could instead create a number based on
//            // year/month/day/hour/minutes/seconds, down the resolution you require.
//            // For example the long value 2011021714 would mean
//            // February 17, 2011, 2-3 PM.
//            doc.add(new LongPoint("modified", lastModified));
//
//            // Add the contents of the file to a field named "contents".  Specify a Reader,
//            // so that the text of the file is tokenized and indexed, but not stored.
//            // Note that FileReader expects the file to be in UTF-8 encoding.
//            // If that's not the case searching for special characters will fail.
//            doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));
//            
//            if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
//                // New index, so we just add the document (no old document can be there):
//                System.out.println("adding " + file);
//                writer.addDocument(doc);
//            } else {
//                // Existing index (an old copy of this document may have been indexed) so
//                // we use updateDocument instead to replace the old one matching the exact
//                // path, if present:
//                System.out.println("updating " + file);
//                writer.updateDocument(new Term("path", file.toString()), doc);
//            }
//        }
//    }
}
