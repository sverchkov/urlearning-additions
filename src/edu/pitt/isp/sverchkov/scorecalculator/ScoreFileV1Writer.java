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
public class ScoreFileV1Writer implements ScoreWriter {
    
    private final File outFile;
    
    public ScoreFileV1Writer( File output ){
        outFile = output;
    }
    
}
