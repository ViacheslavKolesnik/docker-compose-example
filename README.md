# docker-compose example
Contains small docker-based environment to introduce the brilliance of docker-compose!
## Prerequisites
1. `docker` installed. `https://docs.docker.com/engine/install/ubuntu/`
2. `docker-compose` installed. `https://docs.docker.com/compose/install/`
## Quickstart
1. Clone repository.
2. Enter cloned folder via cmd(`cd docker-compose-example`).
3. Run `docker-compose up`.
4. Enjoy fun in logs.
5. Run `docker-compose down`(If you wish to interrupt fun).
## Under the hood
List of components:
- `sender`. Java application. Gets block info from ethereum(`https://ethereum.org/`) test network and sends it to `rabbitmq`.
- `receiver`. Java application. Listens for blocks from `rabbitmq`, inserts it in `postgres`(if not duplicate), increments counter in `redis`(if not duplicate).
- `rabbitmq`. Used for `sender`-`receiver` communication.
- `redis`. Used as block counter storage.
- `postgres`. Used as block storage.
## Data safe
Component volumes mounted in `volumes` folder in project root so your data kept safe when components aren't working.
## Customization
Components are getting configs from `env` folder.

For example `BLOCK_RATE` from `env/sender.env` controls how often to query block from ethereum(in milliseconds).
## Helpful commands
`docker-compose ps` - list running containers. Also shows containers 'health' based on `healthcheck` from `docker-compose.yml`

`docker-compose logs -f {container_name}` - replace `{container_name}` with the name of running container and watch logs from it.
## Other
Each Java application is built via multy-stage build. Build process described in `Dockerfile` in corresponding app root folder
