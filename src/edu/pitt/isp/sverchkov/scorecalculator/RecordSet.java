/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.util.Map;

/**
 *
 * @author YUS24
 */
public interface RecordSet extends Iterable<Map<Variable,String>> {

    /**
     * @return an array of variable descriptions
     */
    public Variable[] getVariableArray();
    
    /**
     * @return the number of records
     */
    public int size();
    
    /**
     * @param variable
     * @param state
     * @return The number of records where variable 'variable' takes state 'state'
     */
    public int count( Variable variable, String state );
    
    /**
     * @param assignment a map of variable-string pairs representing the
     * assignment of that collection of variables to those corresponding states.
     * @return The number of records that match this assignment.
     */
    public int count( Map<Variable,String> assignment );
    
}
