

import java.util.ArrayList;
import java.util.Scanner;

public class spotifyapp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        ArrayList<Track> library = new ArrayList<>();

        try {
            Track[] tracks = filereader.readFile("media_scifi_mixed_tracks.csv");
            for (Track t : tracks) {
                library.add(t);
            }
        } catch (Exception e) {
            System.out.println("Error loading database: " + e.getMessage());
        }


        while (running) {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Print all entries");
            System.out.println("2. Print only Tracks");
            System.out.println("3. Print only Audio Books");
            System.out.println("4. Print only TV Episodes");
            System.out.println("5. Print all by creator");
            System.out.println("6. Sort by rating (descending)");
            System.out.println("7. Sort by year (ascending)");
            System.out.println("8. Sort by title (A–Z)");
            System.out.println("9. Print entries released on or after a given year");
            System.out.println("10. Quit");
            System.out.println("Choice: ");

            String input = scanner.nextLine().trim();


            switch (input) {

                case "1":
                    System.out.println();
                    for (Track t : library) {
                        System.out.println(
                                t.getTitle() + " | " +
                                t.getCreator() + " | " +
                                t.getAlbum() + " | " +
                                t.getYear() + " | " +
                                t.getDuration() + " mins | Rating: " + t.getRating()
                        );
                    }
                    break;


                case "2":
                    System.out.println();
                    library.stream()
                           .filter(t -> t.getClass().getSimpleName().equals("Track"))
                           .forEach(t -> System.out.println(t.getTitle() + " | " + t.getCreator()));
                    break;


                case "3":
                    System.out.println();
                    library.stream()
                           .filter(t -> t instanceof AudioBook)
                           .forEach(t -> System.out.println(t.getTitle() + " | " + t.getCreator()));
                    break;


                case "4":
                    System.out.println();
                    library.stream()
                           .filter(t -> t instanceof TVEpisode)
                           .forEach(t -> System.out.println(t.getTitle() + " | " + t.getCreator()));
                    break;


                case "5":
                    System.out.print("Creator: ");
                    String creator = scanner.nextLine().toLowerCase();

                    System.out.println();
                    library.stream()
                           .filter(t -> t.getCreator().toLowerCase().equals(creator))
                           .forEach(t -> System.out.println(t.getTitle() + " | " + t.getCreator()));
                    break;


                case "6":
                    System.out.println();
                    library.sort((a, b) -> {
                        String r1 = a.getRating().toString();
                        String r2 = b.getRating().toString();
                        return r2.compareTo(r1);
                    });
                    break;


                case "7":
                    System.out.println();
                    library.sort((a, b) ->
                       Integer.compare(a.getYear(), b.getYear())
                    );
                    break;


                case "8":
                    System.out.println();
                    library.sort((a, b) ->
                       a.getTitle().compareToIgnoreCase(b.getTitle())
                    );
                    break;


                case "9":
                    System.out.print("Min year: ");
                    int min = Integer.parseInt(scanner.nextLine());

                    System.out.println();
                    library.stream()
                           .filter(t -> t.getYear() >= min)
                           .forEach(t -> System.out.println(t.getTitle() + " | " + t.getYear()));
                    break;


                case "10":
                    running = false;
                    break;


                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }

        scanner.close();
    }
}
