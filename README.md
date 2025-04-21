# Project 3: Turing Machine Simulator

* Authors: Hailey Revel-Whitaker and Cameron Quitugua
* Class: CS361 Section 001
* Semester: Spring 2025

## Overview

Our project simulates a bi-infinite turing machine. It reads and parses information from an input text file and simulates how a turing machine would process and iterate through the information provided in the file. Our simulator stops when it reaches the halting state of the machine. When the simulation is over, the content of the visited tape cells is printed to the console.

## Reflection

This project went relatively well. The prep work for this project was the hardest, but it made the implementation easier. We spent majority of the first bit of this project breaking down how the input files should be read and interpreted by the machine. We walked through each of the example text files with pen and paper and gathered information on how these files should be read (because we were confused at first). Once we understood the method of reading files, we made a checklist of what each step in the process should be. This clarity and distinct process made our implementation a lot easier.

We understood most of this project and don't have anything to ask about specific concepts. Figuring out how the files were read was the hardest/most confusing part. To make our code object oriented and easy to understand, we separated our "Transition" class from the "TuringMachine" class, which were both separate from our main class to run the simulation. We also used javadoc comments for clarification on what each method/class does. If we could change our design process, we would have started the project sooner so we weren't in as much of a rush.

## Compiling and Using

While inside of the TM folder, navigate into the tm subfolder.
```
$ cd tm
```

Next compile the TMSimulator.java file with the following command.
```
$ javac TMSimulator.java
```

Navigate back, out of tm subfolder, into the main folder
```
$ cd ..
```

Lastly run the following line with a required .txt file input containing a proper format. 
```
$ java tm.TMSimulator.java <file.txt>
```
You can run the code with the following .txt files that are provided in TM: <b>file0.txt</b>, <b>file2.txt</b>, <b>file5.txt</b>.

The result will output to the terminal the resulting tape, tape length, and the sum of symbols. 


## Sources used

We used class lecture recordings and example files!
