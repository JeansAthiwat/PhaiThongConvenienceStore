package application;

import utils.IO;

public class Main {

    public static void main(String[] args) {
        System.out.println("---===Welcome to PaiThong Store You are the Manager Here. Good Luck!!===---");
        while (true) {
            IO.getInstance().home(); // call method home from an instance of IO
        }
    }

}
