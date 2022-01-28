package first.project.service;

import first.project.dto.AnswerDTO;
import first.project.exceptions.AddAnswerException;
import first.project.model.Answer;

public interface AnswerService
{
    boolean checkAnswer(String answer);

    boolean checkEmail(String email);

    Answer validateAndPost(AnswerDTO answerDTO) throws AddAnswerException;
}
