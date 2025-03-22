public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    // Thêm 1 DVD vào giỏ hàng
    public void addDigitalVideoDisc(DigitalVideoDisc disc ){
        if(qtyOrdered < MAX_NUMBERS_ORDERED){
            itemsOrdered[qtyOrdered] = disc;
            qtyOrdered++;
        }else{
            System.out.println("The cart is almost full!");
        }
    }
    // Thêm 1 mảng DVD vào giỏ hàng
//    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList){
//        for(DigitalVideoDisc disc : dvdList){
//            if(qtyOrdered < MAX_NUMBERS_ORDERED){
//                itemsOrdered[qtyOrdered] = disc;
//                qtyOrdered++;
//            }else{
//                System.out.println("The cart is almost full!");
//                break;
//            }
//        }
//    }
    // Thêm số lượng DVD tùy ý
    // Trả lời câu hỏi thích dùng method nào hơn
    //Trong Java, bạn chỉ được chọn 1 trong 2 cách array[] / DigitalVideoDisc... discs  khi định nghĩa phương thức nạp chồng (overloading) với cùng kiểu dữ liệu:
    public void addDigitalVideoDisc(DigitalVideoDisc... discs){
        for(DigitalVideoDisc disc : discs){
            if(qtyOrdered < MAX_NUMBERS_ORDERED){
                itemsOrdered[qtyOrdered] = disc;
                qtyOrdered++;
            }else{
                System.out.println("The cart is almost full!");
                break;
            }
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc){
        boolean found = false;
        for(int i = 0; i < qtyOrdered; i++){
            if(itemsOrdered[i].getTitle().equals(disc.getTitle())){
                found = true;
                for(int j = i; j < qtyOrdered - 1; j++){
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                qtyOrdered--;
                break;
            }
        }
        if(!found){
            System.out.println("The disc is not in the cart!");
        }
    }

    //14.2
    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2){
        if(qtyOrdered < MAX_NUMBERS_ORDERED){
            itemsOrdered[qtyOrdered] = dvd1;
            qtyOrdered++;
        }else{
            System.out.println("The cart is almost full!");
        }
        if(qtyOrdered < MAX_NUMBERS_ORDERED){
            itemsOrdered[qtyOrdered] = dvd2;
            qtyOrdered++;
        }else{
            System.out.println("The cart is almost full!");
        }
    }

    // Tính tổng tiền giỏ hàng
    public float totalCost() {
        float total = 0.0f;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemsOrdered[i].getCost();
        }
        return total;
    }
}
