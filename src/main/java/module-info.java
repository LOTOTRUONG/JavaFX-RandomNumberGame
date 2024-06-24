module loto.vn.numbergame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.controlsfx.controls;
    requires java.naming;
    requires lombok;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.elusive;
    requires org.kordamp.ikonli.entypo;



    opens loto.vn.numbergame to javafx.fxml;
    exports loto.vn.numbergame;
}