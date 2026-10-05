/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.signs;

import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.xml.client.*;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import pl.edu.wat.wcy.cop.app.client.utils.resources.copResources;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
// Creates app 6 a menu instances.

public class App6AMenuFactory {

    private static TextButton installationMenu;
    private static TextButton unitMenu;
    private static SelectionHandler<Item> unitHandler;
    private static SelectionHandler<Item> installationHandler;
    private static Map<String, String> map = new TreeMap<String, String>();

    public static TextButton getInstallationMenu(SelectionHandler<Item> handler) {
        installationMenu = processDocument(2, handler);
        return installationMenu;
    }

    public static TextButton getAreaMenu(SelectionHandler<Item> handler) {
        return processDocument(3, handler);
    }

    public static TextButton getUnitMenu(SelectionHandler<Item> handler) {
        unitMenu = processDocument(0, handler);
        return unitMenu;

    }

    private static TextButton processDocument(int index, SelectionHandler<Item> handler) {
        Menu menu = new Menu();
        Document dom = XMLParser.parse(copResources.INSTANCE.units().getText());
        Node root = dom.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node currentNode = childNodes.item(i);
            if (currentNode instanceof Element)
                nodes.add(currentNode);
        }
        Node processedNode = nodes.get(index);
        TextButton button = new TextButton(((Element) processedNode).getAttribute("name"));
        button.setData("app6aCode", ((Element) processedNode).getAttribute("app6aCode"));
        map.put(((Element) processedNode).getAttribute("app6aCode"), ((Element) processedNode).getAttribute("name"));

        Menu subMenu = new Menu();
        subMenu.addSelectionHandler(handler);
        MenuItem newMenu = new MenuItem(((Element) processedNode).getAttribute("name"));
        newMenu.setData("app6aCode", ((Element) processedNode).getAttribute("app6aCode"));

        menu.add(newMenu);
        newMenu.setSubMenu(subMenu);
        button.setMenu(menu);
        menu.addSelectionHandler(handler);
        processChildNodes(processedNode.getChildNodes(), subMenu, handler);
        return button;
    }

    private static void processChildNodes(NodeList childNodes, Menu menu, SelectionHandler<Item> handler) {
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node currentNode = childNodes.item(i);
            if (currentNode instanceof Element) {
                String data = ((Element) currentNode).getAttribute("app6aCode");
                String label = ((Element) currentNode).getAttribute("name");
                MenuItem newMenu = new MenuItem(label);
                newMenu.setData("app6aCode", data);
                map.put(data, label);
                menu.add(newMenu);
                NodeList child = currentNode.getChildNodes();
                int count = 0;
                for (int j = 0; j < child.getLength(); j++) {
                    Node currentChild = child.item(j);
                    if (currentChild instanceof Element) {
                        count++;
                        j = child.getLength() + 10;
                    }
                }
                if (count != 0) {

                    Menu subMenu = new Menu();
                    subMenu.addSelectionHandler(handler);
                    newMenu.setSubMenu(subMenu);
                    processChildNodes(currentNode.getChildNodes(), subMenu, handler);
                }
            }

        }

    }

    public static String getNameFromApp6a(String app6aCode) {
        return map.get(app6aCode.substring(4, 10));
    }
}
