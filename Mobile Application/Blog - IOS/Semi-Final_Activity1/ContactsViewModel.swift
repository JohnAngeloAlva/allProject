//
//  ContactsViewModel.swift
//  Semi-Final_Activity1
//
//  Created by MSEUF CCMS on 11/8/21.
//

import Foundation

class ContactsViewModel : ObservableObject {
    @Published var contact: [Contact] = ContactListData.data

}

struct ContactListData {
    static let data = [
        
        Contact(name: "John Alva", phoneNumber: "092656315651", email: "ja@mseuf.edu.ph", address: "Quezon Province, Lucena City, Brgy. Ibabang Dupay", course: "Bachelor of Science in Information Technology", school: "Manuel S. Enverga     University Foundation"),
        Contact(name: "Angelo John", phoneNumber: "092656315651", email: "aj@mseuf.edu.ph", address: "Quezon Province, Lucena City, Brgy. Ibabang Dupay", course: "Bachelor of Science in Information Technology", school: "Manuel S. Enverga     University Foundation"),
        Contact(name: "Alva Angelo", phoneNumber: "092656315651", email: "aa@mseuf.edu.ph", address: "Quezon Province, Lucena City, Brgy. Ibabang Dupay", course: "Bachelor of Science in Information Technology", school: "Manuel S. Enverga     University Foundation")
    ]
}
