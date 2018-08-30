package article;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Article {
    SimpleStringProperty name;
    SimpleIntegerProperty quantity;
    SimpleDoubleProperty price;
    SimpleDoubleProperty total;
    SimpleBooleanProperty state;

    public  Article (String name, Integer quantity, Double price){
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
        this.total = new SimpleDoubleProperty((price*quantity));
        this.state = new SimpleBooleanProperty(false);
    }


    public SimpleStringProperty nameProperty(){
        return name;
    }
    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public SimpleDoubleProperty totalProperty(){
        return total;
    }

    public SimpleBooleanProperty isCompleteProperty() {
        return state;
    }


    public String getName(){
        return this.name.get();
    }

    public void toggle(){ //cambia el estado al negar el estado dependiendo de su estado inicial
        if (!this.getState()){
            this.state = new SimpleBooleanProperty(true);
        }
        else {
            this.state = new SimpleBooleanProperty(false);
        }
    }

    public int getQuantity(){
        return this.quantity.get();
    }
    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public Double getPrice(){
        return this.price.get();
    }
    public void setPrice(double price) {
        this.price.set(price);
    }

    public boolean isComplete(){
        return this.state.get();
    }

    public boolean getState() {
        return state.get();
    }

    public void setState(boolean state) {
        this.state.set(state);
    }


    public double getTotal() {
        return total.get();
    }

    public void setTotal(double total) {
        this.total.set(total);
    }



}
