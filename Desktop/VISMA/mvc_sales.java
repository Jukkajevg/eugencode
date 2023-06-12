

public class SalesData {
    private int totalSales;

    public void setTotalSales(int sales) {
        totalSales = sales;
    }
	
	// model 

    public int getTotalSales() {
        return totalSales;
    }
}

// view

public class SalesView {
    public void displaySalesData(int totalSales) {
        System.out.println("Total sales: " + totalSales);
    }
}

// controller

public class SalesController {
    private SalesData model;
    private SalesView view;

    public SalesController(SalesData model, SalesView view) {
        this.model = model;
        this.view = view;
    }

    public void updateSalesData(int sales) {
        model.setTotalSales(sales);
    }

    public void displaySalesData() {
        int totalSales = model.getTotalSales();
        view.displaySalesData(totalSales);
    }
}

// main

public class Main {
    public static void main(String[] args) {
        SalesData salesData = new SalesData();
        SalesView salesView = new SalesView();
        SalesController salesController = new SalesController(salesData, salesView);

        // Update sales data
        salesController.updateSalesData(1000);

        // Display sales statistics
        salesController.displaySalesData();
    }
}
