package Network;

import java.io.Serial;
import java.io.Serializable;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 21L;


    private LoginError loginError;

    private String result="Success\n---\n";

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

    public LoginError getLoginError(){
        return this.loginError;
    }

    public String toString(){
        return result + loginError;
    }


}
