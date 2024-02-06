package application;

import javafx.fxml.FXMLLoader;

public class LoadSave {
    private static GameController loader1;
    private static GameController loader2;
    private static GameController loader3;
    private static GameController loader4;

    public static GameController getLoader1() {
        return loader1;
    }

    public static GameController getLoader2() {
        return loader2;
    }

    public static GameController getLoader3() {
        return loader3;
    }

    public static GameController getLoader4() {
        return loader4;
    }


    public static void setLoader1(GameController loader) {
        loader1 = loader;
    }

    public static void setLoader2(GameController loader) {
        loader2 = loader;

    }
    public static  void setLoader3(GameController loader) {
        loader3 = loader;

    }
    public static  void setLoader4(GameController loader) {
        loader4 = loader;

    }
}
