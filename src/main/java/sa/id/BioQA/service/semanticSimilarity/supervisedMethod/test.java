package sa.id.BioQA.service.semanticSimilarity.supervisedMethod;

import com.google.common.io.Resources;
import sa.id.BioQA.BioQaApplication;
import sa.id.BioQA.domain.Pair;
import sa.id.BioQA.service.semanticSimilarity.core.FileOperations;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class test {

    public static List<String> readStopWordsList() throws IOException {
        List<String> stopWordsList = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Resources.getResource("stopWords/stop_words.txt").getFile()));
        String line;

        while ((line = bufferedReader.readLine())!=null){
            if(!stopWordsList.contains(line))
                stopWordsList.add(line.toLowerCase());
        }

        return stopWordsList;

    }
    public static void main(String[] args) throws IOException {

        LinkedList<Pair> pairList;
        FileOperations operations = new FileOperations();
        pairList = operations.readPairsFromFile(BioQaApplication.class.getResource("/public/sentencePairsData/pairs.txt").toString());

        List<String> stopWordsList = readStopWordsList();
//        FeatureExtractor featureExtractor = new FeatureExtractor(pairList,stopWordsList);
//        featureExtractor.createArffFileFromInstances();


        for(Pair p: pairList){

            String preprocessedS1 = operations.removeStopWordsFromSentence(p.getSentence1(), stopWordsList);
            String preprocessedS2 = operations.removeStopWordsFromSentence(p.getSentence2(), stopWordsList);

            LinearRegressionMethod linearRegressionMethod = new LinearRegressionMethod();
            double similarityScore = linearRegressionMethod.getSimilarity(preprocessedS1, preprocessedS2);
            System.out.println(p.getPairId() + ": " + similarityScore);


        }




     //   featureExtractor.createSVMFileFormatInstances();

    }
}
