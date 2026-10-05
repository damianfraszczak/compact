/**
 *
 */
package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wat.wcy.cop.app.server.EntityDtoConverter;
import pl.edu.wat.wcy.cop.app.server.utils.PoiDataProvider;
import pl.edu.wat.wcy.cop.app.shared.GwtEndpoints;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioDto;
import pl.edu.wat.wcy.cop.app.shared.response.ErrorCode;
import pl.edu.wat.wcy.cop.domain.scenario.Scenario;
import pl.edu.wat.wcy.cop.services.VisualizationService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
// Handles visualisation data provider requests.
public class VisualisationDataProviderController {

    @Autowired
    private EntityDtoConverter converter;
    @Autowired
    private VisualizationService service;

    @RequestMapping(value = GwtEndpoints.GWT_USER_SERVICE, method = RequestMethod.GET, produces = {
            "application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String user = auth.getPrincipal().toString();
            if(user.equalsIgnoreCase("anonymousUser")){
                user = "";
            }
            return WebUtil.createOkEntity(user);

        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = GwtEndpoints.GWT_VISUALZIATION_SERVICE, method = RequestMethod.POST, produces = {
            "application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> createVisualisation(@RequestBody ScenarioDto scenario) {
        //always add default poi
        Scenario scenarioEntity = toEntity(scenario, true);
        getDefaultScenarioEntities(scenarioEntity);
        return WebUtil.createOkEntity(service.createScenario(scenarioEntity).getId());
    }

    @RequestMapping(value = GwtEndpoints.GWT_VISUALZIATION_SERVICE, method = RequestMethod.DELETE, produces = {
            "application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> clearScenario(Long id) {
        return WebUtil.createOkEntity(service.clearScenario(id));
    }

    @RequestMapping(value = GwtEndpoints.GWT_VISUALZIATION_SERVICE, method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public ResponseEntity<?> getVisualizationForId(long id) {
        Scenario scenario = service.getScenarioById(id);
        if (scenario != null) {
            return WebUtil.createOkEntity(toDto(scenario));
        } else
            return WebUtil.createErrorEntity(HttpStatus.NOT_FOUND, ErrorCode.GLOBAL, "Scenario not found for id " + id);
    }

    @RequestMapping(value = GwtEndpoints.GWT_VISUALZIATION_SERVICE_GET_LIST, method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public ResponseEntity<?> getVisualizationList() {
        return WebUtil.createOkEntity(service.getScenarioIds());
    }

    /**
     *
     * @param dto
     * @return
     */
    private Scenario toEntity(ScenarioDto dto) {
        return converter.toEntity(dto, Scenario.class);
    }

    /**
     *
     * @param scenario
     * @return
     */
    private Scenario toEntity(ScenarioDto dto, boolean skipId) {
        return converter.toEntity(dto, Scenario.class, skipId);
    }

    /**
     * /**
     *
     */
    private ScenarioDto toDto(Scenario entity) {
        ScenarioDto dto = converter.toDto(entity, ScenarioDto.class);
        return dto;
    }

    /**
     * @param scenarioList
     * @return
     */
    private List<ScenarioDto> toDtos(List<Scenario> scenarioList) {
        return scenarioList.stream().map(x -> toDto(x)).collect(Collectors.toList());
    }

    private void getDefaultScenarioEntities(Scenario scenarioEntity) {

//        scenarioEntity.getPoi().addAll(PoiDataProvider.getSopos());
//        scenarioEntity.getPoi().addAll(PoiDataProvider.getStacjeChemiczne());
//        scenarioEntity.getPoi().addAll(PoiDataProvider.getZakladyZagrozenia());
    }


}
