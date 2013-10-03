public class PrintPrimes {
  int primesToBePrinted;
  int rowsToBePrinted;
  int columsToBePrinted;
  int comparisonBoundryMax;
  int listOfPrimes[];

  //constants
  final int PRIME_THE_FIRST = 2;
  final int COMPARISON_BOUNDRY_STARTING = 2;
  final int NUMBER_STARTNG = 1;
  final int FIRST_SQUARE = 9;
  final int PAGE_NUMBER_FIRST = 1;
  final int OFFSET_FIRST = 1;

  /*Constructor description
   ************************************************************
   * this is the default constructor that sets the necessary variables 
   * by initializing a PrintPrimes object with the values bellow the user 
   * how many prime numbers will be printed and how they will be printed
   ************************************************************  
   */
  public PrintPrimes(int primesToBePrinted, int rowsToBePrinted, int columsToBePrinted, int comparisonBountryMax) 
  {
    this.primesToBePrinted   = primesToBePrinted;
    this.rowsToBePrinted  = rowsToBePrinted;
    this.columsToBePrinted  = columsToBePrinted;
    this.comparisonBoundryMax = comparisonBountryMax;
    this.listOfPrimes = new int[++primesToBePrinted];
  }


  public static void main(String[] args) 
  {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 30);
      printPrimes.calculatePrimes();
      printPrimes.printPrimesToConsole();
  }

  //sets the first prime number and calls calculateOddPrimes 
  public void calculatePrimes() 
  {
      /* 
       * Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = PRIME_THE_FIRST;
      calculateOddPrimes();
  }
  
  /*calculateOddPrimes() Description
   *********************************************************
   * this method finds all the odd prime numbers after 2 (effectively all prime numbers after 2 )
   * until the amount of primes found matches the class variable numberOfPrimes. the method executes a version of
   * the Sieve of Eratosthenes algorithm. The difference this version does not considering even numbers
   * and by only comparing the current number being check with multiples of primes up to the square of the last prime number
   * when the current number being reached equals the square of the last prime number the square number gets updated to the next prime number
   * that was found and then comparisons continue.
   * ******************************************************** 
   */
  private void calculateOddPrimes() 
  {
      boolean isPrime;
      int primeNumberIndex;//prime number index that is in charge of iterating through the range of multiples of prime numbers 
      int multiplesOfPrimes[] = new int[comparisonBoundryMax+1];//array that holds the multiples of prime numbers found in order to compare them with the current number being checked 

      int currentNumber = NUMBER_STARTNG;//the current number that is being checked for primeness. starts at 1  
      int comparisonBoundry = COMPARISON_BOUNDRY_STARTING;//keeps track of the range of prime multiples that will be compared to current number 
      int square = FIRST_SQUARE;//the first square is set to 9 as 2^2 is 4 which is even 

      for(int primesFoundSoFar = 2; primesFoundSoFar <= primesToBePrinted; primesFoundSoFar++)
      {
        do 
        {
          currentNumber +=2;//by two because we are skipping all even numbers
          
          if (currentNumber == square) //time to update the square number
          {
            comparisonBoundry ++; //increase the range of multiples being checked with the current number 
            
            square = listOfPrimes[comparisonBoundry] * listOfPrimes[comparisonBoundry];
            
            multiplesOfPrimes[comparisonBoundry - 1] = currentNumber;//store the square that was reached
          }
          primeNumberIndex = 2;//start comparisons always at the third index ie 3 
          isPrime = true;
          while (primeNumberIndex < comparisonBoundry && isPrime)//while the square number rage has not been reached and the number is potentially prime 
          {
        	//if the multiples being checked are less then the current number update the list as those numbers under the current the number will never equal the next numbers being checked
            while (multiplesOfPrimes[primeNumberIndex] < currentNumber)
            {
            	multiplesOfPrimes[primeNumberIndex] += (2*listOfPrimes[primeNumberIndex]);
            }
            //if the current number equals to a multiple of a prime number set isprime to false causing the loop to not add this number and move on to the next one
            if (multiplesOfPrimes[primeNumberIndex] == currentNumber) 
            {
              isPrime = false;
            }
            primeNumberIndex ++;//move on to next multiple
          }
        } while (!isPrime);//if isprime is still true that means the current number is a prime number
        
        listOfPrimes[primesFoundSoFar] = currentNumber;//add the prime number to the list
      }
    }

  	/*printPrimesToConsole method description
  	 **************************************************************  
  	 * This prints the contents of the listOfPrimes array in a format based on what is passed to the
  	 * PrintPrimes object. the format is based on what numberOfColums and numberOfRows is set to.   
  	 **************************************************************
  	 */
    public void printPrimesToConsole() 
    {
        int pageNumber = PAGE_NUMBER_FIRST;
        int offsetOfPage = OFFSET_FIRST;
        while (offsetOfPage <= primesToBePrinted)//while not all the prime numbers have been printed keep printing pages  
        {
          System.out.println("The First " + primesToBePrinted +" Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          for (int rowOffset = offsetOfPage; rowOffset < offsetOfPage + rowsToBePrinted; rowOffset++)//keep printing rows until the number of set rows is reached  
          {
            for (int C = 0; C < columsToBePrinted;C++)//print row of primes until the number of set columns is reached
            {
              if (rowOffset + C * rowsToBePrinted <= primesToBePrinted)//if the number of primes printed is less then the number of primes found 
              {
                System.out.format("%10d", listOfPrimes[rowOffset + C * rowsToBePrinted]);
              }
            }
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber ++;
          offsetOfPage = offsetOfPage + rowsToBePrinted * columsToBePrinted;
        }
    }
}

					 
