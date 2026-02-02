/**
 *
 */
package pl.edu.wat.wcy.cop.app.server.utils;

import pl.edu.wat.wcy.cop.domain.GeoPoint;
import pl.edu.wat.wcy.cop.domain.scenario.PointOfInterest;
import pl.edu.wat.wcy.cop.domain.scenario.PointOfInterestType;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
// Supplies poi data data.


public class PoiDataProvider {

    public static List<PointOfInterest> getStacjeChemiczne() {
        List<PointOfInterest> result = new LinkedList<>();
        try (Scanner in = new Scanner(PoiDataProvider.class.getResourceAsStream("grupy chemiczne 26-11-17.csv"),
                "UTF-8")) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] poiData = line.split(";");
                String name = poiData[0];
                String description = poiData[1];
                double lat = Double.parseDouble(poiData[2]);
                double lon = Double.parseDouble(poiData[3]);
                String group = "Grupy chemiczne";
                PointOfInterest poi = new PointOfInterest(name, description, new GeoPoint(lat, lon),
                        PointOfInterestType.CHEMICAL);
                poi.setMapGroup(group);
                result.add(poi);
            }
        }
        return result;
    }

    public static List<PointOfInterest> getZakladyZagrozenia() {
        List<PointOfInterest> result = new LinkedList<>();
        try (Scanner in = new Scanner(
                PoiDataProvider.class.getResourceAsStream("Zakłady - zagrozenia - substancje.csv"), "UTF-8")) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] poiData = line.split(";");

                if (poiData.length > 3) {
                    String name = poiData[0];
                    String description = poiData[1];
                    double lat = Double.parseDouble(poiData[2]);
                    double lon = Double.parseDouble(poiData[3]);
                    System.out.println(lat + " " + lon);
                    String group = "Zakłady - zagrozenia - substancje";
                    PointOfInterest poi = new PointOfInterest(name, description, new GeoPoint(lat, lon),
                            PointOfInterestType.FACTORY);
                    poi.setMapGroup(group);
                    result.add(poi);
                }

            }
        }
        return result;
    }

    public static List<PointOfInterest> getSopos() {
        List<PointOfInterest> result = new LinkedList<>();
        try (Scanner in = new Scanner(PoiDataProvider.class.getResourceAsStream("Stacje SAPOS.csv"), "UTF-8")) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] poiData = line.split(";");
                String name = poiData[0];
                double lat = Double.parseDouble(poiData[1]);
                double lon = Double.parseDouble(poiData[2]);
                String description = poiData[3];
                String group = "Stacje SAPOS";
                PointOfInterest poi = new PointOfInterest(name, description, new GeoPoint(lat, lon),
                        PointOfInterestType.SAPOS);
                poi.setMapGroup(group);
                result.add(poi);
            }
        }
        return result;

    }

    public static void main(String[] args) {
        getSopos();
        getStacjeChemiczne();
        getZakladyZagrozenia();
    }
}
