package ga.components.GRNs;

import com.sun.istack.internal.Interned;
import com.sun.istack.internal.NotNull;
import ga.components.genes.BinaryGene;
import ga.components.genes.Gene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zhenyue Qin on 26/03/2017.
 * The Australian National University.
 */
public class GRN extends Gene<Integer[][]> {

    private int[][] nodes;
    private int[][] edges;
    private double fitness;


    public GRN(int[] target, int maxCycle, @NotNull Integer[][] connections) {
        super(connections);

    }

    @Override
    public Gene<Integer[][]> copy() {
        return null;
    }

    @Override
    public void setValue(@NotNull Integer[][] value) {

    }


  public static void main(String[] args) {
  }

}
