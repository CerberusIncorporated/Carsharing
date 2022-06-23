<h1>Carsharing</h1>
<hr>
<h4>The customer selects a car from the list of available ones.
Fills out the order form, specifying passport details, rental period.
The customer pays for the order.<br>
The administrator registers the return of the car. In case of damage to the car, the administrator enters the information and issues an invoice for repairs.
The administrator can reject the application by specifying the reasons for refusal.</h4>

| Function                | Guest | User | Admin |
|-------------------------|-------|------|-------|
| Change language         | +     | +    | +     |
| See news                | +     | +    | +     |
| See cars                | +     | +    | +     |
| See comments            | +     | +    | +     |
| Login/Register          | +     | -    | -     |
| Logout                  | -     | +    | +     |
| Order car               | -     | +    | +     |
| See orders              | -     | +    | +     |
| See orders all users    | -     | -    | +     |
| Add/Edit news           | -     | -    | +     |
| Add/Edit cars           | -     | -    | +     |
| Approve/Decline payment | -     | -    | +     |


## Demo
### News page
![news_guest](screenshots/news_guest.JPG)
### Register
![news_admin](screenshots/register.JPG)
### Login
![login](screenshots/login.JPG)
### Car
Guest/User
![car_guest](screenshots/cars_guest.JPG)
Admin
![car_admin](screenshots/cars_admin.JPG)
### Order
![order](screenshots/order.JPG)
### Orders list
User
![order_user](screenshots/order_user.JPG)
Admin
![orders_admin](screenshots/orders_admin.JPG)
### Payment
![payment](screenshots/payment.JPG)
## Database diagram
![database](screenshots/db.JPG)


Maksim Yukhnevich, 2022
