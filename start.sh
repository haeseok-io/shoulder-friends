docker_username=""
db_image_name="app"
db_container_name="web"
db_password=""
version=""
port=80

echo "## Automation docker-database build and run ##"

# remove container
echo "=> Remove previous container..."
docker rm -f ${db_container_name}

# remove image
echo "=> Remove previous image..."
docker rmi -f ${db_image_name}

# new-build/re-build docker image
echo "=> Build new image..."
docker build --tag ${db_image_name} -f Dockerfile .

# Run container
echo "=> Run container..."
docker run -d -p ${port}:${port}  --name ${db_container_name} ${db_image_name}

# 도커 자동배포 테스트 주석