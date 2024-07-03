package Network;

import Commands.Command;
import MainClasses.SpaceMarine;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 20L;
    public SpaceMarine spaceMarine;
    Command command;
    Object args;


    public Request( SpaceMarine spaceMarine) {

        this.spaceMarine=spaceMarine;
    }

    public Request(Command command, Object args){
        this.command = command;
        this.args = args;
    }
    public Request(Command command ,SpaceMarine spaceMarine, Object args){
        this.command = command;
        this.spaceMarine = spaceMarine;
        this.args = args;

    }
    public Request(Command command, SpaceMarine spaceMarine){
        this.command= command;
        this.spaceMarine = spaceMarine;
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

