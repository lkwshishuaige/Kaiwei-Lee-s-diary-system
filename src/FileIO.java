import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO {
    public static void main(String[] args) {
        save();
//        input();
    }
    public static void save() {
        String content = "123456789";
        String filePath = "C:\\Users\\11206\\Desktop\\test.txt";
        File file = new File(filePath);// 相对路径，如果没有则要建立一个新的output.txt文件
        try {
            PrintWriter out = new PrintWriter(file);
            out.println(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void input(){
        int[][] arr = new int[7][7];
        File file = new File("C:\\Users\\11206\\Desktop\\test.txt");
        try {
            Scanner input = new Scanner(file);
            for(int i = 0;i<7;i++){
                for(int j = 0;j < 7; j++){
                    arr[i][j] = input.nextInt();
                }
            }
            System.out.println(arr);    //打印出地址
            for(int i = 0;i<7;i++){
                for(int j = 0;j < 7; j++){
                    System.out.print(arr[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
