package list;

import article.Article;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class List {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleStringProperty name;
    SimpleStringProperty description;
    SimpleObjectProperty<String> date;
    SimpleIntegerProperty pending;
    SimpleDoubleProperty estimated;
    private ArrayList<Article> articles;
    public List (String name, String description){
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleObjectProperty<>(dateFormat.format(new Date()));
        this.pending = new SimpleIntegerProperty();
        this.estimated = new SimpleDoubleProperty();
        this.articles = new ArrayList<>();
    }
    public String getName() {
        return this.name.get();
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }
    public String getDescription() {
        return description.get();
    }
    public SimpleObjectProperty dateProperty() {
        return date;
    }
    public SimpleDoubleProperty estimatedProperty(){
        return estimated;
    }
    public SimpleIntegerProperty pendingProperty(){
        return pending;
    }
    public java.util.List<Article> getPendingArticles(){
        return this.articles.stream()             // convert list to stream
                .filter(p -> !p.getState())     // we dont like not pendings
                .collect(Collectors.toList());
    }
    public java.util.List<Article> getAllArticles(){
        return this.articles;
    }
    public double getSumOfPendings(){
        return this.articles.stream()   //suma en la lista aquellos artículos que tienen el estado en falso
                .filter(article -> !article.getState())
                .mapToDouble(article -> article.getTotal())
                .sum();
    }

    public void toggleArticlebyName(String name){ //busca un artículo por nombre y aplica .toggle en dicho artículo (cambia estado)
        for (int x=0; x<articles.size(); x++){
            if (articles.get(x).getName() == name){
                articles.get(x).toggle();
                this.estimated = new SimpleDoubleProperty(getSumOfPendings());
                this.pending = new SimpleIntegerProperty(getPendingArticles().size());
            }
        }
    }
    public void addArticle(Article article) { //añade un nuevo artículo y hace un update en los pendientes y estimados
        this.articles.add(article);
        this.estimated = new SimpleDoubleProperty(getSumOfPendings());
        this.pending = new SimpleIntegerProperty(getPendingArticles().size());
    }






}



