# online-shop-api
University project for creating an API for an online shop (amazon like)

-Steps needed to use our project:
     
   1) clone the git repository using the command , git clone https://github.com/ahmedWessamF/online-shop-api.git
   2) All libraries will be added automatically by using maven
   3) MySQL should be installed
   4) To use the API:
      4.1) Run our main Server Class
	  4.2) Requests can be send using localhost:8000/{request-name}, without curly brackets.
	  4.3) So far there are three types of requests.
	       4.3.1) register, you can register as admin, storeOwner, or customer using register/admin, register/storeowner, register/customer respictively.
		          provided info for registeration will be json objects in the request body, like this
				  {
	               "email" : "email@gmail.com",
	               "username" : "peter",
	               "password" : "1211134561"
                  }
		   
		   4.3.2) login, you can login with username and password as admin, storeOwner, or customer, using login/admin, login/storeowner or login/customer respictively,
		          username and password can be provided in the body of the request if you're using POSTMAN or any other similar tool, a response with a token will be send after,
				  this token will be used to authenticate whether a logged in user can execute a certain request or no, token should be provided in the header section of requests.
		   
		   4.3.3) list all the registered users(StoreOwners and Customers),to be able to request this service you should log in first,
		          then request localhost:8000/getAll, any type of users other than admin will not be authorized to get list of registered users.
	
		   
		   
