package examples.experiment4;

import com.sun.istack.internal.NotNull;
import ga.operations.selectionOperators.selectionSchemes.SelectionScheme;

import java.util.List;

/**
 * Created by Chinyuer on 19/03/2017.
 */
public class Exp4Selector implements SelectionScheme {

    public Exp4Selector() {

    }

    @Override
    public int select(@NotNull List<Double> fitnessValues) {
        double r = Math.random();
        double currentValue = 0;
        for (int i=0; i<fitnessValues.size(); i++) {
            currentValue = fitnessValues.get(i);
            if (r < currentValue) {
                return i;
            }
            r -= currentValue;
        }
        return fitnessValues.size()-1;
    }
}
