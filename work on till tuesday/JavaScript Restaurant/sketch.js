/*
Code In The Dark Challenge "Birthday Edition".
By Professor D. March 2019.
*/


// 1. Run the program and examine what happens.
// 2. Examine the starter code and try to determine how it works.
// 3. Use course resources to do so.
// 4. Make a copy of the original code folder and rename it "yourName_MAW_CITDC".
// 5. Place a copy of the orginal program folder and your modified
// version in your Dropbox folder. You should save work in the P5 online editor.
// 6. Follow the comment prompts in the original program for challenges. Try to
// answer them all.
// 7. Create a detailed blog post that walks through your process and final/modified code.
// 8. Try to complete the bonuses.
// Document progress in a well-crafted blog post that uses
// supporting media.
// 9. Have fun!

// Warning: Bonus Section below. Try the initial challenges, first.

/***** Bonus Challenge #1:
Make the sketch reset without using the mouse or keyboard.
*****/

/***** Bonus Challenge #2:
Replace the static image with a programmatically coded representation.
In other words, create the image with code only. Make it reusable and modular.
*****/

/***** Bonus Challenge #3:
Program the static image to have only the flames go out and light up with non-keyboard/mouse inputs.
*****/

// Document your work! ; D

// The img variable is used to call the image stored in the asset folder. The mic variable is used to use
// the microphone. the scope of the variables are global made before the anything else. advantages include
// simple to setup and making it easy to access anywhere in the code. disadvantage uses memory.
let img;
let mic;

//The function is preload so it is put before the setup.
//An alternative is to put it in the setup and use a callback to display the image after the loading.
function preload() {
  // Image source/credit: https://www.dgreetings.com/birthday/birthday-candles.html
  img = loadImage("/assets/birthdaycandles1123.jpeg");
}

// mic = new p5.AudioIn(); creates an audio input. mic.start(); start the audio input
function setup() {
  createCanvas(600, 400);
  mic = new p5.AudioIn();
  mic.start();
}

function draw() {
  let micLevel = mic.getLevel();
  // Could maybe use a object or an array
  let w = "Don't forget to make a wish when you blow out the candles!";
  image(img, 0, 0, width, height);
  stroke(255, 0, 0);
  strokeWeight(2);
  fill(255, 0, 0);
  textSize(20);
  textFont('Indie Flower');
  text(w, 50, 50);

  //If the sound is louder than .7 in the mic turn the background black and print the message in the console.
  if (micLevel > 0.7) {
    mic.stop();
    background(0);
    console.log('Who turned out the lights???');
    noLoop();
  }
}

//could maybe put the loop without needing an action so someone who can't read the code doesn't need to understand?
function mousePressed() {
  loop();
}
