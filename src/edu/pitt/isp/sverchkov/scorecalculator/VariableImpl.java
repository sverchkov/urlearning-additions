/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.util.Set;

/**
 *
 * @author YUS24
 */
public class VariableImpl implements Variable {
    
    private final String name;
    private final Set<String> values;
    
    public VariableImpl( String name, Set<String> values ){
        this.name = name;
        this.values = values;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCardinality() {
        return values.size();
    }

    @Override
    public Iterable<String> getInstantiations() {
        return values;
    }
    
}
