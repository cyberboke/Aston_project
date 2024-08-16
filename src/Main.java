
import enums.TypeClass;
import enums.*;
import customClasses.factory.loader.LoaderFactory;
import menu.Menu;
import operation.BinarySearch;
import operation.EvenOddSort;
import operation.TimSort;
import strategy.Actions;
import java.util.*;


public class Main {
    private static ListClasses listClasses;

    private static TypeClass typeClass;
    private static TypeAction typeAction;
    private static int count = 10;
    private static TypeSort typeSort;
    private static TypeChoice typeChoice;

    public static void main(String[] args) {
        //выбираем класс с которым будем работать и генерируем список на основе выбранного класса
        do {
            typeClass = Menu.choosingClass();
            listClasses = ListClasses.generateList(typeClass);

            do {
                //выбираем тип загрузки
                TypeLoad typeLoad2 = Menu.choosingLoad();
                //выбираем количество объектов в массиве
                count = Menu.choosingSize();
                //устанавливаем способ загрузки
                Actions.LOAD.addStrategy(listClasses.strategy::add, () -> {
                    //очищаем список для того, чтобы у пользователя при смене загрузки не остался старый список
                    listClasses.list.clear();
                    //получаем список элементов и добавляем его в наш массив
                    listClasses.list.addAll(
                            LoaderFactory.getFactory(typeLoad2).load(typeClass, count)
                    );
                });
                //выполняем загрузку
                listClasses.execute(Actions.LOAD.name);
                //удаляем способ загрузки из стратегии, чтобы список не обновлялся при изменении действия
                Actions.LOAD.removeStrategy(listClasses.strategy::remove);

                do {
                    //выбираем действие - поиск или сортировка
                    typeAction = Menu.choosingAction();
                    if (typeAction == TypeAction.SEARCH) {
                        //устанавливаем сортировку по умолчанию - Tim Sort
                        Actions.SORT.addStrategy(listClasses.strategy::add, () -> {
                            TimSort.sort(listClasses.list);
                        });
                        //добавляем в стратегию поиск
                        Actions.SEARCH.addStrategy(listClasses.strategy::add, () -> {
                            //запрашиваем у пользователя объект, который он хочет искать
                            Object obj = Menu.createObject(typeClass);
                            //ищем объект
                            int index = BinarySearch.binarySearch(listClasses.list, (Comparable) obj);
                            //выводим объект для поиска в консоль
                            System.out.println("Object: " + obj);
                            //если индекс -1, объект не найден
                            if (index == -1) {
                                System.out.println("Not found"+"\n");
                            //иначе объект найден
                            } else {
                                System.out.println("Found"+"\n");
                            }
                        });
                    } else {
                        //Выбираем тип сортировки
                        typeSort = Menu.choosingSort();
                        //загружаем сортировку
                        Actions.SORT.addStrategy(listClasses.strategy::add, () -> {
                            //выбираем правило сортировки, по умолчанию или по какому-либо полю
                            Comparator comp = Menu.choosingComparator(typeClass);
                            //в зависимости от выбора пользователя производим сортировку
                            switch (typeSort) {
                                case TIMSORT -> TimSort.sort(listClasses.list, comp);
                                case EVENSORT -> EvenOddSort.sort(listClasses.list, true, comp);
                                case ODDSORT -> EvenOddSort.sort(listClasses.list, false, comp);
                            }
                            //выводим результат в консоль
                            listClasses.list.forEach(System.out::println);
                            System.out.println();
                        });
                    }
                    try {
                        //выполняем поиск или сортировку
                        listClasses.executeAll();
                    } catch (RuntimeException e) {
                        System.err.println("An error has occurred. Please try again.");
                    }
                    //предлагаем пользователю что-либо изменить - класс, загрузка, действие или выход из программы
                    typeChoice = Menu.choosingChoice();
                    if (typeChoice == TypeChoice.EXIT) return;
                    //очищаем мапу
                    Actions.SORT.removeStrategy(listClasses.strategy::remove);
                    Actions.SEARCH.removeStrategy(listClasses.strategy::remove);
                    //меняем действие
                } while (typeChoice == TypeChoice.TYPE_ACTION);
                //меняем загрузку
            } while (typeChoice == TypeChoice.TYPE_LOAD);
            //меняем класс
        } while (typeChoice == TypeChoice.TYPE_CLASS);
    }

}
