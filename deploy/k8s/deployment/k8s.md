# Kubernets

## Deployment

```bash
kubectl create deployment mysql8 --image=mysql:8 --port=3306 --dry-run=client -o yaml > deployment-mysql.yaml
```

```
$ kubectl describe deployment mysql8
Name:                   mysql8
Namespace:              default
CreationTimestamp:      Sat, 19 Apr 2025 19:41:11 -0500
Labels:                 app=mysql8
Annotations:            deployment.kubernetes.io/revision: 1
Selector:               app=mysql8
Replicas:               1 desired | 1 updated | 1 total | 1 available | 0 unavailable
StrategyType:           RollingUpdate
MinReadySeconds:        0
RollingUpdateStrategy:  25% max unavailable, 25% max surge
Pod Template:
  Labels:  app=mysql8
  Containers:
   mysql:
    Image:      mysql:8
    Port:       3306/TCP
    Host Port:  0/TCP
    Environment:
      MYSQL_ROOT_PASSWORD:  demo
      MYSQL_DATABASE:       demo
    Mounts:                 <none>
  Volumes:                  <none>
  Node-Selectors:           <none>
  Tolerations:              <none>
Conditions:
  Type           Status  Reason
  ----           ------  ------
  Available      True    MinimumReplicasAvailable
  Progressing    True    NewReplicaSetAvailable
OldReplicaSets:  <none>
NewReplicaSet:   mysql8-599c49856 (1/1 replicas created)
Events:
  Type    Reason             Age   From                   Message
  ----    ------             ----  ----                   -------
  Normal  ScalingReplicaSet  47s   deployment-controller  Scaled up replica set mysql8-599c49856 from 0 to 1

```

```
$ kubectl describe pods mysql8-599c49856-wv766
Name:             mysql8-599c49856-wv766
Namespace:        default
Priority:         0
Service Account:  default
Node:             minikube/192.168.135.96
Start Time:       Sat, 19 Apr 2025 19:41:11 -0500
Labels:           app=mysql8
                  pod-template-hash=599c49856
Annotations:      <none>
Status:           Running
IP:               10.244.0.4
IPs:
  IP:           10.244.0.4
Controlled By:  ReplicaSet/mysql8-599c49856
Containers:
  mysql:
    Container ID:   docker://f2905ebcba43a58f86d4e0a10f2fa3525a0de62b4999066cc9c09e141e88227a
    Image:          mysql:8
    Image ID:       docker-pullable://mysql@sha256:3a89257a3a979e46e3650628bea227a6bc318a2bdc95f954489ec89573a4c6a2
    Port:           3306/TCP
    Host Port:      0/TCP
    State:          Running
      Started:      Sat, 19 Apr 2025 19:41:12 -0500
    Ready:          True
    Restart Count:  0
    Environment:
      MYSQL_ROOT_PASSWORD:  demo
      MYSQL_DATABASE:       demo
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-ptbdj (ro)
Conditions:
  Type                        Status
  PodReadyToStartContainers   True
  Initialized                 True
  Ready                       True
  ContainersReady             True
  PodScheduled                True
Volumes:
  kube-api-access-ptbdj:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    ConfigMapOptional:       <nil>
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type    Reason     Age   From               Message
  ----    ------     ----  ----               -------
  Normal  Scheduled  113s  default-scheduler  Successfully assigned default/mysql8-599c49856-wv766 to minikube
  Normal  Pulled     112s  kubelet            Container image "mysql:8" already present on machine
  Normal  Created    112s  kubelet            Created container: mysql
  Normal  Started    112s  kubelet            Started container mysql

```

## expose

```bsh
kubectl expose deployment mysql8 --port=3306 --type=ClusterIp
```

```
$ kubectl get services
NAME         TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)    AGE
kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP    28m
mysql8       ClusterIP   10.96.8.39   <none>        3306/TCP   5s

```

```
$ kubectl describe services mysql8
Name:                     mysql8
Namespace:                default
Labels:                   app=mysql8
Annotations:              <none>
Selector:                 app=mysql8
Type:                     ClusterIP
IP Family Policy:         SingleStack
IP Families:              IPv4
IP:                       10.96.8.39
IPs:                      10.96.8.39
Port:                     <unset>  3306/TCP
TargetPort:               3306/TCP
Endpoints:                10.244.0.4:3306
Session Affinity:         None
Internal Traffic Policy:  Cluster
Events:                   <none>

```
```
$ kubectl get all
NAME                         READY   STATUS    RESTARTS   AGE
pod/mysql8-599c49856-wv766   1/1     Running   0          13m

NAME                 TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)    AGE
service/kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP    31m
service/mysql8       ClusterIP   10.96.8.39   <none>        3306/TCP   3m1s

NAME                     READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/mysql8   1/1     1            1           13m

NAME                               DESIRED   CURRENT   READY   AGE
replicaset.apps/mysql8-599c49856   1         1         1       13m

```
