package examples.experiment4;

import ga.components.genes.DataGene;

import javax.xml.crypto.Data;
import java.util.Arrays;

/**
 * Created by Zhenyue Qin on 9/04/2017.
 * The Australian National University.
 */
public class TestField {
  public static void main(String[] args) {
    DataGene[] a = {new DataGene(1), new DataGene(1), new DataGene(-1)};
    for (DataGene e : a) {
      e.setValue(-1);
    }
    System.out.println(Arrays.toString(a));
  }
}
