import university.repository.domain.University;
import university.repository.jdbc.DatabaseService;
import university.repository.jdbc.JdbcUniversityRepository;
import university.repository.repository.UniversityRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UniversityApplication {
    public static void main(String[] args) throws SQLException {
        System.out.println("Allo");

        DatabaseService dbService = new DatabaseService();
        UniversityRepository universityRepository = new JdbcUniversityRepository(dbService);

        University itmo = universityRepository.createUniversity("Университет ИТМО", "ИТМО", 1934);
        System.out.println("Вставленная запись: " + itmo.getUniversityId());

        List<University> universities = universityRepository.findAll();
        for (University u: universities
             ) {
            System.out.println(u.getShortName() + " " + u.getFoundationYear());

        }

        Connection connection = dbService.openConnection();
        connection.close();
    }
}
