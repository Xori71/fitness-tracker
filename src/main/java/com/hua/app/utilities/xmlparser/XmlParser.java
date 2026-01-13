package com.hua.app.utilities.xmlparser;

import com.hua.app.activityelements.Activity;
import com.hua.app.activityelements.Lap;
import com.hua.app.activityelements.ParsedActivity;
import com.hua.app.activityelements.Track;
import com.hua.app.activityelements.Trackpoint;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;

public class XmlParser {
    private static final double DEFAULT_VALUE = 0;
    
    public void TcxParse(Set<File> fileList, ArrayList<Activity> activityArray){
        for (File file : fileList) {
            try {
                DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
                Document document = dBuilder.parse(new FileInputStream(file));
                
                /* Get activities */
                NodeList activityList = document.getElementsByTagName("Activity");
                for(int i = 0; i < activityList.getLength(); i++){
                    Element activityElement = (Element) activityList.item(i); 
                    String sport = activityElement.getAttribute("Sport");
                    NodeList idList = activityElement.getElementsByTagName("Id");
                    String id = idList.item(0).getTextContent();
                    String date = getDateFromId(id);
                    ParsedActivity activity = new ParsedActivity(sport, date);
                    
                    /* Get laps */
                    NodeList laps = activityElement.getElementsByTagName("Lap");
                    for (int a = 0; a < laps.getLength(); a++) {
                        Element lapElement = (Element) laps.item(a);
                        Lap lap = new Lap();
                        
                        /* Get tracks */
                        NodeList trackList = lapElement.getElementsByTagName("Track");
                        for (int b = 0; b < trackList.getLength(); b++) {
                            Element trackElement = (Element) trackList.item(b);
                            Track track = new Track();
                            
                            /* Get trackpoints */
                            NodeList trackPoints = trackElement.getElementsByTagName("Trackpoint");
                            for (int j = 0; j < trackPoints.getLength(); j++) {
                                Element e = (Element) trackPoints.item(j);
                                
                                Instant timestamp = Instant.parse(getNodeValue(e.getElementsByTagName("Time")));
    
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
            } catch (Exception e) {
                System.out.println("Cannot open TCX file/s");
            }
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
