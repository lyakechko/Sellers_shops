package Base.RandomObject;

import Base.DBO.Address;
import Base.DBO.Seller;
import Base.DBO.Shop;
import Base.GetObjects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class CreateRandomObjects {

    static int allObjects;
    static List<String> names;
    static List<String> sunames;
    static List<String> middleNames;
    static List<String> cites;
    static List<String> streets;
    static List<String> nameShops;
    static Long currentDate;

    static {
        currentDate = System.currentTimeMillis();
        allObjects = (int) (Math.random() * 10);
        names = Arrays.asList("Илья", "Андрей", "Василий", "Евгений", "Геннадий", "Максим", "Матвей");
        sunames = Arrays.asList("Лебедев", "Иванов", "Петров", "Сидоров", "Кечко", "Жук", "Мицкевич");
        middleNames = Arrays.asList("Игоревич", "Андреевич", "Васильевич", "Евгеньевич", "Владиславович", "Дмитриевич", "Владимирович");
        cites = Arrays.asList("Минск", "Москва", "Питер", "Киев", "Владивосток", "Казань", "Гродно", "Витебск");
        streets = Arrays.asList("Ложинская", "Нарочанская", "Небесная", "Ленина", "Привокзальная", "Кульман", "Сурганова", "Независимости");
        nameShops = Arrays.asList("ГИППО", "ЕВРООПТ", "КОМАРОВКА", "СИЛУЕТ", "ГАЛЕРЕЯ", "ГАЛЛИЛЕО", "СТОЛИЦА", "Р-к Ждановичи");
    }

    public static Set<Seller> createSellers(int random_num) {
        Set<Seller> list = new HashSet<>();
        for (int i = 1; i <= random_num; i++) {
            int randomSelectNames = (int) (Math.random() * (names.size()));
            int randomSelectSurnames = (int) (Math.random() * (sunames.size()));
            int randomSelectMiddleNames = (int) (Math.random() * (middleNames.size()));
            double randomSelectSalary = (Math.random() * (50000 + 1));
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
        for (int i = 1; i <= random_num; i++) {
            double randomSelectProceeds = (Math.random() * (50000 + 1));
            int randomSelectNameShops = (int) (Math.random() * (nameShops.size()));
            String name = nameShops.get(randomSelectNameShops);
            if (shopList.size() == nameShops.size()) {
                continue;
            }
            if (shopList.stream().filter(Objects::nonNull).anyMatch(shop -> name.equals(shop.getNameShop()))) {
                System.out.println("name : " + name);
                randomSelectNameShops = (int) (Math.random() * (nameShops.size()));
                String shopRandom = nameShops.get(randomSelectNameShops);
                System.out.println("name2 : " + shopRandom);
                while (shopList.stream().noneMatch(shop -> shopRandom.equals(shop.getNameShop()))) {
                    BigDecimal bd = new BigDecimal(Double.toString(randomSelectProceeds));
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    shopList.add(Shop.builder().nameShop(shopRandom).
                            proceeds(bd.doubleValue()).
                            createDate(dates.get(i - 1)).
                            address(address.get(i - 1)).
                            build());
                }
            } else {
                BigDecimal bd;
                bd = new BigDecimal(Double.toString(randomSelectProceeds)).
                        setScale(2, RoundingMode.HALF_UP);
                shopList.add(Shop.builder().nameShop(name).
                        proceeds(bd.doubleValue()).
                        createDate(dates.get(i - 1)).
                        address(address.get(i - 1)).
                        build());
            }

        }
        return shopList;
    }

    public static List<Address> createAddress(int random_num) {
        List<Address> addresses = new ArrayList<>();
        for (int i = 1; i <= random_num; i++) {
            int randomSelectCites = (int) (Math.random() * (cites.size()));
            int randomSelectStreets = (int) (Math.random() * (streets.size()));
            addresses.add(Address.builder().
                    city(cites.get(randomSelectCites)).
                    street(streets.get(randomSelectStreets)).
                    build());
        }
        return addresses;
    }

    public static List<Date> createDates(int random_num) {
        List<Date> list = new ArrayList<>();
        for (int i = 1; i <= random_num; i++) {
            list.add(new Date((long) (Math.random() * (currentDate + 1))));
        }
        return list;
    }
}
