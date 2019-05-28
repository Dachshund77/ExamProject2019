package Application;

public abstract class AbstractController implements Controllers {


    @Override
    public void initValues(String string) {
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }
}
