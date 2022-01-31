package first.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import first.project.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>
{
    @Query("SELECT q FROM Question q WHERE q.id=?1")
    public Question findById(int id);
}
