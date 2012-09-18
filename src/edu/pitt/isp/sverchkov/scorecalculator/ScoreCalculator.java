/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.util.Set;

/**
 *
 * @author YUS24
 */
public class ScoreCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        UserInterface ui = ( System.console() == null ? new GUI() : new CLI( args ) );
        
        ui.queryUser();
        
        RecordSet rs = RecordReader.read( ui.getRecordFile() );
                        
        ScoreWriter sw = new ScoreFileV1Writer( ui.getOutputFile() );
        
        ScoreFunction sf = ui.getScoreFunction();
        
        sw.writeScores( rs, sf );
    }
}
