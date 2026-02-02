/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.visualisation.tree;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.menu.Menu;
import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ADatP3ReportClientModel;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ADatP3ReportClientModel.AdatP3FeatureType;
import pl.edu.wat.wcy.cop.app.client.ol.OLStyleManager;
import pl.edu.wat.wcy.cop.app.client.ol.forms.OLStyleForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.ADatP3CoordinatesForm;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.DefaultTreeElementObject;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreeElement;
import pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel;
import pl.edu.wat.wcy.cop.app.client.utils.CopLogger;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
import pl.edu.wat.wcy.cop.app.shared.domain.ADatP3CoordinatesDto;
import pl.edu.wat.wcy.cop.app.shared.domain.UniqueObject;
import pl.edu.wat.wcy.cop.app.shared.ol.OLStyleOptions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
// Renders a dat 3 tree panel UI.


public class ADatP3TreePanel extends TreePanel<UniqueObject<String>, String> {
    private static GisImages GIS_IMAGES = GisImages.INSTANCE;
    private List<ADatP3ReportClientModel> reports;
    private DefaultTreeElement<UniqueObject<String>, String> root;

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ui.tree.TreePanel#loadTreeDataFromRoot(
     * com.google.gwt.core.client.Callback)
     */
    @Override
    protected void loadTreeDataFromRoot(Callback<TreeElement<UniqueObject<String>, String>, Exception> callback) {
        root = new DefaultTreeElement<UniqueObject<String>, String>(
                new DefaultTreeElementObject(MESSAGES.appmanagementtabs_reports_tree_root()),
                GIS_IMAGES.layers_operational());
        CopLogger.getInstance().debug(this, "Reports " + reports);
        if (reports != null) {
            for (ADatP3ReportClientModel object : reports) {
                for (TreeElement<UniqueObject<String>, String> child : getNodes(object))
                    root.addChild(child);
            }
        }
        callback.onSuccess(root);

    }

    /**
     * @param object
     * @return
     */
    private List<TreeElement<UniqueObject<String>, String>> getNodes(ADatP3ReportClientModel object) {
        List<TreeElement<UniqueObject<String>, String>> childs = new LinkedList<>();
        object.getReportFeatures().entrySet().forEach(e -> {
            childs.add((TreeElement<UniqueObject<String>, String>) getNode(object));
        });
        return childs;
    }

    /**
     * @param object
     * @return
     */
    private TreeElement<? extends UniqueObject<String>, String> getNode(ADatP3ReportClientModel object) {
        List<Feature> features = new ArrayList<>();
        if (object.getFeatures(AdatP3FeatureType.RELEASE) != null) {
            for (Feature feature : object.getFeatures(AdatP3FeatureType.RELEASE)) {
                features.add(feature);
            }
        }
        for (Feature feature : object.getFeatures(AdatP3FeatureType.HAZARD)) {
            features.add(feature);
        }
        return new ADatP3TreeElement(object, features.toArray(new Feature[features.size()]), null);
    }

    /**
     * @return the reports
     */
    public List<ADatP3ReportClientModel> getReports() {
        return reports;
    }

    /**
     * @param reports the reports to set
     */
    public void setReports(List<ADatP3ReportClientModel> reports) {
        this.reports = reports;
         Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                reloadData();
            }
        });
    }

    @Override
    protected Menu getContextMenuForObject(TreeElement<UniqueObject<String>, String> selectedElement) {
        Menu menu = new Menu();
        menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.report_editStyle(), selectEvent -> {
            ADatP3ReportClientModel aDatP3ReportClientModel = (ADatP3ReportClientModel) selectedElement.getObject();
            OLStyleOptions olStyleOptions = new OLStyleOptions();
            OLStyleForm styleForm = new OLStyleForm(olStyleOptions, new SelectEvent.SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    updateReportStyle(aDatP3ReportClientModel, olStyleOptions);
                }
            });
        }, GIS_IMAGES.layer_edit()));
//		menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.report_editData(), selectionEvent -> {}, GIS_IMAGES.layer_edit()));
        menu.add(GxtComponentsUtils.createMenuItem(MESSAGES.report_showData(), selectionEvent -> {
            ADatP3ReportClientModel aDatP3ReportClientModel = (ADatP3ReportClientModel) selectedElement.getObject();
            ADatP3CoordinatesDto aDatP3CoordinatesDto = new ADatP3CoordinatesDto(aDatP3ReportClientModel.getaDatP3());
            ADatP3CoordinatesForm aDatP3ReportForm = new ADatP3CoordinatesForm(aDatP3CoordinatesDto, selectEvent -> {
            });
        }, GIS_IMAGES.layers_vector()));
        return menu;
    }

    private void updateReportStyle(ADatP3ReportClientModel aDatP3ReportClientModel, OLStyleOptions olStyleOptions) {
        if (aDatP3ReportClientModel.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.RELEASE) != null) {
            for (Feature feature : aDatP3ReportClientModel.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.RELEASE)) {
                feature.setStyle(OLStyleManager.createStyle(olStyleOptions,
                        ADatP3ReportClientModel.AdatP3FeatureType.RELEASE,
                        aDatP3ReportClientModel.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.RELEASE)));

            }
        }

        for (Feature feature : aDatP3ReportClientModel.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.HAZARD)) {
            feature.setStyle(OLStyleManager.createStyle(olStyleOptions,
                    ADatP3ReportClientModel.AdatP3FeatureType.HAZARD,
                    aDatP3ReportClientModel.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.HAZARD)));
        }
    }
}
