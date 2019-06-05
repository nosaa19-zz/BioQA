package sa.id.BioQA.service.semanticSimilarity.unsupervisedMethod;

import sa.id.BioQA.service.semanticSimilarity.core.SimilarityMeasure;
import sa.id.BioQA.service.stringSimilarityMeasure.CosineSimilarity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class ParagraphVector implements SimilarityMeasure {
    private ParagraphVectorModel model;
    private HashMap<String, Vector<Double>> sentenceVectors;

    public ParagraphVector(ParagraphVectorModel model) throws IOException {
        this.model = model;
        loadSentenceVectorsFromModel();
    }

    public double getSimilarity(String sentence1ID, String sentence2ID) {
        Vector<Double> sentenceVector1 = sentenceVectors.get(String.valueOf(Integer.valueOf(sentence1ID)*2-1));
        Vector<Double> sentenceVector2 = sentenceVectors.get(String.valueOf(Integer.valueOf(sentence2ID)*2));
        CosineSimilarity cosine = new CosineSimilarity(sentenceVector1,sentenceVector2);
        Double similarityScore = cosine.calculateDistanceAmongVectors();
        return similarityScore;
    }

    public void loadSentenceVectorsFromModel() throws IOException {

        this.sentenceVectors = this.model.readModelFile();
    }
    public ParagraphVectorModel getModel() {
        return model;
    }

    public void setModel(ParagraphVectorModel model) {
        this.model = model;
    }


}
