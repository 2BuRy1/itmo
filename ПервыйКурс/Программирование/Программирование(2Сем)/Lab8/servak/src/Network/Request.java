package Network;

import Commands.Command;
import MainClasses.SpaceMarine;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 20L;

    User user;

    String registration;
    public SpaceMarine spaceMarine;
    Command command;
    Object args;

    public Request(String registration, User user){

        this.registration = registration;
        this.user = user;
    }


    public Request( SpaceMarine spaceMarine, User user) {
        this.spaceMarine=spaceMarine;
        this.user=user;
    }

    public Request(Command command, Object args, User user){
        this.command = command;
        this.args = args;
        this.user = user;
    }
    public Request(Command command ,SpaceMarine spaceMarine, Object args, User user){
        this.command = command;
        this.spaceMarine = spaceMarine;
        this.args = args;
        this.user = user;

    }
    public Request(Command command, SpaceMarine spaceMarine, User user){
        this.command= command;
        this.spaceMarine = spaceMarine;
        this.user = user;
    }


    public User getUser(){
        return user;
    }

    public Object getArgs(){
        return args;
    }

    public Command getCommand(){
        return command;
    }
    public Request(Command command){
        this.command = command;
    }

    public SpaceMarine getObject(){
        return this.spaceMarine;
    }
}

