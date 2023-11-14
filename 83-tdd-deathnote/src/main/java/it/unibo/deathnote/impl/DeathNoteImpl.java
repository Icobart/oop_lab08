package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    private Map<String, List<String>> names = new HashMap<>();
    /* 
    public DeathNoteImpl(List<String> names) {
        this.names = names;
    }*/

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
            names.put(name, new ArrayList<>(2));
        }
        throw new NullPointerException("non existing name"); 
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(Objects.isNull(names)) {
            throw new IllegalStateException("there is no name written in this DeathNote");  
        }
        if(Objects.isNull(cause)) {
            throw new IllegalStateException("the cause is null");
        }
        
        return false;
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