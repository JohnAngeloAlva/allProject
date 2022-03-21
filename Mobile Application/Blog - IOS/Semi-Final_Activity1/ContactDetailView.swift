//
//  ContactDetailView.swift
//  Semi-Final_Activity1
//
//  Created by MSEUF CCMS on 11/8/21.
//

import SwiftUI

struct ContactDetailView: View {
    
    var contact : Contact
    
    var body: some View {
        
        //Main Container
        VStack (alignment : .center){
            HStack{
                Image(systemName: "person.crop.circle")
                    .resizable()
                    .frame(width: 130, height: 130)
                VStack{
                    Text(contact.name)
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    Text(contact.email)
                        .fontWeight(.medium)
                        .foregroundColor(Color.white)
                    Text(contact.phoneNumber)
                        .fontWeight(.regular)
                        .foregroundColor(Color.white)
                }
            }
            .padding(/*@START_MENU_TOKEN@*/.vertical, 35.0/*@END_MENU_TOKEN@*/)
            .frame(
                maxWidth:  .infinity
            ).background(Color.gray)
            
            // Location Container
            HStack{
                Image(systemName: "location")
                    .resizable()
                    .scaledToFit()
                    .frame(height : 50)
                    Text(contact.address)
                        .multilineTextAlignment(.center)
                
            }.padding(/*@START_MENU_TOKEN@*/.all/*@END_MENU_TOKEN@*/)
            
            //Course Container
            HStack{
                Image(systemName: "graduationcap")
                    .resizable()
                    .scaledToFit()
                    .frame(height : 50)
                    Text(contact.course)
                        .multilineTextAlignment(.center)
                
            }.padding(/*@START_MENU_TOKEN@*/.all/*@END_MENU_TOKEN@*/)
            
            
            //School Container
            HStack{
                Image(systemName: "building.columns")
                    .resizable()
                    .scaledToFit()
                    .frame(height : 50)
                    Text(contact.school)
                        .multilineTextAlignment(.center)
                
            }.padding(/*@START_MENU_TOKEN@*/.all/*@END_MENU_TOKEN@*/)
            
        }
        .padding(.bottom)
        //End Main Container
    }
}


struct ContactDetailView_Previews: PreviewProvider {
    static var previews: some View {
        ContactDetailView(contact: ContactListData.data.first!)
    }
}
