/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import edu.pitt.isp.sverchkov.combinatorics.Assignments;
import java.util.Map;
import java.util.Set;
import org.apache.commons.math.special.Gamma;
import org.apache.log4j.Logger;

/**
 * The BDeu scoring function.
 * @author YUS24
 */
class BDeu implements ScoreFunction {
    
    private static final Logger LOG = Logger.getLogger(BDeu.class);
    private final double ess;

    /**
     * Constructor
     * @param ess The Equivalent Sample Size (ESS) hyperparameter.
     */
    public BDeu(double ess) {
        this.ess = ess;
    }

    @Override
    public double score(Variable variable, Set<Variable> parents, RecordSet rs) {
        
        double result = 0;
        
        // Calculate number of parent instantiations and initialize the
        // iterable parent instantiations object.
        int nParentInstantiations = 1;
        Assignments<Variable,String> parentInstantiations;
        {
            Variable[] pArray = parents.toArray( new Variable[parents.size()] );
            String[][] parentStates = new String[parents.size()][];
            
            for( int i=0; i<pArray.length; i++ ){                
                int cardinality = pArray[i].getCardinality();
                nParentInstantiations *= cardinality;
                
                parentStates[i] = new String[cardinality];
                
                int j=0;
                for( String state : pArray[i].getInstantiations() )
                    parentStates[i][j++] = state;
            }
            
            parentInstantiations = new Assignments<>( pArray, parentStates );
            LOG.debug("Parent instantiation iterable initialized.");
        }

        double aIJ = ess/variable.getCardinality();
        
        for( String state : variable.getInstantiations() ){
            // Variable state-level term
            // Gamma( alpha_ij ) / Gamma( alpha_ij + N_ij )
            double nIJ = rs.count( variable, state );            
            result += Gamma.logGamma( aIJ ) - Gamma.logGamma( aIJ + nIJ );
                        
            double aIJK = aIJ/nParentInstantiations;
            
            LOG.debug("Iterating over instantiations for "+variable.getName()+" = "+state+"...");
            for( Map<Variable,String> assignment : parentInstantiations ){
                // Parent assignment -level term
                // Gamma( alpha_ijk + N_ijk ) / Gamma( alpha_ijk )
                double nIJK = rs.count( assignment );
                result += Gamma.logGamma( aIJK + nIJK ) - Gamma.logGamma( aIJK );
            }
        }
        
        return -result;
    }
    
}
