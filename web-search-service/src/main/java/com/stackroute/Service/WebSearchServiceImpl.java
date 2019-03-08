package com.stackroute.Service;

import com.stackroute.domain.SearchDocument;
import com.stackroute.domain.UIDocument;
import com.stackroute.exception.DomainNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WebSearchServiceImpl implements WebSearchService  {

    public static final String USER_AGENT = "Chrome Morzilla Safari";

    public List<SearchDocument> getUrls(UIDocument uiDocument) throws IOException, DomainNotFoundException {

        List<SearchDocument> searchDocumentList = new ArrayList<>();
        String[] conceptArray = uiDocument.getConceptName();
        if(uiDocument.getDomain() == null || uiDocument.getDomain() == "")
        {
            throw new DomainNotFoundException();
        }

        for (int i = 0; i < conceptArray.length; i++) {
            SearchDocument searchDocument = new SearchDocument();
            searchDocument.setId(UUID.randomUUID().toString());
            searchDocument.setDomain(uiDocument.getDomain());
            searchDocument.setConceptName(conceptArray[i]);
            Document doc = Jsoup.connect("https://google.com/search?q=" + conceptArray[i]).userAgent(USER_AGENT).get();
            for (Element result : doc.select("h3.r>a")) {
                String url = result.attr("href");
                url = url.substring(7);
                searchDocument.setUrl(url);
                searchDocumentList.add(searchDocument);
            }
        }
        return searchDocumentList;
    }
}