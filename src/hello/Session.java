/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

/**
 *
 * @author Syndarin
 */
public class Session {

    private int status;
    private String idKassi;
    private String sessionHash;
    private String name;

    public String getIdKassi() {
        return idKassi;
    }

    public String getName() {
        return name;
    }

    public String getSessionHash() {
        return sessionHash;
    }

    public int getStatus() {
        return status;
    }

    public void setIdKassi(String idKassi) {
        this.idKassi = idKassi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSessionHash(String sessionHash) {
        this.sessionHash = sessionHash;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
