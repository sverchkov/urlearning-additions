/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.util.HashMap;
import java.util.HashSet;
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
        Set<Variable> parentsAndChild = new HashSet<>( parents );
        parentsAndChild.add(variable);
        
        // Calculate number of parent instantiations.
        int nParentInstantiations = 1;
        for( Variable parent : parents )
            nParentInstantiations *= parent.getCardinality();

        double aIJ = ess/variable.getCardinality();
        
        for( String state : variable.getInstantiations() ){
            // Variable state-level term
            // Gamma( alpha_ij ) / Gamma( alpha_ij + N_ij )
            double nIJ = rs.count( variable, state );            
            result += Gamma.logGamma( aIJ ) - Gamma.logGamma( aIJ + nIJ );
                        
            double aIJK = aIJ/nParentInstantiations;
            
            //LOG.debug("Getting parent instantiation counts for "+variable.getName()+" = "+state+"...");
            // We just need the parent instantiations that have non-zero counts
            Map<Map<Variable,String>, Integer> counts = new HashMap<>();
            for( Map<Variable,String> assignment : rs )
                if( assignment.get(variable).equals(state) ){
                    assignment.keySet().retainAll( parentsAndChild );
                    Integer count = counts.get( assignment );
                    counts.put(assignment, count == null ? 1 : count + 1 );
                }
            
            for( Integer nIJK : counts.values() ){
                // Parent assignment -level term
                // Gamma( alpha_ijk + N_ijk ) / Gamma( alpha_ijk )
                result += Gamma.logGamma( aIJK + nIJK ) - Gamma.logGamma( aIJK );
            }
        }
        
        return -result;
    }
    
}
