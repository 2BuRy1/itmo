package Commands;

import Managers.DataBaseManager;
import Network.LoginError;
import Network.Request;
import Network.Response;
import Network.Server;

import java.io.Serial;
import java.io.Serializable;

public class Register extends Command implements Serializable {


    @Serial
    private final static long serialVersionUID = 24L;
    DataBaseManager dataBaseManager;

    public Register(DataBaseManager dataBaseManager) {
        super("register");
        this.dataBaseManager = dataBaseManager;
    }

    @Override
    public Response execute(Request request) {
        if (!dataBaseManager.existUser(request.getUser())) {
            dataBaseManager.addUser(request.getUser());
            return new Response("Регистрация прошла успешно");

        }
        return new Response(LoginError.LOGIN_ERROR,"Введенный логин занят, попробуйте ввести НАСТОЯЩИЕ(НЕКРАДЕННЫЕ) данные или зарегестрируйтесб");
    }
}
