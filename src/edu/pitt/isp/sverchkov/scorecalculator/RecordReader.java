/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author YUS24
 */
class RecordReader {
    public static RecordSet read( File recordFile ) {
        
        // Read into arrays+lists
        String splitter = "\\s*,\\s*";
        String[] varnames = null;
        List<List<String>> rows = new ArrayList<>();
        try( Scanner in = new Scanner( new BufferedReader( new FileReader( recordFile ) ) ) ){
            varnames = in.nextLine().split(splitter);
            while( in.hasNext() ){
                rows.add( Arrays.asList( in.nextLine().split(splitter) ) );                
            }
        }catch( IOException e ){}
        
        // Convert lists to arrays
        String[][] data = new String[rows.size()][];
        for( int i=0; i<data.length; i++ )
            data[i] = rows.get(i).toArray( new String[0] );
        
        RecordSet result = null;
        if( varnames != null ) result = new RecordSetImpl( varnames, data );
        return result;
    }
}
