//
// Program that compute the Netpay
//A program that accept the three values (hourly pay rate, hours work, and the gross salary)

#include <cstdlib>
#include <cstdio>
#include <iostream>

using namespace std;

int main(int nNAmeofArgs, char * pszArgs[]){
	
	//declaring the variables
	int hPay, hWorked ;
	float grossSallary, gPay, wholdingAmount, netPay;
	
	//Input the value Hours pay
	cout << "\nEnter the value of Hourly Pay Rate: ";
	cin >> hPay;
	
	//Input the value Hours Worked
	cout << "\nEnter the value of Hours Worked: ";
	cin >> hWorked;
	
	//Input the value Gross Sallary
	cout << "\nEnter the value of Gross salary in percentage: ";
	cin >> grossSallary;
	
	cout << "\n............................................................... ";
	
	//resut and computation of the gross pay
	gPay = hPay * hWorked;
	cout << "\nThe Gross pay is "<<gPay <<endl;
	
	//result and computation of the with holding amount
	wholdingAmount =(grossSallary / 100  ) *gPay;
	cout << "\nThe with holding amount is "<<wholdingAmount<<"," <<endl;
	
	//result and computation of the netpay
	netPay = gPay - wholdingAmount;
	cout << "\nTherefore, the Computed Netpay after taxes is "<<netPay <<endl<<endl<<endl;
	
	system("pause");
	return 0;
}	
