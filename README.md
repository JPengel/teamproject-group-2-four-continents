# 55starterfiles
Starter Project for Comp 55

You will use this project as your base.
Make sure that you understand the two files provided here

## Feature Overview
![](media/55GroupProjectUML.jpg)
Making it convenient to add new ingredients to ITALIAN CHEF.

At the moment, if some developer wants to add new ingredients to the game, they would need to write new code in every single file in the program. The idea is to change methods/classes in order to make the experience of adding new ingredients to the game more convenient. After the changes, only to lines of code will need to be written, facilitating the process of adding ingredients to the game for both new developers and people with no experience at coding.

## Pseudocode 
![](media/55GroupProjectSequenceDiagram.png)

ToppyingType.java

        create a static array that maintains an ordered collection of sound files names, matching the enum class order
        create a static array of size equal to the number of ingredients, that maintains an ordered collection of integers/counters, matching the enum class order 
        
        
 /* This method receives a integer as input and matches the input with the ingredients names based on their enum position */
 
        public static ToppingType TypeName(int i)
          "n" case matches with ingredient on "n" enum position and returns it
          if case is not matched, by default return rock
        
  
/* This method simply initializes the array thats keeps track of how many times each ingredient type was sliced based on the ingredients enum position */

        public static void initArray()
          create new array that has same size as the sound file array
          initialize each element of the array to zero
  
  
  
 Mode.java
 
/* This method based on a preset probability, first selects if a topping, hazard or upgrade is going to be generated and tossed into the screen. After, another random generator selects what exact type of topping/hazard/upgrade is going to be generated and displayed on the screen. */

        public void generateObject()
          if timer is not running returnnothing
          
          randomly picks a number between 1-100
          if number is < 71
            randomly choose between 0-topping type count array
            generate an ingredient based on the chosen number and its enum position
            add topping to the topping array

          if number is > 70 and < 86
            randomly selects either can or pineapple
            add hazard to the topping array

          if number is > 85
            if current game mode = timer Mode
              if no rock was cut
                randomly selects either clock or rock
                add the upgrade to the topping array
              if rock was cut and chosen number is even
                generate a clock 
                add clock to the topping array
            if not in Timer mode
              if the chosen number is even and no rock was cut
                generate a rock 
                add rock to the topping array
        
        
  
  
/* This method takes care of the mouse drag and its functionalities. It is responsible for gathering/updating x and y coordinates that are later used to draw a combo line. Besides that, this method also checks to see if ingredients are being cut by checking if the cursor's coordinates overlap any of the ingredient’s coordinates */
 
        public void mouseDragged(MouseEvent e)
          if the game is paused return
          if not paused
            set previous combo x and y coordinates to new combo x and y coordinates
            if coordinates counter = 5
              set new combo x and y coordinates to current x and y coordinates of the cursor
              set coordinates counter to 0

            increment coordinates counter 
            if the topping array is not empty
              iterate through the array 
              check if the cursor's coordinates match with any ingredients that were not cut dimensions/coordinates
                if the coordinates match, cut the topping and perform their effects
                if topping type enum position <= topping type count array size
                  set combo entry x and y to current x and y coordinates of the cursor
                  check for combo
        
        
/* This method checks which ingredient/hazard/upgrade was cut and performs their effect accordingly. This method is responsible for playing the ingredient's audio, adding labels, and incrementing the integers in the topping array list as the toppings are cut */

         public void checkForEffects(Topping i)
          if the ingredient cut was pineapple
            play pineapple sound
            if the current game mode is Timer Mode
              decrease 10 seconds from the game timer
              set the pineapple label location and add it to the screen
            if not Timer Mode
              stop the timer and declare game over

          if the ingredient cut was a clock
            play clock sound
            add 5 seconds to the timer
            set clock label and add it to the screen

          if the ingredient cut was a rock
            play rock sound
            start rock timer
            set rock label and add it to the screen

          if the ingredient cut was a can
            play can sound
            add splash images to the screen

          play audio of topping from topping type audio array 
          increment by one the integer counter of ingredients cut on topping type count array
          increment the game score counter by 1
          if rock was cut
            increment the game score counter by 1

          update game score counter label
  
  
  
GameOver.java

/* This method is responsible for receiving information of how many of each topping was cut and randomly placing images of the cut topping inside the game over pizza that is later displayed to the user showing all the ingredients that were sliced during the round */

        private void addToppings()
          create a vector of pairs that store ingredient type and how many times it was sliced

          while the vector of pair size is not zero
            randomly generate a number from 0-size of topping type count array
            randomly generate coordinated to place the images inside the pizza
            add an image to the pizza using the random generated number and coordinates
            decrease the topping counter by one
            if topping counter = 0
              delete vector index
            
         Set numbers from the Topping Type sliced array to zero once again
  
  
/* This method generates a vector of pairs. The key of the pair is the topping type and the value is the topping type slice count */
        typeCountVectorGenerator()
          create a vector of pairs

          for topping type count array size -1 times
            create a new par with the key being topping type and the value being how many times this toppin type was sliced
            if the value is different from zero
                add pair to the vector of pairs
                

/* This methos is resposible for adding/displaying the slied toppings image on the game over pizza. It receives a integer and a pair of coordinates as input and with these information it sets the location of the sliced ingredient image in the game over screen*/

        public void addToppingIMG(int i, Pair<Double, Double> coords)
          create a new image that follow a path based on the Topping Type name and position in the vector of pairs
          set image location using the coords passed by reference
          add images to the screen
          add image to the array that keeps all sliced Toppings images that are on the screen
          


## Feature Demonstration
After implementing the proposed feature, new developers and even people who never coded before can easily add new toppings to the game. Before this feature
 was applied, to add new ingredients to the game, every single file would need to be modified and a lot of repetitive code would need to be written. However, adding one or two words in a single is all it takes to add new elements to the game after the new feature was implemented.

As seen in the first screen shot, there are only four spots that need to be changes when adding a new topping to the game and all these changes occur inside ToppingType.java. Circled in red are all the changes I needed to perform in order to add tomatos to the game besides adding the images and sounds to their respective folders (shown on second screen shot).

<img width="1470" alt="Screen Shot 2023-01-30 at 6 38 52 PM" src="https://user-images.githubusercontent.com/114448502/215652556-d7e87948-ff2b-41c5-9208-e5d7f5d44c4d.png">

<img width="369" alt="Screen Shot 2023-01-30 at 6 28 13 PM" src="https://user-images.githubusercontent.com/114448502/215653394-b40edb63-cc6c-41b7-8368-dcc07065243f.png">

The following screenshots, shows that tomatos were added to the game and that each of their images (tomato, cut tomato and sliced tomato) appear during different stages of the game. 


<img width="957" alt="Screen Shot 2023-01-30 at 6 40 19 PM" src="https://user-images.githubusercontent.com/114448502/215653586-e410e2fb-612d-494b-b15e-7c4f7e3d0ee5.png">


<img width="833" alt="Screen Shot 2023-01-30 at 6 41 59 PM" src="https://user-images.githubusercontent.com/114448502/215653618-49c1d676-5adf-48e0-aec1-f4c7ec448d89.png">


<img width="961" alt="Screen Shot 2023-01-30 at 6 32 17 PM" src="https://user-images.githubusercontent.com/114448502/215653650-54b59347-96ee-40d0-94d2-4a8230fc9d9a.png">


<img width="958" alt="Screen Shot 2023-01-30 at 6 41 46 PM" src="https://user-images.githubusercontent.com/114448502/215653683-947bf6a8-8f12-47d0-9861-19507f9302ab.png">
