package first.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

import first.project.dto.AnswerDTO;
import first.project.dto.UserAnswerStatusDTO;
import first.project.exceptions.InvalidAnswerException;
import first.project.model.Answer;
import first.project.model.UserAnswerStatus;
import first.project.service.AnswerService;
import first.project.service.UserAnswerStatusService;

@Controller
@RequestMapping(value = "/answers")
public class AnswerController
{
    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserAnswerStatusService userAnswerStatusService;

    @GetMapping("/{userId}/view-answer/{answerId}")
    public String ViewAnswer(@PathVariable String answerId, @PathVariable String userId, Model model)
    {
        model.addAttribute("id", answerId);
        ArrayList<Answer> answerList = answerService.getAllAnswerForQuestion(Integer.parseInt(answerId));
        ArrayList<UserAnswerStatus> userAnswerStatusArrayList = userAnswerStatusService.getAllByUserId(Integer.parseInt(userId));
        model.addAttribute("userAnswerStatusArrayList", userAnswerStatusArrayList);
        model.addAttribute("answerList", answerList);
        return "viewAnswer";
    }

    @PostMapping("/answer-successful")
    @ResponseBody
    public ResponseEntity<Answer> postAnswer(@RequestBody AnswerDTO answerDTO) throws InvalidAnswerException
    {
        Answer answer = answerService.validateAndSave(answerDTO);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/up-vote-answer")
    @ResponseBody
    public ResponseEntity<String> upVoteAnswer(@RequestBody UserAnswerStatusDTO userAnswerStatusDTO)
    {
        answerService.upVoteAnswer(userAnswerStatusDTO);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/down-vote-answer")
    @ResponseBody
    public ResponseEntity<String> downVoteAnswer(@RequestBody UserAnswerStatusDTO userAnswerStatusDTO)
    {
        answerService.downVoteAnswer(userAnswerStatusDTO);
        return ResponseEntity.ok("OK");
    }
}
