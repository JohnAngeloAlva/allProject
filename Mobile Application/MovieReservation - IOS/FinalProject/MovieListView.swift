//
//  ContentView.swift
//  FinalProject
//
//  Created by MSEUF CCMS on 12/10/21.
//

import SwiftUI

struct ContentView: View {
    
    @StateObject var movieViewModel = MovieViewModel()
        
        var body: some View {
            
            NavigationView {
                List {
                    ForEach(movieViewModel.movies) { movie in
                        
                        let movieView = Movies(title: movie.title ?? "", date: movie.date ?? "", descriptions: movie.descriptions ?? "", director: movie.director ?? "")
                        
                        NavigationLink(destination: Text(""),
                                       label: {
                                            MovieRow(movie : movieView)
                                       }
                            
                        )
                    }
                }.navigationTitle("Admin Movie Lists")
                .toolbar {
                    NavigationLink(
                        destination: AddMovieView(movieViewModel: movieViewModel),
                        label: {
                            Text("Add Movie")
                            Image(systemName: "memories.badge.plus")
                        })
                }
            }

        }
    }

    struct MovieRow: View {
        
        let movie: Movies
        
        var body: some View {
            HStack {
                Image(systemName: "person.crop.circle")
                    .resizable()
                    .scaledToFit()
                    .frame(height: 50)
                
                VStack(alignment: .leading) {
                    Text(movie.title)
                        .fontWeight(.semibold)
                    Text(movie.descriptions)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                    Text(movie.director)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                    Text(movie.date)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                }
            }
        }
    }

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
