import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {

        String decodedMessage = decode("src/coding_qual_input.txt");
        System.out.println(decodedMessage);

    }

    public static String decode(String message_file) throws IOException {

        ArrayList<String> fileLineArray = new ArrayList<>();
        HashMap<Integer, String> codeMap = new HashMap<>();

        BufferedReader buffRead = new BufferedReader(new FileReader(message_file));
        String line = buffRead.readLine();
        String test = "test";

        while (line != null && line.length() > 0) {
            fileLineArray.add(line);
            line = buffRead.readLine();
        }

        for (int i = 0; i < fileLineArray.size(); i++) {
            String currentLine = fileLineArray.get(i);

            String[] currentSplit = currentLine.split(" ");
            Integer number = Integer.parseInt(currentSplit[0]);
            String word = currentSplit[1];

            codeMap.put(number, word);
        }

        Integer iterations = 1;
        Integer maxNumber = Collections.max(codeMap.keySet());
        StringJoiner finalString = new StringJoiner(" ");

        for (int i = 1; i < maxNumber; i += iterations) {

            String currentWord = codeMap.get(i);
            finalString.add(currentWord);
            iterations += 1;
        }

        return finalString.toString();
    }
}