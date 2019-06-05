package sa.id.BioQA.domain;

public class Similarity implements Comparable<Similarity> {
    private Integer index;
    private Double score;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Similarity(Integer index, Double score){
        super();
        this.index = index;
        this.score = score;
    }

    public Similarity(){}

    @Override
    public int compareTo(Similarity o) {
        return this.getScore().compareTo(o.getScore());
    }
}
