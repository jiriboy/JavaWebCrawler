import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Crawler {

    public static void main(String[] args) throws Exception {
        String date = "2021-09-01";

        long start = System.currentTimeMillis();
        crawl_codeK(date);
        long end = System.currentTimeMillis();

        System.out.println("걸린 시간 : " + (end - start) + " ms");
    }

    public static void crawl_codeK(String date) throws IOException {
        Map<Integer, String> jijum = new HashMap<>();
        jijum.put(1, "강남점");
        jijum.put(2, "구월점");
        jijum.put(3, "홍대점");

        for (int i = 1; i <= 3; i++) {
            String url = "http://www.code-k.co.kr/sub/code_sub04_1.html?R_JIJEM=S" + i + "&DIS_T=A&CHOIS_DATE=" + date;
            Document document = Jsoup.connect(url).get();

            System.out.println("-------------------------------- " + jijum.get(i) + " --------------------------------");
            System.out.println();

            Elements themes = document.select(".thema" + i);
            for (int j = 1; j <= themes.size(); j++) {
                Element times = document.getElementById("CQ" + j);
                Elements elements = times.select("li[class*=time]");

                System.out.println("---------------- " + jijum.get(i) + "\t" + themes.get(j - 1).text() + " ----------------");
                for (Element e : elements) {
                    if (e.text().charAt(0) == '★') {
                        System.out.println(e.text().substring(2) + "\t 예약 불가");
                    } else {
                        System.out.println(e.text().substring(2) + "\t 예약 가능");
                    }
                }
                System.out.println();
            }
        }
    }

}
