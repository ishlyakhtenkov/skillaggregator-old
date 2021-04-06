package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface HtmlDocumentCreator {

    Document getDocument(String docAddress) throws IOException;
}
