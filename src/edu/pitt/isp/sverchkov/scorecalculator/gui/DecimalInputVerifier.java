/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.isp.sverchkov.scorecalculator.gui;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author YUS24
 */
public class DecimalInputVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        JTextField tf = (JTextField) input;
        try{
            Double.parseDouble( tf.getText() );
        }catch( NumberFormatException e ){
            return false;
        }
        return true;
    }
    
}
