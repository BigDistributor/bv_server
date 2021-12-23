# Build
mvn package

# Create docker image
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/bv_server-jvm .

# Tag image
docker tag 5c6fe2c359fa mzouink/bv_server-jvm:latest

# Push to docker hub
docker push mzouink/bv_server-jvm

# ssh to ec2
ssh -i "ec2_bdv_server.pem" ec2-user@ec2-54-175-102-36.compute-1.amazonaws.com

# install docker
sudo yum install -y docker
sudo service docker start
docker info

# to fix permission denied
sudo groupadd docker
$ sudo usermod -aG docker $USER
newgrp docker

# run
docker pull mzouink/bv_server-jvm
docker run -d -p 80:8080 mzouink/bv_server-jvm