var ds= new DataService();


$(document).ready(function(){
    
    
    ds.loadItems(getSuccess,errors);
    //$(document).on("submit", "#make-purchase", makePurchase);

    var money = 0;
    $("#add-dollar-button").on("click", function(){
        money++;
        $("#add-money").val(money);
    })

    $("#add-quarter-button").on("click", function(){
        money= money+.25;
        $("#add-money").val(money);
    })

    $("#add-dime-button").on("click", function(){
        money= money+.10;
        $("#add-money").val(money);
    })

    $("#add-nickle-button").on("click", function(){
        money= money+ .05;
        //money=money.toFixed(2);
        $("#add-money").val(money);
    })


    
    $("#make-purchase").click(function(){
        var amount = $("#add-money").val(); 
        var itemId = $("#item-id").val();
        
        ds.makePurchase(amount, itemId, displayChange, errors);
        //$('#change').val(str);
        let itemTable = $("#itemTable>tbody");
        itemTable.empty();
        ds.loadItems(getSuccess,errors);
        $('#add-money-form')[0].reset();
        
        
        
    });
   

});

function displayChange(callBack){
    //console.log(callBack);

    var change= "";
    if (callBack.quarters>0) change +="Quarters: "+callBack.quarters;
    if (callBack.dimes>0) change +=" Dimes: "+callBack.dimes;
    if (callBack.nickels>0) change +=" Nickels: "+ callBack.nickels;
    if (callBack.pennies>0) change+= " Pennies: "+ callBack.pennies;

    $('#change').val(change);
    $('#messages').val("Thank You!");
}

function errors(errorFunc){
    
    $('#messages').val(errorFunc.responseJSON.message);
  
}

function getSuccess(itemArray){
    var itemRows = $('#itemRows');
    for(let i=0; i<3;i++){ 
        var row= '<div class="row">';
       
         for (let j=(i%3)*3; j<(i%3)*3+3; j++){
             var item = itemArray[j];

             var itemId= item.id;
             var name = item.name;
             var price=item.price;
             var quantity= item.quantity;

             
                 row+='<div class="card" sytle="width:200px"> <div class="card-body">';
                 row+= '<a href="#" onClick="$(item-id.val(5))" id='+item.id+' class="btn btn-light">'+itemId+'</a><br>';
                 row+= '<h4 class="card-title">'+name+'</h4>';
                 row+='<p class="card-text"> $'+price+ '<br>Quantity: '+ quantity+'</p></div></div>';
                 //row+= '<a href="#" class="btn btn-light">'+itemId+'</a>';

             }     
             
         
         row+='</div>'
         itemRows.append(row);
     }
         
 }





