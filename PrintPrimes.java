public class PrintPrimes {
  int numberOfPrimes;
  int numberOfRows;
  int numberOfColums;
  int comparisonBoundryMax;
  int listOfPrimes[];

  final int FIRST_PRIME = 2;
  final int STARTING_COMPARISON_BOUNDRY = 2;
  final int STARTNG_NUMBER = 1;
  final int FIRST_SQUARE = 9;
  final int STARTING_PAGE_NUMBER = 1;
  final int STARTING_PAGE_OFFSET = 1;
  
  public PrintPrimes(int numberOfPrimes, int nummberOfRows, int numberOfColums, int comparisonBountry) 
  {
    this.numberOfPrimes   = numberOfPrimes;
    this.numberOfRows  = nummberOfRows;
    this.numberOfColums  = numberOfColums;
    this.comparisonBoundryMax = comparisonBountry;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }


  public static void main(String[] args) 
  {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 30);
      printPrimes.calculatePrimes();
      printPrimes.printPrimes();
  }

  public void calculatePrimes() 
  {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = FIRST_PRIME;
      calculateOddPrimes();
  }

  private void calculateOddPrimes() 
  {
      boolean isPrime;
      int currentPrimeNumberIndex;
      int multiplesOfPrimes[] = new int[comparisonBoundryMax + 1];

      int currentNumber = STARTNG_NUMBER;
      int comparisonBoundry = STARTING_COMPARISON_BOUNDRY;
      int square = FIRST_SQUARE;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++)
      {
        do 
        {
          currentNumber = currentNumber + 2;
          if (currentNumber == square) 
          {
            comparisonBoundry = comparisonBoundry + 1;
            square = listOfPrimes[comparisonBoundry] * listOfPrimes[comparisonBoundry];
            multiplesOfPrimes[comparisonBoundry - 1] = currentNumber;
          }
          currentPrimeNumberIndex = 2;
          isPrime = true;
          while (currentPrimeNumberIndex < comparisonBoundry && isPrime) 
          {
            while (multiplesOfPrimes[currentPrimeNumberIndex] < currentNumber)
            {
            	multiplesOfPrimes[currentPrimeNumberIndex] = multiplesOfPrimes[currentPrimeNumberIndex] + listOfPrimes[currentPrimeNumberIndex] + listOfPrimes[currentPrimeNumberIndex];
            }
            if (multiplesOfPrimes[currentPrimeNumberIndex] == currentNumber)
            {
              isPrime = false;
            }
            currentPrimeNumberIndex = currentPrimeNumberIndex + 1;
          }
        } while (!isPrime);
        
        listOfPrimes[primesFoundSoFar] = currentNumber;
      }
    }

    public void printPrimes() 
    {
        int pageNumber = STARTING_PAGE_NUMBER;
        int pageOffset = STARTING_PAGE_OFFSET;
        while (pageOffset <= numberOfPrimes) 
        {
          System.out.println("The First " + numberOfPrimes +" Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          for (int rowOffset = pageOffset; rowOffset < pageOffset + numberOfRows; rowOffset++)
          {
            for (int C = 0; C < numberOfColums;C++)
            {
              if (rowOffset + C * numberOfRows <= numberOfPrimes)
              {
                System.out.format("%10d", listOfPrimes[rowOffset + C * numberOfRows]);
              }
            }
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + numberOfRows * numberOfColums;
        }
    }
}

					 
