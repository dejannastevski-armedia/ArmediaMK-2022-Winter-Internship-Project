package first.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import first.project.dto.AnswerDTO;
import first.project.exceptions.InvalidAnswerException;
import first.project.model.Answer;
import first.project.model.Question;
import first.project.repository.AnswerRepository;
import first.project.repository.QuestionRepository;

@Service
public class AnswerServiceImpl implements AnswerService
{
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public boolean checkEmail(String email)
    {
        if (email == null || "".equals(email))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkAnswer(String answer)
    {
        if (answer == null || "".equals(answer))
        {
            return false;
        }
        return true;
    }

    @Override
    public Answer validateAndSave(AnswerDTO answerDTO) throws InvalidAnswerException
    {
        ArrayList<String> res = new ArrayList<>();
        if (checkEmail(answerDTO.getEmail()) == false)
        {
            throw new InvalidAnswerException("You are not logged in. Please log in to answer the question");
        }
        if (checkAnswer(answerDTO.getAnswer()) == false)
        {
            throw new InvalidAnswerException("Answer is empty. Please enter answer");
        }
        if (res.isEmpty())
        {
            Answer answer = new Answer();
            answer.setAnswer(answerDTO.getAnswer());
            answer.setCreator(answerDTO.getEmail());
            answer.setDownvotes(0);
            answer.setUpvotes(0);
            Question question = questionRepository.findById(answerDTO.getId());
            if (question == null)
            {
                throw new InvalidAnswerException("Invalid question ID");
            }
            answer.setQuestion(question);
            return answerRepository.save(answer);
        }
        return null;
    }

    @Override
    public ArrayList<Answer> getAllAnswerForQuestion(Integer id)
    {
        return answerRepository.getAllAnswerForQuestion(id);
    }
}
