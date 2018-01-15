/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.sie.inpg12.mainclass;

/**
 *
 * @author sonnguyen
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.FSDirectory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Paths;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TextFragment;

public class SimpleSearcher {

    public static final String index_path = Configs.getInstance().getString("index.dir");
    public static final String field = "contents";
    private static final Logger logger = LogManager.getLogger(SimpleSearcher.class);
    private IndexReader reader = null;
    private IndexSearcher searcher = null;
    private Analyzer analyzer = new StandardAnalyzer();
    private QueryParser parser = new QueryParser(field, analyzer);
    public static final int hitsPerPage = 10;

    public SimpleSearcher() throws IOException {
        logger.debug("Loading index from " + index_path);
        reader = DirectoryReader.open(FSDirectory.open(Paths.get(index_path)));
        searcher = new IndexSearcher(reader);
    }

    public JSONArray search(String query_text, int page) throws ParseException, IOException, InvalidTokenOffsetsException {
        Query query = parser.parse(query_text);
        TopDocs results = searcher.search(query, 1000);
        ScoreDoc[] hits = results.scoreDocs;
        //JSONObject out = new JSONObject();
        JSONArray arr = new JSONArray();

        int start = (page - 1) * hitsPerPage;
        int end = start + hitsPerPage;

        // highilight
        Formatter formatter = new SimpleHTMLFormatter();
        QueryScorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer, 1000);
        highlighter.setTextFragmenter(fragmenter);

        for (int i = start; i < Math.min(end, hits.length); ++i) {
            ScoreDoc scoreDoc = hits[i];
            Document doc = searcher.doc(scoreDoc.doc);
            JSONObject obj = new JSONObject();
//            obj.put("path", doc.get("path"));
            String text = doc.get("content");
            TokenStream stream = TokenSources.getAnyTokenStream(reader, scoreDoc.doc, "content", analyzer);

            //Get highlighted text fragment
            TextFragment[] frags = highlighter.getBestTextFragments(stream, text, false, 4);
            String highlight = "";
            for (TextFragment frag : frags) {
                highlight = highlight + frag;
                System.out.println(highlight);
            }
            obj.put("url", doc.get("url"));
            obj.put("title", doc.get("title"));
//            obj.put("content", doc.get("content"));
            if (highlight.length() < 200) {
                obj.put("frags", highlight);
            } else {
                obj.put("frags", highlight.substring(0, 200));
            }

            arr.add(obj);
        }
        //  out.put("hits", arr);
        // return out;
        return arr;
    }
}
