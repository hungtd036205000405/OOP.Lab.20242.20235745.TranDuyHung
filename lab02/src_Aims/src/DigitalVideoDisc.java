public class DigitalVideoDisc {
    private String title; // moi instance co 1 Title rieng biet
    private String category;
    private String director;// đạo diễn
    private int length;// độ dài phim
    private float cost;// giá tiền
    private int id;

    private static int nbDigitalVideoDiscs = 0; // moi instance co 1 nbDigitalVideoDiscs chung , tang len1 ko can tao doi tuong
    // phuong thu static :
    // ✅ Constructor đầy đủ: gán ID tự động
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;

        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }


    public DigitalVideoDisc(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }

    // thêm setter cho tile
    public void setTitle(String title) {
        this.title = title;
    }
}
