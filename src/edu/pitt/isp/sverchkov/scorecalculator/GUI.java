/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import edu.pitt.isp.sverchkov.scorecalculator.gui.ScoreCalculatorJFrame;
import java.awt.EventQueue;
import java.io.File;
import org.apache.log4j.Logger;

/**
 *
 * @author YUS24
 */
public class GUI extends AbstractUI implements UserInterface {
    
    private static final Logger LOG = Logger.getLogger(GUI.class);
    
    @Override
    public void queryUser() {
        
        ScoreCalculatorJFrame.setLookAndFeel();
        Object lock = new Object();
        ScoreCalculatorJFrame frame = new ScoreCalculatorJFrame( lock );
        frame.setVisible(true);
        try {
            synchronized( lock ){ lock.wait(); }
        } catch (InterruptedException ex) {
            LOG.fatal( ex.getMessage() );
            LOG.trace( ex, ex );
        }
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
