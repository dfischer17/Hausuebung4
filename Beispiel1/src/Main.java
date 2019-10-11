import java.io.File;
import java.io.IOException;
import java.lang.module.ModuleDescriptor;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    static List<Integer> numberList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int amountOfChunks = 0;
        int divider = 0;

        // Zahlen einlesen
        readNumbers();

        // Nutzereingabe einlesen
        System.out.println("Anzahl an Chuncks");
        amountOfChunks = input.nextInt();
        System.out.println("Teiler eingeben:");
        divider = input.nextInt();

        // Unterlisten erstellen
        List<List<Integer>> sublists = batches(numberList, numberList.size() / amountOfChunks).collect(Collectors.toList());

        // Threadpool erstellen
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        // Threads hinzufuegen
        for (int i = 0; i < sublists.size(); i++) {
            executor.execute(new DivideChecker(sublists.get(i), divider));
        }
        executor.shutdown();
    }

    private static void readNumbers() throws IOException {
        // Zahlen aus File in List speichern
        Files.lines(new File("numbers.csv").toPath()).forEach((n) ->
        {
            String[] numbers = n.split(":");
            try {
                for (int i = 0; i < numbers.length; i++) {
                    if (!numbers[i].equals("")) {
                        numberList.add(Integer.parseInt(numbers[i]));
                    }
                }
            } catch (NumberFormatException e) {
            }
        });
    }

    public static <T> Stream<List<T>> batches(List<T> source, int length) {
        if (length <= 0)
            throw new IllegalArgumentException("length = " + length);
        int size = source.size();
        if (size <= 0)
            return Stream.empty();
        int fullChunks = (size - 1) / length;
        return IntStream.range(0, fullChunks + 1).mapToObj(
                n -> source.subList(n * length, n == fullChunks ? size : (n + 1) * length));
    }
}
