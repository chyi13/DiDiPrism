package com.hexin.plat.android.assist.floating;

public class MenuItem {

    String name;
    String id;
    int icon;
    String type;

    public MenuItem(String id, String name, String type, int icon) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }
}
