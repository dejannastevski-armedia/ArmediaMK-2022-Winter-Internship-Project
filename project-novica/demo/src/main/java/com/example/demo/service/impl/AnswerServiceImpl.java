package com.example.demo.service.impl;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    public void upVote(Long id)
    {
        String query = "UPDATE answer SET up_votes=up_votes+1 WHERE id=?1";
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter(1, id);
        nativeQuery.executeUpdate();
    }

    @Override
    @Transactional
    public void downVote(Long id)
    {
        String query = "UPDATE answer SET down_votes=down_votes+1 WHERE id=?1";
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter(1, id);
        nativeQuery.executeUpdate();
    }
}
