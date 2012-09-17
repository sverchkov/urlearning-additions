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
public interface UserInterface {

    public void queryUser();

    public File getRecordFile();

    public File getOutputFile();

    public ScoreFunction getScoreFunction();
    
}
