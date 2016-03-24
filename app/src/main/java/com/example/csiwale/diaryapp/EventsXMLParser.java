package com.example.csiwale.diaryapp;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by csiwale on 2/17/2016.
 */
public class EventsXMLParser {
    // We don't use namespaces
    private static final String ns = null;

    public EventsXMLParser() {
    }

    public ArrayList parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readEvents(parser);
        } finally {
            in.close();
        }
    }

    private ArrayList readEvents(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "events");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("event")) {
                entries.add(readEvent(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private Event readEvent(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "event");
        String eventType = null;
        String eventDay = null;
        String eventTime = null;
        String eventNote = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("eventType")) {
                eventNote = readEventType(parser);
            } else if (name.equals("eventDay")) {
                eventDay = readEventDay(parser);
            } else if (name.equals("eventTime")) {
                eventTime = readEventTime(parser);
            } else if (name.equals("eventNote")) {
                eventNote = readEventNote(parser);
            } else {
                skip(parser);
            }
        }
        return new Event(eventNote, eventDay, eventTime, eventNote);
    }

    // Processes eventType tags in the feed.
    private String readEventType(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "eventType");
        String eventType = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "eventType");
        return eventType;
    }

    // Processes eventDay tags in the feed.
    private String readEventDay(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "eventDay");
        String eventDay = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "eventDay");
        return eventDay;
    }

    private String readEventTime(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "eventTime");
        String eventTime = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "eventTime");
        return eventTime;
    }

    private String readEventNote(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "eventNote");
        String eventNote = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "eventNote");
        return eventNote;
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
