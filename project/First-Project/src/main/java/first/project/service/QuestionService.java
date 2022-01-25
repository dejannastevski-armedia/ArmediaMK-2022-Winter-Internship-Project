package first.project.service;

import first.project.dto.QuestionDTO;

import java.util.ArrayList;

public interface QuestionService
{
    boolean checkTitle(String email);

    boolean checkQuestion(String question);

    ArrayList<String> validateAndPost(QuestionDTO questionDTO);
}
