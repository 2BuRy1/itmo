import Exceptions.InvalidDataException;
import Network.Program;

import java.io.IOException;

import static Enums.MeleeWeapon.valueOf;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, InvalidDataException {

        Program program = new Program();
        program.execute();

    }
}




