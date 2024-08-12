package menu;

import java.util.ArrayList;
import java.util.List;

public class MenuEntry {
    private String title; // Заголовок пункута меню
    private List<MenuEntry> entries; // дочерние элементы пункта меню
    private Runnable runnable; // лямбда которая запускается при выборе меню

    // Конструктор
    public MenuEntry(String title) {
        this.title = title;
        this.entries = new ArrayList<>();
    }

    // Конструктор
    public MenuEntry(String title, Runnable runnable) {
        this.title = title;
        this.entries = new ArrayList<>();
        this.runnable = runnable;
    }


    public String getTitle() {
        return title;
    }

    public List<MenuEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<MenuEntry> entries) {
        this.entries = entries;
    }

    // функция которая запускает сохраненную лямбду
    public void run() {
        if(this.runnable != null) {
            this.runnable.run();
        }
    }
}
