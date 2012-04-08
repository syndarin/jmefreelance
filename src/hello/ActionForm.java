/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Syndarin
 */
public class ActionForm extends Form{

    private final static String title="Введите код";
    
    private MainScreen root;
    
    public ActionForm(MainScreen root) {
        super(title);
        this.root=root;
    }
    
    private void setupForm(){
    
    }
    
}
