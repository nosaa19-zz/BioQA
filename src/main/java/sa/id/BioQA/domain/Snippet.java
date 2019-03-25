package sa.id.BioQA.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "snippet")
public class Snippet implements Serializable{
    Integer offsetInBeginSection;
    Integer offsetInEndSection;
    String text;
    String beginSection;
    String document;
    String endSection;

    public Integer getOffsetInBeginSection() {
        return offsetInBeginSection;
    }

    public void setOffsetInBeginSection(Integer offsetInBeginSection) {
        this.offsetInBeginSection = offsetInBeginSection;
    }

    public Integer getOffsetInEndSection() {
        return offsetInEndSection;
    }

    public void setOffsetInEndSection(Integer offsetInEndSection) {
        this.offsetInEndSection = offsetInEndSection;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBeginSection() {
        return beginSection;
    }

    public void setBeginSection(String beginSection) {
        this.beginSection = beginSection;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEndSection() {
        return endSection;
    }

    public void setEndSection(String endSection) {
        this.endSection = endSection;
    }

    public Snippet(Integer id, Integer offsetInBeginSection, Integer offsetInEndSection, String text, String beginSection, String document, String endSection){
        super();
    }
    public Snippet(){}
}
