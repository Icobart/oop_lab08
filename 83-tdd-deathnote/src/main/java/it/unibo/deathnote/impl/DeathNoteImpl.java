package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    private Map<String, Note> names = new HashMap<>();
    private String lastWrittenName;
    /* 
    public DeathNoteImpl(List<String> names) {
        this.names = names;
    }*/

    @Override
    public String getRule(int ruleNumber) {   
        if(ruleNumber-1 >= 0 && ruleNumber-1 < RULES.size()) {
            return RULES.get(ruleNumber-1);
        }
        throw new IllegalArgumentException("rule number out of bounds");
    }

    @Override
    public void writeName(String name) {
        if(!Objects.isNull(name)) {
            names.put(name, new Note());
            lastWrittenName = name;
        }
        else {
            throw new NullPointerException("non existing name");
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(names.isEmpty()) {
            throw new IllegalStateException("there is no name written in this DeathNote");  
        }
        if(Objects.isNull(cause)) {
            throw new IllegalStateException("the cause is null");
        }
        if(Objects.isNull(lastWrittenName)) {
            throw new IllegalStateException("there is no new name written in this DeathNote");
        }
        return names.get(lastWrittenName).writeCause(cause);    
    }

    @Override
    public boolean writeDetails(String details) {
        if(names.isEmpty()) {
            throw new IllegalStateException("there is no name written in this DeathNote");  
        }
        if(Objects.isNull(details)) {
            throw new IllegalStateException("the details are null");
        }
        if(Objects.isNull(lastWrittenName)) {
            throw new IllegalStateException("there is no new name written in this DeathNote");
        }
        return names.get(lastWrittenName).writeDetail(details);
    }

    @Override
    public String getDeathCause(String name) {
        if(!isNameWritten(name)) {
            throw new IllegalArgumentException("the provided name is not written in this DeathNote");
        }
        return names.get(name).getCauseOfDeath();
    }

    @Override
    public String getDeathDetails(String name) {
        if(!isNameWritten(name)) {
            throw new IllegalArgumentException("the provided name is not written in this DeathNote");
        }
        return names.get(name).getDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
        return names.containsKey(name);
    }

    private static final class Note {
        private String causeOfDeath;
        private String details;
        private final long timeOfDeath;

        private Note(String cause, String details){
            this.causeOfDeath = cause;
            this.details = details;
            this.timeOfDeath = System.currentTimeMillis();
        }

        private Note() {
            this("heart attack", "");
        }

        public long getTimeOfDeath() {
            return timeOfDeath;
        }

        public String getCauseOfDeath() {
            return causeOfDeath;
        }

        public String getDetails() {
            return details;
        }

        public boolean writeCause(final String cause) {
            return System.currentTimeMillis() - getTimeOfDeath() <= 40 ? successCause(cause) : false;
        }

        private boolean successCause(final String cause) {
            this.causeOfDeath = cause;
            return true;
        }

        public boolean writeDetail(final String detail) {
            return System.currentTimeMillis() - getTimeOfDeath() <= 6040 ? successDetail(detail): false;
        }

        private boolean successDetail(final String detail) {
            this.details = detail;
            return true;
        }

    }

}