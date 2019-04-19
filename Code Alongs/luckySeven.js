/*Math.random()*6;
for (var i=0; i<100;i++){
  console.log(Math.floor(Math.random()*6)+1);
}
*/
//DICE ROLL
function rollDice(numSides){
  return Math.floor(Math.random()*numSides)+1;
}

for (var i =0; i<10; i++){
  document.write(rollDice(6));
}
