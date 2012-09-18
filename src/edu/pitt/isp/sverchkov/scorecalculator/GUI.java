/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import edu.pitt.isp.sverchkov.scorecalculator.gui.ScoreCalculatorJFrame;
import java.awt.EventQueue;
import java.io.File;

/**
 *
 * @author YUS24
 */
public class GUI extends AbstractUI implements UserInterface {
    
    @Override
    public void queryUser() {
        
        ScoreCalculatorJFrame.setLookAndFeel();
        ScoreCalculatorJFrame frame = new ScoreCalculatorJFrame();
        frame.setVisible(true);
        recordFile = frame.getRecordFile();
        outputFile = frame.getOutputFile();
        scoreFunction = new BDeu( frame.getESS() );
        
    }

    @Override
    public File getRecordFile() {
        return recordFile;
    }

    @Override
    public File getOutputFile() {
        return outputFile;
    }

    @Override
    public ScoreFunction getScoreFunction() {
        return scoreFunction;
    }
    
}
