# springboot-user-demo
Demo webApp for userdemo.
Deployed to heroku.
link: https://user-demo-pnet.herokuapp.com

endpoints:
1. [GET] /api/user/all : get all users
2. [GET] /api/user/username : get specific user
3. [POST] /api/user/create : create a user
4. [PUT] /api/user/username : update user
5. [DELETE] /api/user/username : delete user

create user JSON format:
  {
    "username" : "",
    "password" : "",
    "name" : ""
  }

admin:

username : admin

password : admin
  
database : H2 Database (in-memory)
