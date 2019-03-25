package sa.id.BioQA.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.id.BioQA.domain.Question;
import sa.id.BioQA.domain.Snippet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Faker {

    @Autowired
    QuestionService questionService;

    public String addData(){

        String result = "";

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("D:/projects/datasets/BioASQ-training7b/training7b.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray questionList = (JSONArray) obj;

            //Iterate over question array
            questionList.forEach( quest -> parseQuestionObject( (JSONObject) quest ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        result = "FINISHED INPUT ALL DATA";
        return result;
    }

    private void parseQuestionObject(JSONObject questionJson)
    {
        Question question = new Question();

        String id = questionJson.get("id") == null ? null : questionJson.get("id").toString();
        question.setIdQuestion(id);

        String type = questionJson.get("type") == null ? null : questionJson.get("type").toString();
        question.setType(type);

        String body = questionJson.get("body") == null ? null : questionJson.get("body").toString();
        question.setBody(body);

        List<String> documents = questionJson.get("documents") == null ? null : (List<String>) questionJson.get("documents");
        question.setDocuments(documents);

        String idealAnswer = questionJson.get("ideal_answer") == null ? null : questionJson.get("ideal_answer").toString();
        question.setIdealAnswer(idealAnswer);

        List<String> exactAnswers = questionJson.get("exact_answers") == null ? null : (List<String>) questionJson.get("exact_answers");
        question.setExactAnswers(exactAnswers);

        List<Snippet> snippets = questionJson.get("snippets") == null ? null : (List<Snippet>) questionJson.get("snippets");
        question.setSnippets(snippets);

        questionService.addQuestion(question);
        System.out.println("succeed input question with id "+ question.getIdQuestion());
    }



}
