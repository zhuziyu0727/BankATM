**<p align=center><sub>Team 11: Dezhou Wang(U46937632), Ziyu Zhu(U43977497), Kaiyuan Fan(U61521997) </sub></p>**

**Class Design**

The main functional classes will discuss below.

**An explanation of your choice of starting design and reasons (benefits) for choosing that design**

We compared each member’s design and decide to stick with the current design because of the most modularity and flexibility. For this design, all the GUI are made up of panels, separate panels are used for different functions. Bank user interfaces are changing by switching the panels, besides that, different button events have separate classes. Customer personalization and login features are best implemented in the current design. 
For bank accounts, our starting design has the loosest coupling, each account associated with only one currency, and a customer can own multiple checking and saving accounts. The design is best to start with implementing the external security account and stock features.

**Evaluation of your decision on your starting design. Did it work out as planned? Why or why not?**

Yes, it worked out as planned. For stock trading, we added a security type of account to handle the events. This type of account is associated with a saving account under the customer. A customer can have multiple security accounts if he or she meets the requirements. The security account was inherited from the original base BankAccount class while it has new fields of binded saving account number, the transaction of stocks and the stocks that security account holds.


### Specification

> #### Customer
>
> - Each customer can have multiple accounts.
> - Each account only supports one currency.
> - For each customer, accounts can have the same type and same currency.
> - If one saving account has more than X money, this customer can have one choice to open a security trading account binding to this saving account.
>   - security trading account only store how many stocks this customer has bought.
>   - The money to buy or the benefit of selling both go into the binding savings account.
>   - When the customer has chosen to sell the saving account, the security trading account automatically sell all its stocks and close.
>   - When a customer buys or sells stocks, for each time, the bank will take some fee.

> #### Information
>
> - The information of a member can consist of five parts: username, password, email, address, and phone number. Each individual including manager and customers should contain this information.
> - Username is the name that represents a customer’s account name.
> - Password should be set up as a certain formula and would be checked when login in.
> - Email should be in a certain formula.
> - Address should be in a certain formula and can be accessed easily.
> - Phone number is a certain kind of numbers and PhoneNumberUS extends it as US number.

> #### Manager
>
> - There is only one manager.
> - The manager is able to change all stocks' buy price and sell price. Maybe mimic a real stock price change. Like, when the stocks remaining is low, the price will become higher. Otherwise, the price will become low. Assume the sale price is always lower or equal to buy price.
> - The manager is able to see one customer's profile.
> - Is able to generate a table of all transaction history by selecting a range of dates.
> - Is able to see a list of all customers with the loan.
> - Is able to set the interest rate for savings and loans.
> - Is able to set the fee charge for CHECKING, LOAN open and close.
> - Is able to set the fee charge for CHECKING deposit, withdraw, and transfer.

> #### Stocks
>
> - Each stock has a unique ID, buy price, and sell price.
> - Assume that each stock has a total limited amount.
> - Each buy and sell, the bank will charge some fee.
> - Currently, the bank atm does not allow customers to trade with each other. The buy and sell of stocks all go into the bank market.
> - Automatically, the fluctuation of the buy price and the sale price of the stock are presented in two ways. 
> - Firstly, the initial price of a stock when it is created is relative to the number of shares of it. The initial price would be higher when the stock is created with fewer shares. 
> - Secondly, the buy price would decrease when a customer sells this stock and the fluctuation degree is relative to the number of shares the customer sells and the selling price change oppositely.
> #### Stock Market
>
> - the Stock market can show the information of each stock to customers.
> - To the manager, the stock market provides the interface to create, delete and modify a stock.
> - When creating a stock, the stock ID and the stock name should be unique.
> - When deleting a stock, no customer can own shares of this stock or the operation would be stopped.
> - When modifying a stock, the manager can only modify the price and the number of the stock in the market.

> #### Money
>
> - Money has both amount and currency.
> - When taking a currency type as an argument, it will automatically transfer current money into another money with the given currency.
> - The manager can set up the currency exchange rate.
> - Money is able to compare with each other.
> - Try to implement add and subtract method, keep original currency.

> #### Account
>
> - There are only 3 different accounts. These are CHECKING, SAVING, and LOAN.
> - After a customer pays all of the LOAN, the LOAN account will be automatically closed.
> - Each account has one single currency type.
> - For CHECKING and SAVING accounts, the bank charges for opening and closing.
> - For CHECKING, the bank charges for a deposit,  withdraw, and transfer.
> - For SAVING, it does not support transfer to other accounts, but can accept the transfer.
> - For LOAN, a customer can pay some loans or lend more loans.
> - For SECURITY, a customer can use to do stock trading.

> #### Security Trading Account
>
> - Will store how many stocks the customer has bought.
> - One security trading account is bound to one single saving account.
> - When the original amount of saving account is bigger or equal to X money, the customer is able to choose whether to open a security trading account under it.

> #### Bank ATM
>
> - Customer and Manager login on the same page.
> - Customer is able to find the password by username and email.
> - Customer is able to find username by email and phone number.
> - The manager is set already, and it does not allow changing a manager.
> - For register, make more options, only take non-empty fields. Something must be occupied: username, password, and email.

> #### Database management
>
> - The interface DataOperation is used to write and read the information on stocks, account, customer, and transactions between memory and database.
> - The interface SecurityDataOperation is used to write and read the information on security account, security account transactions between memory and database.
