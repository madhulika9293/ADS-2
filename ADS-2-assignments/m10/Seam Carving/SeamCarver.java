import java.awt.Color;
/**
 * Class for seam carver.
 */
public class SeamCarver {
  /**
   * picture from input.
   */
  private Picture picInp;
  /**
   * energy matrix.
   */
  private double[][] energy;
  /**
   * create a seam carver object based on the given picture.
   *
   *Complexity : width*height
   *
   * @param      picture  The picture
   */
  public SeamCarver(Picture picture) throws Exception {
    if (picture == null) {
      throw new Exception("picture is null");
    }
    this.picInp = picture;
    energy = new double[picture.width()][picture.height()];
    for (int i = 0; i < picture.width(); i++) {
      for (int j = 0; j < picture.height(); j++) {
        if (i == 0 || j == 0 ||
            i == picture.width() - 1 ||
            j == picture.height() - 1) {
          energy[i][j] = 1000.00;
        } else {
          Color lPix = picture.get(i - 1, j);
          Color rPix = picture.get(i + 1, j);
          double sum = Math.pow(lPix.getRed() - rPix.getRed(), 2)
                       + Math.pow(lPix.getBlue() - rPix.getBlue(), 2)
                       + Math.pow(lPix.getGreen() - rPix.getGreen(), 2);
          Color tPix = picture.get(i, j - 1);
          Color bPix = picture.get(i, j + 1);
          sum += Math.pow(tPix.getRed() - bPix.getRed(), 2)
                 + Math.pow(tPix.getBlue() - bPix.getBlue(), 2)
                 + Math.pow(tPix.getGreen() - bPix.getGreen(), 2);
          energy[i][j] = Math.sqrt(sum);
        }
      }
    }
  }

  /**
   * picture.
   *
   * @return     this picture.
   */
  public Picture picture() {
    return picInp;
  }


  /**
   * width of current picture.
   *
   * @return     { description_of_the_return_value }
   */
  public int width() {
    return picInp.width();
  }

  /**
   * height of current picture.
   *
   * @return     { description_of_the_return_value }
   */
  public int height() {
    return picInp.height();
  }

  // energy of pixel at column x and row y
  public double energy(int x, int y) {
    return energy[x][y] ;
  }

  // sequence of indices for horizontal seam
  public int[] findHorizontalSeam() {
    return new int[0];
  }

  // sequence of indices for vertical seam
  public int[] findVerticalSeam() {
    return new int[0];
  }

  // remove horizontal seam from current picture
  public void removeHorizontalSeam(int[] seam) {

  }

  // remove vertical seam from current picture
  public void removeVerticalSeam(int[] seam) {

  }
}
