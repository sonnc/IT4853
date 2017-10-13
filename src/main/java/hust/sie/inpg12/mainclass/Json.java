/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.sie.inpg12.mainclass;

import java.util.ArrayList;

/**
 *
 * @author sonnguyen
 */
public class Json {
    String query;
    String description;
    int sitesFile;
    ArrayList<SiteUrl> arrSites;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSitesFile() {
        return sitesFile;
    }

    public void setSitesFile(int sitesFile) {
        this.sitesFile = sitesFile;
    }

    public ArrayList<SiteUrl> getArrSites() {
        return arrSites;
    }

    public void setArrSites(ArrayList<SiteUrl> arrSites) {
        this.arrSites = arrSites;
    }
    
    
    
}
