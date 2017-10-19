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
import org.apache.lucene.queryparser.classic.ParseException;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleHandler extends AbstractHandler
{
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SimpleHandler.class);
    private SimpleSearcher searcher = new SimpleSearcher();
    public static final int hitsPerPage = 10;

    public SimpleHandler() throws IOException {
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        if (!target.equalsIgnoreCase("/search"))
            return;

        try {
            String q = baseRequest.getParameter("q");
            String page_str = baseRequest.getParameter("page");

//            org.apache.logging.log4j.LogManager.getRootLogger().debug("Query: " + q + " page: " + page_str);
            logger.debug("Query: " + q + " page: " + page_str);
            int page = 1;
            if (page_str!= null && !page_str.isEmpty())
                page = Integer.parseInt(page_str);
            JSONObject result = searcher.search(q, page);
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            response.getWriter().write(result.toJSONString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}