/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author YUS24
 */
public class ScoreFileV1Writer implements ScoreWriter {
    
    private final File outFile;
    
    public ScoreFileV1Writer( File output ){
        outFile = output;
    }

    @Override
    public void writeScores(RecordSet rs, ScoreFunction sf) {
        
        Variable[] variables = rs.getVariableArray();
        
        if( variables.length > 31 )
            throw new UnsupportedOperationException("Current implementation does not support more than 31 variables.");
        
        long[] bitmasks = new long[variables.length];
        long all = 0;
        for( int i=0; i < bitmasks.length; i++ )
            all |= ( bitmasks[i] = 1L << i );
        
        // Initialize the output stream
        try {
            try ( DataOutputStream stream = new DataOutputStream( new BufferedOutputStream( new FileOutputStream( outFile ) ) )){
                
                // Write the header
                writeHeader( stream, variables.length-1, rs.size(), variables.length );

                // Iterate over all variables
                for( int i=0; i < variables.length; i++ ){

                    // Write header for variable
                    writeVariable( stream, variables[i], (int) all/2 );

                    // Iterate over all subsets
                    for( long bits = all; bits >= 0; bits-- )
                        // If set doesn't contain current variable (i)
                        if( ( bits & bitmasks[i] ) == 0 ){

                            // Make set
                            Set<Variable> set = new HashSet<>();
                            for( int j=0; j<variables.length; j++ )
                                // If set contains variable j
                                if( ( bits & bitmasks[j] ) != 0 )
                                    set.add( variables[j] );

                            // Compute score
                            double score = sf.score( variables[i], set, rs );

                            // Write score
                            writeScore( stream, bits, score );
                        }
                }
            }        
        }catch( FileNotFoundException e ){
            e.printStackTrace();
        }catch( IOException e ){
            e.printStackTrace();
        }
    }

    private void writeVariable(DataOutputStream stream, Variable variable, int numScores) throws IOException {
        stream.writeUTF( variable.getName() );
        stream.writeByte( variable.getCardinality() );
        for( String instantiation : variable.getInstantiations() )
            stream.writeUTF(instantiation);
        stream.writeInt(numScores);
    }

    private void writeScore(DataOutputStream stream, long bits, double score) throws IOException {
        stream.writeLong(bits);
        stream.writeFloat( (float) score );
    }

    private void writeHeader(DataOutputStream stream, int nParents, int nRecords, int nVariables) throws IOException {
        stream.writeByte(1); // Version number 1
        stream.writeByte(nParents); // Max number of parents
        stream.writeInt(nRecords); // Number of records
        stream.writeInt(nVariables); // Number of variables
    }
    
}
