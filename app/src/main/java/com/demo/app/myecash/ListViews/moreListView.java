package com.demo.app.myecash.ListViews;

public class moreListView implements Comparable<moreListView> {


    private String image;
    private String name;
    private String category;
    private boolean isSectionHeader;

    @Override
    public int compareTo(moreListView other) {
        return this.category.compareTo(other.category);
    }


    public moreListView(String image,String name, String category) {
        this.image=image;
        this.name = name;
        this.category = category;
        isSectionHeader = false;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setToSectionHeader() {
        isSectionHeader = true;
    }

    public boolean isSectionHeader() {
        return isSectionHeader;
    }


}
