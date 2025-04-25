package hust.soict.hespi.aims;

import hust.soict.hespi.aims.book.Book;
import hust.soict.hespi.aims.cart.Cart;
import hust.soict.hespi.aims.cd.CompactDisc;
import hust.soict.hespi.aims.cd.Track;
import hust.soict.hespi.aims.disc.DigitalVideoDisc;
import hust.soict.hespi.aims.media.Media;
import hust.soict.hespi.aims.media.Playable;
import hust.soict.hespi.aims.store.Store;

import java.util.Scanner;

public class Aims {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();

        while (true) {
            showMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> viewStore(store, cart);
                case 2 -> updateStore(store, scanner);
                case 3 -> seeCart(cart);
                case 0 -> {
                    System.out.println("Exiting application.");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    public static void showMenu() {
        System.out.println("AIMS:");
        System.out.println("-----------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("-----------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    private static void viewStore(Store store, Cart cart) {
        while (true) {
            System.out.println("\n=== STORE ITEMS ===");
            store.printStore();// hiển thị toàn bộ media trong store
            storeMenu(); // show menu khi chọn viewstore
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> seeMediaDetails(store, cart, scanner);
                case 2 -> addMediaToCart(store, cart);
                case 3 -> playMedia(store);
                case 4 -> seeCart(cart);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    public static void storeMenu() {
        System.out.println("Options:");
        System.out.println("-----------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("-----------------------------");

        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options:");
        System.out.println("-----------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("-----------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
    }

    public static void cartMenu() {
        System.out.println("Options:");
        System.out.println("-----------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("-----------------------------");
        System.out.print("Please choose a number: 0-5: ");
    }

    private static void seeMediaDetails(Store store, Cart cart, Scanner scanner) {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine().trim();
        Media media = store.findMediaByTitle(title);

        if (media == null) {
            System.out.println(" Media not found.");
            return;
        }

        System.out.println("\n--- Media Info ---");
        System.out.println(media);

        if (media instanceof Playable) {// Nếu media là CD hoặc DVD(playeable được ), hiển thị thêm menu con:
            int choice = -1;
            do {
                mediaDetailsMenu();
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    choice = -1;
                }

                switch (choice) {
                    case 1 -> cart.addMedia(media);
                    case 2 -> ((Playable) media).play();
                    case 0 -> System.out.println("🔙 Returning to store menu...");
                    default -> System.out.println("⚠️ Invalid choice. Please try again.");
                }
            } while (choice != 0);
        } else {
            System.out.println("📚 This media cannot be played.");
        }
    }


    private static void addMediaToCart(Store store, Cart cart) {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        if (media != null) {
            cart.addMedia(media);
        } else {
            System.out.println("Media not found.");
        }
    }

    private static void playMedia(Store store) {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        if (media instanceof Playable playable) {
            playable.play();
        } else {
            System.out.println("Cannot play this media type.");
        }
    }

    private static void updateStore(Store store, Scanner scanner) {
        int choice;
        do {
            System.out.println("\n=== UPDATE STORE MENU ===");
            System.out.println("-----------------------------");
            System.out.println("1. Add a media to store");
            System.out.println("2. Remove a media from store");
            System.out.println("0. Back");
            System.out.println("-----------------------------");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("Choose type to add: 1. DVD  2. Book  3. CD");
                    int type = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter cost: ");
                    float cost = Float.parseFloat(scanner.nextLine());

                    Media media = null;

                    switch (type) {
                        case 1 -> {
                            System.out.print("Enter director: ");
                            String director = scanner.nextLine();
                            System.out.print("Enter length: ");
                            int length = Integer.parseInt(scanner.nextLine());
                            media = new DigitalVideoDisc(title, category, director, length, cost);
                        }
                        case 2 -> {
                            media = new Book(0, title, category, cost);
                            System.out.print("Enter author (comma separated): ");
                            String[] authors = scanner.nextLine().split(",");
                            for (String author : authors) {
                                ((Book) media).addAuthor(author.trim());
                            }
                        }
                        case 3 -> {
                            System.out.print("Enter artist: ");
                            String artist = scanner.nextLine();
                            System.out.print("Enter director: ");
                            String director = scanner.nextLine();
                            CompactDisc cd = new CompactDisc(0, title, category, cost, artist, director);

                            System.out.println("How many tracks to add?");
                            int numTracks = Integer.parseInt(scanner.nextLine());
                            for (int i = 1; i <= numTracks; i++) {
                                System.out.print("Track " + i + " title: ");
                                String tTitle = scanner.nextLine();
                                System.out.print("Track " + i + " length: ");
                                int tLength = Integer.parseInt(scanner.nextLine());
                                cd.addTrack(new Track(tTitle, tLength));
                            }

                            media = cd;
                        }
                    }

                    if (media != null) store.addMedia(media);
                }

                case 2 -> {
                    System.out.print("Enter title to remove: ");
                    String titleToRemove = scanner.nextLine();
                    Media media = store.findMediaByTitle(titleToRemove);
                    if (media != null) {
                        store.removeMedia(media);
                    } else {
                        System.out.println("Media not found.");
                    }
                }

                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }


    private static void seeCart(Cart cart) {
        cart.print();// hiển thị tất cả media có trong giỏ
        cartMenu(); // hiện thị menu của cart menu
        int opt = scanner.nextInt();
        scanner.nextLine();
        switch (opt) {
            case 1 -> {
                System.out.print("Enter title to filter: ");
                String t = scanner.nextLine();
                cart.getItemsOrdered().stream().filter(m -> m.getTitle().equalsIgnoreCase(t)).forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("1. Sort by title then cost\n2. Sort by cost then title");
                int sortOpt = scanner.nextInt();
                if (sortOpt == 1) cart.sortByTitleCost();
                else cart.sortByCostTitle();
                cart.print();
            }
            case 3 -> {
                System.out.print("Enter media title to remove: ");
                String t = scanner.nextLine();
                cart.removeMedia(cart.getItemsOrdered().stream().filter(m -> m.getTitle().equalsIgnoreCase(t)).findFirst().orElse(null));
            }
            case 4 -> {
                System.out.print("Enter media title to play: ");
                String t = scanner.nextLine();
                Media m = cart.getItemsOrdered().stream().filter(media -> media.getTitle().equalsIgnoreCase(t)).findFirst().orElse(null);
                if (m instanceof Playable p) p.play();
                else System.out.println("This media cannot be played.");
            }
            case 5 -> {
                System.out.println("Order placed. Cart is now empty.");
                cart.getItemsOrdered().clear();
            }
            default -> {}
        }
    }
}