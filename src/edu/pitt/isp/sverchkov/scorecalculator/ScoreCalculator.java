/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

/**
 *
 * @author YUS24
 */
public class ScoreCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserInterface ui = new GUI();
        ui.gueryUser();
        
        RecordSet rs = RecordReader.read( ui.getRecordFile() );
                
        ScoreWriter sw = new ScoreFileV1Writer( ui.getOutputFile() );
        
        ScoreFunction sf = ui.getScoreFunction();
        
        sw.writeScores( rs, sf );
    }
}
