package sa.id.BioQA.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import sa.id.BioQA.domain.Question;

@Repository
public interface QuestionSearchRepository extends ElasticsearchRepository<Question, String> {

}
