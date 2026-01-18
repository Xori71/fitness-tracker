package com.hua.app.data;

import com.hua.app.basicelements.Lap;
import com.hua.app.basicelements.Activity;
import com.hua.app.basicelements.ParsedActivity;
import com.hua.app.basicelements.Track;
import com.hua.app.basicelements.Trackpoint;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

public class XmlParser {
    private static final double DEFAULT_VALUE = 0;
    private final DocumentBuilder dBuilder;
    
    public XmlParser() throws ParserConfigurationException {
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw e;
        }
    }
    
    public void parseFile(File file, ArrayList<Activity> activityArray) throws SAXException {
        try {
            Document document = dBuilder.parse(new FileInputStream(file));
            
            /* Get activities */
            NodeList activityList = document.getElementsByTagName("Activity");
            if (activityList.getLength() == 0) {
                throw new IllegalArgumentException();
            }
            
            for(int i = 0; i < activityList.getLength(); i++){
                Element activityElement = (Element) activityList.item(i); 
                String sport = activityElement.getAttribute("Sport");
                
                NodeList idList = activityElement.getElementsByTagName("Id");
                String date;
                if (idList.getLength() != 0) {
                    String id = idList.item(0).getTextContent();
                    date = getDateFromId(id);
                } else {
                    date = "";
                }
                
                ParsedActivity activity = new ParsedActivity(sport, date);
                
                /* Get laps */
                NodeList lapList = activityElement.getElementsByTagName("Lap");
                if (lapList.getLength() == 0) {
                    throw new IllegalArgumentException();
                }
                
                for (int a = 0; a < lapList.getLength(); a++) {
                    Element lapElement = (Element) lapList.item(a);
                    Lap lap = new Lap();
                    
                    /* Get tracks */
                    NodeList trackList = lapElement.getElementsByTagName("Track");
                    if (trackList.getLength() == 0) {
                        throw new IllegalArgumentException();
                    }
                    
                    for (int b = 0; b < trackList.getLength(); b++) {
                        Element trackElement = (Element) trackList.item(b);
                        Track track = new Track();
                        
                        /* Get trackpoints */
                        NodeList trackPoints = trackElement.getElementsByTagName("Trackpoint");
                        if (trackPoints.getLength() == 0) {
                            throw new IllegalArgumentException();
                        }
                        
                        for (int j = 0; j < trackPoints.getLength(); j++) {
                            Element e = (Element) trackPoints.item(j);
                            
                            NodeList timeList = e.getElementsByTagName("Time");
                            Instant timestamp;
                            if (timeList.getLength() != 0) {
                                timestamp = Instant.parse(getNodeValue(e.getElementsByTagName("Time")));
                            } else {
                                timestamp = null;
                            }
                            
                            double distance = getDoubleSafely(e, "DistanceMeters");
                            double altitude = getDoubleSafely(e, "AltitudeMeters");
    
                            NodeList posList = e.getElementsByTagName("Position");
                            Element posElement = (Element) posList.item(0);
                            double latitude =  getDoubleSafely(posElement, "LatitudeDegrees");
                            double longtitude = getDoubleSafely(posElement, "LongitudeDegrees");
    
                            NodeList hrbList = e.getElementsByTagName("HeartRateBpm");
                            Element hrbElement = (Element) hrbList.item(0);
                            int heartRate = getIntSafely(hrbElement, "Value");
                            
                            track.addTrackpoint(new Trackpoint(latitude, longtitude, altitude, distance, heartRate, timestamp));
                        }
                        lap.addTrack(track);
                    }
                    activity.addLap(lap);
                }    
                activityArray.addLast(activity);
            }
        } catch (IOException e) {
            System.err.println("Could not open TCX file");
        } catch (SAXException e) {
            throw e;
        }
    }
    
    public String getNodeValue(NodeList n) {
        //n.item(0) is e.g. <DistanceMeters>,
        return n.item(0).getChildNodes().item(0).getNodeValue();

    }
    
    private double getDoubleSafely(Element e, String tagName) {
        try {
            return Double.parseDouble(getNodeValue(e.getElementsByTagName(tagName)));
        } catch (Exception ex) {
            return DEFAULT_VALUE;
        }
    }
    
    private int getIntSafely(Element e, String tagName) {
        try {
            return Integer.parseInt(getNodeValue(e.getElementsByTagName(tagName)));
        } catch (Exception ex) {
            return (int) DEFAULT_VALUE;
        }
    }
    
    private String getDateFromId(String Id) {
        String[] tokens = Id.split("T");
        return tokens[0];
    }
}
