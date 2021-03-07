package practice.service;

public class SecurityInfoService {

    public boolean checkTxnValid(String txnId) throws InterruptedException {
        System.out.println("ThreadId: " + Thread.currentThread().getId());
        Thread.sleep(2000);
        System.out.println("Transaction validation completed");
        return true;
    }

    public boolean checkNoCrime(String name, String ssn) throws InterruptedException {
        System.out.println("ThreadId: " + Thread.currentThread().getId());
        Thread.sleep(2000);
        System.out.println("Crime check completed");
        return true;
    }

}
