/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/**
 * @author Syndarin
 */
public class MainScreen extends MIDlet {

    public final static String HOST="http://62.149.12.172:8084";
    
    public void startApp() {
        setLoginPage();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void setLoginPage(){
        Display.getDisplay(this).setCurrent(new LoginForm(this));
    }
    
    public void setActionPage(Session session){
        Display.getDisplay(this).setCurrent(new ActionForm(this, session));
    }
}
