package first.project.dto;

import java.sql.Date;

public class QuestionDTO
{
    private String title;
    private String question;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }
}
