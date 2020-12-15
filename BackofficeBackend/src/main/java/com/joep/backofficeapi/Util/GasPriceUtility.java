package com.joep.backofficeapi.Util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GasPriceUtility {

    public static Double getDieselPrice() {
        try {
            Document doc = Jsoup.connect("https://www.unitedconsumers.com/tanken/informatie/brandstof-prijzen/diesel").get();
            var elements = doc.select("body > div > div.body-content-wrapper.body-content-wrapper-informatie > div > div > div > div.body-content > div.table > div:nth-child(8) > div:nth-child(3)");
            var res =  elements.get(0).toString();
            res = res.replaceAll("<div class=\"col-xs-4 col-sm-3 text-right\">", "");
            res = res.replaceAll("</div>", "");
            res = res.replaceAll("€", "");
            res = res.replaceAll(" ", "");
            res = res.replaceAll(",", ".");
            return Double.parseDouble(res);
        }
        catch (Exception e){
            return 0.0;
        }

    }
}
