//
//  MovieDetailView.swift
//  FinalProject
//
//  Created by MSEUF CCMS on 12/10/21.
//

import SwiftUI


struct AddMovieView: View {
    
    @Environment(\.presentationMode) var presentation
    @ObservedObject var movieViewModel = MovieViewModel()
    
    @State var title: String = ""
    @State var date: String = ""
    @State var description: String  = ""
    @State var director: String = ""
    
    var body: some View {
        MovieDetailView(function: {
            
            movieViewModel.addMovies(movies: Movies(title: title, date: date, descriptions: description, director: director))
            self.presentation.wrappedValue.dismiss()
        }, title: $title, date: $date, description: $description, director: $director)
        
    }
}

struct UpdateContactView: View {
    
    @Environment(\.presentationMode) var presentation
    @ObservedObject var movieViewModel = MovieViewModel()
    
    @State var title: String
    @State var date: String
    @State var description: String
    @State var director: String
    
    var movieEntity: MovieEntity
    
    var body: some View {
        MovieDetailView(function: {
            update()
        }, title: $title, date: $date, description: $description, director: $director)
    }
    
    func update() {
        movieEntity.title = title
        movieEntity.date = date
        movieEntity.descriptions = description
        movieEntity.director = director
        
        movieViewModel.updateMovie()
        self.presentation.wrappedValue.dismiss()
    }
}
struct MovieDetailView: View {
    
        var function: () -> Void
    
        @Binding var title: String
        @Binding var date: String
        @Binding var description: String
        @Binding var director: String
    
        
        var body: some View {
            NavigationView {
                VStack(alignment: .leading) {
                    Text("Title")
                        .font(.callout)
                        .bold()
                    TextField("",text: $title)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                    Text("Released Date")
                        .font(.callout)
                        .bold()
                    TextField("",text: $date)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                    Text("Description")
                        .font(.callout)
                        .bold()
                    TextField("",text: $description)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                    Text("Director")
                        .font(.callout)
                        .bold()
                    TextField("",text: $director)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                    Button("Save") {
                        self.function()
                    }
                    Spacer()
                }
                .padding()
                .navigationTitle("Add Movie Form")
            }
        }
}

struct MovieDetailView_Previews: PreviewProvider {
    static var previews: some View {
        AddMovieView()
    }
}
