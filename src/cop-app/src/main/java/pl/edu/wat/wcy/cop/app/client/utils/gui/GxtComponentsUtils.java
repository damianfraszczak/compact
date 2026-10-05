/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.gui;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.menu.*;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.AppImages;
// Provides gxt components utilities.


public class GxtComponentsUtils {

    private static final AppImages IMAGES = AppImages.INSTANCE;
    private static final Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();

    /**
     *
     * @param handler
     * @return
     */
    public static MenuItem createAddMenuItem(SelectionHandler<Item> handler) {
        return createMenuItem(MESSAGES.action_add(), handler, IMAGES.add());
    }

    /**
     *
     * @param handler
     * @return
     */
    public static TextButton createAddTextButton(SelectHandler handler) {
        return createTextButton(MESSAGES.action_add(), handler, IMAGES.add());
    }

    /**
     *
     * @param handler
     * @return
     */
    public static MenuItem createEditMenuItem(SelectionHandler<Item> handler) {
        return createMenuItem(MESSAGES.action_edit(), handler, IMAGES.edit());
    }

    /**
     *
     * @param handler
     * @return
     */
    public static TextButton createEditTextButton(SelectHandler handler) {
        return createTextButton(MESSAGES.action_edit(), handler, IMAGES.edit());
    }

    /**
     *
     * @param handler
     * @return
     */
    public static MenuItem createRemoveMenuItem(SelectionHandler<Item> handler) {
        return createMenuItem(MESSAGES.action_remove(), new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event1) {
                DialogUtils.showConfirmDialog(MESSAGES.action_remove(), MESSAGES.action_remove_msg(),
                        new SelectHandler() {

                            @Override
                            public void onSelect(SelectEvent event2) {
                                handler.onSelection(event1);
                            }
                        });

            }
        }, IMAGES.remove());
    }

    /**
     *
     * @param handler
     * @return
     */
    public static TextButton createRemoveTextButton(SelectHandler handler) {
        return createTextButton(MESSAGES.action_remove(), new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                DialogUtils.showConfirmDialog(MESSAGES.action_remove(), MESSAGES.action_remove_msg(),
                        new SelectHandler() {

                            @Override
                            public void onSelect(SelectEvent event2) {
                                handler.onSelect(event);
                            }
                        });

            }
        }, IMAGES.add());
    }

    public static TextButton createTextButton(String text, SelectHandler handler, ImageResource icon) {
        TextButton button = new TextButton(text);
        if (icon != null) {
            button.setIcon(icon);
        }
        if (handler != null) {
            button.addSelectHandler(handler);
        }
        return button;
    }

    /**
     *
     * @param text
     * @param handler
     * @param icon
     * @return
     */
    public static TextButton createTextButton(String text, SelectionHandler<Item> handler, ImageResource icon) {
        return createTextButton(text, createDefaultSelectHandler(handler), icon);
    }

    /**
     *
     * @param text
     * @param handler
     * @return
     */
    public static MenuItem createMenuItem(String text, SelectionHandler<Item> handler) {
        return createMenuItem(text, handler, null);
    }

    public static MenuItem createMenuItem(String text, SelectionHandler<Item> handler, ImageResource icon) {
        MenuItem menuItem = new MenuItem(text);
        menuItem.addSelectionHandler(handler);
        if (icon != null) {
            menuItem.setIcon(icon);
        }
        return menuItem;
    }

    public static MenuItem createMenuMenuItem(String text, Menu menu) {
        return createMenuMenuItem(text, menu, null);
    }

    public static MenuItem createMenuMenuItem(String text, Menu menu, ImageResource image) {
        MenuItem menuItem = null;
        if (image != null) {
            menuItem = new MenuItem(text, image);
        } else {
            menuItem = new MenuItem(text);
        }
        menuItem.setSubMenu(menu);
        return menuItem;
    }

    public static Widget createButtonMenu(String title, Menu menu) {
        return createButtonMenu(title, menu, null);
    }

    /**
     *
     * @param title
     * @param menu
     * @param icon
     * @return
     */
    public static Widget createButtonMenu(String title, Menu menu, ImageResource icon) {
        TextButton textButton = new TextButton(title);
        textButton.setMenu(menu);
        if (icon != null) {
            textButton.setIcon(icon);
        }
        return textButton;
    }

    /**
     *
     * @param text
     * @param groupName
     * @param icon
     * @return
     */
    public static CheckMenuItem createCheckMenuItemWithGroup(String text, String groupName, ImageResource icon) {
        CheckMenuItem item = new CheckMenuItem(text);
        item.setGroup(groupName);
        if (icon != null)
            item.setIcon(icon);
        return item;
    }

    /**
     *
     * @param text
     * @param groupName
     * @return
     */
    public static CheckMenuItem createCheckMenuItemWithGroup(String text, String groupName) {
        return createCheckMenuItemWithGroup(text, groupName, null);
    }

    /**
     *
     * @param widget
     * @return
     */
    public static ContentPanel createContentPanel(IsWidget widget) {
        return createContentPanel(widget, "");
    }

    public static ContentPanel createContentPanel(IsWidget widget, String title) {
        return createContentPanel(widget, title, null);
    }

    /**
     *
     * @param widget
     * @param title
     * @return
     */
    public static ContentPanel createContentPanel(IsWidget widget, String title, ContentPanel.ContentPanelAppearance appearance) {
        ContentPanel panel;
        if (appearance != null) {
            panel = new ContentPanel(appearance);
        } else {
            panel = new ContentPanel();
        }

        panel.setWidget(widget.asWidget());
        panel.setHeading(title);
        if (StringUtils.isNullOrEmpty(title)) {
            panel.setHeaderVisible(false);
        }
        if (GXT.isTouch()) {
            panel.getElement().getStyle().setOverflow(Overflow.SCROLL);
        } else {
            panel.getElement().getStyle().setOverflow(Overflow.VISIBLE);
        }
        return panel;
    }
    // AccordionLayoutContainer.AccordionLayoutAppearance appearance = GWT.<AccordionLayoutContainer.AccordionLayoutAppearance> create(AccordionLayoutContainer.AccordionLayoutAppearance.class);
    //

    /**
     *
     * @param margins
     * @param flex
     */
    public static BoxLayoutData createBoxLayoutDataWithFlex(Margins margins, int flex) {
        BoxLayoutData data = new BoxLayoutData(margins);
        data.setFlex(flex);
        return data;
    }

    /**
     *
     * @param text
     * @param image
     * @return
     */
    public static ToggleButton createToogleButton(String text, ImageResource image) {
        ToggleButton button = new ToggleButton(text);
        if (image != null) {
            button.setIcon(image);
        }
        return button;
    }

    public static IsWidget createColorPicker(TextField field) {
        field.setEnabled(false);
        final TextButton colorButton = new TextButton("Zmień");
        final ColorMenu colorMenu = new ColorMenu();
        colorMenu.setFocusOnShow(false);
        colorMenu.getPalette().addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                colorMenu.hide();
                colorButton.setText(event.getValue());
                field.setValue(event.getValue());
            }
        });
        colorButton.setMenu(colorMenu);
        return FormUtils.createHorizontalPanelWith2Elements(field, colorButton, 0.8, 0.2);
    }

    public static <T> ValueChangeHandler<T> createDefaultalueChangeHandler(SelectionHandler<?> toWrapp) {
        return new ValueChangeHandler<T>() {

            @Override
            public void onValueChange(ValueChangeEvent<T> event) {
                toWrapp.onSelection(null);
            }

        };

    }

    public static <T> SelectionHandler<T> createDefaultSelectionHander(SelectionHandler<?> toWrapp) {
        return new SelectionHandler<T>() {

            @Override
            public void onSelection(SelectionEvent<T> event) {
                toWrapp.onSelection(null);
            }
        };

    }

    public static SelectHandler createDefaultSelectHandler(SelectionHandler<?> toWrapp) {
        return new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                toWrapp.onSelection(null);

            }
        };

    }

    public static IsWidget createSliderWithMessage(Slider slider) {
        Label field = new Label();
        field.setText(slider.getValue() + "");

        slider.addValueChangeHandler(new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange(ValueChangeEvent<Integer> valueChangeEvent) {
                field.setText(slider.getValue() + "");
            }
        });
        slider.addBeforeShowHandler(new BeforeShowEvent.BeforeShowHandler() {
            @Override
            public void onBeforeShow(BeforeShowEvent beforeShowEvent) {
                field.setText(slider.getValue() + "");
            }
        });
        return FormUtils.createHorizontalPanelWith2Elements(slider, field, 0.9, 0.1, null, new Margins(0, 0, 0, 5));
    }
}
