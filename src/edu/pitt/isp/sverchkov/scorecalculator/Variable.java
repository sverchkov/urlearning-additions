/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

/**
 *
 * @author YUS24
 */
public interface Variable {

    public String getName();

    public int getCardinality();

    public Iterable<String> getInstantiations();
    
}
