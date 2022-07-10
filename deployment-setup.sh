# component
kubectl create ns dev || true
sed -i -e "appVersion/c appVersion: \"${RELEASE_ARTIFACTS_BUILD_SOURCEBRANCHNAME}\"" helm/spec-helm-chart/Chart.yaml
helm upgrade -n dev  -i spec  helm/spec-helm-chart --values code/helm-values/dev.yml --wait


# db
kubectl create ns dev || true
helm repo add bitnami https://charts.bitnami.com/bitnami
helm upgrade -i  mongodb bitnami/mongodb  -n dev  --set auth.enabled=false --wait


#  --debug --dry-run