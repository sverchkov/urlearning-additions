/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.io.File;
import java.io.PrintStream;

/**
 *
 * @author YUS24
 */
public class CLI extends AbstractUI implements UserInterface {
    
    private String[] args;
    
    public CLI( String... args ){
        this.args = args;
    }

    @Override
    public void queryUser() {
        for( int i=0; i < args.length; i++ ){
            if( "--gui".equalsIgnoreCase(args[i]) ){
                UserInterface ui = new GUI();
                ui.queryUser();
                recordFile = ui.getRecordFile();
                outputFile = ui.getRecordFile();
                scoreFunction = ui.getScoreFunction();
                return;
            }else if( "--records".equalsIgnoreCase(args[i]) )
                recordFile = new File( args[++i] );
            else if( "--output".equalsIgnoreCase( args[i] ) )
                outputFile = new File( args[++i] );
            else if( "--BDe-ess".equalsIgnoreCase( args[i] ) )
                scoreFunction = new BDeu( Double.parseDouble( args[++i] ) );
        }
        
        if( recordFile == null || outputFile == null || scoreFunction == null )
            printUsage();    
    }
    
    static void printUsage(){
        PrintStream out = System.out;
        out.println("Command line parameters:");
        out.println("--gui");
        out.println("\t Use the GUI. Other command-line parameters are ignored if this option is chosen.");
        out.println("--records <string>");
        out.println("\t Set the file to use as the records file.");
        out.println("--output <string>");
        out.println("\t Set the output (scores) file.");
        out.println("--BDe-ess <number>");
        out.println("\t Set the equivalent sample size hyperparameter for BDeu.");
    }
}
