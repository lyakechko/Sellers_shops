package Base.RandomObject;

import Base.DBO.Address;
import Base.DBO.Seller;
import Base.DBO.Shop;

import java.util.*;

public class CreateRandomObjects {

    static int allObjects;
    static List<String> names;
    static List<String> sunames;
    static List<String> middleNames;
    static List<String> cites;
    static List<String> streets;
    static List<String> nameShops;


    static {
        allObjects = (int) (Math.random() * 10);
        names = Arrays.asList("Илья", "Андрей", "Василий", "Евгений", "Геннадий", "Максим", "Матвей");
        sunames = Arrays.asList("Лебедев", "Иванов", "Петров", "Сидоров", "Кечко", "Жук", "Мицкевич");
        middleNames = Arrays.asList("Игоревич", "Андреевич", "Васильевич", "Евгеньевич", "Владиславович", "Дмитриевич", "Владимирович");
        cites = Arrays.asList("Минск", "Москва", "Питер", "Киев", "Владивосток", "Казань", "Гродно", "Витебск");
        streets = Arrays.asList("Ложинская", "Нарочанская", "Небесная", "Ленина", "Привокзальная", "Кульман", "Сурганова", "Независимости");
        nameShops = Arrays.asList("ГИППО", "ЕВРООПТ", "КОМАРОВКА", "СИЛУЕТ", "ГАЛЕРЕЯ", "ГАЛЛИЛЕО", "СТОЛИЦА", "Р-к Ждановичи");
    }

    public static List<Seller> createSellers(int random_num) {
        int randomSelectNames = (int) (Math.random() * (names.size() + 1));
        int randomSelectSurnames = (int) (Math.random() * (sunames.size() + 1));
        int randomSelectMiddleNames = (int) (Math.random() * (middleNames.size() + 1));
        double randomSelectSalary = (Math.random() * (50000 + 1));
        List<Seller> list = new ArrayList<>();
        for (int i = 0; i <= random_num; i++) {
            list.add(Seller.builder().name(names.get(randomSelectNames)).
                    surname(sunames.get(randomSelectSurnames)).
                    salary(randomSelectSalary).
                    middleName(middleNames.get(randomSelectMiddleNames)).
                    build());
        }
        return list;
    }

    public static List<Shop> createShops(int random_num) {
        List<Shop> shopList = new ArrayList<>();
        List<Address> address = createAddress(random_num);
        List<Date> dates = createDates(random_num);
        double randomSelectProceeds = (Math.random() * (50000 + 1));
        int randomSelectNameShops = (int) (Math.random() * (nameShops.size() + 1));
        for (int i = 0; i <= random_num; i++) {
            Address addressForDB = null;
            for (Address address1 : address) {
                addressForDB = address1;
            }

            Date date = new Date();
            for (Date date1 : dates) {
                date = date1;
            }
            shopList.add(Shop.builder().nameShop(nameShops.get(randomSelectNameShops)).
                    proceeds(randomSelectProceeds).
                    createDate(date).
                    address(addressForDB).
                    build());
        }
        return shopList;
    }

    public static List<Address> createAddress(int random_num) {
        List<Address> addresses = new ArrayList<>();
        int randomSelectCites = (int) (Math.random() * (cites.size() + 1));
        int randomSelectStreets = (int) (Math.random() * (streets.size() + 1));
        for (int i = 0; i <= random_num; i++) {
            addresses.add(Address.builder().
                    city(cites.get(randomSelectCites)).
                    street(streets.get(randomSelectStreets)).
                    build());
        }
        return addresses;
    }

    public static List<Date> createDates(int random_num) {
        List<Date> list = new ArrayList<>();
        for (int i = 0; i <= random_num; i++) {
            Date date = new Date();
            long randomSelectProceeds = (long) (Math.random() * (1000000000 + 1));
            date.setTime(randomSelectProceeds);
            list.add(date);
        }
        return list;
    }
}
