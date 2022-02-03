package first.project.service;

import java.util.ArrayList;

import first.project.model.UserAnswerStatus;

public interface UserAnswerStatusService
{
    ArrayList<UserAnswerStatus> getAllByUserId(Integer id);

    Integer getUserId(String email);
}
