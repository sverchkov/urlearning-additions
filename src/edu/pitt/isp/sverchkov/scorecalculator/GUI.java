/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.io.File;

/**
 *
 * @author YUS24
 */
public class GUI implements UserInterface {
    
    private File recordFile, outputFile;
    private double ess;

    @Override
    public void gueryUser() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        return new BDe( ess );
    }
    
}
