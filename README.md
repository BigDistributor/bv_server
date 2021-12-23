# Big Data Viewer Onetime Link 

## Goal:
Being able to load same data with the same view in another computer or embedded device using simple one time code.

#### Server used for https://github.com/BigDistributor/BDVShareable

Quarkus RESTFull application for one time smart code sharing with json information.

##How to Use:
`docker run -i --rm -p 8080:8080 quarkus/bv_server-jvm`

---
### REST APIs:
![APIs](img/img1.png)

---
### Workflow:
#### 1- Create
![APIs](img/1.png)

#### 2-Get
![APIs](img/2.png)


---


I used quarkus, Docker and Swagger-UI