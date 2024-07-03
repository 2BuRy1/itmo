package Managers;

public class QueryManager {



    String findUser = "SELECT passwd, salt FROM users where name = ?; ";

    String getPassword = "SELECT salt from users where name = ? ;";
    String addUser = "INSERT INTO users(name, passwd, salt) VALUES (?, ?, ?)";

    String addSpaceMarine = """
            INSERT INTO marines(name, y, x, creationdate, health, category, weapontype, meleeweapon, chaptername, marinescount, userlogin) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) 
            RETURNING id;
            """;

    String clearObjects = "delete from marines where (userlogin = ?) and (id = ?) returning id;";

    String deleteObject = "delete from marines where (userlogin = ?) and (id = ?) returning id;";

    String updateObject = """
            update marines 
            set (name, y, x, creationdate, health, category, weapontype, meleeweapon, chaptername, marinescount) = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) where (userlogin = ?) and (id = ?) returning id;
            """;

    String addObjects = """
            select * from marines ;
            
            """;
}
