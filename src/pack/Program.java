package pack;

import model.Bestallning;
import model.Kund;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Program {

    public static void main(String args[]) throws SQLException {

        Repository repository = new Repository();
        Scanner scanner = new Scanner(System.in);

        boolean keepAlive = true;

        while (keepAlive) {

            List<Kund> kundList = new ArrayList<>();
            List<Product> products = new ArrayList<>();


            System.out.println("\n");
            System.out.println("Välj ditt alternativ");
            System.out.println("===============================");
            System.out.println("1: sök kund");
            System.out.println("2: se alla kunder");
            System.out.println("3: se produkter per kategori");
            System.out.println("4: lägg beställning");
            System.out.println("5: avsluta");

            String input = scanner.next();

            if (input.equalsIgnoreCase("1")) {
                System.out.println("ange kundId");
                input = scanner.next();
                kundList = repository.getTotalFromKund(input);
                kundList.forEach(kund -> System.out.println(kund.toString()));
            } else if (input.equalsIgnoreCase("2")) {
                kundList = repository.getTotalFromKund(null);
                kundList.forEach(kund -> System.out.println(kund.toString()));
            } else if (input.equalsIgnoreCase("3")) {
                products = repository.getProductsPerCategory();

                Integer categoryId = null;

                for (Product product : products) {
                    if (null == categoryId || product.getCategoryId() != categoryId) {
                        System.out.println("\n");
                        System.out.println(product.getCategoryName());
                        System.out.println("==================================");
                        categoryId = product.getCategoryId();
                    }
                    System.out.println(product.getName() + ", " + product.getStorlek() + ", " + product.getFarg());
                }
            } else if (input.equalsIgnoreCase("4")) {

                System.out.println("Välj kund nedan (siffra):");
                System.out.println("=======================================");

                kundList = repository.getTotalFromKund(null);

                AtomicInteger kundInteger = new AtomicInteger(1);
                kundList.forEach(kund -> System.out.println(kundInteger.getAndIncrement() + ": " + kund.getFornamn() + " " + kund.getEfternamn()));

                String kundId = scanner.next();

                System.out.println("Välj vilken produkt du vill lägga beställning på (siffra)");
                System.out.println("=======================================");

                List<Product> productsInLager = repository.getProductsInLager();

                AtomicInteger productInteger = new AtomicInteger(1);
                productsInLager.forEach(product -> System.out.println(productInteger.getAndIncrement() + ": " + product.getName() + ", " + product.getStorlek() + ", " + product.getFarg()));

                String produktId = scanner.next();

                System.out.println("Välj vilken beställning du vill lägga till produkten till (-1 om ny beställning)");
                System.out.println("=======================================");

                List<Bestallning> bestallnings = repository.getEjExpideradeBetallningar(String.valueOf(kundList.get(Integer.parseInt(kundId) -1).getId()));

                if (bestallnings.isEmpty()) {
                    System.out.println("Det finns ingen öppen beställning, skapar en ny");
                    repository.addBestallningViaStoredProcedure(String.valueOf(kundList.get(Integer.parseInt(kundId) - 1).getId()), null, String.valueOf(productsInLager.get(Integer.parseInt(produktId) - 1).getId()));
                } else {

                    AtomicInteger bestallningsInteger = new AtomicInteger(1);
                    bestallnings.forEach(bestallning -> System.out.println(bestallningsInteger.getAndIncrement() + ": " + bestallning.getBestallningsDatum()));

                    String bestallningsId = scanner.next();

                    repository.addBestallningViaStoredProcedure(String.valueOf(kundList.get(Integer.parseInt(kundId) - 1).getId()), bestallningsId, String.valueOf(productsInLager.get(Integer.parseInt(produktId) - 1).getId()));
                }

            } else {
                keepAlive = false;
            }


        }
    }

}


