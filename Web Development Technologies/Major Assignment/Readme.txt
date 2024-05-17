Pass Part
2.6a This database was created using a code first approach. As our database was being used in two seperate projects, the models, viewmodels and the DB Context (McbaContext) was placed in a class library.
We used a both fluent API and code first to ensure a firm mapping to the relationships. 

2.6b We managed to do this part, however we have implemented the login using the identity API for the High Distinction Part. 

ATM Functionality 
For the ATM functionality it was called from the customer controller. The customer Controller had two methods to ATM which were Transaction and transfer. We had created extension classes called AccountExt and TransActionExtension
The TransActionExtension class provided a facade and was implemented using a factory pattern. It had methods that were deposit, billpay, Withdraw and transfer with required parameters and were called from the controller. The Transaction extension also checked for insufficient funds 
and negative amounts and throws an exception if they occur to bind to business rules.  The AccountExt class performed the getbalance and gettransactions after and getfreetransactions, mostly related to determining balance and amount of transactions. 
The controllers used the extension methods and passed the appropiate data to the views. 

My Statement Functionality
This was implemented using the CustomerController, returning a list of transactions using the McbaContext based on customer number. 
Credit Part
In the bill pay controller, it had methods to perform the CRUD operations relating to paying a bill. The logic for managing the bill paying data was in the baillpayextension class, where it adds, deletes updates and creates bill pays. 
The actual transactions are performed in the Billpaybackgroundservice and billpaybackground task. Here these classes run a background process to run through the scheduled billpays and interacts with the transactionextension class to perform the transactions on the customer's account, while
checking for insufficient funds. 
Distinction Part
For the Admin, functionality as per the spec, a seperate API project was created so that the Admin website interacts through this. The API project has two main classes, AdminController and AdminManager which are based on the repository pattern. The AdminManager was the main class that handled the
database logic with the MCDBA context to lock and get user accounts and transactions. The repository pattern was based on the AdminManager, managing the database logic and the AdminController, handling the requests and passing the data in the required formats for each client. The Admincontroller was the facade of the API, it handled the incoming Http requests and interacted to with the AdminManager to fetch the data and return the data as a JSOn as well as passing on requests to the Adminmanager
to lock accounts and get data. Inside our main project, the AdminController used the HttpClient to interact with our API. The data was then Deserialized using the NewtonSoft,json package when it came to the get methods. This controller
was restricted to users with Admin permission in the identity API. 