package com.callfornation.savetheirsouls.register.data;

public class FamilyMember {
    private String name;
    private String voterId;
    private String relation;
    public FamilyMember(String name, String voterId, String relation) {
        this.name = name;
        this.voterId = voterId;
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public String getRelation() {
        return relation;
    }

    public String getVoterId() {
        return voterId;
    }
}
