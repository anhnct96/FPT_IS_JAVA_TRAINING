package core;

import java.awt.*;
import java.util.ArrayList;

public class Form {
    private ArrayList<Component> listComponent;

    public Form() {
        listComponent = new ArrayList<>();
    }
    public ArrayList<Component> getListComponent() {
        return listComponent;
    }

    public void setListComponent(ArrayList<Component> listComponent) {
        this.listComponent = listComponent;
    }

    public void addComponent(Component component) {
        listComponent.add(component);
    }

    public boolean validateForm() {
        return true;
    }
}
