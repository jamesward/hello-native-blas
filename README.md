Hello Native BLAS
-----------------

Run Locally:
```
./gradlew -t run
```

Visit: [http://localhost:8080](http://localhost:8080)

Run Locally with Docker:
```
docker build -t hello-native-blas-libs .
./gradlew jibDockerBuild -Djib.from.image=docker://hello-native-blas-libs -Djib.to.image=hello-native-blas
docker run --rm -p 8080:8080 hello-native-blas
```

Run on Cloud Run:
```
export PROJECT_ID=YOUR_PROJECT_ID
docker build -t gcr.io/$PROJECT_ID/hello-native-blas-libs .
docker push gcr.io/$PROJECT_ID/hello-native-blas-libs
./gradlew jib -Djib.from.image=gcr.io/$PROJECT_ID/hello-native-blas-libs -Djib.to.image=gcr.io/$PROJECT_ID/hello-native-blas -Djib.to.auth.credHelper=gcr
gcloud beta run deploy --image gcr.io/$PROJECT_ID/hello-native-blas --project $PROJECT_ID --platform managed --region us-central1 --allow-unauthenticated hello-native-blas
```
