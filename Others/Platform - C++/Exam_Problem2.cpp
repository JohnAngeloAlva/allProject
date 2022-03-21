//
//Write a Little Man program to accept an indefinite number of input values. 
//The output value will be the largest of the input values. You should use the value 0 as a flag to indicate the end of input
//

#include <iostream>
#include <cstdio>
#include <cstdlib>

using namespace std;

int main(int nNumberofArgs, char * pszArgs[]){
	
	//declaring variables
	int number[1000],i,inputted_numbers=0;

	//input the value that stores in the array
	while(true){
		
		cout << "Enter Number " <<inputted_numbers+1<< " : ";
		cin >> number[inputted_numbers];
		
		if(number[inputted_numbers] == 0){
			break;
		}
		inputted_numbers++;
	}
	
	//finding the largest value
   for(i=0; i<inputted_numbers; i++){
   		if(number[inputted_numbers]<number[i]){
            number[inputted_numbers]=number[i];
        }
   	
   }
   
	//Print the largest number
	cout << "\n\n\nLargest value is: " << number[inputted_numbers]<<endl<<endl<<endl;
	system("PAUSE");
	return 0;

}
