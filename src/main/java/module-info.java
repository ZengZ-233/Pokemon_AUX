module com.example.pokemon {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pokemon to javafx.fxml;
    exports com.example.pokemon;
    exports com.example.pokemon.place;
    opens com.example.pokemon.place to javafx.fxml;
}