package application;

import utils.IO;

public class Main {

    public static void main(String[] args) {
        //TODO
        System.out.println("---===Welcome to PaiThong Store You are the Manager Here. Good Luck!!===---");
        // create New instance of IO (IO constructor will call method home())
        IO io = IO.getInstance();

        while (true) {
            io.home();

        }

    }
}
