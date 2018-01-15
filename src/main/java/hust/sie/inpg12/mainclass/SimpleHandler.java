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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.json.simple.JSONArray;

public class SimpleHandler extends AbstractHandler {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SimpleHandler.class);
    private SimpleSearcher searcher = new SimpleSearcher();
    public static final int hitsPerPage = 5;

    public SimpleHandler() throws IOException {
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Html h = new Html();
        if (target.equalsIgnoreCase("/")) {
            try {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                response.getWriter().println(h.getHome());

            } catch (Exception e) {
            }
        };
        if (!target.equalsIgnoreCase("/search")) {
            return;
        } else {
            try {
                String q = baseRequest.getParameter("q");
                String page_str = baseRequest.getParameter("page");

//            org.apache.logging.log4j.LogManager.getRootLogger().debug("Query: " + q + " page: " + page_str);
                logger.debug("Query: " + q + " page: " + page_str);
                int page = 1;
                if (page_str != null && !page_str.isEmpty()) {
                    page = Integer.parseInt(page_str);
                }
                //JSONObject result = searcher.search(q, page);
                JSONArray result;
                result = searcher.search(q, page);
//                response.setContentType("application/json;charset=utf-8");
//                response.setStatus(HttpServletResponse.SC_OK);
//                baseRequest.setHandled(true);
//                response.getWriter().write(result.toJSONString());
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                response.getWriter().println(h.getResultHead());
                for (int i = 0; i < result.size(); i++) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject = (JSONObject) result.get(i);
                    response.getWriter().println("<div class=\"page\">\n"
                            + "	<a href=\"" + (String) jSONObject.get("url") + "\"><i class=\"ion-ios-flower-outline\"></i>" + (String) jSONObject.get("title") + "</a>\n"
                            + " <span>" + (String) jSONObject.get("url") + "</span>\n"
                            + "	<p>" + (String) jSONObject.get("frags") + "</p>\n"
                            + "	</div>");
                }
                response.getWriter().println("</div>\n"
                        + "					</div>\n"
                        + "					<div class=\"text-center\" id=\"nh-group-button\">\n"
                );
                if (page >= 2) {
                    response.getWriter().println("<a href=\"./search?q=" + q + "&page=" + (page - 1) + "\"><button class=\"btn btn-primary\" type=\"\" id=\"\">Lùi</button></a>\n");
                }
                response.getWriter().println("<button class=\"btn btn-primary\" type=\"\" id=\"\">" + page + "</button>\n"
                        + "						<a href=\"./search?q=" + q + "&page=" + (page + 1) + "\"><button class=\"btn btn-primary\" type=\"\" id=\"\">" + (page + 1) + "</button></a>\n"
                        + "						<a href=\"./search?q=" + q + "&page=" + (page + 2) + "\"><button class=\"btn btn-primary\" type=\"\" id=\"\">" + (page + 2) + "</button></a>\n"
                );
                response.getWriter().println("<a href=\"./search?q=" + q + "&page=" + (page + 1) + "\"><button class=\"btn btn-primary\" type=\"\" id=\"\">Tiến</button></a>\n"
                        + h.getResultFooter()
                );
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (InvalidTokenOffsetsException ex) {
                Logger.getLogger(SimpleHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
