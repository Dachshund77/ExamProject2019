package Application.Controller;

import Application.SearchContainer;
import Domain.*;

public abstract class AbstractController implements Controllers {


    @Override
    public void initValues(String string) {
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(Provider provider){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(Education education){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(Employee employee){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(Interview interview){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(Consultation consultation){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(Company company){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(SearchContainer searchContainer){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(SearchContainer searchContainer, Provider provider){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(SearchContainer searchContainer, Education education){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(SearchContainer searchContainer, Employee employee){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(SearchContainer searchContainer, Interview interview){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(SearchContainer searchContainer, Consultation consultation){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }

    @Override
    public void initValues(SearchContainer searchContainer, Company company){
        throw new UnsupportedOperationException("initValues was not Overridden! Please consult the documentation!");
    }
}
