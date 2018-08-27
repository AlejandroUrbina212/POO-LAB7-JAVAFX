package administrator;

import java.util.ArrayList;

import article.Article;
import list.List;

public class Administrator {
    private static Administrator instance;
    private ArrayList<List> listBook = new ArrayList<>();

    protected Administrator(){
        addAnything();
        //solo sirve para instanciar la clase como tal
    }
    public static Administrator getInstance(){
        if(instance == null) {
            instance = new Administrator();
        }
        return instance; //para instanciar la clase con datos cuales quiera, esto servirá como un objeto de tipo
        //administrador que almacenará las listas
    }
    private void addAnything(){
        List list1 = new List("Supermercado", "Lista de compras para cumpleaños");
        list1.addArticle(new Article("Bananos", 2, 5.00));
        List list2 = new List("prueba2", "Lista de compras para whatever");
        list2.addArticle(new Article("Bananos", 3, 2.00));
        this.listBook.add(list1);
        this.listBook.add(list2);

    }
    public boolean addList(String name, String description){
        if(getListByName(name) == null)
        {
            this.listBook.add(new List(name, description));
            return true;
        }
        return false;
    }

    public ArrayList<List> getListBook() {
        return this.listBook;
    }

    public List getListByName(String name){
        return this.listBook.stream()             // convert list to stream
                .filter(list -> list.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public boolean addArticle(String nameList, Article article) {
        for(int x = 0; x < listBook.size(); x++){
            if(listBook.get(x).getName().equals(nameList)){
                listBook.get(x).addArticle(article);
                return true;
            }
        }
        return false;
    }



}
