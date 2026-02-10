docker run -d --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=local -p 5432:5432 postgres:17.2

docker exec -it pg-docker /bin/bash

psql -U postgres -d local