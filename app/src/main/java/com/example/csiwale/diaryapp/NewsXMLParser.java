package com.example.csiwale.diaryapp;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by csiwale on 2/17/2016.
 */
public class NewsXMLParser {
    // We don't use namespaces
    private static final String ns = null;

    public NewsXMLParser() {
    }

    public ArrayList parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readNews(parser);
        } finally {
            in.close();
        }
    }

    private ArrayList readNews(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "news");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("news_item")) {
                entries.add(readNewsItem(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private NewsItem readNewsItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "news_item");
        String title = null;
        String keywords = null;
        String overview = null;
        String createdAt = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                title = readTitle(parser);
            } else if (name.equals("keywords")) {
                keywords = readKeywords(parser);
            } else if (name.equals("overview")) {
                overview = readOverview(parser);
            } else if (name.equals("createdAt")) {
                createdAt = readCreatedAt(parser);
            } else {
                skip(parser);
            }
        }
        return new NewsItem(title, keywords, overview, createdAt);
    }

    // Processes title tags in the feed.
    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }

    // Processes keywords tags in the feed.
    private String readKeywords(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "keywords");
        String keywords = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "keywords");
        return keywords;
    }

    private String readOverview(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "overview");
        String overview = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "overview");
        return overview;
    }

    private String readCreatedAt(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "createdAt");
        String createdAt = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "createdAt");
        return createdAt;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }


}
