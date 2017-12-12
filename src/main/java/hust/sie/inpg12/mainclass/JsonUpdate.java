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
public class JsonUpdate {

    private int id;
    private String documents;
    private String content;
    private String modified;
    private String title;
    private String url;

    public JsonUpdate() {
    }

    public JsonUpdate(int id, String documents, String content, String modified, String title, String url) {
        this.id = id;
        this.documents = documents;
        this.content = content;
        this.modified = modified;
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
