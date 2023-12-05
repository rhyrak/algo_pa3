import java.util.ArrayList;

public abstract class AbstractHashMap {

    ArrayList<Integer> primes;
    boolean isPrime(int num)
    {
        /* Exit condition */
        if(num <= 1)
        {
            return false;
        }
        for(int i = 2; i <= num / 2; i++)
        {
            if((num % i) == 0)
                return false;
        }
        return true;
    }

    ArrayList<Integer> genPrimeArray(int size) {
        ArrayList<Integer> arr = new ArrayList<>(size);
        int z = 0;
        for (int i = 1; z < size; i++) {
            if (isPrime(i)) {
                arr.add(i);
                z++;
            }
        }
        return arr;
    }

    /* change this */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
