package main.program;

import swift.search.modes.*;
import cli.*;
import swiftbot.*;

public class Main {    
    public static void main(String[] args) {
        SwiftBotAPI API = new SwiftBotAPI();
        
        CuriousSwift curiousSwift = new CuriousSwift(70, API);
        ScaredySwift scaredySwift = new ScaredySwift(70, API);
        DubiousSwift dubiousSwift = new DubiousSwift(curiousSwift, scaredySwift);
        
        CommandLineInterface terminal = new CommandLineInterface(API);
    

        terminal.start();
        
        if (terminal.chosenMode.equals("Curious Swiftbot"))
            curiousSwift.start();
        else if (terminal.chosenMode.equals("Scaredy Swiftbot")) 
            scaredySwift.start();
        else if (terminal.chosenMode.equals("Dubious Swiftbot"))
            dubiousSwift.start();

    }
}