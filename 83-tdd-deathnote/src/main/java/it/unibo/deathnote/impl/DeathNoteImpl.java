package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    private List<String> names = new ArrayList<>();

    @Override
    public String getRule(int ruleNumber) {   
        if(ruleNumber >= 1 && ruleNumber <= RULES.size()) {
            return RULES.get(ruleNumber);
        }
        throw new IllegalArgumentException("rule number out of bounds");
    }

    @Override
    public void writeName(String name) {
        if(!Objects.isNull(name)) {

        }
        throw new NullPointerException("non existing name"); 
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }

}