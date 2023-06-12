import java.util.ArrayList;
import java.util.List;

public class EconomicDevelopment {

    private static final int START_YEAR = 2010; // Define the start year of the analysis
    private static final int END_YEAR = 2020; // Define the end year of the analysis

    // Method to fetch the economic data for Helsinki
    private List<Double> fetchEconomicData() {
        
        // It can use libraries like Retrofit or HttpURLConnection
        // Parse the response and extract the relevant economic indicators for Helsinki
        // Return the economic data as a list of doubles
        // Let's assume we have the following data
        List<Double> economicData = new ArrayList<>();
        economicData.add(100.0);
        economicData.add(120.0);
        economicData.add(130.0);
        // ... add more economic data points
        return economicData;
    }

    // Method to calculate the rate of economic development
    private double calculateRateOfDevelopment(List<Double> economicData) {
        if (economicData.size() < 2) {
            throw new IllegalArgumentException("Insufficient data to calculate rate of development.");
        }

        double startValue = economicData.get(0);
        double endValue = economicData.get(economicData.size() - 1);

        double rateOfDevelopment = ((endValue - startValue) / startValue) * 100.0;
        return rateOfDevelopment;
    }

    public void analyzeEconomicDevelopment() {
        // Fetch the economic data
        List<Double> economicData = fetchEconomicData();

        // Calculate the rate of economic development
        double rateOfDevelopment = calculateRateOfDevelopment(economicData);

        System.out.println("Rate of Economic Development in Helsinki:");
        System.out.println(rateOfDevelopment + "%");
    }

    public static void main(String[] args) {
        EconomicDevelopment analysis = new EconomicDevelopment();
        analysis.analyzeEconomicDevelopment();
    }
}
