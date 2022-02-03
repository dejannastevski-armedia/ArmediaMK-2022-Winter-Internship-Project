package com.example.demo.service.impl;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.dto.UserAnswerDTO;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.model.UserAnswer;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserAnswerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService
{

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAnswerRepository userAnswerRepository;

    @Override
    public List<Answer> listAllAnswers()
    {
        return answerRepository.findAll();
    }

    @Override
    public String createAnswer(AnswerDTO answerDTO)
    {
        ArrayList<String> res = new ArrayList<>();
        if (!isValidAnswer(answerDTO.getAnswer()))
        {
            res.add("Answer is empty");
        }
        if (res == null || res.isEmpty())
        {
            Answer answer = new Answer();
            answer.setAnswer(answerDTO.getAnswer());
            answer.setCreator(answerDTO.getEmail());
            answer.setDownVotes(0);
            answer.setUpVotes(0);
            Optional<Question> questionOptional = questionRepository.findById(answerDTO.getQuestionId());
            try
            {
                Question question = questionRepository.getById(answerDTO.getQuestionId());
                if (question != null && questionOptional.isPresent())
                {
                    answer.setQuestion(question);
                }
                answerRepository.save(answer);
            }
            catch (Exception e)
            {
                res.add("Invalid question ID");
            }
        }
        return String.join(", ", res);
    }

    private boolean isValidAnswer(String answer)
    {
        if (answer == null || answer.isEmpty())
        {
            return false;
        }
        return true;
    }

    @Override
    public List<Answer> listAllAnswersPerQuestion(Long id)
    {
        return answerRepository.listAllAnswerPerQuestion(id);
    }

    @Override
    @Transactional
    public void upVote(UserAnswerDTO userAnswerDTO)
    {
        UserAnswer userAnswer = userAnswerRepository.findUserAnswerByUserIdAndAnswerId(userAnswerDTO.getUserId(),
                userAnswerDTO.getAnswerId());
        if (userAnswer == null)
        {
            UserAnswer newUserAnswer = new UserAnswer();
            // find user
            Optional<User> u = userRepository.findById(userAnswerDTO.getUserId());
            // find answer
            Optional<Answer> a = answerRepository.findById(userAnswerDTO.getAnswerId());
            newUserAnswer.setUser(u.get());
            newUserAnswer.setAnswer(a.get());
            newUserAnswer.setLiked(true);
            newUserAnswer.setDisliked(false);
            userAnswerRepository.save(newUserAnswer);
            Answer answerToBeUpdated = a.get();
            answerToBeUpdated.setUpVotes(answerToBeUpdated.getUpVotes() + 1);
            answerRepository.save(answerToBeUpdated);
        }
        else
        {
            if (userAnswer.getLiked())
            {
                userAnswerRepository.delete(userAnswer);
                Optional<Answer> a = answerRepository.findById(userAnswerDTO.getAnswerId());
                Answer answerToBeUpdated = a.get();
                answerToBeUpdated.setUpVotes(answerToBeUpdated.getUpVotes() - 1);
                answerRepository.save(answerToBeUpdated);
            }
            else if (userAnswer.getDisliked())

            {
                userAnswer.setDisliked(false);
                userAnswer.setLiked(true);
                userAnswerRepository.save(userAnswer);
                Optional<Answer> a = answerRepository.findById(userAnswerDTO.getAnswerId());
                Answer answerToBeUpdated = a.get();
                answerToBeUpdated.setUpVotes(answerToBeUpdated.getUpVotes() + 1);
                answerToBeUpdated.setDownVotes(answerToBeUpdated.getDownVotes() - 1);
                answerRepository.save(answerToBeUpdated);
            }
        }

    }

    @Override
    @Transactional
    public void downVote(UserAnswerDTO userAnswerDTO)
    {

    }
}
