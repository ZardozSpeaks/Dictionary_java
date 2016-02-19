import org.junit.*;
import static org.junit.Assert.*;

public class DefinitionTest {

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void Definition_instansiatesCorrectly() {
    Definition testDefinition = new Definition("a word that means nothing");
    assertEquals(true, testDefinition instanceof Definition);
  }

  @Test
  public void Definition_instansiatesWithText() {
    Definition testDefinition = new Definition("snugglebottoms");
    assertEquals("snugglebottoms", testDefinition.getText());
  }

  @Test
  public void all_returnsAllInstancesOfDefinition_true() {
    Definition firstDefinition = new Definition("a kind of bunny");
    Definition secondDefinition = new Definition("beautiful sadness");
    assertTrue(Definition.all().contains(firstDefinition));
    assertTrue(Definition.all().contains(secondDefinition));
  }

  @Test
  public void Definition_instansiatesWithId_true() {
    Definition testDefinition = new Definition("a long widget");
    assertEquals(Definition.all().size(), testDefinition.getId());
  }

  @Test
  public void find_returnsCorrectId_secondDefinition() {
    Definition firstDefinition = new Definition("a kind of bunny");
    Definition secondDefinition = new Definition("beautiful sadness");
    assertEquals(Definition.find(secondDefinition.getId()), secondDefinition);
  }

  @Test
  public void find_returnsNullWhenBeyondArrayLength_true() {
    assertTrue(Definition.find(999) == null);
  }

  @Test
  public void clear_EmptiesArray() {
    Definition testDefinition = new Definition("a tall mouse");
    Definition.clear();
    assertEquals(Definition.all().size(), 0 );
  }

}
