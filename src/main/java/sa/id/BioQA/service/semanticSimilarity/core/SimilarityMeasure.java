package sa.id.BioQA.service.semanticSimilarity.core;

import java.io.IOException;

public interface SimilarityMeasure {
    double getSimilarity(String sentence1, String sentence2) throws IOException;
}
