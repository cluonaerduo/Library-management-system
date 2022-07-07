package IOstream;
import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static  List<Book> LIST = new ArrayList<>();//final命名规范所有字母都是大写
    public static void main(String[] args) {
        readData();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=====图书管理系统=====");
            System.out.println("1.输入信息");
            System.out.println("2.修改信息");
            System.out.println("3.查询图书列表");
            System.out.println("4.删除图书");
            System.out.println("（按其他任意键退出系统）");
            String str = scanner.nextLine();
            switch (str){
                case "1":
                    insertBook(scanner);
                    break;
                case "2":
                    modifyBook(scanner);
                    break;
                case "3":
                    showBooks();
                    break;
                case "4":
                    deletBook(scanner);
                    break;
                default:
                    saveData();
                    scanner.close();
                    return;
            }
        }
    }
    @SuppressWarnings("unchecked")
    private static void readData(){
        File file = new File("data");
        if(file.exists()){

        }else {
            LIST = new ArrayList<>();
        }
        try (ObjectInputStream InputStream = new ObjectInputStream(new FileInputStream("data"))){
            LIST= (List<Book>) InputStream.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //持久化
    private static void saveData(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("data"))){
            outputStream.writeObject(LIST);
            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    //修改信息
    private static void modifyBook(Scanner scanner){
        int i = 0;
        for(Book book:LIST) System.out.println(i+"book");
        int index=scanner.nextInt();
        scanner.nextLine();
        if(index>=LIST.size()) System.out.println("错误的序号");
        else LIST.get(index).name(scanner.nextLine()).author(scanner.nextLine()).price(scanner.nextDouble());
        scanner.nextLine();
}
    //删除图书
    private static void deletBook(Scanner scanner) {
        int i = 0;
        for(Book book:LIST) System.out.println(i+"book");
        int index=scanner.nextInt();
        if(index>=LIST.size()) System.out.println("错误的序号");
        else LIST.remove(i);
        scanner.nextLine();
    }
    //查询方法
    private static void showBooks(){
        LIST.forEach(System.out::println);
    }
    //插入信息
    private static void insertBook(Scanner scanner){

        LIST.add(new Book().name(scanner.nextLine()).author(scanner.nextLine()).price(scanner.nextDouble()));
    scanner.nextLine();
    }
    private static class Book implements Serializable{
        String name;
        String author;
        double price;
        public Book name(String name){
            this.name = name;
            return this;
        }
        public Book author (String author){
            this.author = author;
            return this;
        }
        public Book price(double price){
            this.price = price;
            return this;
        }
        @Override
        public String toString() {
            return "书籍{" +
                    "名称='" + name + '\'' +
                    ", 作者='" + author + '\'' +
                    ", 价格=" + price +
                    '}';
        }
    }
}
