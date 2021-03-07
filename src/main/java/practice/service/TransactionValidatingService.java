package practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TransactionValidatingService {

    SecurityInfoService securityInfoService;

    public TransactionValidatingService() {
        securityInfoService = new SecurityInfoService();

    }

    public boolean validate(String txnId, String personName, String ssn) {
        System.out.println("== Security check starting... ==");
        boolean result = false;

        // We want to validate TxnId and Crime history concurrently

        Callable<Boolean> checkTxnId = () -> {
            if (securityInfoService.checkTxnValid(txnId)) {
                System.out.println("Good TxnId");
                return true;
            }
            return false;
        };

        Callable<Boolean> checkNoCrime = () -> {
            if (securityInfoService.checkNoCrime(personName, ssn)) {
                System.out.println("No crime history found");
                return true;
            }
            return false;
        };

        List<Callable<Boolean>> runList = new ArrayList<Callable<Boolean>>(){{add(checkTxnId); add(checkNoCrime);}};
        ExecutorService executorService = Executors.newFixedThreadPool(runList.size());
        List<Future<Boolean>> dupFutures = runList.stream().map(executorService::submit).collect(Collectors.toList());
        executorService.shutdown();

        for (Future<Boolean> future: dupFutures){
            try {
                result = future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Not able to operate security check.");
                result = false;
            }
        }
        return result;
    }


}
