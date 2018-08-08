# Demo Cache Service
## Purpose

* The purpose of this demo service is to show-case how the caching-component library  can be added to it to enable   caching.

* It uses the **'caching-component'** library as available in, [Caching-Component](https://github.com/gsamartian/caching-component)

## Details

The Service exposes below end-points

## End-points for Fetching Entities by Id

```
GET /members/{id}
GET /users/{id}
```



## End-points for Invalidating Caches

```
DELETE /cache/users/{id}
DELETE /cache/users
```

## Note:

Ensure Redis Server is up and running before starting the service


## Application Configuration

The App can be configured for Caching as below,

## Cache Configuration

```
app:
  cache-config:
  - name: members
    expiry-seconds: 60
  - name: users
    expiry-time: "0 * * * * ?"   
  - name: plans
    expiry-time: "0 55 23 * * ?" 
```


  
    
* We can specify one or more caches as required by the service.
* For each cache, we can provide a **'name'**, either TTL of cache as **'expiry-seconds'** or as a absolute time using **'expiry-time'**
* If using **'expiry-time'**, the format to be followed is same as **Standard CRON format**.
* For More Details refer to, [Cron Expressions](https://www.baeldung.com/cron-expressions)
* If the **expiry-time** as provided by the CRON format is triggered then the cache associated by the **'app.cache-config.name'** would
be cleared/flushed.

If **expiry-seconds** is used, then after the elapse of duration of seconds the cache would be cleared.
    
    
## Redis Configuration

```
spring:
  cache:
    type: redis  
  redis:
    host: 127.0.0.1
    port: 638 
```
* We need to provide the type of cache as **'redis'** when using redis as the Cache Server.
* Also, we need to provide machine on which redis server is running as value for **'host'** and also the **'port'** config accordingly.

    