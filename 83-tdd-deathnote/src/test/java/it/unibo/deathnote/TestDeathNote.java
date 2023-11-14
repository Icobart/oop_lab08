package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}