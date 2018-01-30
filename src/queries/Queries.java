package queries;

public class Queries {

    public static String totalAllCustomers() {
       return   "select kund.id, fornamn, efternamn, sum(produkt.pris * delbestallning.antalx) as totalt_belopp from kund " +
                "inner join bestallning on kund.id = bestallning.kundid " +
                "inner join delbestallning on bestallning.id = delbestallning.bestallningsid " +
                "inner join produkt on delbestallning.produktid = produkt.id " +
                "group by kund.id";
    }

    public static String totalOneCustomer(String kundId) {

       return   "select kund.id, fornamn, efternamn, sum(produkt.pris * delbestallning.antalx) as totalt_belopp from kund " +
                "inner join bestallning on kund.id = bestallning.kundid " +
                "inner join delbestallning on bestallning.id = delbestallning.bestallningsid " +
                "inner join produkt on delbestallning.produktid = produkt.id " +
                "where kund.id = " + Integer.valueOf(kundId) + " " +
                "group by kund.id";
    }

    public static String productsPerCategory() {

        return  "select kategori.id, kategori.namn, produkt.id, produkt.namn, produkt.storlek, produkt.farg from produkt " +
                "inner join kategorirelation on kategorirelation.produktid = produkt.id " +
                "inner join kategori on kategorirelation.kategoriid = kategori.id " +
                "order by kategori.namn";

    }

    public static String productsInLager() {

        return  "select kategori.id, kategori.namn, produkt.id, produkt.namn, produkt.storlek, produkt.farg from produkt " +
                "inner join kategorirelation on kategorirelation.produktid = produkt.id " +
                "inner join kategori on kategorirelation.kategoriid = kategori.id " +
                "where produkt.lagerantal > 0 " +
                "group by kategori.id";

    }

    public static String ejExpideradeBestallning(String kundId) {

        return "select * from bestallning " +
                "where bestallning.expiderad = 0 and bestallning.kundid = " + Integer.valueOf(kundId);
    }
}
