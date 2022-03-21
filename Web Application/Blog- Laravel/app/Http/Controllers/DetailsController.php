<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class DetailsController extends Controller
{
    //Detail Function list of foods

    public function detailsList(){

        $foods = [
            [
                'id' => '1',
                'image' => 'image/adobo.jpg',
                'name' => 'Pork Adobo', 
                'description' =>'Philippine adobo is a popular Filipino dish and cooking process in Philippine 
                                cuisine that involves meat, seafood, or vegetables marinated in vinegar, soy sauce,
                                garlic, bay leaves, and black peppercorns, which is browned in oil, and simmered in 
                                the marinade',
            ],

            [
                'id' => '2',
                'image' => 'image/bicol.jpg',
                'name' => 'Bicol Express', 
                'description' =>'Bicol express is a spicy Filipino stew consisting of bite sized pieces of 
                                pork with chilies, coconut milk, shrimp paste, onions, tomato and garlic. 
                                The dish is named after the train in the Bicol region of the Philippines, known for its spicy food.',
            ],

            [
                'id' => '3',
                'image' => 'image/kaldereta.jpg',
                'name' => 'Kaldereta or caldereta', 
                'description' =>'Kaldereta or caldereta is a goat meat stew from the Philippines. 
                                Commonly, the goat meat is stewed with vegetables and liver paste. Vegetables may include 
                                tomatoes, potatoes, olives, bell peppers, and hot peppers. Kaldereta sometimes includes 
                                tomato sauce.',
            ],

            [
                'id' => '4',
                'image' => 'image/wings.jpg',
                'name' => 'Buffalo Wings', 
                'description' =>'Buffalo wing, also called hot wing, chicken wing, or wing, deep-fried 
                                unbreaded chicken wings or drums coated with a vinegar-and-cayenne-pepper hot sauce mixed 
                                with butter. They commonly are served with celery and a blue cheese dipping sauce, which acts 
                                as a cooling agent for the mouth.',
            ],

            [
                'id' => '5',
                'image' => 'image/giniling.jpg',
                'name' => 'Pork Giniling', 
                'description' =>'Pork Giniling is a pork dish that makes use of ground pork as the primary 
                                ingredient. Ground pork is stewed in tomato sauce and water to bring out the taste while vegetables
                                such as carrots and potatoes (some also like this with raisins and green peas) are added for 
                                additional flavor and nutrition.',
            ],

            [
                'id' => '6',
                'image' => 'image/sisig.jpg',
                'name' => 'Sisig', 
                'description' =>'Along with Adobo, Sisig is another famous Filipino dish known around 
                                the world. This dish contains cut up pig’s ears, snout, cheeks, and belly sauteed with garlic, 
                                onion, green chilies, and vinegar and is served on a sizzling plate. There are so many 
                                different ways to cook Sisig, but the process is always the same: boiling, grilling, and 
                                finally frying the meat to produce those crunchy pieces.',
            ],

            [
                'id' => '7',
                'image' => 'image/chopsuey.jpg',
                'name' => 'Chopsuey', 
                'description' =>'Chopsuey , the Filipino stir fried vegetables loaded with chayote, carrots, broccoli, 
                                cauliflower, mushrooms and different meats like pork and chicken.',
            ],
            
            [
                'id' => '8',
                'image' => 'image/curry.jpg',
                'name' => 'Chicken Curry', 
                'description' =>'This Pinoy Chicken Curry recipe is the Filipino version of the popular chicken curry dish. It is delicious and easy to cook. 
                                The sauce is rich, creamy, and flavorful. It goes well with warm white rice. Filipino Style Chicken Curry is a type of 
                                chicken curry cooked with potatoes, carrots and capsicum in coconut milk with mild curry powder.',
            ],

            [
                'id' => '9',
                'image' => 'image/tinola.jpg',
                'name' => 'Tinolang Manok', 
                'description' =>'Tinolang Manok or Chicken Tinola is a classic comfort food made by sautéing 
                                chicken pieces with garlic, ginger, onion, and fish sauce and then simmering it in chicken 
                                stock for about an hour. It is traditionally cooked with green papaya, which can be substituted with sayote.',
            ],

            [
                'id' => '10',
                'image' => 'image/dinuguan.jpg',
                'name' => 'Dinuguan', 
                'description' =>'Dinuguan comes from the word “dugo”, which translates as blood, and is a 
                                savory Filipino pork blood stew made of diced pork, vinegar, pork blood, and chili peppers. 
                                Some people like using pork offal such as intestines or other pork body parts, although this 
                                is an acquired taste. Dinuguan is savory but not heavily salty and usually has a sour note. 
                                The texture varies widely from a fairly thin soup to a thick, dark grainy stew. This special 
                                dish is usually served at weddings, fiestas, and in Lechon houses.',
            ],

        ];

        $data = [

            'foods' => $foods
        
        ];

        return view ('details', $data);
    }

    //End Detail list of foods
}
