package ga.components.materials;

import ga.components.genes.EdgeGene;
import ga.components.genes.Gene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenyue Qin on 7/04/2017.
 * The Australian National University.
 */
public class EdgeMaterial extends SimpleMaterial {

  protected final int networkSideSize;
  protected EdgeGene[][] edges;

  /**
   * Constructs a SimpleMaterial by a list of genes.
   *
   * @param strand a list of genes
   */
  public EdgeMaterial(List<? extends Gene> strand, int networkSideSize) {
    super(strand);
    this.networkSideSize = networkSideSize;
    if (this.networkSideSize * this.networkSideSize != strand.size()) {
      throw new IllegalArgumentException("An edge material is wrong");
    }
    edges = new EdgeGene[this.networkSideSize][this.networkSideSize];
    this.initialiseEdges();
  }

  public void initialiseEdges() {
    for (int i=0; i<networkSideSize; i++) {
      for (int j=0;j<networkSideSize; j++) {
        edges[i][j] = (EdgeGene) this.strand[i*networkSideSize + j];
      }
    }
  }

  public int getNetworkSideSize() {
    return networkSideSize;
  }

  public Gene getGene(final int row, final int column){
    return strand[row * networkSideSize + column];
  }

  @Override
  public EdgeMaterial copy() {
    List<Gene> strand = new ArrayList<>(size);
    for (int i = 0; i < size; i++)
      strand.add((Gene)this.strand[i].copy());
    return new EdgeMaterial(strand, this.networkSideSize);
  }

  public String getEdgeGenesMatrixString() {
    String rtn = "";
    for (int i=0; i<this.networkSideSize; i++) {
      for (int j=0; j<this.networkSideSize; j++) {
        rtn += this.edges[i][j].getValue() + "\t";
      }
      rtn += "\n";
    }
    return rtn;
  }

}
