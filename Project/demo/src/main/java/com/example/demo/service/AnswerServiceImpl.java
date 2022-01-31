package com.example.demo.service;

import com.example.demo.dto.AnswerDTO;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService
{
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

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

}
