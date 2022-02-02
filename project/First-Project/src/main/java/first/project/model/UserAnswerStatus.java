package first.project.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userAnswerStatus")
@EntityListeners(AuditingEntityListener.class)
@IdClass(UserAnswerIdPrimaryKey.class)
public class UserAnswerStatus
{
    @Id
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "answerId", nullable = false)
    private Answer answer;

    @Column(name = "status", nullable = false)
    private boolean status;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Answer getAnswer()
    {
        return answer;
    }

    public void setAnswer(Answer answer)
    {
        this.answer = answer;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }
}
