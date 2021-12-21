package io.microcatalogservices.model;

public class ItemInfo {

    private String itemId;
    private String itemType;
    private String name;


    public ItemInfo() {
    }

    public ItemInfo(String itemId, String itemType, String name) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.name = name;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
