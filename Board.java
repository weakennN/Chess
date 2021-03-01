import java.util.List;

public class Board {

    private List<Figure> figures;
    private int rows;
    private int cols;

    public Board(List<Figure> figures) {

        this.figures = figures;

        this.rows = 8;
        this.cols = 8;
    }

}
