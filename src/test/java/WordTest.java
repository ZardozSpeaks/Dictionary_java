import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

public class DefinitionTest {

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void Word_instansiatesCorrectly() {
    Word testWord = new Word("Cromulent");
    assertEquals(true, testWord instanceof Word);
  }

  @Test
  public void Word_instansiatesWithWordText_true() {
    Word testWord = new Word("Embiggen");
    assertEquals("Embiggen",testWord.getWord());
  }

  @Test
  public void getDefinitions_initiallyReturnsEmptyArrayList() {
    Word testWord = new Word("Rowdy");
    assertTrue(testWord.getDefinitions() instanceof ArrayList);
}

  @Test
  public void all_returnsAllInstancesOfWord_true() {
    Word firstWord = new Word("Bathycolpian");
    Word secondWord = new Word("Reubenesque");
    assertTrue(Word.all().contains(firstWord));
    assertTrue(Word.all().contains(secondWord));
  }

  @Test
  public void getId_returnsCorrectId_true() {
    Word testWord = new Word("Opulent");
    assertTrue(Word.all().size == testWord.getId());
  }

  @Test
  public void find_returnsWordWithSameId() {
    Word testWord = new Word("Salicious");
    assertEquals(Word.find(testCategory.getId()), testCategory);
  }

  @Test
  public void clear_removesAllWordInstancesFromMemory() {
    Word testWord = new Word("Avuncular");
    Word.clear();
    assertEquals(Word.all().size(), 0);
  }

  @Test
  public void addDefinition_addsDefinitionToList_true() {
    Word testWord = new Word("Cyclopean");
    Definition testDefinition = new Definition("a big ol wall");
    testWord.addDefinition(testDefinition);
    assertTrue(testWord.getDefinitions().contains(testDefinition));
  }
}
