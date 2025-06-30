I have setup nginx in spring boot application.
1: Create a simple application, which is present in master branch.
2: Downloaded nginx in my local machine.
3: Create 2 jar files for parallel execution such as (employee-instance-2, employee-instance-1).
4: Executed both jar files on different ports such 9001 for employee-instance-1 and 9002 for employee-instance-2.
5: Made changes in nginx file such as nginx.config in config folder which I will share below.
6: Started the nginx server.
7: Setup postman to execute 1000's of api at a time for save call only. (Collection name -> Run --> select Save call in Functional option and in Performance option 80 virtual users and 2 min time duration).
8: Finally checked database and in result seen that there are records which are coming from both 9001 and 9002 ports.

Content for nginx(nginx.config)

worker_processes  1;

error_log  logs/error.log;
pid        logs/nginx.pid;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    
    sendfile        on;
    keepalive_timeout  65;

    upstream backend {
        server localhost:9001;
        server localhost:9002;
    }

    server {
        listen       80;
        server_name  localhost;

        location / {
            proxy_pass http://backend;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }

        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
}
