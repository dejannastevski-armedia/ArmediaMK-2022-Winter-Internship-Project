package first.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import first.project.model.UserAnswerStatus;

public interface UserAnswerStatusRepository extends JpaRepository<UserAnswerStatus, Long>
{
}
