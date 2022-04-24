package ru.igar15.skillsaggregator.aggregator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HtmlPageMaker {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36" +
            " (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36";
    private static final String REQUEST_REFERRER = "http://www.google.com";

    public Document makePage(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .referrer(REQUEST_REFERRER)
                .get();
    }
}