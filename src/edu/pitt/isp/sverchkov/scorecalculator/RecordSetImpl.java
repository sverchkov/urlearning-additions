/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.util.*;

/**
 * Simple recordset implementations as a 2D array of strings
 * @author YUS24
 */
public class RecordSetImpl implements RecordSet {
    
    private String[][] data;
    private Variable[] variables;
    private Map<Variable,Integer> indexes;

    RecordSetImpl(String[] varnames, String[][] data) {
        this.data = data;
        indexes = new HashMap<>();
        variables = new Variable[varnames.length];
        for( int i=0; i<varnames.length; i++ ){
            Set<String> states = new HashSet<>();
            for( int j=0; j<data.length; j++ )
                states.add(data[j][i]);
            indexes.put(variables[i] = new VariableImpl( varnames[i], states ), i);
        }
    }

    @Override
    public Variable[] getVariableArray() {
        return variables;
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public int count(Variable variable, String state) {
        return count( Collections.singletonMap(variable, state) );
    }

    @Override
    public int count(Map<Variable, String> assignment) {
        int count = 0;
        OUTER: for( String[] row : data ){
            for( Map.Entry<Variable, String> instantiation : assignment.entrySet() )
                if( !row[indexes.get(instantiation.getKey())].equals(instantiation.getValue()) ) continue OUTER;
            ++count;
        }
        return count;
    }

    @Override
    public Iterator<Map<Variable, String>> iterator() {
        return new RecordIterator();
    }
    
    private class RecordIterator implements Iterator<Map<Variable, String>> {
        
        private int index=0;

        @Override
        public boolean hasNext() {
            return index < data.length;
        }

        @Override
        public Map<Variable, String> next() {
            Map<Variable,String> result = new HashMap<>();
            for( int i=0; i < variables.length; i++ )
                result.put(variables[i],data[index][i]);
            ++index;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This iterator does not support the remove operation.");
        }
    }
    
}
