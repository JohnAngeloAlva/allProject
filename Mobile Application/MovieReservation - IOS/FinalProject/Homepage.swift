//
//  Homepage.swift
//  FinalProject
//
//  Created by MSEUF CCMS on 12/10/21.
//


import SwiftUI

struct Homepage: View {
    var body: some View {
        NavigationView{
            
                VStack{
                    NavigationLink(
                        destination: ContentView(),
                        label: {
                            Text("Admin Page").padding()
                            Image(systemName: "person.crop.circle")
                        })
                    
                    NavigationLink(
                        destination: ContentView(),
                        label: {
                            Text("User Page").padding()
                            Image(systemName: "person.crop.circle")
                        })
                }
                
                
                
            .navigationTitle("Homepage")
            
        }
       
    }
}

struct Homepage_Previews: PreviewProvider {
    static var previews: some View {
        Homepage()
    }
}
