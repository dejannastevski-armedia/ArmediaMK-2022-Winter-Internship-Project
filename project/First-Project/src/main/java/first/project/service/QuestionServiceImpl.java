package first.project.service;

import first.project.dto.QuestionDTO;
import first.project.model.Question;
import first.project.repository.QuestionRepository;
import first.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    public ArrayList<String> validateAndPost(QuestionDTO questionDTO)
    {
        ArrayList<String> res = new ArrayList<String>();
        if (checkTitle(questionDTO.getTitle()) == false)
        {
            res.add("Title is empty. Please enter title");
        }
        if (checkQuestion(questionDTO.getQuestion()) == false)
        {
            res.add("Question is empty. Please enter question");
        }
        if (res.isEmpty())
        {
            Question question = new Question();
            question.setCreator("Kiko");
            question.setModifier("Kiko");
            question.setTitle(questionDTO.getTitle());
            question.setQuestion(questionDTO.getQuestion());
            questionRepository.save(question);
        }
        return res;
    }
}
