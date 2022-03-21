//
//  MovieViewModel.swift
//  FinalProject
//
//  Created by MSEUF CCMS on 12/10/21.
//

import Foundation
import CoreData

class MovieViewModel : ObservableObject {
    @Published var movies: [MovieEntity] = []
        let container: NSPersistentContainer
        
        init() {
            self.container = NSPersistentContainer(name: "MovieData")
            self.container.loadPersistentStores { description, error in
                if let error = error {
                    print("An error occured. \(error)")
                }
            }
            fetchMovies()
        }
        
        func fetchMovies() {
            let request = NSFetchRequest<MovieEntity>(entityName: "MovieEntity")
            
            do {
                movies = try container.viewContext.fetch(request)
            } catch let error {
                print("An error occured. \(error)")
            }
            
        }
    
        func addMovies(movies: Movies)
            {
                let newMovie = MovieEntity(context: container.viewContext)
                
                newMovie.title = movies.title
                newMovie.date = movies.date
                newMovie.descriptions = movies.descriptions
                newMovie.director = movies.director
                    
                saveChanges()
                
            }
        func updateMovie(){
           saveChanges()
        }
    
        func deleteMovie(indexSet: IndexSet) {
            
            guard let index = indexSet.first else { return }
            let entity = movies[index]
            
            container.viewContext.delete(entity)
            
            saveChanges()
        }
    
        func saveChanges() {
            do {
                try container.viewContext.save()
                fetchMovies()
            } catch let error {
                print("An error occured. \(error)")
            }
        }
        
      
}

