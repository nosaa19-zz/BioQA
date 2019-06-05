package sa.id.BioQA.service;

import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import sa.id.BioQA.domain.Question;
import sa.id.BioQA.domain.Similarity;
import sa.id.BioQA.repository.QuestionSearchRepository;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class QuestionService {

    private final QuestionSearchRepository questionSearchRepository;

    @Autowired
    SSESService ssesService;

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

    public List<Question> search(String query, Integer size, Integer methodType) {
        System.out.println("Request to search for a page of Question for query {}" + query);

        /*QueryBuilder boolQueryBuilder = queryStringQuery(query)
                .queryName("body");

        NativeSearchQueryBuilder q = new NativeSearchQueryBuilder();
        q.withQuery(boolQuery().must(boolQueryBuilder));*/

        List<Question> questions = Lists.newArrayList(questionSearchRepository.findAll());
        List<Similarity> similarities = new ArrayList<>();
        for(int i = 0; i<questions.size(); i++){
            similarities.add(new Similarity(i, ssesService.doCalculation(query, questions.get(i).getBody(), methodType)));
        }

        Collections.sort(similarities, Collections.reverseOrder());

        List<Question> result = new ArrayList<>();
        //System.out.println("MOST 10 SIMILAR QUESTION");
        for(int i = 0; i < size; i++){
            /*System.out.println(questions.get(similarities.get(i).getIndex()).getBody()
                    +" | score : "+similarities.get(i).getScore());*/
            result.add(questions.get(similarities.get(i).getIndex()));
        }
        return result;
    }
}
