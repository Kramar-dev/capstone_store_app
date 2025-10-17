## Capstone project: Store Application

Requirements:

1. Register new user. Example request: {“email":"my@email.com", “password":"123"}
   Respond with an appropriate HTTP codes (200 for ok, 409 for existing user)
   Your app must not store password as plain text, use some good approach to identify user.

2. Login into system. Example request: {“email":"my@email.com", “password":"123"}
   Respond with JSON containing sessionId. \
   *(optional) Think about preventing an intruder from bruteforcing.

3. *(optional) Reset password.

4. Get all products in store.
   Respond with JSON list of items you have, e.g.:
   {“id":"2411", “title":"Nail gun", “available":8, “price": “23.95"}

5. Add item to cart. Example request: {“id":"363", “quantity":"2"}
   Allow adding only one position at a time. If you don’t have this quantity in store - respond with an error. The
   information has to be session-scoped: once session expires - user will get new empty cart.

6. Display your cart content.
   Respond with list of product names with their quantities added. Calculate subtotal. Assign an ordinal to each cart
   item.

7. Remove an item from user’s cart.

8. Modify cart item. Example request: {“id":2, quantity: 3} - user should be able to modify number of some items in
   his cart.

9. Checkout: verify your prices in cart, ensure you still have desired amount of goods. If all is good - send a user
   confirmation about successful order.

10. *(optional) Cancel order: return all products from order back to available status.

11. *(optional) Get user’s order list. Should contain order id, date, total, status.

---

### API description

![()](https://img.shields.io/static/v1?label=&message=POST&color=30c030) ```/v1/user/register``` **Register new user**

#### request x-www-form-urlencoded data

```
"email": "email@email.com"
"password": "12345678"
```

#### response **<font color='30c030'>201</font>** Created

```json
{
  "id": "ad4332a3-98f5-4557-93fb-ec92ae915626"
}
```

#### response **<font color='f93e3e'>400</font>**  Bad Request

---
![()](https://img.shields.io/static/v1?label=&message=POST&color=30c030) ```/v1/user/login``` **Login**

#### request x-www-form-urlencoded data

```
"email": "email@email.com"
"password": "12345678"
```

#### response **<font color='30c030'>200</font>**  OK

```json
{
  "token": "token",
  "expiration": "datetime"
}
```

#### response **<font color='f93e3e'>400</font>**  Bad Request

#### response **<font color='f93e3e'>401</font>**  Unauthorized

---
![()](https://img.shields.io/static/v1?label=&message=POST&color=30c030) ```/v1/user/reset-password``` **Reset password**

#### response **<font color='f93e3e'>501</font>**  Not Implemented

---
![()](https://img.shields.io/static/v1?label=&message=GET&color=0c90ff) ```/v1/products``` **Get all products**

#### response **<font color='30c030'>200</font>**  OK

```json
{
  "products": [
    {
      "id": "31079160-01b5-49e5-9feb-dfc038985f85",
      "title": "Random Product 1",
      "description": "This is a description for random product 1",
      "price": 30.83,
      "quantity": 48
    },
    {
      "id": "33fed41e-b48f-4a52-9a82-7ef295c0cfb2",
      "title": "Random Product 2",
      "description": "This is a description for random product 2",
      "price": 87.8,
      "quantity": 34
    }
  ]
}
```

---
![()](https://img.shields.io/static/v1?label=&message=POST&color=30c030) ```/v1/cart/items``` **Add item to cart**

#### response **<font color='30c030'>201</font>** Accepted

#### response **<font color='f93e3e'>400</font>**  Bad Request

---
![()](https://img.shields.io/static/v1?label=&message=GET&color=0c90ff) ```/v1/cart/items``` **View cart content**

#### response **<font color='30c030'>200</font>** OK

```json
{
  "items": [
    {
      "id": "6ab804fc-7bb8-4e26-8f2a-c28444f08b8e",
      "cartId": "dc0d0301-d67e-4d27-9022-350337f8f437",
      "productId": "1b2ea277-0eca-4163-824a-64fddc76a80b",
      "quantity": 2
    }
  ]
}
```

---
![()](https://img.shields.io/static/v1?label=&message=DELETE&color=f93e3e) ```/v1/cart/items/{itemId}``` **Remove item from cart**

#### response **<font color='30c030'>200</font>** OK
#### response **<font color='f93e3e'>400</font>**  Bad Request

---
![()](https://img.shields.io/static/v1?label=&message=PUT&color=fca130) ```/v1/cart/items/{itemId}``` **Modify cart item**

#### request body

```json
{
  "productId": "363",
  "quantity": 7
}
```

#### response **<font color='30c030'>201</font>** Accepted
#### response **<font color='f93e3e'>400</font>**  Bad Request

---
![()](https://img.shields.io/static/v1?label=&message=POST&color=30c030) ```/v1/orders``` **Checkout (create order)**

#### response **<font color='30c030'>200</font>** OK

---
![()](https://img.shields.io/static/v1?label=&message=DELETE&color=f93e3e) ```/v1/orders/{orderid}/cancel``` **Cancel order**

#### response **<font color='30c030'>200</font>** OK

---
![()](https://img.shields.io/static/v1?label=&message=GET&color=0c90ff) ```/v1/orders``` **Get user's order list**

#### response **<font color='f93e3e'>501</font>** Not Implemented