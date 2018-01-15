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
public class Html {

    private String home = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <meta charset=\"utf-8\">\n"
            + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
            + "        <title>Search</title>\n"
            + "        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n"
            + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" integrity=\"sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb\" crossorigin=\"anonymous\">\n"
            + "        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n"
            + "        <link href=\"https://fonts.googleapis.com/css?family=Fira+Sans|Lato|Montserrat\" rel=\"stylesheet\">\n"
            + "        <link rel=\"stylesheet\" href=\"css/style.css\">\n"
            + "        <style>\n"
            + "            html{\n"
            + "                overflow-x:hidden; \n"
            + "            }\n"
            + "            body{\n"
            + "                width:100%;\n"
            + "                margin:0;\n"
            + "                padding:0;\n"
            + "                background-size: cover;\n"
            + "                min-width:320px;\n"
            + "            }\n"
            + "            body::-webkit-scrollbar-track\n"
            + "            {\n"
            + "                -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);\n"
            + "                background-color: #F5F5F5;\n"
            + "            }\n"
            + "\n"
            + "            body::-webkit-scrollbar\n"
            + "            {\n"
            + "                width: 6px;\n"
            + "                background-color: #F5F5F5;\n"
            + "            }\n"
            + "\n"
            + "            body::-webkit-scrollbar-thumb\n"
            + "            {\n"
            + "                background-color: #aaa;\n"
            + "            }\n"
            + "            *{\n"
            + "                font-family: 'Fira Sans', sans-serif;\n"
            + "                font-family: 'Montserrat', sans-serif;\n"
            + "                font-family: 'Lato', sans-serif;\n"
            + "            }\n"
            + "            .container-fluid{\n"
            + "                padding-top:3rem!important;\n"
            + "            }\n"
            + "            .container-fluid, .col-sm-12, .row, .col-sm-6{\n"
            + "                padding:0;\n"
            + "                margin:0;\n"
            + "            }\n"
            + "            #bottom{\n"
            + "                margin-top:3rem;\n"
            + "            }\n"
            + "            #bottom .input-group{\n"
            + "                width:75%;\n"
            + "            }\n"
            + "            #bottom input,input{\n"
            + "                border:1px solid chocolate!important;\n"
            + "            }\n"
            + "            #nh-button{\n"
            + "                background-color:chocolate;\n"
            + "                border-color:chocolate;\n"
            + "            }\n"
            + "            #nh-button:active{\n"
            + "                box-shadow: none!important;\n"
            + "            }\n"
            + "\n"
            + "\n"
            + "\n"
            + "            /* result */\n"
            + "            #r-top{\n"
            + "                padding-top:3rem;\n"
            + "\n"
            + "            }\n"
            + "            .bg-faded{\n"
            + "                padding-left:0;\n"
            + "                padding-bottom:0;\n"
            + "            }\n"
            + "            #list-link span{\n"
            + "                border-bottom:2px solid #FAFAFA;\n"
            + "                padding-bottom:12px;\n"
            + "            }\n"
            + "            #list-link span:hover{\n"
            + "                color:chocolate;\n"
            + "                border-bottom:2px solid chocolate;\n"
            + "                padding-bottom:11px;\n"
            + "            }\n"
            + "            #r-bottom{\n"
            + "                padding-top:3rem;\n"
            + "                background:#FAFAFA;\n"
            + "                border-top:1px solid #ccc;\n"
            + "            }\n"
            + "            #r-bottom .page{\n"
            + "                margin-bottom:2rem;\n"
            + "            }\n"
            + "            #r-bottom .page a{\n"
            + "                font-size:20px;\n"
            + "                color: #0056b3;\n"
            + "            }\n"
            + "            #r-bottom .page span{\n"
            + "                color:chocolate ;\n"
            + "                display:block;\n"
            + "            }\n"
            + "            #r-bottom .page p{\n"
            + "                font-size:14px;\n"
            + "            }\n"
            + "\n"
            + "\n"
            + "            #nh-group-button{\n"
            + "                padding-bottom:40px;\n"
            + "            }\n"
            + "            #nh-group-button .btn-primary{\n"
            + "                margin-right:15px;\n"
            + "                background:#fff;\n"
            + "                border:1px solid #0056b3;\n"
            + "                color:#000;\n"
            + "            }\n"
            + "            #nh-group-button .btn-primary:first-child,#nh-group-button .btn-primary:hover{\n"
            + "                color:#fff;\n"
            + "                background-color:chocolate;\n"
            + "                border:1px solid chocolate;\n"
            + "            }\n"
            + "\n"
            + "            @media only screen and (min-width:320px) and (max-width: 576px){\n"
            + "                #r-top,#r-bottom{\n"
            + "                    padding-left:20px;\n"
            + "                    padding-right:20px;\n"
            + "                }\n"
            + "                #nav-content{\n"
            + "                    text-align:center;\n"
            + "                }\n"
            + "            }\n"
            + "        </style>\n"
            + "    </head>\n"
            + "    <body>\n"
            + "        <div class=\"container-fluid\" >\n"
            + "            <div  class=\"text-center\" id=\"top\">\n"
            + "                <img src=\"https://myupdatestar.com/wp-content/uploads/2017/07/Fotolia_63892082_S_-SEARCH-2.jpg\" class=\"img-fluid\" alt=\"Responsive image\" width=\"50%\">\n"
            + "            </div>\n"
            + "            <div class=\"text-center\" id=\"bottom\">\n"
            + "                <div class=\"row\">\n"
            + "                    <div class=\"col-md-12\" align=\"center\">\n"
            + "                        <form action=\"search\" method=\"get\">\n"
            + "                            <div class=\"input-group\">\n"
            + "                                <input name=\"q\" type=\"text\" class=\"form-control\" placeholder=\"Search by keyword . . .\" required>\n"
            + "                                <span class=\"input-group-btn\">\n"
            + "                                    <button class=\"btn btn-secondary\" type=\"submit\" id=\"nh-button\">\n"
            + "                                        <i class=\"fa fa-search\"></i>\n"
            + "                                    </button>\n"
            + "                                </span>\n"
            + "                            </div>\n"
            + "                        </form>\n"
            + "                    </div>\n"
            + "                </div>\n"
            + "\n"
            + "            </div>\n"
            + "        </div>\n"
            + "\n"
            + "\n"
            + "        <!---- script ------>\n"
            + "        <script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n"
            + "        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js\" integrity=\"sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh\" crossorigin=\"anonymous\"></script>\n"
            + "        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\" integrity=\"sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ\" crossorigin=\"anonymous\"></script>\n"
            + "    </body>	\n"
            + "</html>";
    private String resultHead = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <meta charset=\"utf-8\">\n"
            + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
            + "        <title>Result</title>\n"
            + "        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n"
            + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" integrity=\"sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb\" crossorigin=\"anonymous\">\n"
            + "        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n"
            + "        <link href=\"https://fonts.googleapis.com/css?family=Fira+Sans|Lato|Montserrat\" rel=\"stylesheet\">\n"
            + "        <link rel=\"stylesheet\" href=\"ionicons-2.0.1/css/ionicons.css\">\n"
            + "        <link rel=\"stylesheet\" href=\"css/style.css\">\n"
            + "        <style>\n"
            + "            html{\n"
            + "                overflow-x:hidden; \n"
            + "            }\n"
            + "            body{\n"
            + "                width:100%;\n"
            + "                margin:0;\n"
            + "                padding:0;\n"
            + "                background-size: cover;\n"
            + "                min-width:320px;\n"
            + "            }\n"
            + "            body::-webkit-scrollbar-track\n"
            + "            {\n"
            + "                -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);\n"
            + "                background-color: #F5F5F5;\n"
            + "            }\n"
            + "\n"
            + "            body::-webkit-scrollbar\n"
            + "            {\n"
            + "                width: 6px;\n"
            + "                background-color: #F5F5F5;\n"
            + "            }\n"
            + "\n"
            + "            body::-webkit-scrollbar-thumb\n"
            + "            {\n"
            + "                background-color: #aaa;\n"
            + "            }\n"
            + "            *{\n"
            + "                font-family: 'Fira Sans', sans-serif;\n"
            + "                font-family: 'Montserrat', sans-serif;\n"
            + "                font-family: 'Lato', sans-serif;\n"
            + "            }\n"
            + "            .container-fluid{\n"
            + "                padding-top:3rem!important;\n"
            + "            }\n"
            + "            .container-fluid, .col-sm-12, .row, .col-sm-6{\n"
            + "                padding:0;\n"
            + "                margin:0;\n"
            + "            }\n"
            + "            #bottom{\n"
            + "                margin-top:3rem;\n"
            + "            }\n"
            + "            #bottom .input-group{\n"
            + "                width:75%;\n"
            + "            }\n"
            + "            #bottom input,input{\n"
            + "                border:1px solid chocolate!important;\n"
            + "            }\n"
            + "            #nh-button{\n"
            + "                background-color:chocolate;\n"
            + "                border-color:chocolate;\n"
            + "            }\n"
            + "            #nh-button:active{\n"
            + "                box-shadow: none!important;\n"
            + "            }\n"
            + "\n"
            + "\n"
            + "\n"
            + "            /* result */\n"
            + "            #r-top{\n"
            + "                padding-top:3rem;\n"
            + "\n"
            + "            }\n"
            + "            .bg-faded{\n"
            + "                padding-left:0;\n"
            + "                padding-bottom:0;\n"
            + "            }\n"
            + "            #list-link span{\n"
            + "                border-bottom:2px solid #FAFAFA;\n"
            + "                padding-bottom:12px;\n"
            + "            }\n"
            + "            #list-link span:hover{\n"
            + "                color:chocolate;\n"
            + "                border-bottom:2px solid chocolate;\n"
            + "                padding-bottom:11px;\n"
            + "            }\n"
            + "            #r-bottom{\n"
            + "                padding-top:3rem;\n"
            + "                background:#FAFAFA;\n"
            + "                border-top:1px solid #ccc;\n"
            + "            }\n"
            + "            #r-bottom .page{\n"
            + "                margin-bottom:2rem;\n"
            + "            }\n"
            + "            #r-bottom .page a{\n"
            + "                font-size:20px;\n"
            + "                color: #0056b3;\n"
            + "            }\n"
            + "            #r-bottom .page span{\n"
            + "                color:chocolate ;\n"
            + "                display:block;\n"
            + "            }\n"
            + "            #r-bottom .page p{\n"
            + "                font-size:14px;\n"
            + "            }\n"
            + "\n"
            + "\n"
            + "            #nh-group-button{\n"
            + "                padding-bottom:40px;\n"
            + "            }\n"
            + "            #nh-group-button .btn-primary{\n"
            + "                margin-right:15px;\n"
            + "                background:#fff;\n"
            + "                border:1px solid #0056b3;\n"
            + "                color:#000;\n"
            + "            }\n"
            + "            #nh-group-button .btn-primary:first-child,#nh-group-button .btn-primary:hover{\n"
            + "                color:#fff;\n"
            + "                background-color:chocolate;\n"
            + "                border:1px solid chocolate;\n"
            + "            }\n"
            + "\n"
            + "            @media only screen and (min-width:320px) and (max-width: 576px){\n"
            + "                #r-top,#r-bottom{\n"
            + "                    padding-left:20px;\n"
            + "                    padding-right:20px;\n"
            + "                }\n"
            + "                #nav-content{\n"
            + "                    text-align:center;\n"
            + "                }\n"
            + "            }\n"
            + "        </style>\n"
            + "    </head>\n"
            + "    <body>\n"
            + "        <div class=\"container-fluid\" style=\"padding-top:0!important\">\n"
            + "            <div class=\"wrapper\">\n"
            + "                <div id=\"r-top\">\n"
            + "                    <div class=\"row\">\n"
            + "                        <div class=\"col-sm-1\">\n"
            + "                            <img src=\"https://myupdatestar.com/wp-content/uploads/2017/07/Fotolia_63892082_S_-SEARCH-2.jpg\" class=\"img-fluid\" alt=\"Responsive image\" width=\"100%\">\n"
            + "                        </div>\n"
            + "                        <div class=\"col-sm-6\">\n"
            + "                         <form action=\"search\" method=\"get\">\n"
            + "                            <div class=\"input-group\">\n"
            + "                                <input name=\"q\" type=\"text\" class=\"form-control\" placeholder=\"\" required>\n"
            + "                                <span class=\"input-group-btn\">\n"
            + "                                    <button class=\"btn btn-secondary\" type=\"submit\" id=\"nh-button\">\n"
            + "                                        <i class=\"fa fa-search\"></i>\n"
            + "                                    </button>\n"
            + "                                </span>	\n"
            + "                            </div>	\n"
            + "                         </form>\n"
            + "                        </div>\n"
            + "                    </div>\n"
            + "                    <div class=\"row\">\n"
            + "                        <div class=\"col-sm-1\"></div>\n"
            + "                        <div class=\"col-sm-6\" id=\"list-link\">\n"
            + "                            <nav class=\"navbar navbar-expand-sm navbar-light bg-faded\">\n"
            + "                                <button class=\"navbar-toggler\" class=\"nh-button\" type=\"button\" data-toggle=\"collapse\" data-target=\"#nav-content\" aria-controls=\"nav-content\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
            + "                                    <span class=\"navbar-toggler-icon\"></span>\n"
            + "                                </button>\n"
            + "\n"
            + "                                <!-- start of Links -->\n"
            + "                                <div class=\"collapse navbar-collapse\" id=\"nav-content\">   \n"
            + "                                    <ul class=\"navbar-nav\">\n"
            + "                                        <li class=\"nav-item\">\n"
            + "                                            <a class=\"nav-link\" href=\"#\"  >\n"
            + "                                                <span class=\"text\" style=\"color:chocolate;\n"
            + "                                                      border-bottom:2px solid chocolate;\n"
            + "                                                      padding-bottom:11px;\">Answers</span>	\n"
            + "                                            </a>\n"
            + "                                        </li>\n"
            + "                                        <li class=\"nav-item\">\n"
            + "                                            <a class=\"nav-link\" href=\"#\">\n"
            + "                                                <span class=\"text\">Images</span>\n"
            + "\n"
            + "                                            </a>\n"
            + "                                        </li>\n"
            + "                                        <li class=\"nav-item\">\n"
            + "                                            <a class=\"nav-link\" href=\"#\">					\n"
            + "                                                <span class=\"text\">Video</span>\n"
            + "                                            </a>\n"
            + "                                        </li>\n"
            + "                                    </ul>\n"
            + "                                </div>\n"
            + "                                <!--end of links-->\n"
            + "                            </nav>\n"
            + "                        </div>\n"
            + "                    </div>\n"
            + "                </div>\n"
            + "                <div id=\"r-bottom\">\n"
            + "                    <div class=\"row\">\n"
            + "                        <div class=\"col-sm-1\">\n"
            + "                        </div>\n"
            + "                        <div class=\"col-sm-6\">";
    private String resultFooter = "</div>\n"
            + "				</div>\n"
            + "			</div>\n"
            + "		</div>\n"
            + "\n"
            + "		<!---- script ------>\n"
            + "		<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n"
            + "		<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js\" integrity=\"sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh\" crossorigin=\"anonymous\"></script>\n"
            + "		<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\" integrity=\"sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ\" crossorigin=\"anonymous\"></script>\n"
            + "	</body>\n"
            + "</html>";

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getResultHead() {
        return resultHead;
    }

    public void setResultHead(String resultHead) {
        this.resultHead = resultHead;
    }

    public String getResultFooter() {
        return resultFooter;
    }

    public void setResultFooter(String resultFooter) {
        this.resultFooter = resultFooter;
    }
    
    
}
