package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.SearchUnitType;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.TerrainCategory;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan.SearchAndRescueSearchPlanEntryDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan.SearchUnitDomainDto;

import java.util.Date;
import java.util.List;
// Defines the contract for search and rescue search plan entry dto property access.

public interface SearchAndRescueSearchPlanEntryDtoPropertyAccess extends PropertyAccess<SearchAndRescueSearchPlanEntryDto> {
    SearchAndRescueSearchPlanEntryDtoPropertyAccess INSTANCE = GWT.create(SearchAndRescueSearchPlanEntryDtoPropertyAccess.class);


    ModelKeyProvider<SearchAndRescueSearchPlanEntryDto> key();


    @Editor.Path("segment.searchEnded")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, Boolean> searchEnded();

    @Editor.Path("segment.segment.area")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, Double> area();

    @Editor.Path("segment.segment.name")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, String> name();

    @Editor.Path("segment.poa")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, Double> poc();

    @Editor.Path("segment.segment.type")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, TerrainCategory> terrainCategory();

    @Editor.Path("searchUnit.type")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, SearchUnitType> searchUnitType();

    @Editor.Path("searchUnit.humanResources")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, List<HumanResourceDto>> crew();


    ValueProvider<SearchAndRescueSearchPlanEntryDto, List<SearchUnitDomainDto>> searchUnits();

    @Editor.Path("segment.formationSquad")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, String> formationSquad();

    @Editor.Path("segment.communicationResources")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, String> communicationResources();

    @Editor.Path("segment.leaveTime")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, Date> leaveTime = new ValueProvider<SearchAndRescueSearchPlanEntryDto, Date>() {

        @Override
        public Date getValue(SearchAndRescueSearchPlanEntryDto object) {
            if (object.getSegment() == null || object.getSegment().getLeaveTime() == null) {
                return null;
            }
            return new Date(object.getSegment().getLeaveTime());
        }

        @Override
        public void setValue(SearchAndRescueSearchPlanEntryDto object, Date value) {
            if (object.getSegment() == null) {
                return;
            }
            if (value == null) {
                object.getSegment().setLeaveTime(null);
            }else{
                object.getSegment().setLeaveTime(value.getTime());
            }

        }

        @Override
        public String getPath() {
            return "leaveTime";
        }
    };

    @Editor.Path("segment.backTime")
    ValueProvider<SearchAndRescueSearchPlanEntryDto, Date> backTime = new ValueProvider<SearchAndRescueSearchPlanEntryDto, Date>() {

        @Override
        public Date getValue(SearchAndRescueSearchPlanEntryDto object) {
            if (object.getSegment() == null || object.getSegment().getBackTime() == null) {
                return null;
            }
            return new Date(object.getSegment().getBackTime());
        }

        @Override
        public void setValue(SearchAndRescueSearchPlanEntryDto object, Date value) {
            if (object.getSegment() == null) {
                return;
            }
            if (value == null) {
                object.getSegment().setBackTime(null);
            } else {
                object.getSegment().setBackTime(value.getTime());
            }

        }

        @Override
        public String getPath() {
            return "backTime";
        }
    };

    ValueProvider<SearchAndRescueSearchPlanEntryDto, Double> ec();

    ValueProvider<SearchAndRescueSearchPlanEntryDto, Double> pod();

    ValueProvider<SearchAndRescueSearchPlanEntryDto, Double> esr();

}