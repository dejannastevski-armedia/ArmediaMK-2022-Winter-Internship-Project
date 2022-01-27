package first.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import first.project.dto.QuestionDTO;
import first.project.model.Question;
import first.project.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService
{
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public boolean checkTitle(String email)
    {
        if ("".equals(email))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkQuestion(String question)
    {
        if ("".equals(question))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkEmail(String email)
    {
        if ("".equals(email) || email == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<String> validateAndPost(QuestionDTO questionDTO)
    {
        ArrayList<String> res = new ArrayList<>();
        if (checkTitle(questionDTO.getTitle()) == false)
        {
            res.add("Title is empty. Please enter title");
        }
        if (checkQuestion(questionDTO.getQuestion()) == false)
        {
            res.add("Question is empty. Please enter question");
        }
        if (checkEmail(questionDTO.getEmail()) == false)
        {
            res.add("You are not logged in. Please log in to ask a question");
        }
        if (res.isEmpty())
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
}
