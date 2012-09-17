/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.util.List;
import java.util.Set;

/**
 * Interface for representing a score function
 * @author YUS24
 */
public interface ScoreFunction {
    /**
     * Gives the local score of the variable 'variable' having the parent set
     * 'parents' based on the data in record set 'rs'.
     * @param variable
     * @param parents set of parents
     * @param rs record set
     * @return the score
     */
    double score( Variable variable, Set<Variable> parents, RecordSet rs );
}
