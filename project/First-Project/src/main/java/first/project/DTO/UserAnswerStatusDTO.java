package first.project.dto;

public class UserAnswerStatusDTO
{
    Integer answerId;
    String userEmail;

    public Integer getAnswerId()
    {
        return answerId;
    }

    public void setAnswerId(Integer answerId)
    {
        this.answerId = answerId;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }
}
