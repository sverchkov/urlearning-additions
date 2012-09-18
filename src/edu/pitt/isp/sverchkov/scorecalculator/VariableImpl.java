/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.util.Objects;
import java.util.Set;

/**
 *
 * @author YUS24
 */
public class VariableImpl implements Variable {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VariableImpl other = (VariableImpl) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.values, other.values)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.values);
        return hash;
    }
    
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
