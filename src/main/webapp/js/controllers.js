
app.controller("LoginController", function($scope, $rootScope, AUTH_EVENTS,
		AuthService) {

	$scope.credentials = {
		username : '',
		password : ''
	};
	$scope.login = function(credentials) {
		AuthService.login(credentials).then(function(user) {
			$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
			$scope.setCurrentUser(user);
		}, function() {
			$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
		});
	};

}

);


app.controller("Products", ['$scope','$http','$cookies', function($scope, $http, $cookies) {
	
	
	//initalize type to earings. Not sure why its not working	
	$scope.filter = {type:'ER'};
	
	$scope.filter.size = {small:true, medium:true, large:true};
	
	$scope.filter.price = {low:true, med:true, high:true};
	
	
	
	// Called by filter hyper links, to set filter type which can enable corresponding radio buttons  
	$scope.setFilterType = function (productType){
				
		$scope.filter.type = productType;
		getProducts();
		
	}
	
	// Will be called when the Type radio buttons are enabled
	 $scope.$watch('filter.type', function(oldValue, newValue) {
		
		 if(oldValue != newValue)
			 getProducts();
		 
	  });
	
	// Will be called when the Size Check boxs are enabled
	 $scope.$watch('filter.size.small', function(oldValue, newValue) {

		 if(oldValue != newValue)
			 getProducts();
		 
	  });

  	// Will be called when the Size Check boxs are enabled
	 $scope.$watch('filter.size.medium', function(oldValue, newValue) {

		 if(oldValue != newValue)
			 getProducts();
		 
	  });

  	// Will be called when the Size Check boxs are enabled
	 $scope.$watch('filter.size.large', function(oldValue, newValue) {
		 if(oldValue != newValue)
			 getProducts();
		 
	  });
	 
	 
	// Will be called when the Size Check boxs are enabled
	 $scope.$watch('filter.price.low', function(oldValue, newValue) {

		 if(oldValue != newValue)
			 getProducts();
		 
	  });

  	// Will be called when the Size Check boxs are enabled
	 $scope.$watch('filter.price.med', function(oldValue, newValue) {

		 if(oldValue != newValue)
			 getProducts();
		 
	  });

  	// Will be called when the Size Check boxs are enabled
	 $scope.$watch('filter.price.high', function(oldValue, newValue) {
		 if(oldValue != newValue)
			 getProducts();
		 
	  });
	 
	 
	 function getProducts(){
		 
		 
		 var queryString = "";
		 var productType = $scope.filter.type;
		 
		 if (productType == undefined) {
			 productType = "";
		 }
		 queryString = productType;
		 
		 var sizeSmall = $scope.filter.size.small;
		 var sizeMedium = $scope.filter.size.medium;
		 var sizeLarge = $scope.filter.size.large;
		 
		 var size = "";
		 if (sizeSmall == true){
			 size = "size=S"
		 }
		 if (sizeMedium == true){
			 if(size != "")
				 size = size + "&size=M";
			 else 
				 size = "size=M";
		 }
		 if (sizeLarge == true){
			 if(size != "")
				 size = size + "&size=L"
			 else 
				 size = "size=L";
		 }
		 
		 if (queryString != "" && size != ""){
			 size = "&" + size;
		 }
		 
		 queryString = queryString + size;
		 
		 var priceDis = $scope.filter.price.dis;
		 var priceLow = $scope.filter.price.low;
		 var priceMed = $scope.filter.price.med;
		 var priceHigh = $scope.filter.price.high;
		 
		 
		 var price = "";
		 
		 if (priceDis == true){
			 price = "price=0"
		 }
		 
		 if (priceLow == true){
			 if(price != "")
				 price = price + "&price=1";
			 else 
				 price = "price=1";
		 }
		 
		 if (priceMed == true){
			 if(price != "")
				 price = price + "&price=2";
			 else 
				 price = "price=2";
		 }
		 if (priceHigh == true){
			 if(price != "")
				 price = price + "&price=3"
			 else 
				 price = "price=3";
		 }
		 
		 if ( queryString != "" && price != ""){
			 
			 price = "&" + price;
		 }
		 
		 queryString = queryString + price;
		 
		 
		 $http.get('/rest/products/filter?type=' + queryString).success(function(data) {
				$scope.products = data;
					
			})

	 }
	 
	// 	This is to allow hyperlicks to dynamically highlight the right link
	$scope.isActive = function (viewLocation) {
	     return $scope.filter.type == viewLocation;
	     
	};
	
	
	 

	/*********************************** Shopping Cart Functionality ******************************************/
	
	// Initialise Cart
	
	// Check for cookies
	var cart ;
		
	try{
		cart = $cookies.getObject('myCart');
	}catch(e){
		// No cookie found

	}

	if (cart == undefined){
		
		cart = {};  
		cart.items = [];
		cart.totalPrice = 0.00;
		
	}

	var idList = "";
	
	// Build querystring parameter for ids in Cart
	for (i = 0 ; i < cart.items.length; i++) { 

		if(i == 0){
			idList = cart.items[i].product.id;
			
		}else{
			idList = idList + "," + cart.items[i].product.id;
		}
		
	}

	// Get List of updated list of Products for the items in the cart. This is to make sure prices are displayed upto date
	/*For localhost use /rest/dress*/
	$http.get('/rest/products/filter?id=' + idList).success(function(data) {
		var products = data;
		
		if (cart != null){
			//loop through and update data in the Cart
			for (i = 0 ; i < cart.items.length; i++) { 
				for (j = 0 ; j < products.length; j++) { 
					if (cart.items[i].product.id == products[j].id){
						cart.items[i].product = products[j];
						
					}
				}
			}
		
		}
		
		$scope.cart = cart;	
		
		
	});
	
	// Takes a product, creates item and adds it to Cart
	$scope.addToCart = function(product){
		
		
		var cart;
		
		try{
			cart = $cookies.getObject('myCart');
		}catch(e){
			
			alert(e);
		}
		
		
		if(cart == undefined){
			cart = {};  
			cart.totalPrice = 0.00;
		}
		if(cart.items == undefined){
			cart.items = [];
		}
		
		var itemInCart = false;
		
		// Just update the quantity if the item is already added to the cart
		for (i = 0 ; i < cart.items.length; i++) { 
		    if (cart.items[i].product.id == product.id) {
		    	cart.items[i].product = product;
		    	cart.items[i].quantity = cart.items[i].quantity + 1;
		    	cart.items[i].subtotal = cart.items[i].quantity * cart.items[i].product.finalPrice; 
		    	itemInCart = true;
		    	break;
		    }
			
		 }
		
		// Add the item to the cart if it is not added yet
		if (!itemInCart){
			var item = {};
			item.product = product;
			item.quantity = 1;
	    	item.subtotal = item.quantity * item.product.finalPrice;
			
			cart.items.push(item);	
		}
		
		
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() + 30);
		$cookies.putObject('myCart', cart);
		$scope.cart = cart;
		
		//Display the CheckOut and Continue Shopping Buttons
		document.getElementById("btnAddToCart").style.visibility = 'hidden';
		document.getElementById("btnCheckOut").style.visibility = 'visible';
		document.getElementById("btnContShopin").style.visibility = 'visible';
		document.getElementById("addedToCart").style.visibility = 'visible';

//		return cart; 

	}
	
	// THis is called when quantity is updated in the cart
	$scope.updateItemInCart = function(item){
		
		
		var cart;
		
		try{
			cart = $cookies.getObject('myCart');
		}catch(e){
			
		}
		
		// Dont do anything if the cart is somehow empty
		if(cart == undefined || cart.items == undefined){
			return;
		}
		
		for (i = 0 ; i < cart.items.length; i++) { 
		    if (cart.items[i].product.id == item.product.id) {
		    	cart.items[i] = item;
		    }
		 }
		
		$cookies.putObject('myCart', cart);
		$scope.cart = cart;
		
	}
	
	
	
	$scope.removeFromCart = function(item){
		
		
		var cart;
		
		try{
			cart = $cookies.getObject('myCart');
		}catch(e){
			
		
		}
		
		// Dont do anything if the cart is somehow empty
		if(cart == undefined || cart.items == undefined){
			return;
		}
		
		for (i = 0 ; i < cart.items.length; i++) { 
		    if (cart.items[i].product.id == item.product.id) {
		    	cart.items.splice(i,1);
		    	break;
		    }
			
		 }
		
		
		$cookies.putObject('myCart', cart);
		$scope.cart = cart;
		
		

	}
	
	
	
	
	$scope.checkout = function(){
		
		document.getElementById("header").style.visibility = 'hidden';
		document.getElementById("shopping-cart").style.visibility = 'visible';
	}	
	
	
	
	/*********************************** PRoduct page Functionlity **********************************************/
	
	

	
	$scope.signup = function(){
		
		
		var user = {
                email: $scope.login.email,
            	firstName: $scope.login.fname,
            	lastName: $scope.login.lname,
        		password: $scope.login.pass
		};
		
		
		$http({
			   url : "/rest/users/userreg",
			   method : "POST",
			   data : JSON.stringify(user),
			   headers : {
			        "Content-Type" : "application/json; charset=utf-8",
			        "Accept" : "application/json"
			   }
			}).success(function(response,status) {
		           if(response == 1){
		        	   document.getElementById("signup-error").innerHTML = 'Already Exits'
		        	   document.getElementById("signup-error").style.display = 'block';
		        	   
		           }else if(response == 0) {
		        	   //TODO Change the menu Login to logout
		         	   //TODO Store the email in cookies, so we can display account info when needed
		        	   //TODO update the cart with user's products
		        	   
		        	   document.getElementById("signup-error").innerHTML = ''
		        	   document.getElementById("signup-error").style.display = 'none';
		        	   location.href="#close";
		        	   
		           }else{
		        	   
		        	   document.getElementById("signup-error").innerHTML = 'System Error'
			           document.getElementById("signup-error").style.display = 'block';
			        	   
		           }
		           
		    }).error(function(data) {
		        //TODO replace alert with better message   
	        	   document.getElementById("signup-error").innerHTML = 'Already Exits1'
	        	   document.getElementById("signup-error").style.visibility = 'visible';

		    });

		
		
	}
	
	
	$scope.login = function(){
		
		var user = {
                email: $scope.login.email,
            	firstName: $scope.login.fname,
            	lastName: $scope.login.lname,
        		password: $scope.login.pass
		};
		
		
		$http({
			   url : "/rest/users/userlogin",
			   method : "POST",
			   data : JSON.stringify(user),
			   headers : {
			        "Content-Type" : "application/json; charset=utf-8",
			        "Accept" : "application/json"
			   }
			}).success(function(response,status) {
		           if(response == false){
		       
		        	   document.getElementById("login-error").style.display = 'block';
		        	   
		           }else{
		        	   //TODO Change the menu Login to logout
		         	   //TODO Store the email in cookies, so we can display account info when needed
		        	   //TODO update the cart with user's products
		        	   
		        	   //hide error message
		        	   document.getElementById("login-error").style.display = 'none';
		        	   document.getElementById("menulogin").innerHTML = 'My Account';
		        	   
		        	   // close the window	
		        	   location.href="#close";
		        	   
		           }
		           
		    }).error(function(data) {
		        //TODO replace alert with better message   
		    	document.getElementById("login-error").style.display = 'block';
		    });
		
		
	}
	
	
	
	$scope.setViewProduct= function(item){
		
		// Initialize view Product Window
		
		$scope.viewProduct = item;
		
		var imageSource = 'https://googledrive.com/host/0B2yfPSrBHXFFfkxxUEplYlVsYWVoMlVQMGJPNnJ1NFlKeXV5Zmg5QUdrR2pNU2RLQms0dmM/'+ item.id + '.jpg'; 
		
		document.getElementById("thumb").src = imageSource;
		document.getElementById("thumb").setAttribute('data-large-img-url',imageSource);
		
		var evt = new Event();
		$scope.m = new Magnifier(evt);
	
		
		$scope.m.attach({
		    thumb: '#thumb',
		    large: 'https://googledrive.com/host/0B2yfPSrBHXFFfkxxUEplYlVsYWVoMlVQMGJPNnJ1NFlKeXV5Zmg5QUdrR2pNU2RLQms0dmM/'+ item.id + '.jpg',
		    largeWrapper: 'preview',
		    zoom: 3
		});
		
		
		document.getElementById("thumb").className = '';
		
		if(document.getElementById("thumb-large") != null)
			document.getElementById("thumb-large").className = 'hidden';
		
		document.getElementById("thumb-lens").style.left='0px'
		document.getElementById("thumb-lens").style.top='0px'
				
		document.getElementById("btnCheckOut").style.visibility = 'hidden';
		document.getElementById("btnContShopin").style.visibility = 'hidden';
		document.getElementById("addedToCart").style.visibility = 'hidden';
		document.getElementById("btnAddToCart").style.visibility = 'visible';		
		
		
        
        
	}
	
	
}]);





