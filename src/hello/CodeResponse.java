/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author Syndarin
 */
public class CodeResponse {
    
    private int status;
    private String text;
    private String sum;
    private String percent;

    public String getPercent() {
        return percent;
    }

    public int getStatus() {
        return status;
    }

    public String getSum() {
        return sum;
    }

    public String getText() {
        return text;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
    
}
