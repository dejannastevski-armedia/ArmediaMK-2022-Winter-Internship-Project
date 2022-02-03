package com.example.demo.service;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.model.UserAnswer;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserAnswerRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService
{
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Override
    public boolean checkAnswer(String answer)
    {
        if (answer == null || answer.length() <= 2 || answer.isEmpty())
        {
            return false;
        }
        return true;
    }

    @Override
    public String validateAnswer(AnswerDTO answerDTO)
    {
        String res = "";
        if (!checkAnswer(answerDTO.getAnswer()))
        {
            res += "Invalid answer,";
        }

        return res;
    }

    @Override
    public Answer createAnswer(AnswerDTO answerDTO)
    {
        Answer answer = new Answer();
        answer.setCreator(answerDTO.getEmail());
        answer.setAnswer(answerDTO.getAnswer());
        try
        {
            Optional<Question> questionOptional = questionRepository.findById(answerDTO.getQuestionId());
            if (questionOptional.isPresent())
            {
                Question question = questionOptional.get();
                answer.setQuestion(question);
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            return null;
        }
        answer.setDownVotes(0);
        answer.setUpVotes(0);
        return answerRepository.save(answer);

    }

    @Override
    public List<Answer> listAllAnswersPerQuestionId(Long questionId)
    {
        return answerRepository.listAllAnswersPerQuestionId(questionId);
    }

    @Override
    @Transactional
    public void updateUpVotes(AnswerDTO answerDTO)
    {
        UserAnswer userAnswer = userAnswerRepository.findStatusByAnswerAndUser(answerDTO.getAnswerId(), answerDTO.getUserId());
        if (userAnswer == null)
        {
            UserAnswer saveUserAnswer = new UserAnswer();
            Answer answer = answerRepository.getById(answerDTO.getAnswerId());
            saveUserAnswer.setAnswer(answer);

            User user = userRepository.getById(answerDTO.getUserId());
            saveUserAnswer.setUser(user);

            saveUserAnswer.setLiked(true);
            userAnswerRepository.save(saveUserAnswer);

            String query = "UPDATE Answer a SET a.up_votes=a.up_votes+1 WHERE a.id=?1";
            Query nativeQuery = entityManager.createNativeQuery(query);
            nativeQuery.setParameter(1, answerDTO.getAnswerId());
            nativeQuery.executeUpdate();

        }
        else
        {
            if (userAnswer.isLiked())
            {
                userAnswer.setLiked(false);
                userAnswerRepository.save(userAnswer);

                String query = "UPDATE Answer a SET a.up_votes=a.up_votes-1 WHERE a.id=?1";
                Query nativeQuery = entityManager.createNativeQuery(query);
                nativeQuery.setParameter(1, answerDTO.getAnswerId());
                nativeQuery.executeUpdate();
            }
            else if (userAnswer.isDisliked())
            {
                userAnswer.setLiked(true);
                userAnswer.setDisliked(false);

                String query = "UPDATE Answer a SET a.up_votes=a.up_votes+1, a.down_votes=a.down_votes-1  WHERE a.id=?1";
                Query nativeQuery = entityManager.createNativeQuery(query);
                nativeQuery.setParameter(1, answerDTO.getAnswerId());
                nativeQuery.executeUpdate();

            }
            else if (!userAnswer.isDisliked() && !userAnswer.isLiked())
            {
                userAnswer.setLiked(true);
                userAnswerRepository.save(userAnswer);

                String query = "UPDATE Answer a SET a.up_votes=a.up_votes+1 WHERE a.id=?1";
                Query nativeQuery = entityManager.createNativeQuery(query);
                nativeQuery.setParameter(1, answerDTO.getAnswerId());
                nativeQuery.executeUpdate();
            }

        }

    }

    @Override
    @Transactional
    public void updateDownVotes(AnswerDTO answerDTO)
    {
        UserAnswer userAnswer = userAnswerRepository.findStatusByAnswerAndUser(answerDTO.getAnswerId(), answerDTO.getUserId());

        if (userAnswer == null)
        {
            UserAnswer saveUserAnswer = new UserAnswer();
            Answer answer = answerRepository.getById(answerDTO.getAnswerId());
            saveUserAnswer.setAnswer(answer);

            User user = userRepository.getById(answerDTO.getUserId());
            saveUserAnswer.setUser(user);

            saveUserAnswer.setDisliked(true);
            userAnswerRepository.save(saveUserAnswer);

            String query = "UPDATE Answer a SET a.down_votes=a.down_votes+1 WHERE a.id=?1";
            Query nativeQuery = entityManager.createNativeQuery(query);
            nativeQuery.setParameter(1, answerDTO.getAnswerId());
            nativeQuery.executeUpdate();

        }
        else
        {
            if (userAnswer.isDisliked())
            {
                userAnswer.setDisliked(false);
                userAnswerRepository.save(userAnswer);

                String query = "UPDATE Answer a SET a.down_votes=a.down_votes-1 WHERE a.id=?1";
                Query nativeQuery = entityManager.createNativeQuery(query);
                nativeQuery.setParameter(1, answerDTO.getAnswerId());
                nativeQuery.executeUpdate();
            }
            else if (userAnswer.isLiked())
            {
                userAnswer.setLiked(false);
                userAnswer.setDisliked(true);

                String query = "UPDATE Answer a SET a.down_votes=a.down_votes+1, a.up_votes=a.up_votes-1 WHERE a.id=?1";
                Query nativeQuery = entityManager.createNativeQuery(query);
                nativeQuery.setParameter(1, answerDTO.getAnswerId());
                nativeQuery.executeUpdate();

            }
            else if (!userAnswer.isLiked() && !userAnswer.isDisliked())
            {
                userAnswer.setDisliked(true);

                String query = "UPDATE Answer a SET a.down_votes=a.down_votes+1 WHERE a.id=?1";
                Query nativeQuery = entityManager.createNativeQuery(query);
                nativeQuery.setParameter(1, answerDTO.getAnswerId());
                nativeQuery.executeUpdate();
            }
        }

    }

}
