package sa.id.BioQA.domain;

import java.io.Serializable;

public class Param implements Serializable{
    private String query;
    private Integer methodType;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getMethodType() {
        return methodType;
    }

    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }
}
