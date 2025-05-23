package tm;

import java.io.*;
import java.util.*;

/**
 * Static class to represent a Turing Machine transition
 */
class Transition {
    int nextState;
    int writeSymbol;
    char move;

    // Transition constructor
    Transition(int nextState, int writeSymbol, char move) {
        this.nextState = nextState;
        this.writeSymbol = writeSymbol;
        this.move = move;
    }
}

/**
 * Represents the Turing Machine with states, tape, and transition logic
 */
class TuringMachine {
    private int numStates;
    private int numSymbols;
    private Map<Integer, Map<Integer, Transition>> transitions;
    private Map<Integer, Integer> tape;
    private int head;
    private int currentState;
    private int leftmost;
    private int rightmost;

    /**
     * Constructor to initialize the Turing Machine
     * 
     * @param numStates
     * @param numSymbols
     * @param transitions
     * @param tapeInput
     * @author Hailey and Cam
     */
    public TuringMachine(int numStates, int numSymbols, Map<Integer, Map<Integer, Transition>> transitions, List<Integer> tapeInput) {
        this.numStates = numStates;
        this.numSymbols = numSymbols;
        this.transitions = transitions;
        this.tape = new HashMap<>();
        this.head = 0;
        this.currentState = 0;

        for (int i = 0; i < tapeInput.size(); i++) {
            tape.put(i, tapeInput.get(i));
        }

        this.leftmost = 0;
        this.rightmost = tapeInput.size() - 1;
    }

    /**
     * Runs the turing machine
     */
    public void run() {
        while (currentState != numStates - 1) {
            int symbol = tape.getOrDefault(head, 0); // read current symbol
            Transition transition = transitions.get(currentState).get(symbol); // get associated transition
            tape.put(head, transition.writeSymbol); // write symbol to tape

            // move the head left or right
            head = (transition.move == 'R') ? head + 1 : head - 1;
            leftmost = Math.min(leftmost, head);
            rightmost = Math.max(rightmost, head);

            // transition to next state
            currentState = transition.nextState;
        }
    }

    /**
     * Constructs and returns the tape after execution in a string format
     * 
     * @return string representation of the tape
     * @author Hailey and Cam
     */
    public String getTapeOutput() {
        // String str = "Output: \n";

        // int sumSymb = 0;
        StringBuilder output = new StringBuilder();
        for (int i = leftmost; i <= rightmost; i++) {
            // sumSymb += tape.getOrDefault(i, 0);
            output.append(tape.getOrDefault(i, 0));
            // outLength++;
        }

        // if (output.length() > 300) {
        //     str += "Very Large\n";
        // } else {
        //     str += output.toString() + "\n";
        // }
        // str += "Output length: " + output.length() + "\nSum of Symbols: " + sumSymb; 

        return output.toString();
    }
}

/**
 * Main class that simulates a Turing Machine from input file.
 */
public class TMSimulator {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Please enter a valid input file name.");
            return;
        }

        BufferedReader file = new BufferedReader(new FileReader(args[0]));

        // read number of states and number of symbols
        int numStates = Integer.parseInt(file.readLine().trim());
        int numSymbols = Integer.parseInt(file.readLine().trim());
        Map<Integer, Map<Integer, Transition>> transitions = new HashMap<>();

        // iterate through transition table
        for (int i = 0; i < numStates - 1; i++) {
            transitions.put(i, new HashMap<>());
            for (int j = 0; j <= numSymbols; j++) {
                String[] parts = file.readLine().trim().split(",");
                int nextState = Integer.parseInt(parts[0]);
                int writeSymbol = Integer.parseInt(parts[1]);
                char move = parts[2].charAt(0);
                transitions.get(i).put(j, new Transition(nextState, writeSymbol, move));
            }
        }

        // read the initial tape input
        String inputLine = file.readLine();
        List<Integer> tape = new ArrayList<>();
        if (inputLine == null || inputLine.trim().isEmpty()) {
            tape.add(0); // Blank tape
        } else {
            for (char c : inputLine.trim().toCharArray()) {
                tape.add(Character.getNumericValue(c));
            }
        }

        // create/run the machine
        TuringMachine tm = new TuringMachine(numStates, numSymbols, transitions, tape);
        tm.run();
        System.out.println(tm.getTapeOutput());
    }
}
