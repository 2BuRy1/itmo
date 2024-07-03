package Builders;

import Network.User;

public class UserBuilder extends Builder{



    public User create(){return new User
            (buildLogin(), buildPassword());}
}
