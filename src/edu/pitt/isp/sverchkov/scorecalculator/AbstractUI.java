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
public abstract class AbstractUI implements UserInterface {
    
    protected File recordFile;
    protected File outputFile;
    protected ScoreFunction scoreFunction;
    
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
