public class RightTriangle implements Shape{

    public void displayShape() {
        int size = 15;

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; j++) {
                if (j == 0 || i == j || i == size - 1)
                    System.out.print('*');
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
