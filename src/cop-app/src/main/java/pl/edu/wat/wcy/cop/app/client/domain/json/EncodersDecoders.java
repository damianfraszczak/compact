/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.domain.json;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import pl.edu.wat.wcy.cop.app.shared.MSWiASymbol;
import pl.edu.wat.wcy.cop.app.shared.domain.*;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.*;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.TechnicalResourceDto;

import java.util.LinkedList;
import java.util.List;
// Defines the contract for encoders decoders.

public interface EncodersDecoders {

    ListOfGeoPointsEncoderDecoder ListOfGeoPointsEncoderDecoderInstance = GWT
            .create(ListOfGeoPointsEncoderDecoder.class);
    PoiEncoderDecoder PoiEncoderDecoderInstance = GWT.create(PoiEncoderDecoder.class);

    ADatP3ReportEncoderDecoder ADatP3ReportEncoderDecoderInstance = GWT.create(ADatP3ReportEncoderDecoder.class);
    App6aMilitaryUnitEncoderDecoder App6aMilitaryUnitEncoderDecoderInstance = GWT
            .create(App6aMilitaryUnitEncoderDecoder.class);
    MSWiAUnitEncoderDecoder MSWiAUnitEncoderDecoderInstance = GWT.create(MSWiAUnitEncoderDecoder.class);
    SearchAndRescueEncoderDecoder SAREncoderDecoderInstance = GWT.create(SearchAndRescueEncoderDecoder.class);
    SearchAndRescueCricleZoneEncoderDecoder SARCricleZoneEncoderDecoderInstance = GWT.create(SearchAndRescueCricleZoneEncoderDecoder.class);
    SearchAndRescueSegmentEncoderDecoder SARSegmentEncoderDecoderInstance = GWT.create(SearchAndRescueSegmentEncoderDecoder.class);
    GpxTraceEncoderDecoder GPX_TRACE_ENCODER_DECODER = GWT.create(GpxTraceEncoderDecoder.class);
    SearchUnitEncoderDecoder SEARCH_UNIT_ENCODER_DECODER = GWT.create(SearchUnitEncoderDecoder.class);
    TechnicalResourcesEncoderDecoder TECHNICAL_RESOURCE_ENCODER_DECODER = GWT.create(TechnicalResourcesEncoderDecoder.class);
    HumanResourcesEncoderDecoder HUMAN_RESOURCE_ENCODER_DECODER = GWT.create(HumanResourcesEncoderDecoder.class);
    CrisisEventEncoderDecoder CrisisEventEncoderDecoderInstance = GWT.create(CrisisEventEncoderDecoder.class);
    CrisisEventCricleZoneEncoderDecoder CrisisEventCricleZoneEncoderDecoderInstance = GWT
            .create(CrisisEventCricleZoneEncoderDecoder.class);
    CrisisEventMultiPointZoneEncoderDecoder CrisisEventMultiPointZoneEncoderDecoderInstance = GWT
            .create(CrisisEventMultiPointZoneEncoderDecoder.class);
    PoiCricleZoneEncoderDecoder PoiCricleZoneEncoderDecoderInstance = GWT.create(PoiCricleZoneEncoderDecoder.class);
    PoiMultiPointZoneEncoderDecoder PoiMultiPointZoneEncoderDecoderInstance = GWT
            .create(PoiMultiPointZoneEncoderDecoder.class);

    interface PoiEncoderDecoder extends JsonEncoderDecoder<PointOfInterestDto> {
    }

    interface SearchAndRescueEncoderDecoder extends JsonEncoderDecoder<SearchAndRescueDto> {
    }

    interface SearchAndRescueCricleZoneEncoderDecoder extends JsonEncoderDecoder<SearchAndRescueCircleZoneDto> {
    }

    interface SearchAndRescueSegmentEncoderDecoder extends JsonEncoderDecoder<SearchAndRescueSegmentDto> {
    }

    interface SearchUnitEncoderDecoder extends JsonEncoderDecoder<SearchUnitDto> {
    }

    interface HumanResourcesEncoderDecoder extends JsonEncoderDecoder<HumanResourceDto> {
    }

    interface TechnicalResourcesEncoderDecoder extends JsonEncoderDecoder<TechnicalResourceDto> {
    }

    interface GpxTraceEncoderDecoder extends JsonEncoderDecoder<GpxTraceDto> {
    }

    interface ADatP3ReportEncoderDecoder extends JsonEncoderDecoder<ADatP3ReportDto> {
    }

    interface App6aMilitaryUnitEncoderDecoder extends JsonEncoderDecoder<App6AMilitaryUnitDto> {
    }

    interface MSWiAUnitEncoderDecoder extends JsonEncoderDecoder<MSWiAUnitDto> {
    }

    interface CrisisEventEncoderDecoder extends JsonEncoderDecoder<CrisisEventDto> {
    }

    interface ListOfGeoPointsEncoderDecoder extends JsonEncoderDecoder<GeoPointsDtoList> {
    }

    interface CrisisEventCricleZoneEncoderDecoder extends JsonEncoderDecoder<CrisisEventCricleZoneDto> {
    }

    interface CrisisEventMultiPointZoneEncoderDecoder extends JsonEncoderDecoder<CrisisEventMultiPointZoneDto> {
    }

    interface PoiCricleZoneEncoderDecoder extends JsonEncoderDecoder<PoiCircleZoneDto> {
    }

    interface PoiMultiPointZoneEncoderDecoder extends JsonEncoderDecoder<PoiMultiPointZoneDto> {
    }

    interface MSWiASymbolEncoderDecoder extends JsonEncoderDecoder<MSWiASymbol> {
    }

    class GeoPointsDtoList {
        private List<GeoPointDto> points = new LinkedList<>();

        /**
         * @return the points
         */
        public List<GeoPointDto> getPoints() {
            return points;
        }

        /**
         * @param points
         *            the points to set
         */
        public void setPoints(List<GeoPointDto> points) {
            this.points = points;
        }

    }
}
