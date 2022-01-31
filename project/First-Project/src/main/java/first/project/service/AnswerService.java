package first.project.service;

import java.util.ArrayList;

import first.project.dto.AnswerDTO;
import first.project.exceptions.InvalidAnswerException;
import first.project.model.Answer;

public interface AnswerService
{
    boolean checkAnswer(String answer);

    boolean checkEmail(String email);

    Answer validateAndSave(AnswerDTO answerDTO) throws InvalidAnswerException;

    ArrayList<Answer> getAllAnswersById(Integer id);
}
