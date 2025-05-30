package hust.soict.hespi.aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media implements Comparable<Media> {
    private int id;
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

    @Override
    public boolean equals(Object obj) {
        try {
            if (this == obj) return true;
            if (!(obj instanceof Media)) return false;

            Media other = (Media) obj;

            // Compare both title and cost
            return Float.compare(cost, other.cost) == 0 &&
                   (title == null ? other.title == null : title.equalsIgnoreCase(other.title));
        } catch (NullPointerException e) {
            System.err.println("ERROR: NullPointerException in equals() - " + e.getMessage());
            return false;
        } catch (ClassCastException e) {
            System.err.println("ERROR: ClassCastException in equals() - " + e.getMessage());
            return false;
        }
    }

    @Override
    public int hashCode() {
        // Update hashCode to be consistent with new equals() method
        return Objects.hash(
            title != null ? title.toLowerCase() : null,
            cost
        );
    }

    @Override
    public int compareTo(Media other) {
        try {
            if (other == null) {
                throw new NullPointerException("Cannot compare with null object");
            }

            // First compare by title
            if (this.title == null && other.title == null) return 0;
            if (this.title == null) return -1;
            if (other.title == null) return 1;

            int titleCompare = this.title.compareToIgnoreCase(other.title);
            if (titleCompare != 0) return titleCompare;

            // If titles are equal, compare by cost
            return Float.compare(this.cost, other.cost);

        } catch (NullPointerException e) {
            System.err.println("ERROR: NullPointerException in compareTo() - " + e.getMessage());
            return 0;
        } catch (ClassCastException e) {
            System.err.println("ERROR: ClassCastException in compareTo() - " + e.getMessage());
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("Title: %s | Category: %s | Cost: %.2f", title, category, cost);
    }
}

