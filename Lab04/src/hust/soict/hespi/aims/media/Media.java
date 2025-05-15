package hust.soict.hespi.aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media {
    private int id ;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    // Constructor
    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }

    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setCost(float cost) { this.cost = cost; }

    // Override equals and hashCode based on title
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Media)) return false;
        Media other = (Media) obj;
        return title != null && title.equalsIgnoreCase(other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title != null ? title.toLowerCase() : null);
    }

    // Override toString to display information
    @Override
    public String toString() {
        return String.format("Title: %s | Category: %s | Cost: %.2f", title, category, cost);
    }
}
