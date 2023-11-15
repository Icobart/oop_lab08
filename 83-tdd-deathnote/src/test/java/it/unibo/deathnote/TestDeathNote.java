package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.lang.Thread.sleep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";

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

    @Test
    public void testWriteHumanName() {
        assertEquals(false, notebook.isNameWritten(ALICE));
        notebook.writeName(ALICE);
        assertEquals(true, notebook.isNameWritten(ALICE));
        assertEquals(false, notebook.isNameWritten(BOB));
        assertEquals(false, notebook.isNameWritten(""));
    }

    @Test
    public void testWriteCauseOfDeath() throws InterruptedException {
        //assertEquals("there is no name written in this DeathNote", notebook.writeDeathCause("sudden death"));
        notebook.writeName(ALICE);
        assertEquals("heart attack", notebook.getDeathCause(ALICE));
        notebook.writeName(BOB);
        assertTrue(notebook.writeDeathCause("karting accident"));
        assertEquals("karting accident", notebook.getDeathCause(BOB));
        sleep(100);
        assertFalse(notebook.writeDeathCause("crushed by a rock"));
        assertEquals("karting accident", notebook.getDeathCause(BOB));
    }

    @Test
    public void testWriteDetails() throws InterruptedException {
        //assertEquals("there is no name written in this DeathNote", notebook.writeDeathCause("sudden death"));
        notebook.writeName(ALICE);
        assertEquals("", notebook.getDeathDetails(ALICE));
        assertTrue(notebook.writeDetails("ran for too long"));
        assertEquals("ran for too long", notebook.getDeathDetails(ALICE));
        notebook.writeName(BOB);
        sleep(6100);
        assertFalse(notebook.writeDetails("LTG has spoken"));
        assertEquals("", notebook.getDeathDetails(BOB));
    }

}