import Base.DBO.Seller;
import Base.DBO.Shop;
import Base.GetObjects;
import Base.RandomObject.CreateRandomObjects;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    private static int random = 10;

    public static void main(String[] args) throws InterruptedException {
        //Создание и сохранение
        GetObjects.saveShops(CreateRandomObjects.createShops(random));
        Set<Shop> shopList = GetObjects.getShops();
        List<Set<Seller>> setList = new ArrayList<>();

        for (Shop shop : shopList) {
            Set<Seller> sellers = CreateRandomObjects.createSellers(random);
            GetObjects.saveSellers(sellers);
            setList.add(sellers);
        }
        List<Shop> shops = new ArrayList<>(shopList);
        int n = 0;
        for (Set<Seller> sellerSet : setList) {
            shops.get(n).setSellers(sellerSet);
            n++;
        }
        GetObjects.updateShops(new HashSet<>(shops));
        Set<Seller> sellers = GetObjects.getSellers();

        Set<Shop> shopSet = GetObjects.getShops();

        for (Seller seller : sellers) {
                seller.setShops(shopSet.stream().skip((int) (shopSet.size() * Math.random())).collect(Collectors.toSet()));
        }

        GetObjects.updateSellers(sellers);

        GetObjects.getShops().forEach(System.out::println);
        GetObjects.getSellers().forEach(System.out::println);

        //Фильтры
        System.out.println("ФИО продавцев, которые работают в магазине у которых выручка больще 1000 :");
        GetObjects.getSellers().stream().filter(Objects::nonNull).
                filter(seller -> seller.getSalary() > 1000).
                forEach(seller -> System.out.println(seller.getSurname() + " " + seller.getName() + " " + seller.getMiddleName()));

        System.out.println("Вывести фамилию и зарплату у продавцев, которые работают в магазине у которых дата создания раньше 06.06.2020 :");
        Calendar calendar = new GregorianCalendar(2020, 06, 06);
        GetObjects.getSellers().stream().filter(Objects::nonNull).
                filter(seller -> Objects.nonNull(seller.getShops())
                        && seller.getShops().stream().filter(Objects::nonNull).
                        anyMatch(shop -> shop.getCreateDate().before(calendar.getTime()))).
                forEach(seller -> System.out.println("Фамилия: " + seller.getSurname() + "; Зарплата: " + seller.getSalary()));
        System.out.println("Вывести магазины в которых работают продавцы с фамилией Иванов, Петров, Сидоров :");
        GetObjects.getSellers().stream().filter(Objects::nonNull).filter(seller -> "Иванов".equals(seller.getSurname())
                || "Сидоров".equals(seller.getSurname()) || "Петров".equals(seller.getSurname())).distinct().
                forEach(seller -> seller.getShops().stream().map(Shop::getNameShop).distinct().forEach(System.out::println));

    }
}
