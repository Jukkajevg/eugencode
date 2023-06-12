import java.util.ArrayList;
import java.util.List;

public class InvestorCalculator {
    
    public static void main(String[] args) {
        List<Investor> investors = getInvestorsInHelsinki(); // Replace with your own data retrieval logic
        
        int investorCount = investors.size();
        System.out.println("Number of investors in Helsinki: " + investorCount);
    }
    
    // Method to fetch the investors from a data source or API
    private static List<Investor> getInvestorsInHelsinki() {
        // Replace this with your data retrieval logic
        // You can fetch the data from a database, CSV file, or an API
        
        // Sample data for demonstration
        List<Investor> investors = new ArrayList<>();
        investors.add(new Investor("Yritys1"));
        investors.add(new Investor("Yritys2"));
        investors.add(new Investor("Yritys3"));
        
        return investors;
    }
    
    // Sample Investor class
    private static class Investor {
        private String name;
        
        public Investor(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
}
