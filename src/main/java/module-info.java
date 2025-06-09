module at.htlkaindorf.animelist {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens at.htlkaindorf.animelist to javafx.fxml;
    exports at.htlkaindorf.animelist;
    exports at.htlkaindorf.animelist.controller;
    opens at.htlkaindorf.animelist.controller to javafx.fxml;
}