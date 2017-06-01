public class MVC {
  private Model m = Model.getInstance();
  private View  v;
  private Controller c;

  public MVC () {
    c = new Controller();
    v = new View(c);
    m.setView(v);
    m.buildTabuleiro();
    c.setModel(m);
    // m = new Model(v);
  }

}