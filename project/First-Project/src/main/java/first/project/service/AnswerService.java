package first.project.service;

import java.util.ArrayList;

import first.project.dto.AnswerDTO;

public interface AnswerService
{
    boolean checkAnswer(String answer);

    boolean checkEmail(String email);

    ArrayList<String> validateAndPost(AnswerDTO answerDTO);
}
