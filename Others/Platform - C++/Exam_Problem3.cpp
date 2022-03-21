//
//Write a Little Man program that accepts three values as input and outputs them in order of size, largest to smallest. 
//


#include <iostream>
#include <cstdlib>
#include <cstdio>

using namespace std;

int main(int nNameofArgs, char * pszArgs[]){
	
	//declaring variables
	int number[3], inputted_numbers , a, b, c;
	
	//Input three numbers that stored in array
	while(inputted_numbers<3){
		cout << "Enter three Number" << inputted_numbers+1 << " : ";
		cin >> number[inputted_numbers];
		inputted_numbers++;
	}
	
	//arranging the inputted number into highest to lowest
	for(a=0;a<3;a++){
		for(b=a+1;b<3;b++){
			if (number[b]>number[a]){
				c = number[a];
				number[a] = number[b];
				number[b] = c;
			}
		}
	}
	
	//Print the output highest to lowest
	cout << "\n\nThe arrange number of the inputted values into highest ot lowest is: "<<endl<<endl;
	for(a=0;a<3;a++){
	 cout<<"\t"<<number[a]<<endl<<endl;
	}
	
	system("PAUSE");
	return 0;
	
}
