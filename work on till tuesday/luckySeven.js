function rollDice(numSides){
  return Math.floor(Math.random()*numSides)+1;
}


function game() {
var initialBet = -1;
var money;
var dice1;
var dice2;
var diceSum;
var rollsNum = 0; //number of rolls before going broke
var highestAmount; // highest amount run is number of time diceSum*4
var rollCountAtHighest = 0; // roll count at highest amount won

  while(initialBet<=0){
    initialBet = prompt("what is your starting bet?(must be greater than 0)");
   }
  money=initialBet;
  highestAmount = initialBet;



  while(money>0){
   dice1 = rollDice(6);
   dice2 = rollDice(6);
   diceSum = dice1 + dice2;
   if(diceSum == 7){
     money=money + 4;

   }else{
     money = money-1;
   }

   rollsNum++;

   if(money>highestAmount){
     highestAmount = money;
     rollCountAtHighest = rollsNum;


  }

// document.getElementById("initialBet").style.display = initialBet;
// document.getElementById("rollsNum").style.display = rollsNum;
// document.getElementById("highestAmount").style.display = highestAmount;
// document.getElementById("rollCountAtHighest").style.display = rollCountAtHighest;




}
game();

console.log(initialBet);
console.log(rollsNum);
console.log(highestAmount);
console.log(rollCountAtHighest);
}
