package first.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import first.project.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>
{
}
