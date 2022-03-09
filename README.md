# JenkinsTest

아래의 포맷 형태로 git repository의 `/deploy.yaml` 경로에 아래 내용을 정리하여 작성

```
deploy:
  build:
    dockerfile: Dockerfile
    path: ./
  k8s:
    namespace: test
    yaml: jenkins/k8s-deploy.yaml

registry:
  url: 
  project: 
  service: 

notification:
  mettermost:
    url: 
    channel: 
    usrname: 
    icon: 
```
