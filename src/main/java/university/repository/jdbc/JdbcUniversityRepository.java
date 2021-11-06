package university.repository.jdbc;

import university.repository.domain.University;
import university.repository.repository.UniversityRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcUniversityRepository implements UniversityRepository {

    private final DatabaseService dbService;

    public JdbcUniversityRepository(DatabaseService dbService) {
        this.dbService = dbService;
    }

    @Override
    public University createUniversity(String name, String short_name, Integer foundation_year) {
        try(Connection connection = dbService.openConnection()) {
            PreparedStatement stmt = connection.prepareStatement("insert into university (name, short_name, foundation_year) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            stmt.setString(2, short_name);
            if(foundation_year == null) {
                stmt.setNull(3, JDBCType.INTEGER.getVendorTypeNumber());
            } else {
                stmt.setInt(3, foundation_year);
            }



            int createRows =  stmt.executeUpdate();
            System.out.println("Вставленно строк: " + createRows);

            ResultSet generatedkeysSet = stmt.getGeneratedKeys();
            generatedkeysSet.next();
            Long universityId = generatedkeysSet.getLong(1);

            return new University(universityId, name, short_name, foundation_year);

        }catch(SQLException exc) {
            System.out.println("Ломается " + exc.getMessage());

        }
        return null;
    }

    @Override
    public List<University> findAll() {
        try(Connection  connection = dbService.openConnection()){
            Statement stat = connection.createStatement();
            ResultSet resultSet = stat.executeQuery("SELECT * FROM public.university");

            return retrieveFromResultSet(resultSet);
        }catch (SQLException exc){
            System.out.println("Ломается " + exc.getMessage());

            return Collections.emptyList();
        }


    }
    private List<University> retrieveFromResultSet(ResultSet resultSet) throws SQLException{
        List<University> universities = new ArrayList<>();
        while(resultSet.next()){
            Long universityId = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String short_name = resultSet.getString("short_name");
            Integer foundation_year = resultSet.getInt("foundation_year");

            University university = new University(universityId, name, short_name, foundation_year);
            universities.add(university);
        }
        return universities;
    }
}
