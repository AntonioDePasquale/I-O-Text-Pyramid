import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {

        String decodedMessage = decode("src/coding_qual_input.txt");
        System.out.println(decodedMessage);

    }

    /**
     * Decodes - Function to the goal is to decode a message from a txt file where each entry is assembled into a pyramid, top to bottom.
     * The decoded message is created from the numbers on the right side of the pyramid in ascending order
     * @param message_file String containing the coding_qual_input.txt url
     * @return String containing the decoded message
     * @throws IOException
     */
    public static String decode(String message_file) throws IOException {

        ArrayList<String> fileLineArray = new ArrayList<>();
        HashMap<Integer, String> codeMap = new HashMap<>();

        BufferedReader buffRead = new BufferedReader(new FileReader(message_file));
        String line = buffRead.readLine();
        String test = "test";

        /*
        lines are read by the buffered reader,
        if each line is not null and longer than 0 in length it is added to an ArrayList
        */
        while (line != null && line.length() > 0) {
            fileLineArray.add(line);
            line = buffRead.readLine();
        }

        // iterates through every line in the ArrayList
        for (int i = 0; i < fileLineArray.size(); i++) {
            String currentLine = fileLineArray.get(i);

            // each index in the ArrayList of lines is split into its number and word then added to a Map.
            String[] currentSplit = currentLine.split(" ");
            Integer number = Integer.parseInt(currentSplit[0]);
            String word = currentSplit[1];

            codeMap.put(number, word);
        }

        Integer pyramidRightEdgeNumber = 1;
        Integer maxNumber = Collections.max(codeMap.keySet());
        StringJoiner finalString = new StringJoiner(" ");

        /*
         We know that if assembled into a pyramid each line is one number longer than the previous line.
         Knowing this we can simply add one to an Integer 'pyramidEdgeNumber' and this will be the number key for
         each right edge number of the pyramid. This number is used to get the associated value from the Map.
         */

        for (int i = 1; i < maxNumber; i += pyramidRightEdgeNumber) {

            String currentWord = codeMap.get(i);
            finalString.add(currentWord);
            pyramidRightEdgeNumber += 1;
        }

        // StringJoiner is used to concatenate each word forming the decoded string to be returned.
        return finalString.toString();
    }
}