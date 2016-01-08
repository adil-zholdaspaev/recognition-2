package net.omsu.recognition.mnk.gauss;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MyEquation implements Gauss<Double, MyEquation> {

    private List<Double> equation = new ArrayList<Double>();

    public List<Double> getEquation(){
        return equation;
    }

    public void generate(final double value){
        equation.add(value);
    }

    public int size(){
        return equation.size();
    }

    public void addEquation(MyEquation item){
        ListIterator<Double> i = equation.listIterator();
        ListIterator<Double> j = item.getEquation().listIterator();
        for(; i.hasNext() && j.hasNext();){
            Double a = i.next();
            Double b = j.next();
            i.set(a + b);
        }
    }

    public void mul(Double coefficient){
        for(ListIterator<Double> i = equation.listIterator(); i.hasNext();){
            Double next = i.next();
            i.set(next * coefficient);
        }
    }

    public Double findCoefficient(Double a, Double b){
        if (a.compareTo(0.0) == 0) return 1.0d;
        return -b/a;
    }

    public Double at(int index){
        return equation.get(index);
    }
}
