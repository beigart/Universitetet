package LabbTwo;

public interface IPurchaseManager {

    float sumOfMonth(int year, int month);

    float[] monthlyAverage(int year);

    float[] yearlyAveragePerCategory(int year);

}
