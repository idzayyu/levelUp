package university.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    public Connection openConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Universities",
                    "postgres",
                    "admin"
            );

        }catch (SQLException exc){
            System.out.println("Беда");
            throw new RuntimeException(exc);
        }
    }


}
