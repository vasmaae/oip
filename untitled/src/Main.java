
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("laba.txt"));
            List<String> blocks = getBlocks(br); // block is (profession name + profession description)
            br.close();

            Map<Integer, String[]> dict = getFilledDict(blocks);
            printMenu(dict);

        } catch (IOException e) {
            System.out.println("Не удалось открыть файл.");
            e.printStackTrace();
        }
    }

    private static List<String> getBlocks(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        String text = sb.toString();
        String[] professionBlocks = text.split("\\*", -1);
        List<String> blockList = new ArrayList<>();
        for (String block : professionBlocks) {
            if (!block.isEmpty()) {
                blockList.add(block);
            }
        }
        return blockList;
    }

    private static Map<Integer, String[]> getFilledDict(List<String> blocks) {
        Map<Integer, String[]> dict = new HashMap<>();
        for (int i = 0; i < blocks.size(); i++) {
            String[] pair = blocks.get(i).split("\\^", -1);
            dict.put(i + 1, pair);
        }
        return dict;
    }

    private static void printMenu(Map<Integer, String[]> dict) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            for (Map.Entry<Integer, String[]> entry : dict.entrySet()) {
                System.out.printf("%d. %s%n", entry.getKey(), entry.getValue()[0].trim());
            }

            System.out.println("0. Выйти");
            System.out.println();

            int value = scanner.nextInt();
            if (value == 0 || !dict.containsKey(value)) {
                break;
            }
            System.out.println(dict.get(value)[1]);
        }
    }
}