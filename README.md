# connect4

## Overview

This project was made in the context of the Advanced Programming subject, computer engineering course, in [ISEC](https://www.isec.pt/PT/Default.aspx).  
The purpose of this project is to learn and apply concepts of software development, such as object-oriented programming; state machines; graphical user interface, using **JavaFX**; **observer-observable pattern** and the nuances of Java.  
The game is also has a text interface.

This project is a recreation of the tradicional game, connect4, with one additional feature: 
- Every 4 turns, each player can play a game, a typing speed test or some simple mathematics. If he wins, gets a special piece, allowing him to remove a whole column of pieces from the game grid. The players can also save the game at the current turn, and load it later.  
The _pc vs pc_ and _replay_ features are only available in text interface

## Usage

1. Download the repository as **.zip**
2. Open it in your prefered **IDE** (with Maven projects support)
3. Build the **Maven** project 
4. Run game  
    1. with **Graphical Interface**
        1. Run the `javafx:run` goal from **javafx-maven-plugin** to execute it
    2. with **Text Interface**
        1. Run the project with the main class in the file **src/main/java/game/ui/text/Main.java**
