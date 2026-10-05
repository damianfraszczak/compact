package pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.impl.cldr.DateTimeFormatInfoImpl_en;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.inject.Singleton;
import ol.Coordinate;
import pl.edu.wat.wcy.cop.app.client.ol.OLFeatureBuilder;
import pl.edu.wat.wcy.cop.app.client.ol.gis.coords.MGRSPoint;

import java.util.*;

@Singleton
// Parses a dat 3.
public class ADatP3Parser {

    public ADatP3 parseADatP3Content(String content) {
        ADatP3 aDatP3 = new ADatP3();
        aDatP3.setReport(content);
        aDatP3.setCbrnType(parseCbrnType(content));
        aDatP3.setFoxtrot(parseFoxtrot(content));
        aDatP3.setMapCenter(parseMapCenter(aDatP3.getFoxtrot()));
        aDatP3.setPapa(parsePapa(aDatP3.getCbrnType(), content));
        aDatP3.setMgrsCoordinates(parsePapax(content));
        aDatP3.setLonLatCoordinates(setLonLatCoordinates(aDatP3));
        aDatP3.setDelta(setDelta(content));
        return aDatP3;
    }

    private Coordinate parseMapCenter(String foxtrot) {
        MGRSPoint mgrs = new MGRSPoint(foxtrot);
        double[] pointDouble = mgrs.toLatLon();
        return OLFeatureBuilder.createCoordinate(pointDouble[0], pointDouble[1]);
    }

    private CBRNType parseCbrnType(String toParse) {
        RegExp pattern = RegExp.compile("CBRNTYPE/(.+)//");
        MatchResult matcher = pattern.exec(toParse);
        boolean matchFound = matcher != null;

        if (matchFound) {
            return CBRNType.findByValue(matcher.getGroup(1));
        }
        return null;
    }

    private String parseFoxtrot(String toParse) {
        RegExp pattern = RegExp.compile("FOXTROT/.*:(.+)/.*//");
        MatchResult matcher = pattern.exec(toParse);
        boolean matchFound = matcher != null;

        if (matchFound) {
            return matcher.getGroup(1);
        }
        return null;
    }

    private Papa parsePapa(CBRNType cbrnType, String toParse) {
        if (cbrnType.equals(CBRNType.CHEM) || cbrnType.equals(CBRNType.BIO)) {
            PapaA papaA = new PapaA();
            RegExp pattern = RegExp.compile("PAPAA/(.+)/.*/.*/.*//");
            MatchResult matcher = pattern.exec(toParse);
            boolean matchFound = matcher != null;
            if (matchFound) {
                papaA.setReleaseAresRadius(getDoubleFromKm(matcher.getGroup(1)));
            }

            pattern = RegExp.compile("PAPAA/.*/.*/(.+)/.*//");
            matcher = pattern.exec(toParse);
            if (matchFound) {
                papaA.setHazardAreaDistance(getDoubleFromKm(matcher.getGroup(1)));
            }
            return papaA;
        } else if (cbrnType.equals(CBRNType.RAD) || cbrnType.equals(CBRNType.NUC)) {
            PapaR papaR = new PapaR();
            RegExp pattern = RegExp.compile("PAPAR/(.+)/.*/.*/.*//");
            MatchResult matcher = pattern.exec(toParse);
            papaR.setHazardAreaDistanceR1(getDoubleFromKm(matcher.getGroup(1)));

            pattern = RegExp.compile("PAPAR/.*/(.+)/.*/.*//");
            matcher = pattern.exec(toParse);
            papaR.setHazardAreaDistanceR2(getDoubleFromKm(matcher.getGroup(1)));

            pattern = RegExp.compile("PAPAR/.*/.*/(.+)/.*//");
            matcher = pattern.exec(toParse);
            papaR.setHazardAreaDistanceR3(getDoubleFromKm(matcher.getGroup(1)));

            pattern = RegExp.compile("PAPAR/.*/.*/.*/(.+)//");
            matcher = pattern.exec(toParse);
            papaR.setReleaseAresRadius(getDoubleFromKm(matcher.getGroup(1)));
            return papaR;
        } else {
            return null;
        }
    }

    private Float getDoubleFromKm(String number) {
        Float area = 0f;
        if (number.endsWith("KM")) {
            number = number.substring(0, number.length() - 2);
            area = Float.parseFloat(number) * 1000;
        } else if (number.endsWith("M")) {
            number = number.substring(0, number.length() - 1);
            area = Float.parseFloat(number);
        } else {
            return null;
        }
        return area;
    }

    private HashMap<String, List<String>> parsePapax(String toParse) {
        HashMap<String, List<String>> mgrsCoord = new HashMap<>();
        RegExp pattern = RegExp.compile("(PAPAX/.*//)", "g");
        MatchResult matcher = pattern.exec(toParse);
        int i = 0;
        while (pattern.getLastIndex() > 0) {
            String papax = matcher.getGroup(0);
            List<String> mgrsCoordinates = getPapax(papax);
            mgrsCoord.put(String.valueOf(i), mgrsCoordinates);
            i++;
            matcher = pattern.exec(toParse);
        }
        return mgrsCoord;
    }

    private List<String> getPapax(String papax) {
        List<String> mgrsCoordinates;
        mgrsCoordinates = new ArrayList<>();
        papax = papax.replaceAll("GRID", "MGRS");
        String pattern1 = "MGRS:";
        String pattern2 = "/";
        RegExp patternCoord = RegExp.compile(pattern1 + "(.*?)" + pattern2, "g"); // g=global:
        // =
        // all-matches
        MatchResult matcherCoord = patternCoord.exec(papax);

        int j = 0;
        String mgrs = "";
        while (patternCoord.getLastIndex() > 0) {
            mgrs = matcherCoord.getGroup(0);
            mgrsCoordinates.add(mgrs.substring(5, mgrs.length() - 1));
            j++;
            matcherCoord = patternCoord.exec(papax);
        }
        return mgrsCoordinates;
    }

    private HashMap<String, List<Coordinate>> setLonLatCoordinates(ADatP3 aDatP3) {
        HashMap<String, List<String>> mgrss = aDatP3.getMgrsCoordinates();
        HashMap<String, List<Coordinate>> Coordinates = new HashMap<>();
        Set<String> ids = mgrss.keySet();
        List<Coordinate> CoordinateList = new ArrayList<>();
        for (String id : ids) {
            for (String mgrs : mgrss.get(id)) {
                MGRSPoint mgrsCoordinate = new MGRSPoint(mgrs);
                double[] coord = mgrsCoordinate.toLatLon();

                Coordinate Coordinate = OLFeatureBuilder.createCoordinate(coord[0], coord[1]);
                CoordinateList.add(Coordinate);
            }
            Coordinates.put(id, CoordinateList);
            CoordinateList = new ArrayList<>();
        }
        return Coordinates;
    }

    private Date setDelta(String toParse) {
        RegExp pattern = RegExp.compile("DELTA/(.+)//");
        MatchResult matcher = pattern.exec(toParse);
        boolean matchFound = matcher != null;

        if (matchFound) {
            String dateString = matcher.getGroup(1);
            return getDateTimeFormat().parse(dateString.replaceAll("Z", ""));
        }
        return null;
    }

    private DateTimeFormat getDateTimeFormat() {
        return new DateTimeFormat("ddHHmmMMMyyyy", new DateTimeFormatInfoImpl_en()) {
        };

    }
}
