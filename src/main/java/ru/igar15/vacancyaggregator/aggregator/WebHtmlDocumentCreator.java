package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebHtmlDocumentCreator implements HtmlDocumentCreator{

    @Override
    public Document getDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")
                .referrer("http://www.google.com")
                .get();
    }
}
