package first.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import first.project.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>
{
    @Query(value = "SELECT * FROM Answer a WHERE a.questionid=?1", nativeQuery = true)
    public ArrayList<Answer> getAllAnswerForQuestion(Integer id);
}
