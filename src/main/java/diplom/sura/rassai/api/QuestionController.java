package diplom.sura.rassai.api;

import diplom.sura.rassai.models.Answer;
import diplom.sura.rassai.models.Question;
import diplom.sura.rassai.service.AnswerService;
import diplom.sura.rassai.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;


    // return all questions
    @GetMapping(value = "")
    public List<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    // returns current user's questions
    @GetMapping(value = "/user")
    public List<Question> getUserQuestions(HttpServletRequest request) {
        return questionService.getUserQuestions(request);
    }

    // returns current user's answers
    @GetMapping(value = "/user/answer")
    public List<Question> getUserAnsweredQuestions(HttpServletRequest request) {
        return questionService.getUserAnsweredQuestions(request);
    }

    // returns 1 question by id
    @GetMapping(value = "/{id}")
    public Question getOneQuestion(@PathVariable(name = "id") Long id) {
        return questionService.getOneQuestion(id);
    }


    // add answer to question by question id
    @PostMapping(value = "/{id}/answer")
    public Question addAnswer(@RequestBody Answer answer, @PathVariable(name = "id") Long id, HttpServletRequest request) {
        Question question = questionService.getOneQuestion(id);
        return answerService.addAnswer(answer, question, request).getQuestion();
    }

    // add new question
    @PostMapping(value = "")
    public Question addQuestion(@RequestBody Question question, HttpServletRequest request) {
        return questionService.addNewQuestion(question, request);
    }


    // deletes question by id
    @DeleteMapping(value = "/{id}")
    public void deleteQuestion(@PathVariable(name = "id") Long id) {
        questionService.deleteQuestion(id);
    }


    // like answer by id
    @PostMapping(value = "/answer/{id}/like")
    public Answer likeAnswer(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        return answerService.likeAnswer(id, request);
    }

    // dislike answer by id
    @PostMapping(value = "/answer/{id}/dislike")
    public Answer dislikeAnswer(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        return answerService.dislikeAnswer(id, request);
    }
}
