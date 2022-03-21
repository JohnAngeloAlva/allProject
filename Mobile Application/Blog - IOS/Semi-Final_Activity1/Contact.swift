//
//  File.swift
//  Semi-Final_Activity1
//
//  Created by MSEUF CCMS on 11/8/21.
//

import Foundation

struct Contact : Identifiable {
    let id = UUID()
    let name : String
    let phoneNumber : String
    let email : String
    let address :String
    let course: String
    let school : String
}
