public class InvestmentModel {
    private double totalInvestments;

    public double getTotalInvestments() {
        return totalInvestments;
    }

    public void calculateTotalInvestments() {
        
        // assume the total investments are already calculated
        totalInvestments = 1000000.0; // with the actual calculated value
    }
}

// investment view

public class InvestmentView {
    public void displayTotalInvestments(double totalInvestments) {
        System.out.println("Total investments in Turku: " + totalInvestments);
    }
}

// interaction between the model and view

public class InvestmentController {
    private InvestmentModel model;
    private InvestmentView view;

    public InvestmentController(InvestmentModel model, InvestmentView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        double totalInvestments = model.getTotalInvestments();
        view.displayTotalInvestments(totalInvestments);
    }

    public void calculateTotalInvestments() {
        model.calculateTotalInvestments();
    }
}

// use the MVC components to calculate and display the total investments in Turku

public class Main {
    public static void main(String[] args) {
        InvestmentModel model = new InvestmentModel();
        InvestmentView view = new InvestmentView();
        InvestmentController controller = new InvestmentController(model, view);

        // Calculate and display the total investments
        controller.calculateTotalInvestments();
        controller.updateView();
    }
}
