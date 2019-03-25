package sa.id.BioQA.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

@Document(indexName = "question")
public class Question implements Serializable{

    @Id
    String idQuestion;
    String type;
    String body;
    List<String> documents;
    String idealAnswer;
    List<String> exactAnswers;
    List<Snippet> snippets;

    public String getIdQuestion() { return idQuestion;
    }

    public void setIdQuestion(String idQuestion) { this.idQuestion = idQuestion; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public String getIdealAnswer() {
        return idealAnswer;
    }

    public void setIdealAnswer(String idealAnswer) {
        this.idealAnswer = idealAnswer;
    }

    public List<String> getExactAnswers() {
        return exactAnswers;
    }

    public void setExactAnswers(List<String> exactAnswers) {
        this.exactAnswers = exactAnswers;
    }

    public List<Snippet> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<Snippet> snippets) {
        this.snippets = snippets;
    }

    public Question(String idQuestion, String type, String body, List<String> documents, String idealAnswer, List<String> exactAnswers, List<Snippet> snippets) {
        super();
        this.idQuestion = idQuestion;
        this.type = type;
        this.body = body;
        this.documents = documents;
        this.idealAnswer = idealAnswer;
        this.exactAnswers = exactAnswers;
        this.snippets = snippets;
    }
    public Question(){}

}
