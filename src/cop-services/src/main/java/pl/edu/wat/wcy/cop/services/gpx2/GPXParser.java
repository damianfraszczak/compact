package pl.edu.wat.wcy.cop.services.gpx2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
// Parses gpx.

public class GPXParser {

    private ArrayList<GPXWaypoint> lstWaypoints;
    private ArrayList<GPXTrack> tracks;


    public GPXParser(BufferedReader buff) throws IOException {
        lstWaypoints = new ArrayList<GPXWaypoint>();
        tracks = new ArrayList<GPXTrack>();
        parse(buff);
    }

    public GPXParser(String filename) throws FileNotFoundException, IOException {
        lstWaypoints = new ArrayList<GPXWaypoint>();
        tracks = new ArrayList<GPXTrack>();
        BufferedReader in
                = new BufferedReader(new FileReader(filename));

        parse(in);
    }

    public GPXParser(File infile) throws FileNotFoundException, IOException {
        lstWaypoints = new ArrayList<GPXWaypoint>();
        tracks = new ArrayList<GPXTrack>();
        BufferedReader in = new BufferedReader(new FileReader(infile));
        parse(in);
    }

    public ArrayList<GPXWaypoint> getWaypoints() {
        return lstWaypoints;
    }

    public ArrayList<GPXTrack> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<GPXTrack> tracks) {
        this.tracks = tracks;
    }

    private void parse(Reader reader) throws IOException {
        // Parse into document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document doc = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new IOException("Error parsing GPX file");
        }

        InputSource is = new InputSource();
        is.setCharacterStream(reader);

        try {
            doc = db.parse(is);
        } catch (SAXException e) {
            throw new IOException("Error parsing GPX file");
        } catch (IOException e) {
            throw new IOException("Error parsing GPX file");
        }

        NodeList records = doc.getElementsByTagName("wpt");

        for (int i = 0; i < records.getLength(); i++) {
            Element record = (Element) records.item(i);
            lstWaypoints.add(new GPXWaypoint(record));
        }

         records = doc.getElementsByTagName("trk");

        for (int i = 0; i < records.getLength(); i++) {
            Element record = (Element) records.item(i);
            tracks.add(new GPXTrack(record));
        }
    }





}