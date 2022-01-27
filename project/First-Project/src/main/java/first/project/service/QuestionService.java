package first.project.service;

import java.util.ArrayList;
import java.util.List;

import first.project.dto.QuestionDTO;
import first.project.model.Question;

public interface QuestionService
{
    boolean checkTitle(String email);

    boolean checkQuestion(String question);

    boolean checkEmail(String email);

    ArrayList<String> validateAndPost(QuestionDTO questionDTO);

    List<Question> getAllQuestions();
}
