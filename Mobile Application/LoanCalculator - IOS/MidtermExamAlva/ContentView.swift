//
//  ContentView.swift
//  MidtermExamAlva
//
//  Created by MSEUF CCMS on 10/12/21.
//

import SwiftUI

struct ContentView: View {
    
    //Handle the state variables
    @State var loanType : String = ""
    @State var loanAmount : Int = 0
    @State var duration : Int = 0
    @State var minAmount: Int = 5000
    @State var maxAmount: Int = 1000000
    @State var minDuration: Int = 6
    @State var maxDuration: Int = 60
    @State var displayAlert = false
    @State var interest : Float = 0.0
    @State var totalAmount : Int = 0
    @State var monthlyAmor : Float = 0.00
    @State var doubleDecimalAmor : String = ""
    @State var expand = false
    
    var body: some View {
        
        //Start navigation View
        NavigationView {
            
            Form{
                
                // Conditional Validation
                VStack{
                    if validationInput(){
                        Text("Success !!No error, Valid Inputs ")
                            .foregroundColor(.blue)
                    }
                    else{
                        Text("Error !! Please put valid values")
                            .foregroundColor(.red)
                    }
                }
                // Conditional Validation
                
                //Dropdown Loan Type
                HStack{
                    Text("Choose Loan Type:")
                    Text("\(loanType)")
                        .fontWeight(.bold)
                        .foregroundColor(.black)
                        .padding()
                    Image(systemName: expand ? "chevron.up" : "chevron.down")
                        .resizable()
                        .frame(width: 13, height: 6)
                        .foregroundColor(.black)
                }.onTapGesture {
                    self.expand.toggle()
                }
                if expand{
                    Button(action: {
                        loanType = "Calamity"
                        self.expand.toggle()
                    }) { Text ("Calamity Loan")
                        .padding()
                    }.foregroundColor(.black)
                    Button(action: {
                        loanType = "Personal"
                        self.expand.toggle()
                    }) { Text ("Personal Loan")
                        .padding()
                    }.foregroundColor(.black)
                    
                }
                //End Dropdown Loan Type
                
                //TextField Loan Amount
                VStack(alignment: .leading){
                    Text("Loan Amount:")
                    TextField("Enter Loan Amount", value: $loanAmount, formatter: NumberFormatter())
                        .keyboardType(.numberPad)
                        .border(Color.black)
                        .multilineTextAlignment(.center)
                    
                    Text("Minimum of Php 5,000 - Maximum of Php 1,000,000")
                        .font(.footnote)
                        .foregroundColor(Color.gray)
                        .multilineTextAlignment(.leading)
                    
                }
                //End TextField Loan Amount
                
                //TextField Duration
                VStack(alignment: .leading){
                    Text("Duration In Months:")
                    TextField("EnterDuration", value: $duration, formatter: NumberFormatter())
                        .keyboardType(.numberPad)
                        .border(Color.black)
                        .multilineTextAlignment(.center)
                    Text("Minimum of 6 months to Maximum of 60 months")
                        .font(.footnote)
                        .foregroundColor(Color.gray)
                        .multilineTextAlignment(.leading)
                }
                //End TextField Duration
                
                //Button compute
                Button("Compute"){
                    displayAlert = true
                        
                    if validationInput(){
                            
                        if loanType == "Calamity" {
                            interest = (Float(loanAmount) * 0.01) * Float(duration)
                                
                        }
                        else{
                            interest = (Float(loanAmount) * 0.012) * Float(duration)
                                
                        }
                            
                        totalAmount = loanAmount + Int(interest)
                            
                        monthlyAmor = Float(totalAmount) / Float(duration)
                            
                        doubleDecimalAmor = String(format: "%.2f", monthlyAmor)
                    }
                    else{
                        loanType = "Invalid Type"
                        loanAmount = 0
                        duration = 0
                        interest = 0
                        totalAmount = 0
                        monthlyAmor = 0                    }
                        
                }
                .frame(width: 290, height: 50, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.blue/*@END_MENU_TOKEN@*/)
                .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                .foregroundColor(/*@START_MENU_TOKEN@*/.white/*@END_MENU_TOKEN@*/)
                .border(Color.black)
                .alert(isPresented: $displayAlert, content: {
                    Alert(title: Text("Computed Values"), message: Text("\nLoan Type: \(loanType)\nLoan Anount: Php \(loanAmount).00\nDuration in Months: \(duration) months\nInterest: Php \(Int(interest)).00\nTotal Loan Amount: Php \(totalAmount).00\nMonthly Amorization: Php \(doubleDecimalAmor)"))
                })
                //End Button Compute
                
                //Button Clear
                Button("Clear"){
                    //Access Function Clear
                    displayAlert = true
                    
                    clearInput()
                }
                .frame(width: 290, height: 50, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.blue/*@END_MENU_TOKEN@*/)
                .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                .foregroundColor(/*@START_MENU_TOKEN@*/.white/*@END_MENU_TOKEN@*/)
                .border(Color.black)
                .alert(isPresented: $displayAlert, content: {
                    Alert(title: Text("Clear Fields"), message: Text("\n\nClearing Fields......."))
                })
                //End button Clear
                
            }
            .navigationTitle("Loan Calculator")
            //End Navigation View
        }.padding(.vertical, -33.0)
        
        
        //End Body
        
    }
    //End Content View
    
    // function Validation
    func validationInput()->Bool{
        
        if loanType == ""{
            return false
        }
        else if loanAmount < minAmount || loanAmount > maxAmount{
            return false
        }
        else if duration < minDuration || duration > maxDuration {
            return false
        }
        else{
            return true
        }
    }
    // function Clear Input
    func clearInput(){
        loanType = ""
        loanAmount = 0
        duration = 0
        interest = 0
        totalAmount = 0
        monthlyAmor = 0
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


