package first.project.service;

import java.util.ArrayList;

import first.project.dto.AnswerDTO;
import first.project.dto.UserAnswerStatusDTO;
import first.project.exceptions.InvalidAnswerException;
import first.project.model.Answer;
import first.project.model.UserAnswerStatus;

public interface AnswerService
{
    boolean checkAnswer(String answer);

    boolean checkEmail(String email);

    Answer validateAndSave(AnswerDTO answerDTO) throws InvalidAnswerException;

    ArrayList<Answer> getAllAnswerForQuestion(Integer id);

    Integer getUserIdFromEmail(String email);

    ArrayList<UserAnswerStatus> checkIfPreviousVoted(Integer userId, Integer answerId);

    void insertNewUserAnswerStatus(Integer userId, Integer answerId, boolean status);

    void deleteUserAnswerStatus(Integer userId, Integer answerId);

    void updateUserAnswerStatus(Integer userId, Integer answerId, boolean status);

    void updateUpVotes(Integer newUpVote, Integer answerId);

    void updateDownVotes(Integer newDownVote, Integer answerId);

    void upVoteAnswer(UserAnswerStatusDTO userAnswerStatusDTO);

    void downVoteAnswer(UserAnswerStatusDTO userAnswerStatusDTO);
}
