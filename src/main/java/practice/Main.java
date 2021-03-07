package practice;

import practice.service.TransactionValidatingService;

public class Main {
    public static void main(String[] args) {

        String txnId = "S123";
        String personName = "Good Man";
        String ssn = "555-55-5555";
        TransactionValidatingService transactionValidatingService = new TransactionValidatingService();

        String canProceed = transactionValidatingService.validate(txnId, personName, ssn) ?
                "YES" : "NO";

        System.out.println("\n==== Report ====");
        System.out.println("Able to proceed the transaction?: " + canProceed);

        System.out.println("\nValidation Completed");


    }
}
