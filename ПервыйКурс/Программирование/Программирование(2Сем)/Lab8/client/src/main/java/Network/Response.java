package Network;

import MainClasses.SpaceMarine;

import java.io.Serial;
import java.io.Serializable;
import java.util.Vector;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 21L;

    private Vector<SpaceMarine> marines;


    private LoginError loginError;

    private String result="Success\n---\n";

    public Response(Vector<SpaceMarine> marines) {
        this.marines = marines;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public Response(String result){
        this.result = result;
    }

    public Response(LoginError loginError, String result) {
        this.loginError = loginError;
        this.result = result;
    }

    public Response(LoginError loginError) {
        this.loginError = loginError;
    }

    public LoginError getLoginError(){
        return this.loginError;
    }

    public String toString(){
        return result + loginError;
    }

    public Vector<SpaceMarine> getMarines() {
        return marines;
    }


}
