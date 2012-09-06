/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

/**
 *
 * @author YUS24
 */
public interface ScoreWriter {

    public void writeScores(RecordSet rs, ScoreFunction sf);
    
}
