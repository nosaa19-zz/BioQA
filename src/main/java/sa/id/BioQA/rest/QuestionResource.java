package sa.id.BioQA.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.id.BioQA.domain.Param;
import sa.id.BioQA.domain.Question;
import sa.id.BioQA.service.Faker;
import sa.id.BioQA.service.QuestionService;
import sa.id.BioQA.service.SSESService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QuestionResource {

    private final QuestionService questionService;

    private final SSESService ssesService;

    public QuestionResource(QuestionService questionService, SSESService ssesService) {
        this.questionService = questionService;
        this.ssesService = ssesService;
    }

    @PostMapping("/question/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question ){
        return new ResponseEntity<>(questionService.addQuestion(question), null, HttpStatus.OK);
    }

    // ONLY FOR INIT DATA
    /*@GetMapping("/question/init")
    public ResponseEntity<String> addQuestion(){
        String result = "";
        try {
            result = faker.addData();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }*/

    @GetMapping("/question/all")
    public ResponseEntity<List<Question>> getQuestions(){
        return new ResponseEntity<>(questionService.getQuestions(), null, HttpStatus.OK);
    }


    @GetMapping("/question/{id}")
    public ResponseEntity<Optional<Question>> getQuestion(@PathVariable String id){
        return new ResponseEntity<>(questionService.getQuestion(id), null, HttpStatus.OK);
    }

    @PutMapping("/question/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String id,@RequestBody Question question){
       return new ResponseEntity<>(questionService.updateQuestion(id, question), null, HttpStatus.OK);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id){
        return new ResponseEntity<>(questionService.deleteQuestion(id), null, HttpStatus.OK);
    }

    @PostMapping("/_search/question")
    public ResponseEntity<List<Question>> searchQuestion(@RequestBody Param param) {
        List<Question> list = questionService.search(param.getQuery(), 10, param.getMethodType());
        return new ResponseEntity<>(list, null, HttpStatus.OK);
    }
}
