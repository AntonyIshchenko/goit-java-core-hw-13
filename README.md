# App

Write the App that will interact with the API https://jsonplaceholder.typicode.com.
You can use Java's standard possibilities (`HttpURLConnection` class) or third-party solutions like **Apache Fluent API**, **Apache HTTPClient**, or **Jsoup**. 

## TASK 1

The App must contain methods for realizing the next functionality:
 - creating a new object in https://jsonplaceholder.typicode.com/users. The method works correctly if you have a response with 2xx status code.
 - updating an object in https://jsonplaceholder.typicode.com/users. The method works correctly if you have a response with 2xx status code.
 - deleting an object from https://jsonplaceholder.typicode.com/users.
 - getting info about all users https://jsonplaceholder.typicode.com/users
 - getting info about user by `id` https://jsonplaceholder.typicode.com/users/{id}
 - getting info about user by `username` https://jsonplaceholder.typicode.com/users?username={username}

## TASK 2

Add a new method that will print all comments until the selected user's last post and write them to file.
The file must be called `user-X-post-Y-comments.json`, where Х - user's `id`, Y - post number.

https://jsonplaceholder.typicode.com/users/1/posts The last post has the highest `id`.
https://jsonplaceholder.typicode.com/posts/10/comments

## TASK 3

Add a new method that will print all opened tasks for user with `id` = X. All tasks with `completed = false` are considered open.

https://jsonplaceholder.typicode.com/users/1/todos.