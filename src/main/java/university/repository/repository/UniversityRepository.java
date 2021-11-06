package university.repository.repository;

import university.repository.domain.University;

import java.util.List;

public interface UniversityRepository {
    List<University> findAll();

    University createUniversity(String name, String short_name, Integer foundation_year);
}
