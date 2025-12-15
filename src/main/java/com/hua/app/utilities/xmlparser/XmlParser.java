package com.hua.app.utilities.xmlparser;

import com.hua.app.activityelements.Activity;
import com.hua.app.activityelements.Lap;
import com.hua.app.activityelements.Track;
import com.hua.app.activityelements.Trackpoint;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class XmlParser{
    public static ArrayList<Activity> TcxParse(String tcxFile, ArrayList<Activity> activityArray){
        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
            // get Doc
            Document document = dBuilder.parse(new FileInputStream(tcxFile));

            // Normilize the xml structure
            
            //document.getDocumentElement().normalize();
            
            // Get all the elements by tag name
            NodeList activityList = document.getElementsByTagName("Activity");

            //activity
            int count = 1;
            for(int i = 0; i < activityList.getLength(); i++){
                //Node activity = activityList.item(i);
                Element activityElement = (Element) activityList.item(i);
                String sport = activityElement.getAttribute("Sport");
                Activity activity = new Activity(sport);

                NodeList laps = activityElement.getElementsByTagName("Lap");
                //laps
                for (int a = 0; a < laps.getLength(); a++) {
                    Element lapElement = (Element) laps.item(a);
                    Lap lap = new Lap();

                    NodeList trackList = lapElement.getElementsByTagName("Track");
                    //tracks
                    for (int b = 0; b < trackList.getLength(); b++) {
                        Element trackElement = (Element) trackList.item(b);
                        Track track = new Track();

                        NodeList trackPoints = trackElement.getElementsByTagName("Trackpoint");
                        //trackpoints

                        for (int j = 0; j < trackPoints.getLength(); j++) {
                            Element e = (Element) trackPoints.item(j);
                            
                            String timestamp = getNodeValue(e.getElementsByTagName("Time"));
                            String[] splStrings = timestamp.split("[T.]");
                            timestamp = splStrings[1];

                            double distance = getDoublesafe(e, "DistanceMeters", 0);
                            //double distance = Double.parseDouble(getNodeValue(e.getElementsByTagName("DistanceMeters")));
                            double altitude = getDoublesafe(e, "AltitudeMeters", 0);
                            //double altitude = Double.parseDouble(getNodeValue(e.getElementsByTagName("AltitudeMeters")));

                            NodeList posList = e.getElementsByTagName("Position");
                            Element posElement = (Element) posList.item(0);
                            //double kati = getDoublesafe(posElement, "latitude", 0);
                            double latitude =  getDoublesafe(posElement, "LatitudeDegrees", 0);
                            //double latitude =  Double.parseDouble(getNodeValue(posElement.getElementsByTagName("LatitudeDegrees")));
                            double longtitude = getDoublesafe(posElement, "LongitudeDegrees", 0);
                            //double longtitude = Double.parseDouble(getNodeValue(posElement.getElementsByTagName("LongitudeDegrees")));


                            NodeList hrbList = e.getElementsByTagName("HeartRateBpm");
                            Element hrbElement = (Element) hrbList.item(0);
                            //NodeList hrElement = (Element) e.getElementsByTagName("HeartRateBpm");
                            int heartRate = getIntsafe(hrbElement, "Value", 0);
                            //int heartRate = Integer.parseInt(getNodeValue(hrbElement.getElementsByTagName("Value")));
                            
                            System.out.println(count);
                            count++;
                            track.addTrackpoint(new Trackpoint(latitude, longtitude, altitude, distance, heartRate, timestamp));
                            
                        }
                        lap.addTrack(track);
                    }
                    activity.addLap(lap);
                }    
                activityArray.addLast(activity);
            }
            return activityArray;

        } catch (Exception e) {
            System.out.println("no");
            e.printStackTrace();
            return null;
        }
    }
    public static String getNodeValue(NodeList n) {
        //n.item(0) is e.g. <DistanceMeters>,
        return n.item(0).getChildNodes().item(0).getNodeValue();

    }
    
    private static double getDoublesafe(Element e,String tagName, double defaultValue ) {
        double value;
        try {
            value = Double.parseDouble(getNodeValue(e.getElementsByTagName(tagName)));
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
            return defaultValue;
        }
    }
    private static int getIntsafe(Element e,String tagName, int defaultValue ) {
        int value;
        try {
            value = Integer.parseInt(getNodeValue(e.getElementsByTagName(tagName)));
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
            return defaultValue;
        }
    }
}
