//
//Use a single-subscripted array to solve the following problem. A company pays its salespeople on a commission basis.
//The salespeople receive $300 per week plus 4 percent of their gross sales for that week. 
//For example, a salesperson who grosses $5000 in sales in a week receives $300 plus 4 percent of $5000, or a total of $500.
//Write a program (using an array of counters) that determines how many of the salespeople earned salaries in each of the following ranges 
//(assume that each salesperson’s salary is truncated to an integer amount):
//

#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <iomanip>

using namespace std;

// Declaring the function of gross_salary with the parameter of array in main
void gross_salary(int countCommission[]){
	
	//declare variables
	int salary;
	//float the variable of gross salary that can enter decimal value 
	float gSalary[salary],commission;
	
	
	
	//Loop for Entering the Data and for the value of the conditions 	
	while (true){
		
		cout << "Enter employee gross sale (-1 to end): ";
		cin >> gSalary[salary];
		
		//set 2 decimal 
		cout<< fixed << setprecision(2);
		
		//Formula of commission that is truncated
		commission =  (gSalary[salary]*4)/100 + 300 ;
		
		//to terminate of entering the value
		if(gSalary[salary] == -1){
			break;
		}
		//display the value	of commission
		cout << "Employee commission is: $"<<commission<<endl<<endl;
		
		
		//for the condition of commission
		//array countCommission is puts the value in the index then plus 1 if the condition is true 
		if (commission >=300 && commission <=399 ){
			countCommission[0]++;	
		}
		else if (commission >=400 && commission <=499 ){
			countCommission[1]++;
		}
		else if (commission >=500 && commission <=599 ){
			countCommission[2]++;
		}
		else if (commission >=600 && commission <=699 ){
			countCommission[3]++;
		}
		else if (commission >=700 && commission <=799 ){
			countCommission[4]++;
		}
		else if (commission >=800 && commission <=899 ){
			countCommission[5]++;
		}
		else if (commission >=900 && commission <=999 ){
			countCommission[6]++;
		}
		else if (commission >=1000 ){
			countCommission[7]++;
		}
	}
	
	//End of function and return to the main function
}

//Declaring the function of void having the parameter of array in main
void display(int countCommission[]){
	
	//declaring the variable
	int i;
	cout << "Employees in Range: \n";
	
	//Loop the value in array countCommission and display the index of the total commission in their respective range  
	for(i=0;i<10;i++){
	
	cout<< "$300-$399: "<< countCommission[0]<<endl;
	cout<< "$400-$499: "<< countCommission[1]<<endl;
	cout<< "$500-$599: "<< countCommission[2]<<endl;
	cout<< "$600-$699: "<< countCommission[3]<<endl;
	cout<< "$700-$799: "<< countCommission[4]<<endl;
	cout<< "$800-$899: "<< countCommission[5]<<endl;
	cout<< "$900-$999: "<< countCommission[6]<<endl;
	cout<< "over $1000: "<< countCommission[7]<<endl<<endl<<endl;
	break;
	}
	
	//End of function and return to the main function
}



int main (int nNameofArgs, char * pzs[]){
	
	// declaring the array that holds the total count of commission
	int countCommission[10]={0};
	
	//call and return the gross_salary  function and pass the array value to the function 
	gross_salary(countCommission);
	//call and return the display function and pass the array value to the function 
	display(countCommission);
	
	system("pause");
	return 0;
	
	
}
