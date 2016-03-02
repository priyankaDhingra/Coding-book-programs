package me.pd.test;

import java.util.ArrayList;
import java.util.List;

public class PN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println(sumPrime2());
	}
	
	public static long sumPrime2() {
	    List<Long> primes = new ArrayList<>();
	    primes.add(2L);
	    primes.add(3L);
	    primes.add(5L);
	    primes.add(7L);
	    long primeSum = 17;

	    for (long primeCandidate = 11; primeCandidate < 1000000; primeCandidate = primeCandidate + 2) {
	        boolean isCandidatePrime = true;
	        //double sqrt = Math.sqrt(primeCandidate);
	        for (int i = 0; i < primes.size(); i++) {
	            Long prime = primes.get(i);
	           // System.out.println(prime);
	            if (primeCandidate % prime == 0) {
	                isCandidatePrime = false;
	                break;
	            }
//	            if (prime > sqrt) {
//	                break;
//	            }
	        }
	        if (isCandidatePrime) {
	            //primes.add(primeCandidate);
	            primeSum += primeCandidate;
	        }
	        //System.out.println(primeCandidate);
	    }
	    //System.out.println(primes.size());
	    return primeSum;
	}
}
