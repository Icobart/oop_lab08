package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static java.lang.Thread.sleep;
import static it.unibo.deathnote.api.DeathNote.RULES;

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
        assertThrows(new IllegalArgumentThrower() {

            @Override
            public void launch() {
                notebook.getRule(-1);  
            }
            
        });

        assertThrows(new IllegalArgumentThrower() {

            @Override
            public void launch() {
                notebook.getRule(0);  
            }
            
        });
        assertThrows(new IllegalArgumentThrower() {

            @Override
            public void launch() {
                notebook.getRule(RULES.size()+1);  
            }
            
        });
        
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
        assertThrows(new IllegalStateThrower() {

            @Override
            public void launch() {
                notebook.writeDeathCause("he knew too much");    
            }
            
        });
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
        assertThrows(new IllegalStateThrower() {

            @Override
            public void launch() {
                notebook.writeDetails("run over by a road pirate");  
            }
            
        });
        notebook.writeName(ALICE);
        assertEquals("", notebook.getDeathDetails(ALICE));
        assertTrue(notebook.writeDetails("ran for too long"));
        assertEquals("ran for too long", notebook.getDeathDetails(ALICE));
        notebook.writeName(BOB);
        sleep(6100);
        assertFalse(notebook.writeDetails("LTG has spoken"));
        assertEquals("", notebook.getDeathDetails(BOB));
    }

    static void assertThrows(final ExceptionThrower exception){
        try {
            exception.launch();
            fail("Exception expected");
        } catch(IllegalStateException | IllegalArgumentException e) {
            assertTrue(exception instanceof IllegalStateThrower && e instanceof IllegalStateException
                        || exception instanceof IllegalArgumentThrower && e instanceof IllegalArgumentException);
        }
    }


    private interface ExceptionThrower{
        void launch();
    }

    private interface IllegalArgumentThrower extends ExceptionThrower{
    }

    private interface IllegalStateThrower extends ExceptionThrower {
    }

}