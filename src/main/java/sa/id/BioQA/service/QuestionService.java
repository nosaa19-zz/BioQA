package sa.id.BioQA.service;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import sa.id.BioQA.domain.Question;
import sa.id.BioQA.repository.QuestionSearchRepository;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@Service
public class QuestionService {

    private final QuestionSearchRepository questionSearchRepository;

    public QuestionService(QuestionSearchRepository questionSearchRepository) {
        this.questionSearchRepository = questionSearchRepository;
    }

    public Question addQuestion(Question question){
        return questionSearchRepository.save(question);
    }

    public List<Question> getQuestions(){
        Iterator<Question> iterator = questionSearchRepository.findAll().iterator();
        List<Question> students = new ArrayList<Question>();
        while(iterator.hasNext()){
            students.add(iterator.next());
        }
        return students;
    }

    public Optional<Question> getQuestion(String id){
        return questionSearchRepository.findById(id);
    }


    public Question updateQuestion(String id, Question question){
        Optional<Question> q = questionSearchRepository.findById(id);
        if(q.isPresent()){
            Question update = q.get();
            update.setBody(question.getBody());
            return questionSearchRepository.save(update);
        }
        else
            return null;
    }

    public String deleteQuestion(String id){
        questionSearchRepository.deleteById(id);
        return "Document Deleted";
    }

    public Page<Question> search(String query, Pageable pageable) {
        System.out.println("Request to search for a page of Question for query {}" + query);

        QueryBuilder boolQueryBuilder = queryStringQuery(query)
                .queryName("body");

        NativeSearchQueryBuilder q = new NativeSearchQueryBuilder();
        q.withQuery(boolQuery().must(boolQueryBuilder));
        Page<Question> result = questionSearchRepository.search(q.build().getQuery(), pageable);
        return result;
    }
}
