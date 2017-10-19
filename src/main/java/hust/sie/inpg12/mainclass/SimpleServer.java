/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.sie.inpg12.mainclass;

import java.net.InetSocketAddress;
import org.eclipse.jetty.server.Server;

/**
 *
 * @author sonnguyen
 */
public class SimpleServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Server server = new Server(new InetSocketAddress("127.0.0.1", 8080));
        server.setHandler(new SimpleHandler());
        server.start();
        System.out.println("Da khoi dong server");
        server.join();
        
    }

}
