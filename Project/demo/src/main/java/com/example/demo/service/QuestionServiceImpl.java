package com.example.demo.service;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.Question;
import com.example.demo.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService
{
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean checkTitle(String title)
    {
        if (title == null || title.length() <= 2 || title.isEmpty())
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkQuestion(String question)
    {
        if (question == null || question.length() <= 5 || question.isEmpty())
        {
            return false;
        }
        return true;
    }

    @Override
    public String validateQuestionAndTitle(QuestionDTO questionDTO)
    {
        String res = "";
        if (!checkTitle(questionDTO.getTitle()))
        {
            res += "Invalid title,";
        }
        if (!checkQuestion(questionDTO.getQuestion()))
        {
            res += "Invalid question";
        }

        return res;
    }

    @Override
    public void saveQuestion(Question question)
    {
        questionRepository.save(question);

    }

    @Override
    public void createQuestion(QuestionDTO questionDTO)
    {
        Question question = new Question();
        question.setCreator(questionDTO.getEmail());
        question.setModifier(questionDTO.getEmail());
        question.setTitle(questionDTO.getTitle());
        question.setQuestion(questionDTO.getQuestion());
        questionRepository.save(question);

    }

    @Override
    public List<Question> listAll()
    {
        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public String deleteQuestion(QuestionDTO questionDTO)
    {
        String res = "";
        Question question = questionRepository.getById(questionDTO.getQuestionId());
        if (question != null)
        {
            if (question.getCreator().equals(questionDTO.getEmail()))
            {
                String query = "DELETE FROM Question q WHERE q.id=?1";
                Query nativeQuery = entityManager.createNativeQuery(query);
                nativeQuery.setParameter(1, questionDTO.getQuestionId());
                nativeQuery.executeUpdate();
            }
            else
            {
                res += "Cannot Delete: logged user is not creator of question";
            }
        }
        return res;
    }

    @Override
    public String editQuestion(QuestionDTO questionDTO)
    {
        String res = "";
        Question question = questionRepository.getById(questionDTO.getQuestionId());
        if (question != null)
        {

            if (checkTitle(questionDTO.getTitle()) && checkQuestion(questionDTO.getQuestion()))
            {
                question.setTitle(questionDTO.getTitle());
                question.setQuestion(questionDTO.getQuestion());
                question.setModifier(questionDTO.getEmail());
                questionRepository.save(question);

            }
            else
            {
                res += "Invalid data";
            }

        }
        return res;

    }
}
