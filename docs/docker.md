# Docker & Distribution Guide

The `deploy/` bundle lets you run COMPACT without building the full toolchain. It includes both a Docker Compose recipe and a plain Dockerfile for platforms that only accept container images.

## Layout of `deploy/`
| Path | Description |
| --- | --- |
| `cop-app.war` | Downloaded from the Releases page; drop the latest WAR here so Tomcat serves it as ROOT. |
| `application.properties` | Production defaults (H2-on-disk, port 8888, DTED path `/root/dted/`). |
| `dockercompose/` | Compose stack (Tomcat + bind mounts for data/config). |
| `docker/` | Standalone Dockerfile for building `compact` images. |

## Shared Prerequisites
- Docker Engine (or Docker Desktop) with Compose v2 support
- 2+ CPU cores, 4 GB RAM
- Local folders for DTED tiles and H2 data

## Option A: Docker Compose (`deploy/dockercompose`)
1. Download the latest `cop-app.war` release asset and save it as `deploy/cop-app.war`.
2. Copy DTED tiles into `deploy/dockercompose/dted/` (create the folder if it is missing).
3. (Optional) tweak `deploy/application.properties` for your environment.
4. Launch:
    ```powershell
    cd deploy/dockercompose
    mkdir data,dted -ErrorAction SilentlyContinue
    docker compose up -d
    ```
    - The compose file mounts `../cop-app.war` (the release asset you downloaded) as `/usr/local/tomcat/webapps/ROOT.war`.
    - `../application.properties` is mounted at `/usr/local/tomcat/config/application.properties` and injected via `SPRING_CONFIG_LOCATION`.
    - Local `data/` and `dted/` folders map to `/root/h2` and `/root/dted` for persistence.
5. Open http://localhost:50090.

To stop the stack run `docker compose down`. Remove `data/` or `dted/` if you need a clean slate.

## Option B: Dockerfile (`deploy/docker`)
Some PaaS providers only accept a Dockerfile. Build the same Tomcat image directly:

```powershell
# from the repository root
docker build -f deploy/docker/Dockerfile -t compact:latest deploy
```

This image embeds the release-provided `cop-app.war` plus `application.properties`, sets `SPRING_CONFIG_LOCATION=file:/usr/local/tomcat/config/application.properties`, and exposes port 8080. Run it locally with volume mounts for persistence:

```powershell
docker run \
  -p 50090:8080 \
  -v ${PWD}/deploy/docker/data:/root/h2 \
  -v ${PWD}/deploy/docker/dted:/root/dted \
  compact:latest
```

(Feel free to replace the host paths with your own storage locations or Docker volumes.)

## Refreshing Artifacts
1. For official deployments, download the newest `cop-app.war` asset from the Releases page and overwrite `deploy/cop-app.war`.
2. During development you can still run `mvn -f src/pom.xml clean install` and copy `cop-app/target/cop-app.war` over `deploy/cop-app.war` for local testing.
3. Restart either the Compose stack (`docker compose up -d --force-recreate`) or the container built from the Dockerfile (`docker build … && docker run …`) after swapping the WAR, and update `deploy/application.properties` whenever configuration changes.

See `README.md` for broader environment setup and `AGENTS.md` for contributor workflows.
