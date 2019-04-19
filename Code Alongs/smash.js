


function rollDice(numSides){
  return Math.floor(Math.random()*numSides)+1;
}

for (var i =0; i<10; i++){
  document.write(rollDice(6));
}

prompt("How much would you like to invest");
