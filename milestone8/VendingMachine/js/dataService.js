var DataService =function(){
    var self=this;
    self.loadItems = function(callback, myErrorFunc){
       

     $.ajax({
         type: 'GET',
         url: 'http://localhost:8080/items',
         success: callback, 
         error: myErrorFunc
      
     });
    }

    
    self.makePurchase = function (amount, itemId, callback, myErrorFunc){

    $.ajax({
        method: 'GET',
        url: 'http://localhost:8080/money/'+ amount+ '/item/' +itemId,
        success: callback,
        error: myErrorFunc
        
    });

    
}


}

//callback is a function you want to run when ajax call was successful. you can define
//the function in home.js and pass it in as a parameter.