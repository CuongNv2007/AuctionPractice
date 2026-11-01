module com.auction.demo {
    requires javafx.controls;
    requires javafx.fxml;

    //requires org.controlsfx.controls;
    //requires net.synedra.validatorfx;
    //requires org.kordamp.ikonli.javafx;
    //requires org.kordamp.bootstrapfx.core;

    opens item to javafx.fxml;
    exports item;
    exports auction;
    opens auction to javafx.fxml;
}