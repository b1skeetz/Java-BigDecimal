import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите пример: ");
        String equation = scan.nextLine();
        calculator(equation);

        BigDecimal money = new BigDecimal("100000"); // стартовая сумма
        BigDecimal percentage = new BigDecimal("13.2"); // процентная ставка
        BigDecimal period = new BigDecimal("6"); // срок вклада
        BigDecimal summa = deposit(money, percentage, period);
        System.out.println(summa);

        BigDecimal percentage2 = new BigDecimal("12"); // НДС
        BigDecimal additionalTax = new BigDecimal("5"); // Дополнительный налог
        System.out.println(priceWithTaxes(money, percentage2, additionalTax));
    }

    public static void calculator(String eq) {
        String[] values = eq.split(" ");
        BigDecimal num1 = new BigDecimal(values[0]);
        String operator = values[1];
        BigDecimal num2 = new BigDecimal(values[2]);
        BigDecimal result = new BigDecimal("0");
        switch (operator) {
            case "+":
                result = num1.add(num2).setScale(10, RoundingMode.HALF_UP);
                break;
            case "-":
                result = num1.subtract(num2).setScale(10, RoundingMode.HALF_UP);
                break;
            case "*":
                result = num1.multiply(num2).setScale(10, RoundingMode.HALF_UP);
                break;
            case "/":
                result = num1.divide(num2, 10, RoundingMode.HALF_UP);
                break;
            default:
                System.out.println("Error = " + operator);
        }
        System.out.println(result);
    }

    public static BigDecimal deposit(BigDecimal sumForBegin, BigDecimal percentage, BigDecimal period) {
        BigDecimal monthsInYear = new BigDecimal("12.0");
        for (int i = 0; i < Integer.parseInt(String.valueOf(period)); i++) {
            BigDecimal sumForYear = sumForBegin.multiply(percentage.divide(new BigDecimal("100.0"))); // сколько начислится за год
            sumForBegin = sumForYear.divide(monthsInYear, 10, RoundingMode.HALF_UP).add(sumForBegin); // за один месяц + стартовая сумма
        }
        return sumForBegin;
    }

    public static BigDecimal priceWithTaxes(BigDecimal startPrice, BigDecimal NDS, BigDecimal additionalTax){
        startPrice = startPrice.multiply(NDS.divide(new BigDecimal("100.0"))).add( // отдельно НДС +
                startPrice.multiply(additionalTax.divide(new BigDecimal("100")))).add( // отдельно дополнительный налог +
                        startPrice); // стартовая цена
        return startPrice;
    }
}