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
   * distance array.
   */
  private double[][] distTo;
  /**
   * edge array.
   */
  private int[][] edgeTo;
  /**
   *the width of image.
   */
  private int width;
  /**
   *the height of pixel.
   */
  private int height;
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
    width = picture.width();
    height = picture.height();
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
    return energy[x][y];
  }

  /**
   * Returns the pixel number.
   *
   * @param      col   The col
   * @param      row   The row
   *
   * @return     { description_of_the_return_value }
   */
  private int pixelNumber(final int col, final int row) {
    return width() * row + col + 1;
  }

  /**sequence of indices for horizontal seam
   *
   *time complexity is O(w*h)
   *w is the width and h is the height
   * @return  sequence of indices of horizontal seam
   */
  public int[] findHorizontalSeam() {
    reset(distTo);
    for (int row = 0; row < height; row++) {
      distTo[row][0] = 1000;
    }
    // this is for relaxation.
    for (int col = 0; col < width - 1; col++) {
      for (int row = 0; row < height; row++) {
        relaxH(row, col, edgeTo, distTo);
      }
    }
    double minDist = Double.MAX_VALUE;
    int minRow = 0;
    for (int row = 0; row < height; row++) {
      if (minDist > distTo[row][width - 1]) {
        minDist = distTo[row][width - 1];
        minRow = row;
      }
    }
    int[] indices = new int[width];
    //to find the horizontal seam.
    for (int col = width - 1, row = minRow; col >= 0; col--) {
      indices[col] = row;
      row -= edgeTo[row][col];
    }
    return indices;
  }
  private void relaxH(int row, int col, int[][] edgeTo, double[][] distTo) {
    int nextCol = col + 1;
    for (int i = -1; i <= 1; i++) {
      int nextRow = row + i;
      if (nextRow < 0 || nextRow >= height) continue;
      if (i == 0) {
        if (distTo[nextRow][nextCol] >= distTo[row][col]  + energy(nextCol, nextRow)) {
          distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
          edgeTo[nextRow][nextCol] = i;
        }
      }
      if (distTo[nextRow][nextCol] > distTo[row][col]  + energy(nextCol, nextRow)) {
        distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
        edgeTo[nextRow][nextCol] = i;
      }
    }
  }
  /**
   *this method is to find the vertical seam.
   *first of all find the shortest path from top to.
   *bottom.
   *time complexity is O(w*h)
   *w is the width and h is the height
   * @return sequence of indices for vertical seam.
   */
  public int[] findVerticalSeam() {
    reset(distTo);
    int[] indices = new int[height];
    if (width == 1 || height == 1) {
      return indices;
    }
    for (int i = 0; i < width; i++) {
      distTo[0][i] = 1000.0;
    }
    // this is for relaxation.
    for (int i = 0; i < height - 1; i++) {
      for (int j = 0; j < width; j++) {
        relaxV(i, j, edgeTo, distTo);
      }
    }
    // calculating from last row
    // column wise
    double minDist = Double.MAX_VALUE;
    int minCol = 0;
    for (int col = 0; col < width; col++) {
      if (minDist > distTo[height - 1][col]) {
        minDist = distTo[height - 1][col];
        minCol = col;
      }
    }
    //indices values of shortest path.
    for (int row = height - 1, col = minCol; row >= 0; row--) {
      indices[row] = col;
      col -= edgeTo[row][col];
    }
    indices[0] = indices[1];
    return indices;
  }
  /**
   *time complexity is O(W * H)
   *W is the width of image
   *H is the height of image
   * @param      distTo  The distance to
   */
  private void reset(double[][] distTo) {
    /**
     *reset all the values to maxvalue.
     */
    for (int i = 0; i < distTo.length; i++) {
      for (int j = 0; j < distTo[i].length; j++) {
        distTo[i][j] = Double.MAX_VALUE;
      }
    }
  }
  private void relaxV(int row, int col, int[][] edgeTo, double[][] distTo) {
    int nextRow = row + 1;
    for (int i = -1; i <= 1; i++) {
      int nextCol = col + i;
      if (nextCol < 0 || nextCol >= width) {
        continue;
      }
      //spl case for bottom element.
      if (i == 0) {
        if (distTo[nextRow][nextCol] >= distTo[row][col] + energy(nextCol, nextRow)) {
          distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
          edgeTo[nextRow][nextCol] = i;
        }
      }
      if (distTo[nextRow][nextCol] > distTo[row][col] + energy(nextCol, nextRow)) {
        distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
        edgeTo[nextRow][nextCol] = i;
      }
    }
  }
  // remove horizontal seam from current picture
  //time complexity is O(width * height)
  public void removeHorizontalSeam(int[] seam) {
    //handle exceptions
    for (int col = 0; col < width; col++) {
      for (int row = seam[col]; row < height - 1; row++) {
        this.picInp.set(col, row, this.picInp.get(col, row + 1));
      }
    }
    height--;
  }
  // remove vertical seam from current picture
  //time complexity is O(width * height)
  public void removeVerticalSeam(int[] seam) {
    for (int row = 0; row < height; row++) {
      for (int col = seam[row]; col < width - 1; col++) {
        this.picInp.set(col, row, this.picInp.get(col + 1, row));
      }
    }
    width--;
  }
}
