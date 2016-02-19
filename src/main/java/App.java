import java.util.HashMap;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/words.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  //WORD LOGIC//

    get("/words", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("words", Word.all());
      model.put("template", "templates/words.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/words", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String word = request.queryParams("word");
      Word newWord = new Word(word);
      model.put("words", Word.all());
      model.put("word", newWord);
      model.put("template", "templates/words.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/words/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Word word = Word.find(Integer.parseInt(request.params(":id")));
      ArrayList<Definition> definitions = word.getDefinitions();
      model.put("word", word);
      model.put("definitions", definitions);
      model.put("template", "templates/definitions.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  //DEFINITIONS LOGIC//

  get("/definitions", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Word word = Word.find(Integer.parseInt(request.params(":id")));
      model.put("word", word);
      model.put("definitions", Definition.all());
      model.put("template", "templates/definitions.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  post("/definitions", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Word word = Word.find(Integer.parseInt(request.queryParams("wordId")));
    ArrayList<Definition> definitions = word.getDefinitions();

    if (definitions == null) {
      definitions = new ArrayList<Definition>();
      request.session().attribute("definitions", definitions);
    }

    String text = request.queryParams("text");
    Definition newDefinition = new Definition(text);
    definitions.add(newDefinition);
    model.put("definitions", definitions);
    model.put("word", word);
    model.put("template", "templates/definitions.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
