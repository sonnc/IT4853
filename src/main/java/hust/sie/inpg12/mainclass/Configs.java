package hust.sie.inpg12.mainclass;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sonnguyen
 */
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class Configs {
    private static Configuration config = null;
    public static Configuration getInstance() {
        if (config == null) {
            Configurations cc = new Configurations();
            try {
                config = cc.properties(new File("./conf/searcher.conf"));
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }
        return config;
    }
}
