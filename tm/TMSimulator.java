package tm;

import java.io.*;
import java.util.*;

public class TMSimulator {

    static class Transition {
        int nextState;
        int writeSymbol;
        char moveDirection;
        Transition(int ns, int ws, char dir) {
            nextState = ns;
            writeSymbol = ws;
            moveDirection = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Missing input file name.");
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        int numStates = Integer.parseInt(reader.readLine().trim());
        int sigmaSize = Integer.parseInt(reader.readLine().trim());

        int gammaSize = sigmaSize + 1; // symbols 0 to m
        Map<Integer, Map<Integer, Transition>> transitions = new HashMap<>();

        for (int state = 0; state < numStates - 1; state++) {
            transitions.put(state, new HashMap<>());
            for (int symbol = 0; symbol < gammaSize; symbol++) {
                String[] parts = reader.readLine().trim().split(",");
                int nextState = Integer.parseInt(parts[0]);
                int writeSymbol = Integer.parseInt(parts[1]);
                char move = parts[2].charAt(0);
                transitions.get(state).put(symbol, new Transition(nextState, writeSymbol, move));
            }
        }

        // Tape setup
        String inputLine = reader.readLine();
        List<Integer> tape = new ArrayList<>();
        if (inputLine == null || inputLine.trim().isEmpty()) {
            tape.add(0); // blank start
        } else {
            for (char c : inputLine.trim().toCharArray()) {
                tape.add(Character.getNumericValue(c));
            }
        }

        int head = 0, state = 0;
        Map<Integer, Integer> tapeMap = new HashMap<>();
        for (int i = 0; i < tape.size(); i++) {
            tapeMap.put(i, tape.get(i));
        }

        int leftmost = 0, rightmost = tape.size() - 1;

        // Simulation
        while (state != numStates - 1) {
            int symbol = tapeMap.getOrDefault(head, 0);
            Transition trans = transitions.get(state).get(symbol);
            tapeMap.put(head, trans.writeSymbol);
            if (trans.moveDirection == 'R') head++;
            else head--;

            if (head < leftmost) leftmost = head;
            if (head > rightmost) rightmost = head;

            state = trans.nextState;
        }

        // Output visited tape content
        StringBuilder output = new StringBuilder();
        for (int i = leftmost; i <= rightmost; i++) {
            output.append(tapeMap.getOrDefault(i, 0));
        }

        System.out.println(output.toString());
    }
}
