package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private DeathNote notebook;

    @BeforeEach
    public void setUp () {
        this.notebook = new DeathNoteImpl();
    }

    @Test
    public void testRulesBounds() {
        assertEquals("""
        The human whose name is written in this note shall die.
        """, notebook.getRule(1));
        assertEquals("rule number out of bounds", notebook.getRule(0));
        assertEquals("rule number out of bounds", notebook.getRule(50));
        
    }

    @Test
    public void testRulesNotEmpty() {
        for(int i = 1; i<=13; i++) {
            assertNotEquals("", notebook.getRule(i));
            assertNotEquals(null, notebook.getRule(i));
        }
    }
}