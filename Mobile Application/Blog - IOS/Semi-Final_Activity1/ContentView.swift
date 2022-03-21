//
//  ContentView.swift
//  Semi-Final_Activity1
//
//  Created by MSEUF CCMS on 11/8/21.
//

import SwiftUI

struct ContentView: View {
    
    @StateObject var contactsViewModel = ContactsViewModel()
    
    var body: some View {
        
        //Start Navigation View
        NavigationView {
            List {
                ForEach(contactsViewModel.contact) {contact in
                    NavigationLink(
                        destination: ContactDetailView(contact: contact)){
                            ContactRow(contact:contact)
                            
                        }
                }
            }
            .navigationTitle("Contact")
        }
        //End Navigation View
    }
}

//Start Contact Row
struct ContactRow : View {
    
    let contact : Contact
    
    var body: some View {
        
        HStack {
            Image (systemName: "person.crop.circle.fill")
                .resizable()
                .scaledToFit()
                .frame(height : 50)
                
            VStack (alignment : .leading){
                Text(contact.name)
                    .fontWeight(.semibold)
                Text(contact.phoneNumber)
                    .font(.subheadline)
                    .foregroundColor(.secondary)
                Text(contact.email)
                    .font(.subheadline)
                    .foregroundColor(.secondary)
                
            }
        }
    }
}
//End Contact Row

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
