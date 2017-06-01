public class MVC {
  private Model m;
  private View  v;
  private Controller c;

  public MVC () {
    c = new Controller();
    v = new View(c);
    m = new Model(v);
  }

}