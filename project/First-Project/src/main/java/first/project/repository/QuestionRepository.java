package first.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import first.project.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>
{
}
