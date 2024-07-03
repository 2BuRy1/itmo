package Commands;

import Managers.CollectionManager;
import Managers.DataBaseManager;
import Network.LoginError;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Login extends Command implements Serializable {


    @Serial
    private final static long serialVersionUID = 25L;
    DataBaseManager dataBaseManager;

    public Login(DataBaseManager dataBaseManager) {
        super("login");
        this.dataBaseManager = dataBaseManager;

    }

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if(dataBaseManager.existUser(request.getUser())){
            return new Response("Авторизация прошла успешно");
        }
        else{
            return new Response(LoginError.LOGIN_ERROR,"Авторизация не прошла");
        }
    }
}
