package com.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@SpringBootApplication
@RestController
@RequestMapping("/k8")
@Slf4j
public class SpringApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationTest.class);
    }
}
/*

Do not’ login to root User

1) Create a repository in Docker Hub - “springtest-helm”

- docker login (better to religion docker desktop)
docker build -t springtest-helm:latest .
docker tag springtest-helm mohapatrasubhakanta/springtest-helm:latest
docker push  mohapatrasubhakanta/springtest-helm:latest

Docker Desktop (enable Kubernetes in Docker settings → Kubernetes → “Enable Kubernetes” ✅)
2) Helm
brew install helm
3) kubectl
brew install kubectl
4)  set up Spring Boot app.
5) add helm files into Spring boot app
- cd /Users/subhakantamohapatra/Downloads/Spark/SparkProject/SpringKubernetesHelm
- helm create helm
You will see “helm” folder with all files

6) Modify “helm” files
A) values.yaml
Change repo
 - repository: mohapatrasubhakanta/springtest-helm
   pullPolicy: Always
   tag: latest # or any version like 1.0


service:
  # This sets the service type more information can be found here: https://kubernetes.io/docs/concepts/services-networking/service/#publishing-services-service-types
  type: LoadBalancer
  # This sets the ports more information can be found here: https://kubernetes.io/docs/concepts/services-networking/service/#field-spec-ports
  port: 8086

- change autoscaling based on needs in values.yaml


B) template/deploy.yaml

Change containerPort -

ports:
  - name: http
    containerPort: 8086
    protocol: TCP


C)templates/service.yaml

spec:
  type: {{ .Values.service.type }}
  ports:
    - port: {{ .Values.service.port }}
      targetPort: 8086
  selector:
    app.kubernetes.io/name: {{ .Chart.Name }}

7)Install helm chart:

- cd /Users/subhakantamohapatra/Downloads/Spark/SparkProject/SpringKubernetesHelm

helm install springtest-helm ./helm -n dev

helm install [RELEASE_NAME] [CHART] [FLAGS]
	•	RELEASE_NAME: This is a unique name that you assign to the specific installation of the chart. It's used to identify and manage this particular deployment within your Kubernetes cluster. 
Note - if name already exist then “helm uninstall springtest-helm” 
8)
kubectl port-forward svc/springtest-helm 8086:8086

 */