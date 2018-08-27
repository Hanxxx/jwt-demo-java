# API

## Get article list

```
GET localhost:8081/forum/article/list
```

Return
```Json
{
    "returnCode": 0,
    "msg": "success",
    "data": [
        {
            "articleId": 1,
            "authorId": "lh0815",
            "title": "test1"
        },
        {
            "articleId": 2,
            "authorId": "lh0815",
            "title": "test2"
        }
    ]
}
```


## Get detail of an aritcle
```
GET localhost:8081/forum/article/detail?articleId=1
Header: Authorization: ${TOKEN}
```
Authenticated User Return
```Json
{
    "returnCode": 0,
    "msg": "success",
    "data": {
        "articleId": 1,
        "authorId": "lh0815",
        "title": "test1",
        "content": "test content"
    }
}
```

### Post an article 
``` 
POST localhost:8081/forum/article/create?authorId=lh0815
Header: Authorization: ${TOKEN}
        Content-Type: application/json
        
Body:   
        {
        	"title": "POST",
        	"content": "POST"
        }
        
```

Return
```Json
{
    "returnCode": 0,
    "msg": "success",
    "data": null
}
```

### Edit an aritcle

```
PUT localhost:8081/forum/article/edit?articleId=1?authorId=lh0815
Header: Content-Type: application/json
        Authorization: ${TOKEN}
   
Body: {
      	"authorId": "lh0815",
      	"title": "Edit",
      	"content": "edit"
      }     
```

Return
```Json
{
    "returnCode": 0,
    "msg": "success",
    "data": null
}
```

##Sign Up

```
POST localhost:8081/users/auth/signup

Header: Content-Type:application/json

Body: {
      	"username": "lh0815",
      	"password": "pass"
      }
```


Return 
```Json
{
    "returnCode": 0,
    "msg": "success",
    "data": null
}
```


##Log in
```
POST localhost:8081/users/auth/login

Header: Content-Type:application/json

Body: {
      	"username": "lh0815",
      	"password": "pass"
      }
```

Return
```
Header: Authorization: ${TOKEN}

Body: {
          "returnCode": 0,
          "msg": "success",
          "data": null
      }

```