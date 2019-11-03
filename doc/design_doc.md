### Specification

> #### Customer
>
> - Each customer can have multiple accounts.
> - Each account only support one currency.
> - For each customer, accounts can have same type and same currency.
> - If one saving account has more than X money, this customer can has one choice to open a security trading account binding to this saving account.
>   - security trading account only store how many stocks this customer has bought.
>   - The money for buy or the benefit of selling both go into the binding saving account.
>   - When the customer has chosen to sell the saving account, the security trading account automatically sell all its stocks and close.
>   - When a customer buy or sell stocks, for each time, the bank will take some fee.
>
> #### Manager
>
> - There is only one manager.
> - The manager is able to change all stocks' buy price and sell price. Maybe mimic a real stock price change. Like, when the stocks remaining is low, the price will become higher. Otherwise, the price will become low. Assume the sell price is always lower or equal to buy price.
> - Manager is able to see one customer's profile.
> - Is able to generate table of all transaction history by selecting range of date.
> - Is able to see list of all customers with loan.
> - Is able to set the interest rate for savings and loans.
> - Is able to set the fee charge for CHECKING, LOAN open and close.
> - Is able to set the fee charge for CHECKING deposit, withdraw, and transfer.
>
> #### Stocks
>
> - Each stock has a unique ID, buy price, and sell price.
> - Assume that each stock has total limited amount.
> - Each buy and sell, bank will charge some fee.
> - Currently, the bank atm does not allow customers to trade with each other. The buy and sell of stocks all goes into the bank market.
>
> #### Money
>
> - Money has both amount and currency.
> - When take a currency type as an argument, it will automatically transfer current money into another money with the given currency.
> - Manager can set up the currency exchange rate.
> - Money is able to compare with each other.
> - Try to implement add and subtract method, keep original currency.
>
> #### Account
>
> - There are only 3 different accounts. These are CHECKING, SAVING, and LOAN.
> - After a customer pays all of LOAN, the LOAN account will be automatically closed.
> - Each account has one single currency type.
> - For CHECKING and SAVING accounts, the bank charges for opening and close.
> - For CHECKING, the bank charges for deposit,  withdraw, and transfer.
> - For SAVING, it does not support transfer to other accounts, but can accept transfer.
> - For LOAN, a customer can pay some loan or lend more loan.
>
> #### Security Trading Account
>
> - Will store how many stocks the customer has bought.
> - One security trading account is bound to one single saving account.
> - When the original amount of saving account is bigger or equal to X money, the customer is able to choose whether to open a security trading account under it.
>
> #### Bank ATM
>
> - Customer and Manager login in the same page.
> - Customer is able to find password by username and email.
> - Customer is able to find username by email and phone number.
> - Manager is set already, and it does not allow changing a manager.
> - For register, make more options, only take non-empty fields. Something must be occupied: username, password, and email.