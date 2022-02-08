package com.example.demo.service.impl;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService
{

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean checkTitle(String title)
    {
        if (title == null || title.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean checkQuestion(String question)
    {
        if (question == null || question.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void saveQuestion(Question question)
    {
        questionRepository.save(question);
    }

    @Override
    public String validateQuestion(String question, String title)
    {
        String res = "";
        if (question == null || question.isEmpty())
        {
            res += "Enter a question ";
        }
        if (title == null || title.isEmpty())
        {
            res += "Enter a title ";
        }
        return res;
    }

    @Override
    public String createQuestion(QuestionDTO questionDTO)
    {
        String res = "";

        if (questionDTO.getQuestion() == null || questionDTO.getQuestion().isEmpty())
        {
            res += "Question is empty ";
        }
        if (questionDTO.getTitle() == null || questionDTO.getTitle().isEmpty())
        {
            res += "Title is empty ";
        }
        if (res.isEmpty() && validateQuestion(questionDTO.getQuestion(), questionDTO.getTitle()).isEmpty())
        {
            Question question = new Question();
            question.setCreator(questionDTO.getEmail());
            question.setModifier(questionDTO.getEmail());
            question.setTitle(questionDTO.getTitle());
            question.setQuestion(questionDTO.getQuestion());

            questionRepository.save(question);
        }
        return res;
    }

    @Override
    public List<Question> getAllQuestions()
    {
        return questionRepository.findAll();
    }

    @Override
    public void deleteQuestion(QuestionDTO questionDTO) throws IllegalAccessException
    {
        Optional<User> u = userRepository.findByEmail(questionDTO.getEmail());
        Question q = questionRepository.getById(questionDTO.getQuestionId());
        if (u.get().getEmail().equals(q.getCreator()))
        {
            questionRepository.deleteById(questionDTO.getQuestionId());
        }
        else
        {
            throw new IllegalAccessException("You are not creator of that question");
        }
    }

    @Override
    public String editQuestion(QuestionDTO questionDTO)
    {
        String res = "";
        if (questionDTO.getQuestion() == null || questionDTO.getQuestion().isEmpty())
        {
            res += "Question is empty ";
        }
        if (questionDTO.getTitle() == null || questionDTO.getTitle().isEmpty())
        {
            res += "Title is empty ";
        }
        Question question = questionRepository.getById(questionDTO.getQuestionId());
        if (res.isEmpty() && validateQuestion(questionDTO.getQuestion(), questionDTO.getTitle()).isEmpty())
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
        return res;
    }
}
