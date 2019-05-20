package Application;

public abstract class Controller implements Controllers {


    @Override
    public void initValues(String string) {
        System.err.println("initValues was not Overridden! Please consult the documentation!");
    }
}
